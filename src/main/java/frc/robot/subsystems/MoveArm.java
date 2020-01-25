/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MoveArm extends SubsystemBase {
  private static WPI_TalonSRX armTalon;
  /**
   * Creates a new moveUp.
   */
  private boolean isPIDRunning = false;
  private int armPosition;
  private double measureAngle;
  public static WPI_TalonSRX armT(){
    return armTalon;
  }
  public MoveArm() {
    armTalon = new WPI_TalonSRX(12);
  }

public double setAngle(double angle){
  measureAngle = 4096/360;
  double finalAngle = measureAngle*angle;
  return finalAngle;
}

public void shootingPosition(){
  isPIDRunning = true;
  armTalon.set(ControlMode.Position, setAngle(70.0));
}

public void intakePosition(){
  isPIDRunning = true;
  armTalon.set(ControlMode.Position, setAngle(35.0));
}

public double getEncoderValues(){
  return armTalon.getSelectedSensorPosition();
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
