/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class Squaretopowerport extends CommandBase {
  double leftultrasonic;
  double rightultrasonic;
  private final Drive subsystem;

  /**
   * Creates a new Squaretopowerport.
   */
  public Squaretopowerport(Drive subsystem) {
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
    SmartDashboard.putString("auto step", "squaretopowerport");

    rightultrasonic = subsystem.getRightUltrasonic();
    leftultrasonic = subsystem.getLeftUltrasonic();
    boolean halfSpeed = false;
    if (/* leftultrasonic <= 30 || */rightultrasonic <= 60) {
      halfSpeed = true;
    }
    if (/* leftultrasonic >= 10 || */rightultrasonic >= 10) {

      subsystem.goforwards(halfSpeed);
    }

    // if (rightultrasonic > leftultrasonic &&(leftultrasonic <=30 ||
    // rightultrasonic <=30)) {
    // subsystem.driveLeft();
    // } else if (rightultrasonic < leftultrasonic && (leftultrasonic <=30||
    // rightultrasonic <=30)) {
    // subsystem.driveRight();
    // } else {
    // subsystem.goforwards();
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (/* leftultrasonic <= 10|| */rightultrasonic <= 10);

  }
}
