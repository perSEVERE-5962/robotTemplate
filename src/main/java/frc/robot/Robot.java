
package frc.robot;

import java.util.concurrent.TimeUnit;
import com.analog.adis16470.frc.ADIS16470_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import java.io.IOException;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.subsystems.Drive;
import frc.robot.commands.RunAutonomous;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.utils.ArmPID;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.utils.Logger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * 
 * project.
 */
public class Robot extends TimedRobot {
	public static OI oi;

	// subsystems
	public static Drive drive = new Drive();
	public static SolenoidSubsystem solenoidSubsystem;
	public static ArmMotor armMotor;
	private Compressor compressor = new Compressor(0);

	// Sensors
	public static RobotGyro gyro = new RobotGyro();
	public static UltrasonicAnalog ultrasonicanalog = new UltrasonicAnalog(1);
	public static DigitalInput topStop = new DigitalInput(0);
	public static DigitalInput bottomStop = new DigitalInput(1);
	public static RemoteHCSR04 remoteHCSR04 = new RemoteHCSR04();

	// commands
	private static RunAutonomous autonomous;
	private Autonomous ato = new Autonomous();

	// utils
	public static pidControl pidValue = new pidControl();
	public static ArmPID armPID = new ArmPID();
	public static Logger logger = new Logger();

	private static Faults leftFaults = new Faults();
	private static Faults rightFaults = new Faults();

	public static enum StartingPosition{
		Left_Lvl_2,
		Left_Lvl_1,
		Middle_Lvl_1,
		Right_Lvl_1,
		Right_Lvl_2,
	}
	public static StartingPosition starting_postion = StartingPosition.Right_Lvl_2;
	private static boolean isLeft = false;

	public static enum TargetPosition{
		Left_Bay_1,
		// Left_Bay_2,
		// Left_Bay_3,
		Middle_Bay_1,
		Middle_Bay_2,
		Right_Bay_1,
		// Right_Bay_2,
		// Right_Bay_3,
	}

	public static enum GamePiece{
		Ball,
		Hatch_Panel,
	}

	SendableChooser<StartingPosition> startingPosition;
	SendableChooser<TargetPosition> targetPosition;
	SendableChooser<GamePiece> gamePiece;

	private void initStartingPosition(){
		startingPosition = new SendableChooser<StartingPosition>();
		startingPosition.setDefaultOption("Left_Lvl_2", StartingPosition.Left_Lvl_2);
		startingPosition.addOption("Left_Lvl_1", StartingPosition.Left_Lvl_1);
		startingPosition.addOption("Middle_Lvl_1", StartingPosition.Middle_Lvl_1);
		startingPosition.addOption("Right_Lvl_1", StartingPosition.Right_Lvl_1);
		startingPosition.addOption("Right_Lvl_2", StartingPosition.Right_Lvl_2);
		SmartDashboard.putData("Select starting position:", startingPosition);
	}

	private void initTargetPosition(){
		targetPosition = new SendableChooser<TargetPosition>();
		targetPosition.setDefaultOption("Left_Bay_1", TargetPosition.Left_Bay_1);
		// targetPosition.addOption("Left_Bay_2", TargetPosition.Left_Bay_2);
		// targetPosition.addOption("Left_Bay_3", TargetPosition.Left_Bay_3);
		targetPosition.addOption("Middle_Bay_1", TargetPosition.Middle_Bay_1);
		targetPosition.addOption("Middle_Bay_2", TargetPosition.Middle_Bay_2);
		targetPosition.addOption("Right_Bay_1", TargetPosition.Right_Bay_1);
		// targetPosition.addOption("Right_Bay_2", TargetPosition.Right_Bay_2);
		// targetPosition.addOption("Right_Bay_3", TargetPosition.Right_Bay_3);
		SmartDashboard.putData("Select target position:", targetPosition);
	}

	private void initGamePiece(){
		gamePiece = new SendableChooser<GamePiece>();
		gamePiece.setDefaultOption("Ball", GamePiece.Ball);
		gamePiece.addOption("Hatch_Panel", GamePiece.Hatch_Panel);
		SmartDashboard.putData("Select game piece:", gamePiece);
	}

	public static boolean getIsLeft(){
		if(starting_postion == StartingPosition.Left_Lvl_2){
			return true;
		}
		return false;
	}
	

