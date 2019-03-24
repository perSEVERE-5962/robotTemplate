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

/**
 * Add your docs here.
 */
public class rocketAutonomous extends Subsystem {
  private RemoteHCSR04 uValue = new RemoteHCSR04();

  public double targetPos;
  public double angle;
  public double range;
  public static boolean crossingStarted = false;
  public static boolean crossingInProgress = false;
  public static boolean isCrossed = false;
  public static boolean turningStarted = false;
  public static boolean isTurned = false;
  public static boolean movingStarted = false;
  public static boolean movingInProgress = false;
  public static boolean isMoved = false;
  final double diameter = 3.0;//7.5 test bot
  final double circumferance = Math.PI*diameter;
  final double ticksPerRotation = 4096;
  public boolean onTarget(){
    // Robot.logger.putMessage("Left Closed Loop Error = " + RobotMap.robotLeftTalon.getClosedLoopError());
    // Robot.logger.putMessage("Right Closed Loop Error = " + RobotMap.robotRightTalon.getClosedLoopError());
   
    if(Math.abs(RobotMap.robotLeftTalon.getClosedLoopError())<100 && 
            Math.abs(RobotMap.robotRightTalon.getClosedLoopError())<100){
        return true;
    }
    return false;
}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void crossTheHabLine(){
    targetPos = (228.28/circumferance)*ticksPerRotation;

    if(crossingStarted == false){
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      crossingStarted = true;
    }
    else if(crossingStarted == true && crossingInProgress == false){
      RobotMap.robotLeftTalon.set(ControlMode.Position,targetPos);
      RobotMap.robotRightTalon.set(ControlMode.Position, targetPos);
      crossingInProgress = true;
    }
    else if(onTarget() && crossingInProgress == true && isCrossed == false){
      Robot.logger.putMessage("Crossed the hab line");
      isCrossed = true;
    }

  } 
  public void turn90(){
    angle = Robot.gyro.getGyroAngle();
    if(Robot.getIsRight() == true && angle>= 90){
      Robot.logger.putMessage("Stopping the drive");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      isTurned = true;
    }
    else if(Robot.getIsLeft() == true && angle<=-90){
      Robot.logger.putMessage("Stopping the drive");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      isTurned = true;
    }
    else{
     if(Robot.getIsRight() == true){ 
      Robot.logger.putNumber("Turning Right", Robot.gyro.getGyroAngle());
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0.25);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, -0.25); 
      
    }
    else if(Robot.getIsLeft() == true){ 
      Robot.logger.putNumber("Turning Left", Robot.gyro.getGyroAngle());
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, -0.25);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0.25); 
      
    }
  }


  } 
  public void goToTheRocket(){
    range = uValue.getLeftRange();
    if(movingStarted == false){
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput , 0.5);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput , 0.5);
      movingStarted = true;
    }
    else if(movingStarted == true && isMoved == false && range<=20){
      Robot.logger.putMessage("At the rocket");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
      isMoved = true;
      // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      // RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      // RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
    }

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
