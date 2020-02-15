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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.sensors.UltrasonicHCSR04;

public class Drive extends SubsystemBase {
  private WPI_TalonSRX robotLeftTalon;
  private WPI_VictorSPX robotLeftVictor;
  private WPI_TalonSRX robotRightTalon;
  private WPI_VictorSPX robotRightVictor;
  private final double speedfactor = 1.0;
  private final double autospeedfactor = 0.23;
  // private double ultrasonicRange;
  // private boolean ultrasonicCheck = false;
  private double radius = 3;
  private double circumfrence = 2 * Math.PI * radius;

  private boolean pathFollowerDone = false;

  public WPI_TalonSRX leftTalon() {
    return robotLeftTalon;
  }

  public WPI_TalonSRX rightTalon() {
    return robotRightTalon;
  }

  // public UltrasonicHCSR04 ultrasonic;
  // }

  public Drive() {
    robotRightTalon = new WPI_TalonSRX(23);
    robotRightVictor = new WPI_VictorSPX(20);
    robotLeftTalon = new WPI_TalonSRX(22);
    robotLeftVictor = new WPI_VictorSPX(21);
    robotLeftVictor.follow(robotLeftTalon, FollowerType.PercentOutput);
    robotRightVictor.follow(robotRightTalon, FollowerType.PercentOutput);
    robotLeftVictor.setInverted(false);
    robotLeftTalon.setInverted(false);
    robotRightVictor.setInverted(true);
    robotRightTalon.setInverted(true);
  }

  public WPI_TalonSRX getRobotRightTalon() {
    return robotRightTalon;
  }

  public WPI_TalonSRX getRobotLeftTalon() {
    return robotLeftTalon;
  }

  public WPI_VictorSPX getRobotRightVictor() {
    return robotRightVictor;
  }

  public WPI_VictorSPX getRobotLeftVictor() {
    return robotLeftVictor;
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

  public void smoothTankDrive(double leftSpeed, double rightSpeed) {
    setsmooth();
    tankDrive(leftSpeed, rightSpeed);
  }

  public void smoothArcadeDrive(double leftSpeed, double rightSpeed) {
    setsmooth();
    arcadeDrive(leftSpeed, rightSpeed);
  }

  public void jamieDrive(double leftSpeed, double rightSpeed) {
    unsetsmooth();
    if (leftSpeed < -0.1) {
      leftTalon().set(ControlMode.PercentOutput, -speedfactor*0.5);
      rightTalon().set(ControlMode.PercentOutput, speedfactor*0.5);
    }

    else if (leftSpeed > 0.1) {
      leftTalon().set(ControlMode.PercentOutput, speedfactor*0.5);
      rightTalon().set(ControlMode.PercentOutput, -speedfactor*0.5);
    } else {
      leftTalon().set(ControlMode.PercentOutput, -speedfactor * rightSpeed);
      rightTalon().set(ControlMode.PercentOutput, -speedfactor * rightSpeed);
    }
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    robotLeftTalon.set(ControlMode.PercentOutput, -speedfactor * leftSpeed);
    robotRightTalon.set(ControlMode.PercentOutput, -speedfactor * rightSpeed);
  }

  public void driveToScoringArea(){
  // ultrasonicRange = ultrasonic.averageRange();
  // if(ultrasonicCheck = false){
  // robotLeftTalon.set(ControlMode.PercentOutput, 0.5);
  // robotRightTalon.set(ControlMode.PercentOutput, 0.5);
  // ultrasonicCheck = true;
  // }
  // else if(ultrasonicCheck = true && ultrasonicRange < 5){
  // robotLeftTalon.set(ControlMode.PercentOutput, 0);
  // robotRightTalon.set(ControlMode.PercentOutput, 0);
  // }
  }

  public void arcadeDrive(double leftSpeed, double rightSpeed) {
    leftTalon().set(ControlMode.PercentOutput, -speedfactor * leftSpeed); 
    rightTalon().set(ControlMode.PercentOutput, -speedfactor * rightSpeed);
  }

  public void autoDrive() {
    leftTalon().set(ControlMode.PercentOutput, autospeedfactor);
    rightTalon().set(ControlMode.PercentOutput, -autospeedfactor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveLeft() {
    SmartDashboard.putString("driveleft", "");
    leftTalon().set(ControlMode.PercentOutput, -autospeedfactor);
    rightTalon().set(ControlMode.PercentOutput, autospeedfactor);
  }

  public void driveRight() {
    leftTalon().set(ControlMode.PercentOutput, autospeedfactor);
    rightTalon().set(ControlMode.PercentOutput, -autospeedfactor);
  }

  public void stopDrive() {
    leftTalon().set(ControlMode.PercentOutput, 0);
    rightTalon().set(ControlMode.PercentOutput, 0);
    SmartDashboard.putString("stoprobot", "");
  }

  public void inchforward(double speed) {
    leftTalon().set(ControlMode.PercentOutput, speed);
    rightTalon().set(ControlMode.PercentOutput, speed);
  }

  public double inchesToTicks(double inch) {
    double input = inch * 4096;
    double answer = input / circumfrence;
    SmartDashboard.putNumber("TargetPose", answer);
    return answer;
  }

  public void goforwards() {
    leftTalon().set(ControlMode.PercentOutput, autospeedfactor);
    rightTalon().set(ControlMode.PercentOutput, autospeedfactor);
  }

  public void gobackwards() {
    leftTalon().set(ControlMode.PercentOutput, -autospeedfactor);
    rightTalon().set(ControlMode.PercentOutput, -autospeedfactor);
  }

  public void resetEncoders(){
    robotLeftTalon.setSelectedSensorPosition(0);
    robotRightTalon.setSelectedSensorPosition(0);
  }

  public void writeEncoderValues() {
    SmartDashboard.putNumber("Encoder Right Value", rightTalon().getSelectedSensorPosition());
    SmartDashboard.putNumber("Encoder Left Value", leftTalon().getSelectedSensorPosition());
  }

  public void endPathFollower() {
    pathFollowerDone = true;
    stopDrive();
  }

  public boolean isPathFollowerDone() {
    return pathFollowerDone;
    //return true;
  }
}
