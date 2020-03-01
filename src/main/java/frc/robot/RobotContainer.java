/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.CameraLight;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Winch;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.sensors.PIDControl;

import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick driverController = new Joystick(0);
  private final Joystick copilotController = new Joystick(1);
  // private final JoystickButton joyButton = new
  // JoystickButton(copilotController, 5);
  // private final JoystickButton joyButton6 = new
  // JoystickButton(copilotController, 6);

  // arm buttons
  private final JoystickButton buttonA = new JoystickButton(copilotController, 1);
  private final JoystickButton buttonB = new JoystickButton(copilotController, 2);
  private final JoystickButton button8 = new JoystickButton(copilotController, 8);
  private final JoystickButton button7 = new JoystickButton(copilotController, 7);

  // camera led buttons
  private final JoystickButton buttonY = new JoystickButton(copilotController, 3);
  private final JoystickButton buttonX = new JoystickButton(copilotController, 4);

  private SendableChooser driveChooser = new SendableChooser<Command>();

  // The robot's subsystems and commands are defined here...
  private final Drive driveSubsystem = new Drive();
  private final Winch winchSubsystem = new Winch();
  private final AutoCommand autoCommand = new AutoCommand(driveSubsystem);
  private final CameraLight cameraLight = new CameraLight();
  private final Elevator elevatorsubsystem = new Elevator();
  private final Arm arm = new Arm();
  // private final RunTankDrive driveCommand = new RunTankDrive(driveSubsystem);

  // private final WinchUp winchUp = new WinchUp(winchSubsystem);

  private NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private NetworkTable table;
  private NetworkTableEntry myEntry;
  private AHRS ahrs = new AHRS(SPI.Port.kMXP);
  private final PIDControl pidControl = new PIDControl();
  private final PathFollow followPath = new PathFollow(driveSubsystem, pidControl, ahrs);

  // private final ColorSensor colorSensor = new ColorSensor();
  // private final ControlPanel controlPanel = new ControlPanel(colorSensor);
  // private final SenseColor senseColorCommand = new SenseColor(colorSensor);
  // private final SpinToColor spinColorCommand = new SpinToColor(controlPanel);
  // private final SpinRotations spinRotCommand = new SpinRotations(controlPanel);
  private final InchForward inchForward = new InchForward(driveSubsystem);
  private Command driveCommand;
  private final RunIntake runIntake = new RunIntake();
  private final Shoot shoot = new Shoot();
  private final TurnOnLight lightOn = new TurnOnLight(cameraLight);
  private final TurnOffLight lightOff = new TurnOffLight(cameraLight);
  private final MoveArmVision armVision = new MoveArmVision();
  private final ElevatorUp elevatorUp = new ElevatorUp(elevatorsubsystem);
  private final ElevatorDown elevatorDown = new ElevatorDown(elevatorsubsystem);
  private final GetCamera cameraCommand = new GetCamera();
  // private final CPSubsystem cpSubsystem = new CPSubsystem();

  private DriveLeft left = new DriveLeft(driveSubsystem);
  private DriveRight right = new DriveRight(driveSubsystem);
  private StopDrive stop = new StopDrive(driveSubsystem);
  private DriveForward goForward = new DriveForward(driveSubsystem);
  private DriveBackwards goBackwards = new DriveBackwards(driveSubsystem);
  private StopArm stopArm = new StopArm();
  // private WinchUp winchUp = new WinchUp();

  public double getIntake() {
    double axisValue = copilotController.getRawAxis(1);
    return axisValue;
  }

  public double getElevatorUpAxis() {
    double axisValue = copilotController.getRawAxis(3);
    return axisValue;
  }

  public double getElevatorDownAxis() {
    double axisValue = copilotController.getRawAxis(2);
    return axisValue;
  }

  public Command getTurnOnLight() {
    return lightOn;
  }

  public Command getTurnOffLight() {
    return lightOff;
  }

  public Command getArmVision() {
    return armVision;
  }

  public Command getInchForward() {
    return inchForward;
  }

  public Command getRunIntake() {
    return runIntake;
  }

  public Command getCamera() {
    return cameraCommand;

  }

  public Command getShoot() {
    return shoot;
  }

  public Command getElevatorUp() {
    return elevatorUp;
  }

  public Command getElevatorDown() {
    return elevatorDown;
  }

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    driveChooser.setDefaultOption("JamieDrive", new RunJamieDrive(driveSubsystem));
    driveChooser.addOption("smooth tankdrive", new SmoothTankDrive(driveSubsystem));
    driveChooser.addOption("smooth arcadedrive", new SmoothArcadeDrive(driveSubsystem));
    driveChooser.addOption("tankdrive", new RunTankDrive(driveSubsystem));
    driveChooser.addOption("arcadedrive", new ArcadeDrive(driveSubsystem));
    SmartDashboard.putData("drivercontrol", driveChooser);
    SmartDashboard.putBoolean("Use PathFollower", true);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    buttonA.whenPressed(new MoveArmToIntake(arm));
    buttonB.whenPressed(new MoveArmToShoot(arm));
    buttonX.whenPressed(new TurnOnLight(cameraLight));
    buttonY.whenPressed(new TurnOffLight(cameraLight));
    // button8.whenPressed(new ResetArm());
    button7.whileHeld(new WinchUp(winchSubsystem));
  }

  public void moveArmToShoot() {
    Command move = new MoveArmToShoot(arm);
    if (move != null) {
      move.schedule();
    }
  }

  public double getArmPosition() {
    return arm.getEncoderValues();
  }

  public String getVisionAction() {
    table = inst.getTable("Vision");
    myEntry = table.getEntry("Action");
    return myEntry.getString("None");
  }
  public boolean getTargetfound() {
    table = inst.getTable("Vision");
    myEntry = table.getEntry("Target Found");
    return myEntry.getBoolean(false);
  }

  public double getLeftUltrasonic() {
    table = inst.getTable("HC-SR04");
    myEntry = table.getEntry("Left Distance");
    double value = myEntry.getDouble(0);
    if (value > 40) {
      // value = 0.0;
    }
    return value;
  }

  public double getRightUltrasonic() {
    table = inst.getTable("HC-SR04");
    myEntry = table.getEntry("Right Distance");
    double value = myEntry.getDouble(0);
    if (value > 40) {
      // value = 0.0;
    }
    return value;
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
    driveCommand = (Command) driveChooser.getSelected();
    return driveCommand;
  }

  public Command getTurnLeftCommand() {
    return left;
  }

  public DriveRight getTurnRightCommand() {
    return right;
  }

  public Joystick getDriverJoystick() {
    return driverController;
  }

  public Joystick getCopilotJoystick() {
    return copilotController;
  }

  public Command getFollowPath() {
    return followPath;
  }

  public Command getgoForward() {
    return goForward;
  }

  public Command getgoBackwards() {
    return goBackwards;
  }

  public Command getStopArm() {
    return stopArm;
  }

  // public Command getWinchUp() {
  // return winchUp;
  // }

  // public Command getSenseColorCommand() {
  // return senseColorCommand;
  // }

  // public Command getSpinColorCommand() {
  // return spinColorCommand;
  // }

  // public Command getSpinRotCommand() {
  // return spinRotCommand;
  // }

  public Command stopdrive() {
    return stop;
  }

  public Command driveLeft() {
    return null;
  }

  public Command driveRight() {
    return null;
  }

  public Drive getDrive() {
    return driveSubsystem;
  }

  public void resetGyro() {
    ahrs.reset();
  }

  public double getGyroAngle() {
    return ahrs.getAngle();
  }

  private String getCurrentTime() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd@HH-mm-ss", Locale.US);
    return dateFormat.format(new Date());
  }

  public void putMessage(String message) {
    String logString = getCurrentTime() + " " + message + System.lineSeparator();
    System.out.println(logString);
  }

  public boolean isUsingPathFollower() {
    return SmartDashboard.getBoolean("Use PathFollower", true);
  }
}