
package frc.robot;

import java.util.concurrent.TimeUnit;
import com.analog.adis16470.frc.ADIS16470_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drive;
import frc.robot.commands.RunAutonomous;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;

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
	// public static srxMagEncoder magEncoder = new srxMagEncoder();
	// public static srxMagEncoder intakeEncoder = new srxMagEncoder();
	public static OI oi;
	public static Drive drive = new Drive();
	public static UltrasonicAnalog ultrasonicanalog = new UltrasonicAnalog(1);
	private static RunAutonomous autonomous = new RunAutonomous();
	private static ArmMotor armMotor;
	public static SolenoidSubsystem solenoidSubsystem;
	private static boolean Step1_done = false;
	private static boolean Step2_done = false;
	private static boolean Step3_done = false;
	private static boolean Step4_done = false;
	private static boolean Step5_done = false;
	private static boolean Step6_done = false;
	private static boolean Step7_done = false;
	private static boolean isLeft = false;
	private Autonomous ato = new Autonomous();
	boolean step1done = false;
	public static pidControl pidValue = new pidControl();
	private static Faults leftFaults = new Faults();
	private static Faults rightFaults = new Faults();
	int counter = 0;
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

		gyro.resetGyro();
		oi = new OI();
		//magEncoder.init();
		//magEncoder.reset();
		//intakeEncoder.init();
		//intakeEncoder.reset();
		armMotor = new ArmMotor();
		solenoidSubsystem = new SolenoidSubsystem();

		SmartDashboard.putNumber("Ultrasonic Distance ", 0);		
		// SmartDashboard.putNumber("kP Value" , pidValue.getkP() );
		// SmartDashboard.putNumber("kI Value" , pidValue.getkI() );
		// SmartDashboard.putNumber("kD Value" , pidValue.getkD() );
		double startPos = RobotMap.armTalon.getSensorCollection().getPulseWidthPosition();
		SmartDashboard.putNumber("Arm Start Position", startPos);
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
		// //magEncoder.reset();
		// //intakeEncoder.reset();
		// pidValue.setkP(SmartDashboard.getNumber("kP Value" , pidValue.getkP()));
		// pidValue.setkI(SmartDashboard.getNumber("kI Value" , pidValue.getkI()));
		// pidValue.setkD(SmartDashboard.getNumber("kD Value" , pidValue.getkD()));

		//autonomous.run();
		// RobotMap.robotRightTalon.setSelectedSensorPosition(0, 0, pidValue.getkTimeoutMS());
		// RobotMap.robotLeftTalon.setSelectedSensorPosition(0, 0, pidValue.getkTimeoutMS());

		//SmartDashboard.putString("Auto Step 1 Done", "No");

		//auto.Step1();

		
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

		// SmartDashboard.putString("Encoder Left Value ", "" + magEncoder.getLeftDistance());
		// SmartDashboard.putString("Encoder Right Value ", "" + magEncoder.getRightDistance());
		// SmartDashboard.putString("Encoder Value ", "" + magEncoder.getDistance());
		SmartDashboard.putNumber("Gyro Value" , gyro.getGyroAngle());
		// RobotMap.robotLeftTalon.clearStickyFaults(pidValue.getkTimeoutMS());
		// RobotMap.robotRightTalon.clearStickyFaults(pidValue.getkTimeoutMS());

		// if(auto.isStep1Done()==false){
		// 	auto.step1();
		// 	SmartDashboard.putString("Auto Step 1", "Done");
		// 	SmartDashboard.putString("Auto Step 1 Done", "yes");
		// }
		// else{
		// //autoPID.stop();
		// 	SmartDashboard.putString("Auto Step 1", "Running");
			
		// 	if(RobotMap.robotLeftTalon.getClosedLoopError()< 2000 && RobotMap.robotRightTalon.getClosedLoopError()<2000){
		// 	 	auto.stop();
		// 	}
		// }
		
		// SmartDashboard.putNumber("Left Error", (double) RobotMap.robotLeftTalon.getClosedLoopError(0));
		// SmartDashboard.putNumber("Right Error", (double) RobotMap.robotRightTalon.getClosedLoopError(0));
	
		// SmartDashboard.putNumber("Left Distance ", RobotMap.robotLeftTalon.getSelectedSensorPosition());
		// SmartDashboard.putNumber("Right Distance ", RobotMap.robotRightTalon.getSelectedSensorPosition());
		// RobotMap.robotRightTalon.getFaults(rightFaults);
		// RobotMap.robotLeftTalon.getFaults(leftFaults);

		// SmartDashboard.putBoolean("Left Out of Phase ", leftFaults.SensorOutOfPhase);
		// SmartDashboard.putBoolean("Right Out of Phase ", rightFaults.SensorOutOfPhase);
		// SmartDashboard.putBoolean("Right Out of Phase ", rightFaults.SensorOutOfPhase);
		double leftPos = RobotMap.robotLeftTalon.getSensorCollection().getPulseWidthPosition();
		SmartDashboard.putNumber("Left Position", leftPos);
		double rightPos = RobotMap.robotRightTalon.getSensorCollection().getPulseWidthPosition();
		SmartDashboard.putNumber("Right Position", rightPos);
		
		SmartDashboard.putNumber("LEFT POSITION", RobotMap.robotLeftTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("RIGHT POSITION", RobotMap.robotRightTalon.getSelectedSensorPosition(0));
		//ato.runSteps();
		//ato.Step1();
		// if(count == 0){
		// 	SmartDashboard.putString("Step1 Done", "no");
		// if(ato.isStep1Done() == false){
		//ato.Step1();
		// }
		// else if (ato.isStep1Done() == true && ato.isStep2Done() == false){
		// 	ato.Step2();
		// }
		if(ato.isStep1Done() == false){
		ato.Step1();
		SmartDashboard.putString("Step1_done", "NO");
		}
		if(ato.isStep1Done() == true && ato.isStep3Done() == false){	
			SmartDashboard.putString("Step1_done", "YES");

			ato.Step3();
		}
		// else if(ato.isStep3Done() == true && ato.isStep4Done() == false){
		// 	ato.Step4();
		// }
		// 	++count;
		// }
		// if(ato.isStep1Done() == true){
		// 	SmartDashboard.putString("Step1 Done2", "yes");
		// 	ato.stopDrive();		
		// }

		// RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0.5);
		// RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0.5);


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
		if (oi.getIntake()) {
			SmartDashboard.putString("value ", "" + GetDistance());
			if (GetDistance() < 0.1) {
				RobotMap.intakeVictor.set(0);
			} else {
				RobotMap.intakeVictor.set(1);
			}
		} else if (oi.getOuttake()) {
			RobotMap.intakeVictor.set(-1);
		} else {
			RobotMap.intakeVictor.set(0);
		}
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro Value", Robot.gyro.getGyroAngle());

		
		
		double currentPos  = RobotMap.armTalon.getSensorCollection().getPulseWidthPosition();
		SmartDashboard.putNumber("Arm current Position", currentPos);




		
		
	}


	/**
	 * This function is called periodically during test mode
	 */
	Autonomous auto = new Autonomous();

	@Override
	public void testPeriodic() {
		//LiveWindow.run();
		Scheduler.getInstance().run();

	}
	private double GetDistance() {
		return SmartDashboard.getNumber("Ultrasonic Distance ", -50);
	}
}