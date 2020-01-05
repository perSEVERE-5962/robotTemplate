
package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
//import com.analog.adis16470.frc.ADIS16470_IMU;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/* The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

	public static OI oi;
	public static Drive drive = new Drive();

	//public static ADIS16448_IMU imu = new ADIS16448_IMU();
	public static final AnalogGyro gyro = new AnalogGyro(1);

	public static ExampleSubsystem m_subsystem = new ExampleSubsystem();

	//private double imuOffset=0;
	
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */


	public void robotInit() {
		RobotMap.init();
		oi = new OI();

		//CameraServer.getInstance().startAutomaticCapture();
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			while(!Thread.interrupted()) {
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
				outputStream.putFrame(output);
			}
		}).start();

		//imuOffset = imu.getAngle();
		gyro.calibrate();
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		//imuOffset = imu.getAngle();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		//imuOffset = imu.getAngle();
		gyro.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();		

		// SmartDashboard.putNumber("IMU", imu.getAngle());
		// SmartDashboard.putNumber("IMU Gyro-X", imu.getAngleX());
		// SmartDashboard.putNumber("IMU Gyro-Y", imu.getAngleY());
		// SmartDashboard.putNumber("IMU Gyro-Z", imu.getAngleZ());
		
		// SmartDashboard.putNumber("IMU Accel-X", imu.getAccelX());
		// SmartDashboard.putNumber("IMU Accel-Y", imu.getAccelY());
		// SmartDashboard.putNumber("IMU Accel-Z", imu.getAccelZ());
		
		// SmartDashboard.putNumber("IMU Pitch", imu.getPitch());
		// SmartDashboard.putNumber("IMU Roll", imu.getRoll());
		double angle = gyro.getAngle();
		SmartDashboard.putNumber("angle", angle);
		//SmartDashboard.putNumber("offset", imuOffset);
		//SmartDashboard.putNumber("robot_angle", angle-imuOffset);
		if (angle > -100)
		{
		  m_subsystem.turnonLed();
		}		
		// SmartDashboard.putNumber("IMU Pressure: ", imu.getBarometricPressure());
		// SmartDashboard.putNumber("IMU Temperature: ", imu.getTemperature()); 


//		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		//LiveWindow.run();
	}
	
}