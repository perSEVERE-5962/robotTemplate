/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Autonomous;
import frc.robot.subsystems.rocketAutonomous;

public class runRocketAutonomous extends Command {
  private rocketAutonomous auto = new rocketAutonomous();
  public boolean isFinished = false;
  public runRocketAutonomous() {

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(auto.isCrossed == false){
      auto.crossTheHabLine();
    }
    else if(auto.isCrossed == true && auto.isTurned == false){
      auto.turn90();
    }
    else if(auto.isTurned == true && auto.placeHatch_done == false){
      auto.placeHatch();
    }
    else if(auto.placeHatch_done == true && auto.isMoved ==false){
      auto.goToTheRocket();
    }
    else if (auto.isMoved == true && auto.deployHatch == false){
      auto.deployHatch();
    }
    else if(auto.deployHatch == true && auto.backup_done == false){
      auto.backup();
    }
    else{
      isFinished = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
