package frc.robot;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
	
	public static void init() {

		robotLeftTalon = new WPI_TalonSRX(23);
		robotLeftVictor = new WPI_VictorSPX(20);
		robotLeftVictor.follow(robotLeftTalon,FollowerType.PercentOutput);
		robotRightTalon = new WPI_TalonSRX(22);
		robotRightVictor = new WPI_VictorSPX(21);
		robotRightVictor.follow(robotRightTalon,FollowerType.PercentOutput);
		leftDrive = new MultiSpeedController(robotLeftTalon, robotLeftTalon);
		rightDrive = new MultiSpeedController(robotRightTalon, robotRightTalon);
		myRobot = new DifferentialDrive(leftDrive, rightDrive);
	}
}