/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
public class Intake extends SubsystemBase {

  /**
   * Creates a new Intake.
   */
  
  
public void SpinPMotor(){
  Robot.m_robotContainer.getDrive().getVictor().set(0.1);

}

public void SpinNMotor(){
  Robot.m_robotContainer.getDrive().getVictor().set(-0.1);
}

public void stop(){
  Robot.m_robotContainer.getDrive().getVictor().set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
