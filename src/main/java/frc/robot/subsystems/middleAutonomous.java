/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class middleAutonomous extends Subsystem {
  private RemoteHCSR04 uValue = new RemoteHCSR04();

  public double targetPos;
  public double speed;
  public boolean crossingStarted = false;
  public boolean crossingInProgress = false;
  public boolean isCrossed = false;
  public boolean turningStarted = false;
  public boolean turningInProgress = false;
  public boolean isTurned = false;
  public boolean movingStarted = false;
  public boolean movingInProgress = false;
  public boolean isMoved = false;
  public boolean armStarted = false;
  public boolean armInProgress = false;
  public boolean isOnPos = false;
  public boolean Step7_Started = false;
  public boolean Step7_inProgress = false;
  public boolean Step7_done = false;
  public boolean Step8_Started = false;
  public boolean Step8_inProgress = false;
  public boolean Step8_done = false;
  final double diameter = 3.0;//7.5 test bot
  final double circumferance = Math.PI*diameter;
  final double ticksPerRotation = 4096;
  public boolean onTarget(){
    Robot.logger.putMessage("Left Closed Loop Error = " + RobotMap.robotLeftTalon.getClosedLoopError());
    Robot.logger.putMessage("Right Closed Loop Error = " + RobotMap.robotRightTalon.getClosedLoopError());
   
    if(Math.abs(RobotMap.robotLeftTalon.getClosedLoopError())<100 && 
            Math.abs(RobotMap.robotRightTalon.getClosedLoopError())<100){
        return true;
    }
    return false;
}
  public void crossTheHabLine(){
    targetPos = (107/circumferance)*ticksPerRotation;
    if(crossingStarted == false){
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      crossingStarted = true;
    }
    else if(crossingStarted == true && crossingInProgress == false){
      RobotMap.robotLeftTalon.set(ControlMode.Position, targetPos);
      RobotMap.robotRightTalon.set(ControlMode.Position, targetPos);
    }
    else if(onTarget() && crossingInProgress == true && isCrossed == false){
      isCrossed = true;
    }

  }
  public double angle;
  public void turnToTheShip(){
    angle = Robot.gyro.getGyroAngle();
    if(angle<=-10){
      Robot.logger.putMessage("Stopping the drive");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
    }
    else{
      Robot.logger.putNumber("Turning Left", Robot.gyro.getGyroAngle());
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, -speed);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, speed);

    }
  }
  public void goToTheShip(){
    double range = uValue.getRightRange();
    if(movingStarted == false){            
      // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      // RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      // RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);  
      movingStarted = true;           
  }
  else if(movingStarted == true && movingInProgress == false){
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput , 0.5);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput , 0.5);
      movingInProgress = true;
  }
  else if(range <=15 && movingInProgress == true && isMoved == false){
      Robot.logger.putMessage("Stopping the drive");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
      isMoved = true;            
  }    

  }
  public void placeHatch() {
    if (Step7_Started == false) {
        Robot.logger.putMessage("Starting Auto Step PlaceHatch");
        Step7_Started = true;
    } else if (Step7_Started == true && Step7_inProgress == false) {
        Robot.armMotor.moveToPlaceHatch();
        Step7_inProgress = true;
    } else if (Robot.armMotor.isOnTarget() && Step7_inProgress == true && Step7_done == false) {
        Robot.logger.putMessage("Auto Step PlaceHatch Finished - final position = " +  RobotMap.armTalon.getSelectedSensorPosition());
        // deploy the pneumatics
        Robot.solenoidSubsystem.deployHatch();
        Step7_done = true;
    } else {
    }
}

  public void moveArmToPlaceHatch(){

  }
  // public void placeHatch(){

  // }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
