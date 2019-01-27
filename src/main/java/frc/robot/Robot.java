
package frc.robot;


import com.analog.adis16470.frc.ADIS16470_IMU;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drive;
import frc.robot.commands.RunAutonomous;
import frc.robot.sensors.srxMagEncoder;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	//public static RobotGyro robotGyro = new RobotGyro();
	public static srxMagEncoder magEncoder = new srxMagEncoder();
	public static ADIS16470_IMU gyro = new ADIS16470_IMU();
	public static OI oi;
	public static Drive drive = new Drive();
	private static RunAutonomous autonomousCommand;
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		gyro.reset();
		//robotGyro.resetGyro();
		oi = new OI();
		magEncoder.init();
		magEncoder.reset();
	}

  	/**
   	 * This function is called every robot packet, no matter the mode. Use
   	 * this for items like diagnostics that you want ran during disabled,
   	 * autonomous, teleoperated and test.
   	 *
   	 * <p>This runs after the mode specific periodic functions, but before
   	 * LiveWindow and SmartDashboard integrated updating.
   	 */
  	@Override
  	public void robotPeriodic() {
	}
	  
    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
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
     *  between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
	public void autonomousInit() {
		autonomousCommand = new RunAutonomous();
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		
		}
		magEncoder.reset();
	}
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
		SmartDashboard.putString("Encoder Left Value ", "" + magEncoder.getLeftDistance());
		SmartDashboard.putString("Encoder Right Value ", "" + magEncoder.getRightDistance());
		SmartDashboard.putString("Encoder Value ", "" + magEncoder.getDistance());
		SmartDashboard.putNumber("Gyro Value" , gyro.getAngleX());
	}

	@Override
	public void teleopInit() {
		oi.startDriveCommand();	
		magEncoder.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();		
	}

	/**
	 * This function is called periodically during test mode
	 */
	Autonomous auto = new Autonomous();
	@Override
	public void testPeriodic() {
		if(auto.Step1_done == false){
			auto.Step1();
		}
	}
	
}