/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.sensors.*;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class Autonomous extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //public RobotGyro gyro = new RobotGyro();
  public double dist = 0;
  public boolean Step1_done = false;
  public boolean Step2_done = false;
  public boolean Step3_done = false;
  public boolean Step4_done = false;
  public boolean Step5_done = false;
  public boolean Step6_done = false;
  public boolean Step7_done = false;
  public boolean Step8_done = false;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void initialize() {
    Step1_done=false;
  }  

  public void Step1(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=48){
      Step1_done = true;
      Robot.magEncoder.reset();
    }
  }
  public void Step2(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=59){
      Step2_done = true;
      Robot.magEncoder.reset();
    }
  }
  public void Step3(){
    if(Robot.gyro.getAngleX()>=getTurn()){//Should turn 67.29 degrees towards right
      Robot.gyro.reset();;
    }
  }
  public void Step4(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=124.61){
      Step4_done = true;
      Robot.magEncoder.reset();
    }
  }
  public void Step5(){
    if(Robot.gyro.getAngleX()>=getTurn()){//Should turn 67.29 degrees towards left
      Robot.gyro.reset();
    }       
  }
  public void Step6(){// Should it stop some distance before the cargo ship if yes, how much?
    dist = Robot.magEncoder.getDistance();
    if(dist>=48.06){
      Step6_done = true;
      Robot.magEncoder.reset();
    }
  }
  public void Step7(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=21.75){
      Step7_done = true;
      Robot.magEncoder.reset();
    }
  }

  private double getTurn(){    
    return 90;
  }
  private double getUltrasonicDist(){
    return 0;
  }
}

