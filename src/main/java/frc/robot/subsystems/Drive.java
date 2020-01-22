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

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MultiSpeedController;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
public class Drive extends SubsystemBase {
	private static WPI_TalonSRX robotLeftTalon;
	private static WPI_VictorSPX robotLeftVictor;
	private static WPI_TalonSRX robotRightTalon;
	private static WPI_VictorSPX robotRightVictor;
	// private static DifferentialDrive myRobot;
	// private static SpeedController leftDrive;
  // private static SpeedController rightDrive;
  private Joystick joystick;
  private static final double speedfactor = 1.0;
  public static WPI_TalonSRX leftTalon(){
    return robotLeftTalon;
  }

  public static WPI_TalonSRX rightTalon(){
    return robotRightTalon;
  }
  public Drive(Joystick joystick) {
    robotLeftTalon = new WPI_TalonSRX(23);
    //  robotLeftTalon.configOpenloopRamp(0.1);
    //  robotLeftTalon.configClosedloopRamp(0);
    robotLeftVictor = new WPI_VictorSPX(20);
		robotLeftVictor.follow(robotLeftTalon,FollowerType.PercentOutput);
    robotRightTalon = new WPI_TalonSRX(22);
    //  robotRightTalon.configOpenloopRamp(0.1);
    //  robotRightTalon.configOpenloopRamp(0);
		robotRightVictor = new WPI_VictorSPX(21);
		robotRightVictor.follow(robotRightTalon,FollowerType.PercentOutput);
		
    this.joystick = joystick;
    robotLeftTalon.setInverted(true);
    robotLeftVictor.setInverted(true);
  }
  private void setsmooth(){
    robotLeftTalon.configOpenloopRamp(0.7);
     robotLeftTalon.configClosedloopRamp(0);
     robotRightTalon.configOpenloopRamp(0.7);
     robotRightTalon.configOpenloopRamp(0);
  }
  private void unsetsmooth(){
    robotLeftTalon.configOpenloopRamp(0);
     robotLeftTalon.configClosedloopRamp(0);
     robotRightTalon.configOpenloopRamp(0);
     robotRightTalon.configOpenloopRamp(0);
  }

  public void smoothTankDrive(){
    setsmooth();
    tankDrive();
  }

  public void smoothArcadeDrive(){
    setsmooth();
    arcadeDrive();
  }
  
  
  public void jamieDrive(){
    unsetsmooth();
    if(joystick.getRawAxis(0)<-0.1){
      leftTalon().set(ControlMode.PercentOutput, speedfactor);//*joystick.getRawAxis(0)
      rightTalon().set(ControlMode.PercentOutput, -speedfactor);
    }

    else if(joystick.getRawAxis(0)>0.1){
      leftTalon().set(ControlMode.PercentOutput, -speedfactor);
      rightTalon().set(ControlMode.PercentOutput, speedfactor);
    }
    else{
      leftTalon().set(ControlMode.PercentOutput, speedfactor*joystick.getRawAxis(5));
      rightTalon().set(ControlMode.PercentOutput, speedfactor*joystick.getRawAxis(5));
    }
  }
  
  

  public void tankDrive() {
    
      // myRobot.tankDrive(joystick.getRawAxis(1), joystick.getRawAxis(5));
    leftTalon().set(ControlMode.PercentOutput, speedfactor*joystick.getRawAxis(1));
    rightTalon().set(ControlMode.PercentOutput, speedfactor*joystick.getRawAxis(5));
	}
  public void arcadeDrive() {
    leftTalon().set(ControlMode.PercentOutput, speedfactor*joystick.getRawAxis(4));
    rightTalon().set(ControlMode.PercentOutput, speedfactor*joystick.getRawAxis(5));
    // myRobot.arcadeDrive(joystick.getRawAxis(5), joystick.getRawAxis(4));
  }
  public void autoDrive() {
    leftTalon().set(ControlMode.PercentOutput, speedfactor);
      rightTalon().set(ControlMode.PercentOutput, -speedfactor);
    // placeholder
    SmartDashboard.putNumber("auto left", joystick.getRawAxis(1));
    SmartDashboard.putNumber("auto right", joystick.getRawAxis(4)); 
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
