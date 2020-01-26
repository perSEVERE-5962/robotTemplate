/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//NO PID in HANG
package frc.robot.subsystems;

//is below import is right and it need here or not?
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hang extends SubsystemBase {
  //also WPI_TalonSRX I am not sure. SO I just put.
  private static WPI_TalonSRX handTalon;

  /**
   * Creates a new Hang.
   */

  //below 2 private need to check because of warning and saying value not used.
  private boolean isHandUp = false;
  private double result;

  //below method is just to return private handTalon. SO please check that out.
  public static WPI_TalonSRX handT(){
    return handTalon;
  } 

  //below method is just to return a number because motor has to be ther. SO we don't know the motor number right now.
    public Hang() {
    handTalon = new WPI_TalonSRX(12);
  }

  //NEW CODE TO FIND TICKS BY CIRCUM. I MADE to find circumference to make Hang.
  public double findTicks(double measureCircumference) {
      double result = measureCircumference/4096;
      return result;
  }

 

  public void moveHandUp(){
    //private from line 21 and from false now it's true
    isHandUp = true;
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
