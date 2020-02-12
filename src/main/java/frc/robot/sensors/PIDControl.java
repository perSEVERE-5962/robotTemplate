package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.subsystems.Drive;

public class PIDControl{
    public void configTalons(){
        Drive.robotLeftVictor.follow(Drive.robotLeftTalon);
        Drive.robotRightVictor.follow(Drive.robotRightTalon);

		// Drive.robotLeftTalon.setSelectedSensorPosition(0);
		// Drive.robotRightTalon.setSelectedSensorPosition(0);
        /** Track button state for single press event */
        boolean _lastButton1 = false;
    
        /** Save the target position to servo to */
        double targetPositionRotations;
		/* Config the sensor used for Primary PID and sensor direction */
        Drive.robotRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 30);
        Drive.robotLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 30);


		/* Ensure sensor is positive when output is positive */
		Drive.robotRightTalon.setSensorPhase(true);//true
		Drive.robotLeftTalon.setSensorPhase(true);//true
		/**
		 * Set based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. 
		//  */ 
        Drive.robotRightTalon.setInverted(true);//compbot: true testbot: false
        Drive.robotRightVictor.setInverted(true);//compbot: true testbot: false
        Drive.robotLeftTalon.setInverted(false);//compbot: false testbot:true
        Drive.robotLeftVictor.setInverted(false);//compbot: false testbot:true

		/* Config the peak and nominal outputs, 12V means full */
        //Drive.robotLeftTalp8uy76tr54e3w2q1w2e3r5tyt-iuyte4r5t54687on.configNominalOutputForward(0, 30);
        Drive.robotRightTalon.configNominalOutputForward(0, 30);
        Drive.robotLeftTalon.configNominalOutputReverse(0, 30);
		Drive.robotRightTalon.configNominalOutputReverse(0, 30);
        Drive.robotLeftTalon.configPeakOutputForward(1, 30);
        Drive.robotRightTalon.configPeakOutputForward(1, 30);
        Drive.robotLeftTalon.configPeakOutputReverse(-1, 30);
		Drive.robotRightTalon.configPeakOutputReverse(-1, 30);
		
		/* Disable Ramping for Auto */
		Drive.robotRightTalon.configOpenloopRamp(0);
		Drive.robotLeftTalon.configOpenloopRamp(0);

		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		Drive.robotLeftTalon.configAllowableClosedloopError(0, 0, 30);
		Drive.robotRightTalon.configAllowableClosedloopError(0, 0, 30);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		Drive.robotLeftTalon.config_kF(0, 0, 30);
		Drive.robotLeftTalon.config_kP(0, 1, 30);
		Drive.robotLeftTalon.config_kI(0, 0, 30);
		Drive.robotLeftTalon.config_kD(0, 0, 30);


		Drive.robotRightTalon.config_kF(0, 0, 30);
		Drive.robotRightTalon.config_kP(0, 1, 30);
		Drive.robotRightTalon.config_kI(0, 0, 30);
	    Drive.robotRightTalon.config_kD(0, 0, 30);
    
		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */

		 //resetting ENC
		 Drive.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
		 Drive.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);


		int absolutePositionLeft = Drive.robotLeftTalon.getSensorCollection().getPulseWidthPosition();
		int absolutePositionRight = Drive.robotRightTalon.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
        absolutePositionLeft &= 0xFFF;
        absolutePositionRight &= 0xFFF;
		if (true) { absolutePositionLeft *= -1; }//left
		if (false) { absolutePositionRight *= -1; }//right
        if (false) { absolutePositionLeft *= -1; }
        if (true) { absolutePositionRight *= -1; }

		
		/* Set the quadrature (relative) sensor to match absolute */
		Drive.robotLeftTalon.setSelectedSensorPosition(0, 0, 30);
		Drive.robotRightTalon.setSelectedSensorPosition(0, 0, 30);

    }

}