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

public class FindTarget extends CommandBase {
  /**
   * Creates a new FindTarget.
   */
  private Drive drive;
  private double angle = 9000;
  private double currentAngle;

  private String visionAction;
  private int counter = 0;
  // private boolean isVisionLost = false;
  private boolean isTargetFound = false;

  public FindTarget(Drive drive) {
    this.drive = drive;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("auto step", "findtarget");
    //currentAngle = drive.getGyroAngle();
    visionAction = drive.getVisionAction();
    SmartDashboard.putString("findtarget",
        "angle = " + angle + " currentangle = " + currentAngle + "visionaction =" + visionAction);
    // isTargetFound = drive.getTargetFound();

    switch (visionAction) {
    case "None":
      //counter = 0;
      //angle = drive.getGyroAngle();
      isTargetFound = true;
      drive.goforwards(false);
      break;
    case "Left":
     // if (drive.getVisionArea() >200) {
      //   if (Math.abs(angle - currentAngle) > 5) {

      //     isTargetFound = true;
      //     drive.stopDrive();
      //   }
      // } else {
      //   counter++;
        drive.driveLeft();
     // }

      break;
    case "Right":
      // if (counter > 100) {
      //   if (Math.abs(angle - currentAngle) > 5) {
      //     isTargetFound = true;
      //     drive.stopDrive();
      //   }
      // } else {
      //   counter++;
        drive.driveRight();
      //}
      break;
    default:
      drive.goforwards(false);
      break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("findtarget",
        "angle = " + angle + " currentangle = " + currentAngle + "visionaction =" + visionAction);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return (drive.getVisionArea()<=200 && isTargetFound == true);
  }
}
