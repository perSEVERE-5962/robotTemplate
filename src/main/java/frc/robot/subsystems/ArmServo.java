/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmServo extends SubsystemBase {
  private Servo servoPWM; 
  /**
   * Creates a new Servo.
   */
  public ArmServo() {
    servoPWM = new Servo(8);
    servoPWM.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    servoPWM.setSpeed(-1.0);
  }

  public void negativePosition(){
    servoPWM.setPosition(-0.3);
    //servoPWM.setSpeed(-1.0); //to close
  }

  public void positivePosition(){
    servoPWM.setPosition(0.3);
    //servoPWM.setSpeed(1.0); //to open
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}