	private static boolean isMiddle = false;

	public static boolean getIsMiddle() {
		return isMiddle;
	}

	private static boolean isRight = false;

	public static boolean getIsRight() {
		if(starting_postion == StartingPosition.Right_Lvl_2){
			return true;
		}
		return false;
	}

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();

		gyro.resetGyro();

		oi = new OI();

		armMotor = new ArmMotor();
		solenoidSubsystem = new SolenoidSubsystem();
		solenoidSubsystem.activateRight();
		compressor.setClosedLoopControl(true);

		logger.putNumber("Ultrasonic Distance ", 0);		
		initStartingPosition();
		initTargetPosition();
		initGamePiece();
		// logger.putNumber("kP Value" , pidValue.getkP() );
		// logger.putNumber("kI Value" , pidValue.getkI() );
		// logger.putNumber("kD Value" , pidValue.getkD() );
	}

	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
	}
	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		logger.putMessage("Robot has been disabled");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		logger.putMessage("Starting autonomous");

		gyro.resetGyro();

		//magEncoder.reset();
		//intakeEncoder.reset();
		// pidValue.setkP(SmartDashboard.getNumber("kP Value" , pidValue.getkP()));
		// pidValue.setkI(SmartDashboard.getNumber("kI Value" , pidValue.getkI()));
		// pidValue.setkD(SmartDashboard.getNumber("kD Value" , pidValue.getkD()));

		// RobotMap.robotRightTalon.setSelectedSensorPosition(0, 0, pidValue.getkTimeoutMS());
		// RobotMap.robotLeftTalon.setSelectedSensorPosition(0, 0, pidValue.getkTimeoutMS());

		logger.putString("Auto Step 1 Done", "No");

		StartingPosition selectedStartingPosition = (StartingPosition) startingPosition.getSelected();
		TargetPosition selectedTargetPosition = (TargetPosition) targetPosition.getSelected();
		GamePiece selectedGamePiece = (GamePiece) gamePiece.getSelected();

		logger.putMessage("Selected starting position: " + selectedStartingPosition.name());
		logger.putMessage("Selected target position: " + selectedTargetPosition.name());
		logger.putMessage("Selected game piece: " + selectedGamePiece.name());

		// autonomousCommand = new RunAutonomous();
		/** if (autonomousCommand != null) {
			autonomousCommand.start();		
		}*/
		autonomous.run();

		//if(step1done == false){
			//autoPID.step1();
		//}
		// else{
		// 	autoPID.stop();
		// }
	}

	/**
	 * This function is called periodically during autonomous
	 */
	int count = 0;



	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		// if(Step1_done == false){
			
        //     RobotMap.robotLeftTalon.set(ControlMode.Position, 7475.2);
		// 	RobotMap.robotRightTalon.set(ControlMode.Position,7475.2);
			
		// }

		// logger.putNumber("Gyro Value", gyro.getGyroAngle());
		// RobotMap.robotRightTalon.clearStickyFaults(30);
		// RobotMap.robotLeftTalon.clearStickyFaults(30);
		// logger.putNumber("Left Error", (double) RobotMap.robotLeftTalon.getClosedLoopError(0));
		// logger.putNumber("Right Error", (double) RobotMap.robotRightTalon.getClosedLoopError(0));
		logger.putNumber("Gyro Value" , gyro.getGyroAngle());

		// logger.putNumber("Left Distance ", RobotMap.robotLeftTalon.getSelectedSensorPosition());
		// logger.putNumber("Right Distance ", RobotMap.robotRightTalon.getSelectedSensorPosition());
		// RobotMap.robotRightTalon.getFaults(rightFaults);
		// RobotMap.robotLeftTalon.getFaults(leftFaults);
		// logger.putBoolean("Left Out of Phase ", leftFaults.SensorOutOfPhase);
		// logger.putBoolean("Right Out of Phase ", rightFaults.SensorOutOfPhase);

		double leftPos = RobotMap.robotLeftTalon.getSensorCollection().getPulseWidthPosition();
		logger.putNumber("Left Position", leftPos);
		double rightPos = RobotMap.robotRightTalon.getSensorCollection().getPulseWidthPosition();
		logger.putNumber("Right Position", rightPos);
		
		logger.putNumber("LEFT POSITION", RobotMap.robotLeftTalon.getSelectedSensorPosition(0));
		logger.putNumber("RIGHT POSITION", RobotMap.robotRightTalon.getSelectedSensorPosition(0));
		//ato.runSteps();
		//ato.Step1();
		// if(count == 0){
		// 	logger.putString("Step1 Done", "no");
		// if(ato.isStep1Done() == false){
		//ato.Step1();
		// }
		// else if (ato.isStep1Done() == true && ato.isStep2Done() == false){
		// 	ato.Step2();
		// }
		// if(ato.isStep1Done() == false){
		// ato.Step1();
		// logger.putString("Step1_done", "NO");
		// }
		if(ato.isStep2Done() == false){	
			
		ato.Step2();
		}
		else if(ato.isStep2Done() == true && ato.isStep3Done()== false ){
		ato.Step3();
		}
		else if(ato.isStep3Done() == true && ato.isStep4Done() == false){
			ato.Step4();
		}
		//chum reap soure
		else if(ato.isStep4Done() == true && ato.isStep5Done() == false){
			ato.Step5();
		}
		else if (ato.isStep5Done() == true && ato.isStep6Done() == false){
			ato.Step6();
		}
		// 	++count;
		// }
		// if(ato.isStep1Done() == true){
		// 	logger.putString("Step1 Done2", "yes");
		// 	ato.stopDrive();		
		// }


	}

	@Override
	public void teleopInit() {

		logger.putMessage("Starting teleop");

		oi.startDriveCommand();
		//gyro.resetGyro();

		// set the motor controllers back to full speed
        RobotMap.robotLeftTalon.configPeakOutputForward(1.0, Constants.kTimeoutMs);
        RobotMap.robotRightTalon.configPeakOutputForward(1.0, Constants.kTimeoutMs);
        RobotMap.robotLeftTalon.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);
		RobotMap.robotRightTalon.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);	
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();


		// run arm with joystick
		/*if (topStop.get()) {
			armMotor.stop();
		} else if (bottomStop.get()) {
			armMotor.stop();
		} else*/ 
		// if (oi.xBoxController.getRawAxis(5) > 0.2) {
		// 	armMotor.runDownward();
		// } else if (oi.xBoxController.getRawAxis(5) < -0.2) {
		// 	armMotor.runUpward();
		// } else if (armMotor.isPIDRunning() == false) {
		// 	armMotor.stop();
		// }

		if (oi.copilotController.getRawAxis(5) > 0.2) {
			armMotor.runDownward();
			//logger.putMessage("Arm moving down");
			logger.putNumber("Arm current Position", RobotMap.armTalon.getSensorCollection().getPulseWidthPosition());
		} else if (oi.copilotController.getRawAxis(5) < -0.2) {
			armMotor.runUpward();
			//logger.putMessage("Arm moving up");
			logger.putNumber("Arm current Position", RobotMap.armTalon.getSensorCollection().getPulseWidthPosition());
		} else {
			armMotor.stop();
			logger.putMessage("Arm not moving");
		}

		// run intake
		double range = ultrasonicanalog.getRange();
		logger.putNumber("Ball Ultrasonic Value", range);
		if (oi.getIntake()) {
			if (ultrasonicanalog.getRange() < 3) {
				RobotMap.intakeVictor.set(0);
				oi.copilotController.setRumble(RumbleType.kLeftRumble, 1);
				oi.incrementRumbleCount();
				logger.putMessage("Ball found in Intake - starting rumble");
			} else {
				RobotMap.intakeVictor.set(0.5);
				logger.putMessage("Intaking ball");
			}
		} else if (oi.getOuttake()) {
			RobotMap.intakeVictor.set(-1);
			logger.putMessage("Shooting ball");
		} else {
			RobotMap.intakeVictor.set(0);
			//logger.putMessage("Intake not running");
		}
		int rumbleCount = oi.getRumbleCount();
		if (rumbleCount > 0) {
			if (rumbleCount > 100) {
				oi.copilotController.setRumble(RumbleType.kLeftRumble, 0);
				oi.setRumbleCount(0);
				logger.putMessage("Turning off rumble");
			} else {
				oi.incrementRumbleCount();
			}
		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	private double GetDistance() {
		return SmartDashboard.getNumber("Ultrasonic Distance ", -50);

	}
}