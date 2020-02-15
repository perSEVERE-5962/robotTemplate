/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class MoveArmVision extends CommandBase {

  Arm subsystem;
  private final double visionAngle = 38.0;
  
  
  /**
   * Creates a new MoveArmCommand.
   */
  public MoveArmVision() {
    // Use addRequirements() here to declare subsystem dependencies.
    subsystem = new Arm();
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // public double getEncoderValues(){
  //   return subsystem.getEncoderValues();
  // }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  subsystem.visionPosition(visionAngle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double encoderValue = subsystem.getEncoderValues();
    // done if encoder is between 35 and 41
    return ( encoderValue >= (visionAngle-5) && encoderValue <= (visionAngle+5) );
  }
}