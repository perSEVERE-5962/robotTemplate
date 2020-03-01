/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class MoveArmToIntake extends CommandBase {

  Arm subsystem;
  private final double intakeAngle = 74.0;

  /**
   * Creates a new MoveArmCommand.
   */
 
  public MoveArmToIntake(Arm arm) {
    // Use addRequirements() here to declare subsystem dependencies.
    subsystem = arm;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  public double getEncoderValues(){
    return subsystem.getEncoderValues();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("down", "down");
    subsystem.intakePosition(intakeAngle);
  }

  // private boolean RunMoveArm() {
  //   return false;
  // }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double encoderValue = subsystem.getEncoderValues();
    // done if encoder is between 77 and 83
    return ( encoderValue >= (intakeAngle-2) && encoderValue <= (intakeAngle+2) );
    // return true;
  }
}


