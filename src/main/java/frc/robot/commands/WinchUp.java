/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Winch;
import edu.wpi.first.wpilibj.Joystick;

public class WinchUp extends CommandBase {

public Joystick joystick = new Joystick(1);

  Winch subsystem;

  //Winch Up button is 7 right now.

  /**
   * Creates a new WinchUp.
   */
  public WinchUp(Winch subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.moveWinch(0.5);
    // subsystem.moveWinch(Math.abs(055*joystick.getRawAxis(5)));
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.moveWinch(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
