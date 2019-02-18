
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
import frc.robot.sensors.TalonConfigsPID;
import frc.robot.utils.ArmPID;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
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

	// commands
	private static RunAutonomous autonomous = new RunAutonomous();

	// utils
	public static ArmPID armPID = new ArmPID();
	public static TalonConfigsPID pidValue = new TalonConfigsPID();

	// other
	private static Faults leftFaults = new Faults();
	private static Faults rightFaults = new Faults();

	private static boolean isLeft = false;
	public static boolean getIsLeft() {
		return isLeft;
	}

	private static boolean isMiddle = false;
	public static boolean getIsMiddle() {
		return isMiddle;
	}

	private static boolean isRight = false;
	public static boolean getIsRight() {
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

		armMotor = new ArmMotor();
		solenoidSubsystem = new SolenoidSubsystem();
		solenoidSubsystem.activateRight();
		compressor.setClosedLoopControl(true);

		SmartDashboard.putNumber("Ultrasonic Distance ", 0);
		SmartDashboard.putNumber("kP Value", pidValue.getkP());
		SmartDashboard.putNumber("kI Value", pidValue.getkI());
		SmartDashboard.putNumber("kD Value", pidValue.getkD());

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
		// autonomousCommand = new RunAutonomous();
		gyro.resetGyro();

		/**
		 * if (autonomousCommand != null) { autonomousCommand.start(); }
		 */

		pidValue.setkP(SmartDashboard.getNumber("kP Value", pidValue.getkP()));
		pidValue.setkI(SmartDashboard.getNumber("kI Value", pidValue.getkI()));
		pidValue.setkD(SmartDashboard.getNumber("kD Value", pidValue.getkD()));

		autonomous.run();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Gyro Value", gyro.getGyroAngle());
		RobotMap.robotLeftTalon.clearStickyFaults(pidValue.getkTimeoutMS());
		RobotMap.robotRightTalon.clearStickyFaults(pidValue.getkTimeoutMS());

		SmartDashboard.putNumber("Left Error", (double) RobotMap.robotLeftTalon.getClosedLoopError(0));
		SmartDashboard.putNumber("Right Error", (double) RobotMap.robotRightTalon.getClosedLoopError(0));

		SmartDashboard.putNumber("Left Distance ", RobotMap.robotLeftTalon.getSelectedSensorPosition());
		SmartDashboard.putNumber("Right Distance ", RobotMap.robotRightTalon.getSelectedSensorPosition());

		RobotMap.robotRightTalon.getFaults(rightFaults);
		RobotMap.robotLeftTalon.getFaults(leftFaults);
		SmartDashboard.putBoolean("Left Out of Phase ", leftFaults.SensorOutOfPhase);
		SmartDashboard.putBoolean("Right Out of Phase ", rightFaults.SensorOutOfPhase);

	}

	@Override
	public void teleopInit() {
		oi.startDriveCommand();
		gyro.resetGyro();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// run arm with joystick
		if (topStop.get()) {
			armMotor.stop();
		} else if (bottomStop.get()) {
			armMotor.stop();
		} else if (oi.xBoxController.getRawAxis(5) > 0.2) {
			armMotor.runDownward();
		} else if (oi.xBoxController.getRawAxis(5) < -0.2) {
			armMotor.runUpward();
		} else if (armMotor.isPIDRunning() == false) {
			armMotor.stop();
		}

		// run intake
		double range = ultrasonicanalog.getRange();
		SmartDashboard.putNumber("Ultrasonic Value", range);
		if (oi.getIntake()) {
			if (range < 6) {
				RobotMap.intakeVictor.set(0);
				oi.xBoxController.setRumble(RumbleType.kLeftRumble, 1);
				oi.incrementRumbleCount();
			} else {
				RobotMap.intakeVictor.set(0.5);
			}
		} else if (oi.getOuttake()) {
			RobotMap.intakeVictor.set(-1);
		} else {
			RobotMap.intakeVictor.set(0);
		}
		int rumbleCount = oi.getRumbleCount();
		if (rumbleCount > 0) {
			if (rumbleCount > 100) {
				oi.xBoxController.setRumble(RumbleType.kLeftRumble, 0);
				oi.setRumbleCount(0);
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

}