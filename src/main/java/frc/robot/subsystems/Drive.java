/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MultiSpeedController;

public class Drive extends SubsystemBase {
	private static WPI_TalonSRX robotLeftTalon;
	private static WPI_VictorSPX robotLeftVictor;
	private static WPI_TalonSRX robotRightTalon;
	private static WPI_VictorSPX robotRightVictor;
	private static DifferentialDrive myRobot;
	private static SpeedController leftDrive;
  private static SpeedController rightDrive;
  private Joystick joystick;
  
  public Drive(Joystick joystick) {
    robotLeftTalon = new WPI_TalonSRX(23);
		robotLeftVictor = new WPI_VictorSPX(20);
		robotLeftVictor.follow(robotLeftTalon,FollowerType.PercentOutput);
		robotRightTalon = new WPI_TalonSRX(22);
		robotRightVictor = new WPI_VictorSPX(21);
		robotRightVictor.follow(robotRightTalon,FollowerType.PercentOutput);
		leftDrive = new MultiSpeedController(robotLeftTalon, robotLeftTalon);
		rightDrive = new MultiSpeedController(robotRightTalon, robotRightTalon);
    myRobot = new DifferentialDrive(leftDrive, rightDrive);
    this.joystick = joystick;
  }


  public void tankDrive() {
    myRobot.tankDrive(joystick.getRawAxis(1), joystick.getRawAxis(5));
    
	}
  public void arcadeDrive() {
    myRobot.arcadeDrive(joystick.getRawAxis(5), joystick.getRawAxis(4));
  }
  public void autoDrive() {
    // placeholder
    SmartDashboard.putNumber("auto left", joystick.getRawAxis(1));
    SmartDashboard.putNumber("auto right", joystick.getRawAxis(4)); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
