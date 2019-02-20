/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class pidControl {

	public void configTalons(){
        RobotMap.robotLeftVictor.follow(RobotMap.robotLeftTalon);
        RobotMap.robotRightVictor.follow(RobotMap.robotRightTalon);

        
        /** Track button state for single press event */
        boolean _lastButton1 = false;
    
        /** Save the target position to servo to */
        double targetPositionRotations;
		/* Config the sensor used for Primary PID and sensor direction */
        RobotMap.robotRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx , Constants.kTimeoutMs);
        RobotMap.robotLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx , Constants.kTimeoutMs);


		/* Ensure sensor is positive when output is positive */
		RobotMap.robotRightTalon.setSensorPhase(Constants.kRightSensorPhase);//false
		RobotMap.robotLeftTalon.setSensorPhase(Constants.kLeftSensorPhase);//true
		/**
		 * Set based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. 
		 */ 
        RobotMap.robotRightTalon.setInverted(Constants.kRightInvert);
        RobotMap.robotRightVictor.setInverted(Constants.kRightInvert);
        RobotMap.robotLeftTalon.setInverted(Constants.kLeftInvert);
        RobotMap.robotLeftVictor.setInverted(Constants.kLeftInvert);

		// RobotMap.robotLeftTalon.setInverted(true);
		// RobotMap.robotLeftVictor.setInverted(true);
		// RobotMap.robotRightTalon.setInverted(false);		
        // RobotMap.robotRightVictor.setInverted(false);
        
        // RobotMap.robotLeftTalon.setSensorPhase(true);
		// RobotMap.robotRightTalon.setSensorPhase(true);

		/* Config the peak and nominal outputs, 12V means full */
        //RobotMap.robotLeftTalp8uy76tr54e3w2q1w2e3r5tyt-iuyte4r5t54687on.configNominalOutputForward(0, Constants.kTimeoutMs);
        RobotMap.robotRightTalon.configNominalOutputForward(0, Constants.kTimeoutMs);
        RobotMap.robotLeftTalon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		RobotMap.robotRightTalon.configNominalOutputReverse(0, Constants.kTimeoutMs);
        RobotMap.robotLeftTalon.configPeakOutputForward(1, Constants.kTimeoutMs);
        RobotMap.robotRightTalon.configPeakOutputForward(1, Constants.kTimeoutMs);
        RobotMap.robotLeftTalon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        RobotMap.robotRightTalon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		RobotMap.robotLeftTalon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		RobotMap.robotRightTalon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		RobotMap.robotLeftTalon.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		RobotMap.robotLeftTalon.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		RobotMap.robotLeftTalon.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		RobotMap.robotLeftTalon.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);


		RobotMap.robotRightTalon.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		RobotMap.robotRightTalon.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		RobotMap.robotRightTalon.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
	    RobotMap.robotRightTalon.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		// reset the position
		RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, Constants.kTimeoutMs);
		RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, Constants.kTimeoutMs);

		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePositionLeft = RobotMap.robotLeftTalon.getSensorCollection().getPulseWidthPosition();
		int absolutePositionRight = RobotMap.robotRightTalon.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
        absolutePositionLeft &= 0xFFF;
        absolutePositionRight &= 0xFFF;
		if (Constants.kLeftSensorPhase) { absolutePositionLeft *= -1; }
		if (Constants.kRightSensorPhase) { absolutePositionRight *= -1; }
        if (Constants.kLeftInvert) { absolutePositionLeft *= -1; }
        if (Constants.kRightInvert) { absolutePositionRight *= -1; }

		
		/* Set the quadrature (relative) sensor to match absolute */
		RobotMap.robotLeftTalon.setSelectedSensorPosition(absolutePositionLeft, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		RobotMap.robotRightTalon.setSelectedSensorPosition(absolutePositionRight, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    }
}
