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
	public ArmMotor armMotor = new ArmMotor();
	public Joystick joystickLeft;
	public Joystick joystickRight;
	public Joystick gamepad1;
	public Joystick xBoxController;
	public boolean isSolenoidZeroPressed = false;
	public boolean isSolenoidOnePressed = false;
	public JoystickButton buttonOne;
	public JoystickButton buttonTwo;
	ActivateLeft activateZero = new ActivateLeft();
	ActivateRight activateOne = new ActivateRight();
	public JoystickButton onFloor;
	public JoystickButton inRobot;
	public JoystickButton placeHatch;
	public boolean hatch = false;
	public JoystickButton shootBall;
	public JoystickButton intakeZero;

	ActivateZero activateZero = new ActivateZero();
	ActivateOne activateOne = new ActivateOne();

	public OI() {
		joystickLeft = new Joystick(1);
		joystickRight = new Joystick(2);
		gamepad1 = new Joystick(0);
		xBoxController = new Joystick(3);
		buttonOne = new JoystickButton(xBoxController, 5);
		buttonTwo = new JoystickButton(xBoxController, 6);
		buttonOne.whenPressed(activateZero);
		buttonTwo.whenPressed(activateOne);
		xBoxController.setRumble(RumbleType.kLeftRumble, 0);
		onFloor = new JoystickButton(gamepad1, 1);
		onFloor.whenPressed(new onFloor());
		shootBall = new JoystickButton(gamepad1 , 2);
		shootBall.whenPressed(new shootBall());
		inRobot = new JoystickButton(gamepad1, 3);
		inRobot.whenPressed(new inRobot());
		placeHatch = new JoystickButton(gamepad1 , 4);
		placeHatch.whenPressed(new placeHatch());


	}
	public boolean getIntake() {
		 double value = xBoxController.getRawAxis(3);
		return value > 0.1;
	}
	public boolean getOuttake() {
		double value = xBoxController.getRawAxis(2);
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
		return gamepad1.getRawAxis(1);
	}
	public double gamepadRightAxis() {
		return gamepad1.getRawAxis(5);
	}
	
	public double gamePadLeftTrigger() {
		return gamepad1.getRawAxis(2);
	}
	
	public double gamePadRightTrigger() {
		return gamepad1.getRawAxis(3);
	}
	
	public double xBoxLeftAxis() {
		return xBoxController.getRawAxis(1);
	}
	
	public double xBoxRightAxis() {
		return xBoxController.getRawAxis(5);
	}
	
	public double xBoxLeftTrigger() {
		return xBoxController.getRawAxis(2);
	}

	public double xBoxRightTrigger() {
		return xBoxController.getRawAxis(3);
	}
}
