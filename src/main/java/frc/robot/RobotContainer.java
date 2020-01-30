/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.RunTankDrive;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick driverController = new Joystick(0);
  private final Joystick copilotController = new Joystick(1);

  // The robot's subsystems and commands are defined here...
  private final Drive driveSubsystem = new Drive(driverController);
  private final AutoCommand autoCommand = new AutoCommand(driveSubsystem);
  // private final RunTankDrive driveCommand = new RunTankDrive(driveSubsystem);
  private final ArcadeDrive driveCommand = new ArcadeDrive(driveSubsystem);

  private final ColorSensor colorSensor = new ColorSensor();
  private final ControlPanel controlPanel = new ControlPanel(colorSensor);

  private final SenseColor senseColorCommand = new SenseColor(colorSensor);
  private final SpinToColor spinColorCommand = new SpinToColor(controlPanel);
  private final SpinRotations spinRotCommand = new SpinRotations(controlPanel);
  

  //private final CPSubsystem cpSubsystem = new CPSubsystem();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }

  public Command getDriveCommand() {
    return driveCommand;
  }

  public Joystick getDriverJoystick() {
    return driverController;
  }

  public Joystick getCopilotJoystick() {
    return copilotController;
  }

  
  public Command getSenseColorCommand(){
    return senseColorCommand;
  }

  public Command getSpinColorCommand(){
    return spinColorCommand;
  }

  public Command getSpinRotCommand(){
    return spinRotCommand;
  }
  
  
}
