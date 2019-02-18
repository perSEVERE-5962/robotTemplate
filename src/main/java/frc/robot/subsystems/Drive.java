
package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private boolean isReducedSpeed = false;

	public void setIsReducedSpeed(boolean value) {
		isReducedSpeed = value;
	}
	public boolean getIsReducedSpeed() {
		return isReducedSpeed;
	}
	public Drive() {
	
	}

	public void joystickTank() {
		double speed = 1.0;
		if (isReducedSpeed) {
			speed = 0.5;
		}
		RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, speed*Robot.oi.joystickLeftAxis());
		RobotMap.robotRightTalon.set(ControlMode.PercentOutput, speed*Robot.oi.joystickRightAxis());
	}

	public void gameTank() {
		double speed = 1.0;
		if (isReducedSpeed) {
			speed = 0.5;
		}
		RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, speed*Robot.oi.gamepad1.getRawAxis(1));
		RobotMap.robotRightTalon.set(ControlMode.PercentOutput, speed*Robot.oi.gamepad1.getRawAxis(5));
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
}