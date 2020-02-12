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
import frc.robot.sensors.UltrasonicHCSR04;

public class Drive extends SubsystemBase {
  private static WPI_TalonSRX robotLeftTalon;
  private static WPI_VictorSPX robotLeftVictor;
  private static WPI_TalonSRX robotRightTalon;
  private static WPI_VictorSPX robotRightVictor;
  private static final double speedfactor = 0.125;
  public WPI_TalonSRX leftTalon() {
    return robotLeftTalon;
  }


  public WPI_TalonSRX rightTalon() {
    return robotRightTalon;
  }

  private double ultrasonicRange;
  private boolean ultrasonicCheck = false;
  public UltrasonicHCSR04 ultrasonic;
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

  private void setsmooth() {
    robotLeftTalon.configOpenloopRamp(0.7);
    robotLeftTalon.configClosedloopRamp(0);
    robotRightTalon.configOpenloopRamp(0.7);
    robotRightTalon.configOpenloopRamp(0);
  }

  private void unsetsmooth() {
    robotLeftTalon.configOpenloopRamp(0);
    robotLeftTalon.configClosedloopRamp(0);
    robotRightTalon.configOpenloopRamp(0);
    robotRightTalon.configOpenloopRamp(0);
  }

  public void smoothTankDrive() {
    setsmooth();
    tankDrive();
  }

  public void smoothArcadeDrive() {
    setsmooth();
    arcadeDrive();
  }

  public void jamieDrive() {
    unsetsmooth();
    if (joystick.getRawAxis(0) < -0.1) {
      leftTalon().set(ControlMode.PercentOutput, -speedfactor);// *joystick.getRawAxis(0)
      rightTalon().set(ControlMode.PercentOutput, speedfactor);
    }


  }
    }
      leftTalon().set(ControlMode.PercentOutput, speedfactor * joystick.getRawAxis(5));
      rightTalon().set(ControlMode.PercentOutput, speedfactor * joystick.getRawAxis(5));
    } else {
      rightTalon().set(ControlMode.PercentOutput, -speedfactor);
      leftTalon().set(ControlMode.PercentOutput, speedfactor);
    else if (joystick.getRawAxis(0) > 0.1) {
  public void tankDrive(double leftSpeed, double rightSpeed) {
    robotLeftTalon.set(ControlMode.PercentOutput, leftSpeed);
    robotRightTalon.set(ControlMode.PercentOutput, rightSpeed);
    SmartDashboard.putNumber("tankdrive left", leftSpeed);
    // myRobot.tankDrive(joystick.getRawAxis(1), joystick.getRawAxis(4));
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

  public void arcadeDrive() {
    leftTalon().set(ControlMode.PercentOutput, speedfactor * joystick.getRawAxis(4));
    rightTalon().set(ControlMode.PercentOutput, speedfactor * joystick.getRawAxis(5));
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
public void driveLeft(){
  SmartDashboard.putString("driveleft", "");
  leftTalon().set(ControlMode.PercentOutput, -speedfactor);
  rightTalon().set(ControlMode.PercentOutput, speedfactor);
}
public void driveRight(){
  leftTalon().set(ControlMode.PercentOutput, speedfactor);
  rightTalon().set(ControlMode.PercentOutput, -speedfactor);
}

public void stopDrive(){
  leftTalon().set(ControlMode.PercentOutput, 0);
  rightTalon().set(ControlMode.PercentOutput, 0);
  SmartDashboard.putString("stoprobot", "");
}

public void inchforward(double speed){
  leftTalon().set(ControlMode.PercentOutput, -speed);
  rightTalon().set(ControlMode.PercentOutput, -speed);
}

public double radius = 3;
public double circumfrence = 2*Math.PI*radius;
public double inchesToTicks (double inch){
  double input = inch*4096;
  double answer = input/circumfrence;
  SmartDashboard.putNumber("TargetPose", answer);
  return answer;
}

public void goforwards(){
  // leftTalon().setInverted(true);
  // rightTalon().setInverted(true);
  leftTalon().set(ControlMode.PercentOutput, speedfactor);
  rightTalon().set(ControlMode.PercentOutput, speedfactor);
}

public void gobackwards(){
  
  leftTalon().set(ControlMode.PercentOutput, -speedfactor);
  rightTalon().set(ControlMode.PercentOutput, -speedfactor);
}

}
