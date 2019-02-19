/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ArmPID {
	final int kSlotIdx = 0;
	final int kPIDLoopIdx = 0;
	final int kTimeoutMs = 30;
	boolean kSensorPhase = false;
	boolean kMotorInvert = false;
	public int absolutePosition = 0;
	public double currentPosition = 0;

	// Gains(kp, ki, kd, kf, izone, peak output);
	final Gains kGains = new Gains(1.6, 0.0, 0.0, 0.0, 0, 1.0);

	public void init() {
		/* Config the sensor used for Primary PID and sensor direction */
		RobotMap.armTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);

		/* Ensure sensor is positive when output is positive */
		RobotMap.armTalon.setSensorPhase(kSensorPhase);

		/**
		 * Set based on what direction you want forward/positive to be. This does not
		 * affect sensor phase.
		 */
		RobotMap.armTalon.setInverted(kMotorInvert);

		/* Config the peak and nominal outputs, 12V means full */
		RobotMap.armTalon.configNominalOutputForward(0, kTimeoutMs);
		RobotMap.armTalon.configNominalOutputReverse(0, kTimeoutMs);
		RobotMap.armTalon.configPeakOutputForward(0.3, kTimeoutMs);
		RobotMap.armTalon.configPeakOutputReverse(-0.3, kTimeoutMs);

		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be neutral
		 * within this range. See Table in Section 17.2.1 for native units per rotation.
		 */
		RobotMap.armTalon.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		RobotMap.armTalon.config_kF(kPIDLoopIdx, kGains.kF, kTimeoutMs);
		RobotMap.armTalon.config_kP(kPIDLoopIdx, kGains.kP, kTimeoutMs);
		RobotMap.armTalon.config_kI(kPIDLoopIdx, kGains.kI, kTimeoutMs);
		RobotMap.armTalon.config_kD(kPIDLoopIdx, kGains.kD, kTimeoutMs);

		RobotMap.armTalon.getSensorCollection().setPulseWidthPosition(0, kTimeoutMs);

		/**
		 * Grab the 360 degree position of the MagEncoder's absolute position, and
		 * intitally set the relative sensor to match.
		 */
		absolutePosition = RobotMap.armTalon.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (kSensorPhase) {
			absolutePosition *= -1;
		}
		if (kMotorInvert) {
			absolutePosition *= -1;
		}

		/* Set the quadrature (relative) sensor to match absolute */
		RobotMap.armTalon.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
	}

}
