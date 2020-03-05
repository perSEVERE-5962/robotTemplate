/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class FindTarget extends CommandBase {
  /**
   * Creates a new FindTarget.
   */
  private Drive drive;
  private double angle = 9000;
  private double currentAngle;

  private boolean isVisionLost = false;

  public FindTarget(Drive drive) {
    this.drive = drive;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (drive.getVisionAction().equals("None")) {
      angle = drive.getGyroAngle();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentAngle = drive.getGyroAngle();
    if(drive.getVisionAction().equals("None")){
      drive.inchforward(0.3, 10, 10);
    }
    else if(drive.getVisionAction().equals("Left") && (Math.abs(angle - currentAngle) < 3) && isVisionLost == false){
      isVisionLost = true;
    }
    else if(drive.getVisionAction().equals("Left") && (Math.abs(angle - currentAngle) > 3) && isVisionLost == false){
      drive.driveLeft();
    }
    else if(drive.getVisionAction().equals("Right") && (Math.abs(angle - currentAngle) < 3) && isVisionLost == false){
      isVisionLost = true;
    }
    else if(drive.getVisionAction().equals("Right") && (Math.abs(angle - currentAngle) > 3) && isVisionLost == false){
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
    return isVisionLost = true;
  }
}
