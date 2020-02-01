/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DriverStation;

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */
  private static WPI_VictorSPX motorControl;
  
  private final ColorSensor colorSensor;
  //corresponding colors
  private final String[] cColors = {"Blue, Red", "Yellow, Green"};

  public void setTargetColor(){
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
  
    if(gameData.length() > 0){
      switch (gameData.charAt(0)){
        case 'B' :
          Constants.SPIN_COLOR = "Blue";
          break;
        case 'G' :
          Constants.SPIN_COLOR = "Green";
          break;
        case 'R' :
          Constants.SPIN_COLOR = "Red";
          break;
        case 'Y' :
          Constants.SPIN_COLOR = "Yellow";
          break;
        default :
          Constants.SPIN_COLOR = "BAD DATA";
      }
    }
  }


  public String getCorrespondingColor(String targetColor){
    //cColor is short for correspondingColor
    for (int i = 0; i < cColors.length; i++){
      String[] cArray = cColors[i].split(", ");
      if (cArray[0].equals(targetColor)){
        return cArray[1];
      } else if (cArray[1].equals(targetColor)){
        return cArray[0];
      }
    }
    return "Confidence Low";
  }

  public void spinToColor(){
    String targetColor = Constants.SPIN_COLOR;
    String currentColor = colorSensor.getColor();
    String correspondingColor = getCorrespondingColor(currentColor);

    if(!correspondingColor.equals(targetColor)){
      SmartDashboard.putString("CPSystem", "Spinning (" + targetColor + ")...");
      motorControl.set(0.5);
    } else {
      SmartDashboard.putString("CPSystem", "Match (" + currentColor + " = " + targetColor + ")");
      motorControl.set(0);
      Constants.IS_SPIN_COMPLETE = true;
    }
  }

  private int currentHalfRot = 0;
  private int currentRot = 0;
  private boolean lastColorChanged = false;
  private String startColor = "";

  public void resetRotations(){
    motorControl.set(0);
    currentRot = 0;
    currentHalfRot = 0;
    startColor = "";
    lastColorChanged = false;
  }

  public void spinRotations(){
    if (startColor.equals("")){
      resetRotations();
      startColor = colorSensor.getColor();
    }

    if (!startColor.equals("Confidence Low")){
      if(currentRot < Constants.MAX_COLOR_ROTATIONS){
        SmartDashboard.putString("CPSystem", "Spinning (" + currentRot + ")...");
        motorControl.set(0.5);
        String currentColor = colorSensor.getColor();
        if (!currentColor.equals(startColor)){
          lastColorChanged = true;
        }
        if (lastColorChanged && currentColor.equals(startColor)){
          lastColorChanged = false;
          currentHalfRot ++;
          currentRot = currentHalfRot / 2;
        }
      } else {
        SmartDashboard.putString("CPSystem", "Finished (" + currentRot + ")");
        Constants.IS_SPIN_COMPLETE = true;
        resetRotations();
      }
    } else {
      SmartDashboard.putString("CPSystem", "No Color");
      resetRotations();
    }
  }

  public ControlPanel(ColorSensor colorSensor) {
    motorControl = new WPI_VictorSPX(19);
    this.colorSensor = colorSensor;
    setTargetColor();
  }



  @Override
  public void periodic() {
    SmartDashboard.putString("Color", colorSensor.getColor());
  }
}
