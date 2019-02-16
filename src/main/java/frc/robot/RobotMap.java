package frc.robot;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static WPI_TalonSRX robotLeftTalon;
	public static WPI_VictorSPX robotLeftVictor;
	public static WPI_TalonSRX robotRightTalon;
	public static WPI_VictorSPX robotRightVictor;
	public static DifferentialDrive myRobot;
	public static SpeedController leftDrive;
	public static SpeedController rightDrive;
	public static WPI_VictorSPX IntakeVictor;
	public static WPI_TalonSRX armTalon;
	
	public static void init() {
		final int kTimeoutMs = 10;

		robotLeftTalon = new WPI_TalonSRX(23);
		robotRightTalon = new WPI_TalonSRX(22);
		robotLeftVictor = new WPI_VictorSPX(20);
		robotRightVictor = new WPI_VictorSPX(21);

		robotRightTalon.configFactoryDefault();
		robotRightVictor.configFactoryDefault();
		robotLeftTalon.configFactoryDefault();
		robotLeftVictor.configFactoryDefault();		

		Robot.pidValue.configTalons();

		leftDrive = new MultiSpeedController(robotLeftTalon, robotLeftTalon);
		rightDrive = new MultiSpeedController(robotRightTalon, robotRightTalon);
		robotLeftTalon.setSelectedSensorPosition(0 , 0 , kTimeoutMs);   
        //initLeftQuadrature();
        robotLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, kTimeoutMs);								// Timeout
		robotRightTalon.setSelectedSensorPosition(0 , 0 , kTimeoutMs);   
        //initRightQuadrature();
        robotRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, kTimeoutMs);								// Timeout
	
		myRobot = new DifferentialDrive(leftDrive, rightDrive);
		IntakeVictor = new WPI_VictorSPX(12);
		armTalon = new WPI_TalonSRX(11);
		armTalon.configFactoryDefault();	
		Robot.armPID.init();	
	}
}