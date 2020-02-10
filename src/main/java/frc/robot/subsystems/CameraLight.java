/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraLight extends SubsystemBase {
  /**
   * Creates a new CameraLight.
   */

  private Solenoid light;

  public CameraLight() {
    light = new Solenoid(3,4);
    
  }

  public void turnOnLight() {
    light.set(true);
  }

  public void turnOffLight() {
    light.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler ru%n
  }
}
