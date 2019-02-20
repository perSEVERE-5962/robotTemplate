package frc.robot;


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.ActivateRight;
import frc.robot.commands.ActivateLeft;
import frc.robot.subsystems.*;

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
	public JoystickButton buttonOne;
	public JoystickButton buttonTwo;
	public JoystickButton buttonSix;
	public boolean isCamera1Active = true;

	private int rumbleCount=0;

	ActivateLeft activateLeft = new ActivateLeft();
	ActivateRight activateRight = new ActivateRight();

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
		buttonSix = new JoystickButton(gamepad1, 6);
		buttonSix.toggleWhenPressed(new CameraToggle());
		driverController = new Joystick(0);	// Driver
		copilotController = new Joystick(3); // Copilot
		driverSpeedButton = new JoystickButton(driverController, 5);	// right bumper
		driverSpeedButton.toggleWhenPressed(new DriverSpeedControl());
		buttonOne = new JoystickButton(copilotController, 5);
		buttonTwo = new JoystickButton(copilotController, 6);
		buttonOne.whenPressed(activateLeft);
		buttonTwo.whenPressed(activateRight);
		copilotController.setRumble(RumbleType.kLeftRumble, 0);
		copilotSpeedButton = new JoystickButton(copilotController, 3);	// X 
		copilotSpeedButton.toggleWhenPressed(new ArmSpeedControl());
		// onFloor = new JoystickButton(xBoxController, 1);
		// onFloor.whenPressed(new onFloor());
		// shootBall = new JoystickButton(xBoxController , 2);
		// shootBall.whenPressed(new shootBall());
		// inRobot = new JoystickButton(xBoxController, 3);
		// inRobot.whenPressed(new inRobot());
		// placeHatch = new JoystickButton(xBoxController , 4);
		// placeHatch.whenPressed(new placeHatch());


	}
	public boolean getIntake() {
		 double value = copilotController.getRawAxis(3);
		return value > 0.1;
	}
	public boolean getOuttake() {
		double value = copilotController.getRawAxis(2);
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
        return gamepad1.getRawButton(3);
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
