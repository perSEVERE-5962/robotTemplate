/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

public class driveOriginal extends Command {
  public driveOriginal() {
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
    RobotMap.robotLeftVictor.follow(RobotMap.robotLeftTalon);
    RobotMap.robotRightVictor.follow(RobotMap.robotRightTalon);
    RobotMap.robotRightTalon.setInverted(true);
    RobotMap.robotLeftTalon.setInverted(false);
    RobotMap.robotRightVictor.setInverted(true);//false
    RobotMap.robotLeftVictor.setInverted(false);//false
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
