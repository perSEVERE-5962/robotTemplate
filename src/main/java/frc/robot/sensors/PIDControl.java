package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.subsystems.Drive;

public class PIDControl{
    public void configTalons(Drive drive){
        drive.getRobotLeftVictor().follow(drive.getRobotLeftTalon());
        drive.getRobotRightVictor().follow(drive.getRobotRightTalon());

		// Drive.robotLeftTalon.setSelectedSensorPosition(0);
		// Drive.robotRightTalon.setSelectedSensorPosition(0);
        /** Track button state for single press event */
        boolean _lastButton1 = false;
    
        /** Save the target position to servo to */
        double targetPositionRotations;
		/* Config the sensor used for Primary PID and sensor direction */
        drive.getRobotRightTalon().configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 30);
        drive.getRobotLeftTalon().configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 30);


		/* Ensure sensor is positive when output is positive */
		drive.getRobotRightTalon().setSensorPhase(true);//true
		drive.getRobotLeftTalon().setSensorPhase(true);//true
		/**
		 * Set based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. 
		//  */ 
        drive.getRobotRightTalon().setInverted(true);//compbot: true testbot: false
        drive.getRobotRightVictor().setInverted(true);//compbot: true testbot: false
        drive.getRobotLeftTalon().setInverted(false);//compbot: false testbot:true
        drive.getRobotLeftVictor().setInverted(false);//compbot: false testbot:true

		/* Config the peak and nominal outputs, 12V means full */
        //Drive.robotLeftTalp8uy76tr54e3w2q1w2e3r5tyt-iuyte4r5t54687on.configNominalOutputForward(0, 30);
		drive.getRobotLeftTalon().configNominalOutputForward(0, 30);        
		drive.getRobotRightTalon().configNominalOutputForward(0, 30);
        drive.getRobotLeftTalon().configNominalOutputReverse(0, 30);
		drive.getRobotRightTalon().configNominalOutputReverse(0, 30);
        drive.getRobotLeftTalon().configPeakOutputForward(1, 30);
        drive.getRobotRightTalon().configPeakOutputForward(1, 30);
        drive.getRobotLeftTalon().configPeakOutputReverse(-1, 30);
		drive.getRobotRightTalon().configPeakOutputReverse(-1, 30);
		
		/* Disable Ramping for Auto */
		drive.getRobotRightTalon().configOpenloopRamp(0);
		drive.getRobotLeftTalon().configOpenloopRamp(0);

		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		drive.getRobotLeftTalon().configAllowableClosedloopError(0, 0, 30);
		drive.getRobotRightTalon().configAllowableClosedloopError(0, 0, 30);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		drive.getRobotLeftTalon().config_kF(0, 0, 30);
		drive.getRobotLeftTalon().config_kP(0, 1, 30);
		drive.getRobotLeftTalon().config_kI(0, 0, 30);
		drive.getRobotLeftTalon().config_kD(0, 0, 30);


		drive.getRobotRightTalon().config_kF(0, 0, 30);
		drive.getRobotRightTalon().config_kP(0, 1, 30);
		drive.getRobotRightTalon().config_kI(0, 0, 30);
	    drive.getRobotRightTalon().config_kD(0, 0, 30);
    
		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */

		 //resetting ENC
		 drive.getRobotLeftTalon().getSensorCollection().setPulseWidthPosition(0, 30);
		 drive.getRobotRightTalon().getSensorCollection().setPulseWidthPosition(0, 30);


		int absolutePositionLeft = drive.getRobotLeftTalon().getSensorCollection().getPulseWidthPosition();
		int absolutePositionRight = drive.getRobotRightTalon().getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
        absolutePositionLeft &= 0xFFF;
        absolutePositionRight &= 0xFFF;
		if (true) { absolutePositionLeft *= -1; }//left
		if (false) { absolutePositionRight *= -1; }//right
        if (false) { absolutePositionLeft *= -1; }
        if (true) { absolutePositionRight *= -1; }

		
		/* Set the quadrature (relative) sensor to match absolute */
		drive.getRobotLeftTalon().setSelectedSensorPosition(0, 0, 30);
		drive.getRobotRightTalon().setSelectedSensorPosition(0, 0, 30);

    }

}