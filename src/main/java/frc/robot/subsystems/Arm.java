/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  private WPI_TalonSRX armTalon;
  public WPI_TalonSRX armTal(){
    return armTalon;
  }
  /**
   * Creates a new moveUp.
   */
  private double measureAngle;
  

  public Arm() {
    armTalon = new WPI_TalonSRX(12);
     //armTalon.setSensorPhase(true);
    armTalon.setSelectedSensorPosition(0);
    // armTalon.set(ControlMode.PercentOutput, 0);
  }
  public void stop() {
    armTalon.set(ControlMode.PercentOutput, 0);
  }

public double setAngle(double angle){
  measureAngle = 4096/360;
  double finalAngle = measureAngle*angle;
  return finalAngle;
}

public void shootingPosition(){
  armTalon.set(ControlMode.Position, setAngle(10.0));
  SmartDashboard.putNumber("Absolute", armTalon.getSelectedSensorPosition());
}
public void visionPosition(){
  armTalon.set(ControlMode.Position, setAngle(38.0));
  SmartDashboard.putNumber("Absolute", armTalon.getSelectedSensorPosition());
}
public void intakePosition(){
  armTalon.set(ControlMode.Position, setAngle(80.0));
  SmartDashboard.putNumber("Absolute", armTalon.getSelectedSensorPosition());
}

public double getEncoderValues(){
  return armTalon.getSelectedSensorPosition();
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
