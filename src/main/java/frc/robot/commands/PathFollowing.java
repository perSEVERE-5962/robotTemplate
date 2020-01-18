/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import java.io.File;
import java.io.IOException;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.subsystems.Drive;
import frc.robot.sensors.PIDControl;

public class PathFollowing extends CommandBase {
  /**
   * Creates a new PathFollowing.
   */
  public int intialPosition = 0;
  public int ticksPerRotation = 4096;
  public double wheelDiameter = 0.1524;
  public EncoderFollower leftFollower;
  public EncoderFollower rightFollower;
  private Drive drive;
  private PIDControl configTalon;
  final double kP = 0.0057;
  final double kI = 0;
  final double kD = 0;
  final double kV = 0.0441;
  final double kA = 0.0151;

  public PathFollowing(Drive drive, PIDControl configTalon){
    addRequirements(drive);
    try{
    //need to implement PIDControl, Encoders??
    leftFollower = follower(Pathfinder.readFromCSV(new File("home/lvuser/deploy/Autonmous20_left")));
    //add left location
    rightFollower = follower(Pathfinder.readFromCSV(new File("home/lvuser/deploy/Autonmous20_right")));   
    }catch(IOException ex){}
    this.drive = drive;
    this.configTalon = configTalon;
    //add right location
    // Use addRequirements() here to declare subsystem dependencies.

    
  }
  
  public EncoderFollower follower(Trajectory trajec) {
    EncoderFollower encFollower = new EncoderFollower(trajec);
    encFollower.configureEncoder(intialPosition, ticksPerRotation, wheelDiameter);
    encFollower.configurePIDVA(kP, kI, kD, kV, kA);
    return encFollower;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Drive.rightTalon().set(ControlMode.PercentOutput, rightFollower.calculate(Drive.rightTalon().getSelectedSensorPosition()));
    Drive.leftTalon().set(ControlMode.PercentOutput, leftFollower.calculate(Drive.leftTalon().getSelectedSensorPosition()));
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
