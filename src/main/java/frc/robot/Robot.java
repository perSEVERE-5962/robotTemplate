
package frc.robot;

import java.util.concurrent.TimeUnit;
import com.analog.adis16470.frc.ADIS16470_IMU;
import com.ctre.phoenix.motorcontrol.Faults;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.subsystems.Drive;
import frc.robot.commands.RunAutonomous;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	 public static RobotGyro gyro = new RobotGyro();
	//public static ADIS16470_IMU gyro = new ADIS16470_IMU();
	public static srxMagEncoder magEncoder = new srxMagEncoder();
	public static srxMagEncoder intakeEncoder = new srxMagEncoder();
	public static OI oi;
	public static Drive drive = new Drive();
	public static UltrasonicAnalog ultrasonicanalog = new UltrasonicAnalog(1);
	private static RunAutonomous autonomousCommand;
	private static ArmMotor armMotor;
	public static SolenoidSubsystem solenoidSubsystem;
	

	private static boolean isLeft = false;
	private AutoPID autoPID = new AutoPID();
	boolean step1done = false;
	public static TalonConfigsPID pidValue = new TalonConfigsPID();
	private static Faults leftFaults = new Faults();
	private static Faults rightFaults = new Faults();
	int counter = 0;
	Compressor compressor = new Compressor(0);


	public static boolean getIsLeft(){
		return isLeft;
	}
	private static boolean isMiddle = false;
	public static boolean getIsMiddle(){
		return isMiddle;
	}
	private static boolean isRight = false;
	public static boolean getIsRight(){
		return isRight;
	}
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		// gyro.resetGyro();
		oi = new OI();
		//magEncoder.init();
		//magEncoder.reset();
		//intakeEncoder.init();
		//intakeEncoder.reset();
		armMotor = new ArmMotor();
		solenoidSubsystem = new SolenoidSubsystem();
		solenoidSubsystem.activateRight();
		compressor.setClosedLoopControl(true);

		SmartDashboard.putNumber("Ultrasonic Distance ", 0);		
		SmartDashboard.putNumber("kP Value" , pidValue.getkP() );
		SmartDashboard.putNumber("kI Value" , pidValue.getkI() );
		SmartDashboard.putNumber("kD Value" , pidValue.getkD() );

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
		//autonomousCommand = new RunAutonomous();
		gyro.resetGyro();

		/** if (autonomousCommand != null) {
			autonomousCommand.start();		
		}*/
		//magEncoder.reset();
		//intakeEncoder.reset();
		pidValue.setkP(SmartDashboard.getNumber("kP Value" , pidValue.getkP()));
		pidValue.setkI(SmartDashboard.getNumber("kI Value" , pidValue.getkI()));
		pidValue.setkD(SmartDashboard.getNumber("kD Value" , pidValue.getkD()));

		RobotMap.robotRightTalon.setSelectedSensorPosition(0, 0, pidValue.getkTimeoutMS());
		RobotMap.robotLeftTalon.setSelectedSensorPosition(0, 0, pidValue.getkTimeoutMS());


		SmartDashboard.putString("Auto Step 1 Done", "No");

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
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//SmartDashboard.putString("Encoder Left Value ", "" + magEncoder.getLeftDistance());
		//SmartDashboard.putString("Encoder Right Value ", "" + magEncoder.getRightDistance());
		//SmartDashboard.putString("Encoder Value ", "" + magEncoder.getDistance());
		SmartDashboard.putNumber("Gyro Value" , gyro.getGyroAngle());
		RobotMap.robotLeftTalon.clearStickyFaults(pidValue.getkTimeoutMS());
		RobotMap.robotRightTalon.clearStickyFaults(pidValue.getkTimeoutMS());



		if(autoPID.isStep1Done()==false){
			autoPID.step1();
			SmartDashboard.putString("Auto Step 1", "Done");
			SmartDashboard.putString("Auto Step 1 Done", "yes");
		}
		else{
		//autoPID.stop();
			SmartDashboard.putString("Auto Step 1", "Running");
			
			// if(RobotMap.robotLeftTalon.getClosedLoopError()< 2000 && RobotMap.robotRightTalon.getClosedLoopError()<2000){
			// 	autoPID.stop();
			// }
		}
		
		SmartDashboard.putNumber("Left Error", (double) RobotMap.robotLeftTalon.getClosedLoopError(0));
		SmartDashboard.putNumber("Right Error", (double) RobotMap.robotRightTalon.getClosedLoopError(0));
	
		SmartDashboard.putNumber("Left Distance ", RobotMap.robotLeftTalon.getSelectedSensorPosition());
		SmartDashboard.putNumber("Right Distance ", RobotMap.robotRightTalon.getSelectedSensorPosition());
		RobotMap.robotRightTalon.getFaults(rightFaults);
		RobotMap.robotLeftTalon.getFaults(leftFaults);

		SmartDashboard.putBoolean("Left Out of Phase ", leftFaults.SensorOutOfPhase);
		SmartDashboard.putBoolean("Right Out of Phase ", rightFaults.SensorOutOfPhase);


		

		//try {
		//	TimeUnit.MILLISECONDS.sleep(10);
		//	counter += 10;
		//} catch (Exception e) { /* Black Magic */ }
		
		/**(auto.Step2_done == false){
			auto.Step2();
		}
		else if (auto.Step3_done == false){
			auto.Step3();
		}
		if( auto.Step4_done == false){
			auto.Step4();
		}
		 if(auto.Step5_done == false){
			auto.Step5();
		}
		 else if(auto.Step5_done == true && auto.Step6_done == false){
			auto.Step6();
		}
		else if(auto.Step6_done == true && auto.Step7_done == false){
			auto.Step7();
		}
		else{
			RobotMap.myRobot.tankDrive(0, 0);	
		}*/
		
	}

	@Override
	public void teleopInit() {
		oi.startDriveCommand();
		gyro.resetGyro();
		//magEncoder.reset();
		//intakeEncoder.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	int RumbleCount=0;
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (oi.xBoxController.getRawAxis(5) > 0.2) {
			armMotor.runDownward();
		} else if (oi.xBoxController.getRawAxis(5) < -0.2) {
			armMotor.runUpward();
		} else {
			armMotor.stop();
		}
		SmartDashboard.putNumber("Ultrasonic Value", ultrasonicanalog.getRange());
		if (oi.getIntake()) {
			SmartDashboard.putString("value ", "" + GetDistance());
			if (ultrasonicanalog.getRange() < 6) {
				RobotMap.IntakeVictor.set(0);
				oi.xBoxController.setRumble(RumbleType.kLeftRumble, 1);
				++RumbleCount;
			} else {
				RobotMap.IntakeVictor.set(1);
			}
		} else if (oi.getOuttake()) {
			RobotMap.IntakeVictor.set(-1);
		} else {
			RobotMap.IntakeVictor.set(0);
		}
		if (RumbleCount >0){
			if (RumbleCount >100){
			oi.xBoxController.setRumble(RumbleType.kLeftRumble, 0);
			RumbleCount=0;
		}
		else {
			++RumbleCount;
		}
		}
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro Value", Robot.gyro.getGyroAngle());
	}
	


	/**
	 * This function is called periodically during test mode
	 */
	Autonomous auto = new Autonomous();

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		if (auto.Step1_done == false) {
			auto.Step1();
		}
	}

	private double GetDistance() {
		SmartDashboard.putNumber("Ultrasonic Value", ultrasonicanalog.getRange());
		return SmartDashboard.getNumber("Ultrasonic Distance ", -50);

	}
}