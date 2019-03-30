package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.*;

public class ArmMotor extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	final double START_POSITION = 0; // [\]
	final double PLACE_HATCH_POSITION = 361; // [|]
	final double SHOOT_BALL_POSITION = 853.06; // [/]
	final double INTAKE_BALL_POSITION = 1479.1;// [_]
	private  boolean isPIDRunning = false;


	private boolean isMaxSpeed = false;
	private final double MAX_SPEED = 1;
	private final double DEFAULT_SPEED = 0.4;


	public void moveToStartPosition() {
		isPIDRunning = true;
		RobotMap.armTalon.set(ControlMode.Position, START_POSITION);
		Robot.logger.putNumber("Arm Target Position", START_POSITION);
	}

	
	public void moveToPlaceHatch() {
		Robot.logger.putMessage("moving arm to place hatch");
		RobotMap.armTalon.configPeakOutputForward(0.4, 30);
		RobotMap.armTalon.configPeakOutputReverse(-0.4, 30);
		RobotMap.armTalon.set(ControlMode.Position, PLACE_HATCH_POSITION);
		isPIDRunning = true;
		
		Robot.logger.putNumber("Arm Target Position", PLACE_HATCH_POSITION);
	}

	public void moveToShootBall() {
		isPIDRunning = true;
		RobotMap.armTalon.set(ControlMode.Position, SHOOT_BALL_POSITION);
		Robot.logger.putNumber("Arm Target Position", SHOOT_BALL_POSITION);
	}

	public void moveToIntakeBall() {
		isPIDRunning = true;
		RobotMap.armTalon.set(ControlMode.Position, INTAKE_BALL_POSITION);
		Robot.logger.putNumber("Arm Target Position", INTAKE_BALL_POSITION);
	}

	public boolean isOnTarget() {
		//boolean isOnTarget = RobotMap.armTalon.getClosedLoopError() < 3;
		Robot.logger.putNumber("Arm Sensor Position", RobotMap.armTalon.getSelectedSensorPosition());
		boolean isOnTarget = RobotMap.armTalon.getSelectedSensorPosition() > 520;
		if (isOnTarget == true) {
			Robot.logger.putMessage("Arm isOnTarget");
			isPIDRunning = false;
		}
		return isOnTarget;
	}

	public boolean isPIDRunning() {
		//Robot.logger.putBoolean("isPIDRunning", isPIDRunning);
		return isPIDRunning;
		//return false;
	}

	public void moveOutOfRobot() {

		Robot.logger.putMessage("Moving arm out of robot");
		double speed = DEFAULT_SPEED;
		if (isMaxSpeed) {
			speed = MAX_SPEED;
		}
		isPIDRunning = false;	// disable PID if running
		RobotMap.armTalon.set(ControlMode.PercentOutput, -speed*Robot.oi.copilotController.getRawAxis(5));
	}
	// public void moveOutOfRobotBackup() {

	// 	Robot.logger.putMessage("Moving arm out of robot");
	// 	double speed = DEFAULT_SPEED;
	// 	if (isMaxSpeed) {
	// 		speed = MAX_SPEED;
	// 	}
	// 	//isPIDRunning = false;	// disable PID if running
	// 	RobotMap.armTalon.set(ControlMode.PercentOutput, -speed*Robot.oi.copilotController.getRawAxis(1));
	// }

	public void moveIntoRobot() {

		Robot.logger.putMessage("Moving arm into robot");
		double speed = DEFAULT_SPEED;
		if (isMaxSpeed) {
			speed = MAX_SPEED;
		}
		isPIDRunning = false; 	// disable PID if running
		RobotMap.armTalon.set(ControlMode.PercentOutput, -speed*Robot.oi.copilotController.getRawAxis(5));
	}
	// public void moveIntoRobotBackup() {

	// 	Robot.logger.putString("RunDownward", "yes");
	// 	double speed = DEFAULT_SPEED;
	// 	if (isMaxSpeed) {
	// 		speed = MAX_SPEED;
	// 	}
	// 	//isPIDRunning = false; 	// disable PID if running
	// 	RobotMap.armTalon.set(ControlMode.PercentOutput, -speed*Robot.oi.copilotController.getRawAxis(1));
	// }

	public void stop() {
		isPIDRunning = false;
		RobotMap.armTalon.set(ControlMode.PercentOutput, 0);
	}

	public void setIsMaxSpeed(boolean value) {
		isMaxSpeed = value;
	}
	public boolean getIsMaxSpeed() {
		return isMaxSpeed;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}