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

	public static TalonSRX robotLeftTalon;
	public static VictorSPX robotLeftVictor;
	public static TalonSRX robotRightTalon;
	public static VictorSPX robotRightVictor;
	public static WPI_VictorSPX intakeVictor;
	public static TalonSRX armTalon;
	
	public static void init() {
		robotLeftTalon = new TalonSRX(23);
		robotRightTalon = new TalonSRX(22);
		robotLeftVictor = new VictorSPX(20);
		robotRightVictor = new VictorSPX(21);

		robotRightTalon.configFactoryDefault();
		robotRightVictor.configFactoryDefault();
		robotLeftTalon.configFactoryDefault();
		robotLeftVictor.configFactoryDefault();		

		Robot.pidValue.configTalons();

		intakeVictor = new WPI_VictorSPX(12);

		armTalon = new TalonSRX(11);
		armTalon.configFactoryDefault();	
		Robot.armPID.init();	
		armTalon.setSelectedSensorPosition(0);
	}
}