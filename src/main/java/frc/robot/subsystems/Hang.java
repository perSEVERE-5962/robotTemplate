/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hang extends SubsystemBase {
  private WPI_TalonSRX hangtalon;
private final double measureCircumfrence = 6; // 6 inches
private double startingposition = 0;
  /**
   * Creates a new Hang.
   */

  public Hang() {
    hangtalon = new WPI_TalonSRX(12);
    hangtalon.setSelectedSensorPosition(0, 0, 0);
   // hangtalon.setInverted(true);
    startingposition = hangtalon.getSelectedSensorPosition();
    SmartDashboard.putNumber("Starting Position", startingposition);



  }

  private double findspaceperTick() {
     double result = 4096 / measureCircumfrence; // The amount of ticks per inch (findspacepertick)
    return result;
  }
 
  private double inchestoTicks(int inchesmoved){ 
    double result = inchesmoved * findspaceperTick(); // distance you want to move (inchesmoved) multiplied by the numbers of ticks per inch (findspacepertick) = inches to ticks
    return result;


  }

  public void hangposition(){
    SmartDashboard.putNumber("Hang position", hangtalon.getSelectedSensorPosition());
    hangtalon.set(ControlMode.Position,  inchestoTicks(12) + startingposition);
  }
  public void climbposition(){
    SmartDashboard.putNumber("Climb position", hangtalon.getSelectedSensorPosition());
    hangtalon.set(ControlMode.Position, inchestoTicks(-12) + startingposition);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
