package frc.robot;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// //import frc.robot.RobotMap;
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
	public static WPI_TalonSRX armTalon;
	public static WPI_VictorSPX IntakeVictor;
	
	public static void init() {
		// Competition Robot
		// robotLeftTalon = new TalonSRX(23);
		// robotRightTalon = new TalonSRX(22);
		// robotLeftVictor = new VictorSPX(20);
		// robotRightVictor = new VictorSPX(21);

		// demo robot
		robotLeftTalon = new WPI_TalonSRX(22);
		robotRightTalon = new WPI_TalonSRX(23);
		robotLeftVictor = new WPI_VictorSPX(20);
		robotRightVictor = new WPI_VictorSPX(21);

		robotRightTalon.configFactoryDefault();
		robotRightVictor.configFactoryDefault();
		robotLeftTalon.configFactoryDefault();
		robotLeftVictor.configFactoryDefault();		

		Robot.pidValue.configTalons();

		armTalon = new WPI_TalonSRX(11);
		IntakeVictor = new WPI_VictorSPX(12);
		RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
		armTalon.setSelectedSensorPosition(0);
		Robot.armPID.init();	
		armTalon.configFactoryDefault();	
		RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
		RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
	}
}