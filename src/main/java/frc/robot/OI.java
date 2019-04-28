package frc.robot;


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.subsystems.*;
import frc.robot.RobotMap;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

 
public class OI {
	public Joystick joystickLeft;
	public Joystick joystickRight;
	public Joystick driverController;
	public Joystick copilotController;
	public boolean isSolenoidZeroPressed = false;
	public boolean isSolenoidOnePressed = false;
	public JoystickButton defenseHold;
	public JoystickButton invertMotors;
	public JoystickButton driveOriginal;
	public JoystickButton deployHatchButton;
	public JoystickButton retractHatchButton;
	public JoystickButton greenLEDButton;
	public JoystickButton orangeLEDButton;
	public JoystickButton buttonSix;
	public JoystickButton placeHatch;
	public JoystickButton armOverride;
	public JoystickButton stopArmOff;
	public boolean isCamera1Active = false;
	public JoystickButton driverSpeedButton;
	public JoystickButton copilotSpeedButton;
	private int rumbleCount=0;
	public JoystickButton stopArmButton;

	DeployHatch deployHatch = new DeployHatch();
	RetractHatch retractHatch = new RetractHatch();
	// SwitchOnGreen switchOnGreen = new SwitchOnGreen();
	// SwitchOffGreen switchOffGreen = new SwitchOffGreen();
	// SwitchOnOrange switchOnOrange = new SwitchOnOrange();
	// SwitchOffOrange switchOffOrange = new SwitchOffOrange(); 

	DefenseHold holdIt = new DefenseHold();
	public void incrementRumbleCount() {
		++rumbleCount;
	}
	public void setRumbleCount(int value) {
		rumbleCount = value;
	}
	public int getRumbleCount() {
		return rumbleCount;
	}

	public OI() {
		joystickLeft = new Joystick(1);
		joystickRight = new Joystick(2);
		driverController = new Joystick(0);	// Driver
		copilotController = new Joystick(3); // Copilot
		driverSpeedButton = new JoystickButton(driverController, 5);	// right bumper
		driverSpeedButton.toggleWhenPressed(new DriverSpeedControl());
		deployHatchButton = new JoystickButton(copilotController, 6);
		retractHatchButton = new JoystickButton(copilotController, 5);
		deployHatchButton.whenPressed(deployHatch);
		retractHatchButton.whenPressed(retractHatch);
		copilotController.setRumble(RumbleType.kLeftRumble, 0);
		copilotSpeedButton = new JoystickButton(copilotController, 3);	// X 
		copilotSpeedButton.toggleWhenPressed(new ArmSpeedControl());
		invertMotors = new JoystickButton(driverController, 7);
		invertMotors.toggleWhenPressed(new invertMotors());
		driveOriginal = new JoystickButton(driverController, 8);
		driveOriginal.whenPressed(new driveOriginal());
		defenseHold = new JoystickButton(driverController, 6);
		defenseHold.whenPressed(holdIt);
		stopArmButton = new JoystickButton(copilotController, 1);
		stopArmButton.toggleWhenPressed(new StopArmCommand());	
		stopArmOff = new JoystickButton(copilotController, 2);
		stopArmOff.whenPressed(new StopArmOff());
		greenLEDButton = new JoystickButton(driverController, 1);
		orangeLEDButton = new JoystickButton(driverController, 4);
		// greenLEDButton.toggleWhenPressed(switchOnGreen);
		// orangeLEDButton.toggleWhenPressed(switchOnOrange);
		buttonSix = new JoystickButton(driverController, 6);
		buttonSix.toggleWhenPressed(new CameraToggle());	
		// onFloor = new JoystickButton(xBoxController, 1);
		// onFloor = new JoystickButton(xBoxController, 1);
		// onFloor.whenPressed(new onFloor());
		// shootBall = new JoystickButton(xBoxController , 2);
		// shootBall.whenPressed(new shootBall());
		// inRobot = new JoystickButton(xBoxController, 3);
		// inRobot.whenPressed(new inRobot());
		placeHatch = new JoystickButton(copilotController , 4);
		placeHatch.toggleWhenPressed(new placeHatch());

		armOverride = new JoystickButton(copilotController , 4);
		armOverride.whenPressed(new OverrideArmPID());


	}
	// public boolean getIntake() {
	// 	 double value = copilotController.getRawAxis(2);
	// 	return value > 0.1;
	// }
	public boolean getOuttake() {
		double value = driverController.getRawAxis(3);
	   return value > 0.1;
	}
	public void startDriveCommand() {
//		Command command = new RunJoystickTank();
		Command command = new RunGameTank();
		command.start();
	}
	

	public double joystickLeftAxis() {
		return joystickLeft.getRawAxis(1);
	}
	
	public double joystickLeftThrottleAxis() {
		return joystickLeft.getRawAxis(3);
	}
	
	public double joystickRightAxis() {
		return joystickRight.getRawAxis(1);
	}
	
	public double joystickRightThrottleAxis() {
		return joystickRight.getRawAxis(3);
	}
	
	public double gamepadLeftAxis() {
		return driverController.getRawAxis(1);
	}
	public double gamepadRightAxis() {
		return driverController.getRawAxis(5);
	}
	
	public double gamePadLeftTrigger() {
		return driverController.getRawAxis(2);
	}
	
	public double gamePadRightTrigger() {
		return driverController.getRawAxis(3);
	}
	
	public boolean gamePadXButtonPressed(){
        return driverController.getRawButton(3);
	}

	public double xBoxLeftAxis() {
		return copilotController.getRawAxis(1);
	}
	
	public double xBoxRightAxis() {
		return copilotController.getRawAxis(5);
	}
	
	public double xBoxLeftTrigger() {
		return copilotController.getRawAxis(2);
	}

	public double xBoxRightTrigger() {
		return copilotController.getRawAxis(3);
	}
}
