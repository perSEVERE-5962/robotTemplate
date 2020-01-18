/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.MoveArm;

public class MoveArmCommand extends CommandBase {

  MoveArm subsystem;
  // CHANGE DOWN BELOW
  public boolean RunArmDown() {
    if (Robot.m_robotContainer.armUp() == true)
    return true;
    else
      return false;
  }
  
  public boolean RunArmUp(){
    if (Robot.m_robotContainer.armDown() == true)
    return true;
    else 
      return false;
  }
  

  /**
   * Creates a new MoveArmCommand.
   */
  public MoveArmCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    subsystem = new MoveArm();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (RunArmDown() == true)
        subsystem.setAngle(35);
  
      else if (RunArmUp() == true)
        subsystem.setAngle(70);
  
      else 
        subsystem.setAngle(0);
    }

  private boolean RunMoveArm() {
    return false;
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}


