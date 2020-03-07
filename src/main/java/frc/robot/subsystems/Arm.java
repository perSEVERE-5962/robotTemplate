/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MoveArmToIntake;
import frc.robot.commands.ResetArm;

public class Arm extends SubsystemBase {
  private WPI_TalonSRX armTalon;
  //new private for Talon
  private WPI_TalonSRX winchTalon;
//  private final double shootAngle = 10.0;
//  private final double intakeAngle = 80.0;
//  private final double visionAngle = 38.0;

  //private DigitalInput limitSwitch;
  private double measureAngle;

  public Arm() {
    armTalon = new WPI_TalonSRX(12);
    //limitSwitch = new DigitalInput(9);
     //armTalon.setSensorPhase(true);
    armTalon.setSelectedSensorPosition(0);
  
    //NEW CODE for Tachometer.
    // armTalon.configForwardSoftLimitEnable(true, 0);
    // armTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    // armTalon.set(ControlMode.PercentOutput, 0);

    // armTalon.configSelectedFeedbackSensor(FeedbackDevice.Tachometer, 0, 30);

  }
  public void stop() {
    armTalon.set(ControlMode.PercentOutput, 0);
  }

  //make a method isEnabaled and if its enable then arm is down.
  //retrurn true if its down and if its not then return false.

 

// //NEW METHOD TO SET RESET AT 80.
// public boolean isArmDown(){
//   if(readencoder = encoderposition == true)
//     return isArmDown.set(encoder = 80);

//   else 
//     return false
// }

public double setAngle(double angle){
  measureAngle = 4096/360;
  double finalAngle = measureAngle*angle;
  return finalAngle;
}

public void shootingPosition(double shootAngle){
  armTalon.set(ControlMode.Position, setAngle(shootAngle));
  SmartDashboard.putNumber("Absolute", armTalon.getSelectedSensorPosition());
}
public void visionPosition(double visionAngle){
  armTalon.set(ControlMode.Position, setAngle(visionAngle));
  SmartDashboard.putNumber("Absolute", armTalon.getSelectedSensorPosition());
}
public void intakePosition(double intakeAngle){
  armTalon.set(ControlMode.Position, setAngle(intakeAngle));
  SmartDashboard.putNumber("Absolute", armTalon.getSelectedSensorPosition());
  

  
}

public void enabledLimitSwitch(){
  armTalon.configForwardSoftLimitEnable(true, 0);
  armTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
}

public void disabledLimitSwitch(){
  armTalon.configForwardSoftLimitEnable(false, 0);
}

//NEW resetPosition
public void resetPosition(){
  armTalon.set(ControlMode.Position, setAngle(79.0));
}


public double getEncoderValues(){
  return armTalon.getSelectedSensorPosition();
}

// //NEW METHOD TO SET RESET AT 80.
// public boolean isArmDown(){
//   if(readencoder = encoderposition == true)
//     return isArmDown.set(encoder = 80);

//   else 
//     return false
// }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
