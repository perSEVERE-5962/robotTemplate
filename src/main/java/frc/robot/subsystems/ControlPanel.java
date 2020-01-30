/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */

  private final ColorSensor colorSensor;
  //corresponding colors
  private final String[] cColors = {"Blue, Red", "Yellow, Green"};
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

  public void spinToColor(String targetColor){
    String currentColor = colorSensor.getColor();
    String correspondingColor = getCorrespondingColor(currentColor);

    if(!correspondingColor.equals(targetColor)){
      SmartDashboard.putString("CPSystem", "Spinning to (" + targetColor + ")...");
    } else {
      SmartDashboard.putString("CPSystem", "Match (" + targetColor + ")");
    }
  }

  private int currentHalfRot = 0;
  private int currentRot = 0;
  private boolean lastColorChanged = false;
  private String startColor = "";

  public void resetRotations(){
    currentRot = 0;
    currentHalfRot = 0;
    startColor = "";
    lastColorChanged = false;
  }

  public void spinRotations(int targetRot){
    if (startColor.equals("")){
      resetRotations();
      startColor = colorSensor.getColor();
    }

    if (!startColor.equals("Confidence Low")){
      if(currentRot < targetRot){
        SmartDashboard.putString("CPSystem", "Spinning (" + currentRot + ")...");
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
        SmartDashboard.putString("CPSystem", "Finished rotating (" + currentRot + ")");
        resetRotations();
      }
    } else {
      SmartDashboard.putString("CPSystem", "No Color");
      resetRotations();
    }
  }

  public ControlPanel(ColorSensor colorSensor) {
    this.colorSensor = colorSensor;
  }



  @Override
  public void periodic() {
    SmartDashboard.putString("Color", colorSensor.getColor());
  }
}
