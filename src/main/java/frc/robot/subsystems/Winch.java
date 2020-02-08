/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Winch extends SubsystemBase {
  private static WPI_VictorSPX motorControl;
  /**
   * Creates a new Winch.
   */
  public Winch() {
    motorControl = new WPI_VictorSPX(11);//13

  }

  public void moveWinch(double speed){
    motorControl.set(ControlMode.PercentOutput, speed);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
