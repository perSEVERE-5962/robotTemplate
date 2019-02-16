/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;
public class inRobot extends Command {
  public boolean onTarget(){
    if(Math.abs(RobotMap.armTalon.getClosedLoopError())<100){
        return true;
    }
    return false;
}
  public inRobot() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  public void angleInRobot(){
    RobotMap.armTalon.set(ControlMode.Position, 0);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    angleInRobot();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override

  protected boolean isFinished() {
    if(onTarget()){
      return true;
   }
   else{
     return false;
   }  
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
