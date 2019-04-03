package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class DefenseHold extends Command{
  public double leftPos;
  public double rightPos;
  public boolean check = false;
  public DefenseHold(){
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(check == false){
    leftPos = RobotMap.robotLeftTalon.getSelectedSensorPosition() + 81.9;
    rightPos = RobotMap.robotRightTalon.getSelectedSensorPosition() + 81.9;
    
    RobotMap.robotLeftTalon.set(ControlMode.Position, leftPos);
    RobotMap.robotRightTalon.set(ControlMode.Position , rightPos);
    check = true;
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    check = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
