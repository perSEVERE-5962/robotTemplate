package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.*;

public class ArmMotor extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	final double START_POSITION = 0; // [\]
	final double PLACE_HATCH_POSITION = 682.6; // [|]
	final double SHOOT_BALL_POSITION = 853.06; // [/]
	final double INTAKE_BALL_POSITION = 1479.1;// [_]
	private boolean isPIDRunning = false;

	public void moveToStartPosition() {
		isPIDRunning = true;
		RobotMap.armTalon.set(ControlMode.Position, START_POSITION);
	}

	public void moveToPlaceHatch() {
		isPIDRunning = true;
		RobotMap.armTalon.set(ControlMode.Position, PLACE_HATCH_POSITION);
	}

	public void moveToShootBall() {
		isPIDRunning = true;
		RobotMap.armTalon.set(ControlMode.Position, SHOOT_BALL_POSITION);
	}

	public void moveToIntakeBall() {
		isPIDRunning = true;
		RobotMap.armTalon.set(ControlMode.Position, INTAKE_BALL_POSITION);
	}

	public boolean isOnTarget() {
		boolean isOnTarget = RobotMap.armTalon.getClosedLoopError(0) < 10;
		if (isOnTarget == true) {
			isPIDRunning = false;
		}
		return isOnTarget;
	}

	public boolean isPIDRunning() {
		return isPIDRunning;
	}

	public void runUpward() {
		isPIDRunning = false;	// disable PID if running
		RobotMap.armTalon.set(ControlMode.PercentOutput, Robot.oi.xBoxController.getRawAxis(5));
	}

	public void runDownward() {
		isPIDRunning = false; 	// disable PID if running
		RobotMap.armTalon.set(ControlMode.PercentOutput, Robot.oi.xBoxController.getRawAxis(5));
	}

	public void stop() {
		isPIDRunning = false;
		RobotMap.armTalon.set(ControlMode.PercentOutput, 0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}