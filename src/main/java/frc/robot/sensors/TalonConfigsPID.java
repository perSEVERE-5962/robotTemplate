package frc.robot.sensors;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

public class TalonConfigsPID {
    final int kTimeoutMS = 10;
    double kP = 1, kI = 0.0, kD = 0.0, kF = 0;
    public int getkTimeoutMS(){
        return kTimeoutMS;
    }
    public double getkP(){
        return kP;
    }
    public double getkI(){
        return kI;
    }
    public double getkD(){
        return kD;
    }
    public void setkP(double value){
        kP = value;
    }
    public void setkI(double value){
        kI = value;
    }
    public void setkD(double value){
        kD = value;
    }
    
    
	public void configTalons() {
		RobotMap.robotLeftVictor.follow(RobotMap.robotLeftTalon);
        RobotMap.robotRightVictor.follow(RobotMap.robotRightTalon);		

        
    	RobotMap.robotLeftTalon.setInverted(false);
		RobotMap.robotLeftVictor.setInverted(false);
		RobotMap.robotRightTalon.setInverted(true);		
        RobotMap.robotRightVictor.setInverted(true);
        
        RobotMap.robotLeftTalon.setSensorPhase(false);
		RobotMap.robotRightTalon.setSensorPhase(false);
        
        RobotMap.robotLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        RobotMap.robotRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
        RobotMap.robotLeftTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMS);
        RobotMap.robotLeftTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMS);
        RobotMap.robotRightTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMS);
        RobotMap.robotRightTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMS);
        
		RobotMap.robotLeftTalon.selectProfileSlot(0, 0);
		RobotMap.robotRightTalon.selectProfileSlot(0, 0);
		
		RobotMap.robotLeftTalon.configNominalOutputForward(0, kTimeoutMS);
		RobotMap.robotRightTalon.configNominalOutputForward(0, kTimeoutMS);
		RobotMap.robotLeftTalon.configNominalOutputReverse(0, kTimeoutMS);
		RobotMap.robotRightTalon.configNominalOutputReverse(0, kTimeoutMS);
		RobotMap.robotLeftTalon.configPeakOutputForward(0.5, kTimeoutMS);
		RobotMap.robotRightTalon.configPeakOutputForward(0.5, kTimeoutMS);
		RobotMap.robotLeftTalon.configPeakOutputReverse(-0.5, kTimeoutMS);
		RobotMap.robotRightTalon.configPeakOutputReverse(-0.5, kTimeoutMS);
		
        RobotMap.robotLeftTalon.config_kF(0, kF, kTimeoutMS);
        RobotMap.robotLeftTalon.config_kP(0, kP, kTimeoutMS);
        RobotMap.robotLeftTalon.config_kI(0, kI, kTimeoutMS);
        RobotMap.robotLeftTalon.config_kD(0, kD, kTimeoutMS);
        
        RobotMap.robotRightTalon.config_kF(0, kF, kTimeoutMS);
        RobotMap.robotRightTalon.config_kP(0, kP, kTimeoutMS);
        RobotMap.robotRightTalon.config_kI(0, kI, kTimeoutMS);
        RobotMap.robotRightTalon.config_kD(0, kD, kTimeoutMS);
        
        RobotMap.robotLeftTalon.configAllowableClosedloopError(0, 2000, kTimeoutMS);
        RobotMap.robotRightTalon.configAllowableClosedloopError(0, 2000, kTimeoutMS);
        
        RobotMap.robotLeftTalon.configMotionCruiseVelocity(8000, kTimeoutMS);
        RobotMap.robotLeftTalon.configMotionCruiseVelocity(8000, kTimeoutMS);
        RobotMap.robotLeftTalon.configMotionAcceleration(3000, kTimeoutMS);
        RobotMap.robotRightTalon.configMotionAcceleration(3000, kTimeoutMS);
	}
	
}