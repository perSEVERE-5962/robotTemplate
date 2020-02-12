/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MultiSpeedController;
import frc.robot.sensors.UltrasonicHCSR04;

public class Drive extends SubsystemBase {
  public UltrasonicHCSR04 ultrasonic;
	public static WPI_TalonSRX robotLeftTalon;
	public static WPI_VictorSPX robotLeftVictor;
	public static WPI_TalonSRX robotRightTalon;
	public static WPI_VictorSPX robotRightVictor;
	private static DifferentialDrive myRobot;
	private static SpeedController leftDrive;
  private static SpeedController rightDrive;

  private double ultrasonicRange;
  private boolean ultrasonicCheck = false;
  // private Joystick joystick;
  
  public static WPI_TalonSRX rightTalon(){
    return robotRightTalon;
  }
  
  public static WPI_TalonSRX leftTalon(){
    return robotLeftTalon;
  }
  public Drive() {
    robotRightTalon = new WPI_TalonSRX(23);
		robotRightVictor = new WPI_VictorSPX(20);
		robotLeftTalon = new WPI_TalonSRX(22);
    robotLeftVictor = new WPI_VictorSPX(21);
    robotLeftVictor.follow(robotLeftTalon,FollowerType.PercentOutput);
    robotRightVictor.follow(robotRightTalon,FollowerType.PercentOutput);
    // rightTalon().setInverted(true);
    // leftTalon().setInverted(true);
    // rightTalon().setSensorPhase(true);
    // leftTalon().setSelectedSensorPosition(0);
    // rightTalon().setSelectedSensorPosition(0);
		// leftDrive = new MultiSpeedController(robotLeftTalon, robotLeftTalon);
		// rightDrive = new MultiSpeedController(robotRightTalon, robotRightTalon);
    // myRobot = new DifferentialDrive(leftDrive, rightDrive);
    // this.joystick = joystick;
  }


  public void tankDrive(double leftSpeed, double rightSpeed) {
    robotLeftTalon.set(ControlMode.PercentOutput, leftSpeed);
    robotRightTalon.set(ControlMode.PercentOutput, rightSpeed);
    // myRobot.tankDrive(joystick.getRawAxis(1), joystick.getRawAxis(4));
    SmartDashboard.putNumber("tankdrive left", leftSpeed);
    SmartDashboard.putNumber("tankdrive right", rightSpeed);
    SmartDashboard.putNumber("encoder left", leftTalon().getSelectedSensorPosition());
    SmartDashboard.putNumber("encoder right", rightTalon().getSelectedSensorPosition());
	}
  
  public void driveToScoringArea(){
    ultrasonicRange = ultrasonic.averageRange();
    if(ultrasonicCheck = false){
      robotLeftTalon.set(ControlMode.PercentOutput, 0.5);
      robotRightTalon.set(ControlMode.PercentOutput, 0.5);
      ultrasonicCheck = true;
    }
    else if(ultrasonicCheck = true && ultrasonicRange < 5){
      robotLeftTalon.set(ControlMode.PercentOutput, 0);
      robotRightTalon.set(ControlMode.PercentOutput, 0);
    }
  }
  public void autoDrive() {
    // placeholder
    // SmartDashboard.putNumber("auto left", joystick.getRawAxis(1));
    // SmartDashboard.putNumber("auto right", joystick.getRawAxis(4)); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
