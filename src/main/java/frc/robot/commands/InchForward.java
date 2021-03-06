/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class InchForward extends CommandBase {
  private final Drive subsystem;
private double speed = 0.2;
private double leftUltrasonic = 0;
private double rightultrasonic = 0;
  public InchForward(Drive subsystem) {
   this.subsystem = subsystem;
   // Use addRequirements() here to declare subsystem dependencies.
   addRequirements(subsystem);
 }

 // Called when the command is initially scheduled.
 @Override
 public void initialize() {
 }

/**
 * @param leftUltrasonic the leftUltrasonic to set
 */
public void setLeftUltrasonic(double leftUltrasonic) {
  this.leftUltrasonic = leftUltrasonic;
}

/**
 * @param rightultrasonic the rightultrasonic to set
 */
public void setRightultrasonic(double rightultrasonic) {
  this.rightultrasonic = rightultrasonic;
}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.inchforward(speed, leftUltrasonic, rightultrasonic);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
  public void setspeed(double speed){
    this.speed = speed;
  }
}
