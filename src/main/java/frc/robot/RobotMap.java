package frc.robot;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	public static WPI_VictorSPX intakeVictor;
	
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
		robotLeftTalon.setSelectedSensorPosition(0);
		robotRightTalon.setSelectedSensorPosition(0);
		robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
		robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
		SmartDashboard.putNumber("LEFT START", robotLeftTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("RIGHT START", robotRightTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("LEFT START2", robotLeftTalon.getSensorCollection().getPulseWidthPosition());
		SmartDashboard.putNumber("RIGHT START2", robotRightTalon.getSensorCollection().getPulseWidthPosition());

		armTalon = new WPI_TalonSRX(11);
		intakeVictor = new WPI_VictorSPX(12);
		armTalon.configFactoryDefault();
		Robot.armPID.init();	
		armTalon.setSelectedSensorPosition(0);
		armTalon.getSensorCollection().setPulseWidthPosition(0, 10);
		SmartDashboard.putNumber("ARM START", armTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("ARM START2", armTalon.getSensorCollection().getPulseWidthPosition());
			
	}
}