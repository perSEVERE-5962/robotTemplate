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
public class ScorePowerCells extends CommandBase {
  /**
   * Creates a new ScorePowerCells.
   */
  private double angle;
  private double currentAngle;
  private Drive drive;

  public ScorePowerCells(Drive drive) {
    this.drive = drive;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    angle = drive.getGyroAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("auto step", "scorepowercells");

    currentAngle = drive.getGyroAngle();
    if(Math.abs(angle - currentAngle) < 3){
      drive.goforwards(false);
    }
    else if(angle < currentAngle){
      drive.driveLeft();
    }
    else if(angle > currentAngle){
      drive.driveRight();
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return drive.getLeftUltrasonic() < 15 || drive.getRightUltrasonic() < 15;
  }
}
