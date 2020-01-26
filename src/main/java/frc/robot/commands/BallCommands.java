/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class BallCommands extends CommandBase {

  Intake subsytem;
  
  public boolean RunIntake() {
    if (Robot.m_robotContainer.getIntake() >= 0.1)
    return true;
    else
      return false;
  }

  public boolean RunOuttake(){
    if (Robot.m_robotContainer.getOuttake() <= -0.1)
    return true;
    else 
      return false;
  }
  
  
  /**
   * Creates a new BallCommands.
   */
  public BallCommands() {
    // Use addRequirements() here to declare subsystem dependencies.
    subsytem = new Intake();
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RunIntake() == true)
      subsytem.SpinPMotor();

    else if (RunOuttake() == true)
      subsytem.SpinNMotor();

    else 
      subsytem.stop();
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
