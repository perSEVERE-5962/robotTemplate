package frc.robot.sensors;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class srxMagEncoder{
    final int kTimeoutMs = 10;
    final boolean kDiscontinuityPresent = true;
    final int kBookEnd_0 = 910;		/* 80 deg */
	final int kBookEnd_1 = 1137;	/* 100 deg */
	final static double diameter = 7.5;
	final static double circumferance = Math.PI*diameter;
	public static double ToInch(double units) {
		double deg = units * 360.0 / 4096.0;

		/* truncate to 0.1 res */
		deg *= 10;
		deg = (int) deg;
		deg /= 10;
		double inchConversion = (deg/360)*circumferance;
		
		return inchConversion;
    }
    
    
	public void init() {
        //RobotMap.robotLeftTalon.setSelectedSensorPosition(0 , 0 , kTimeoutMs);   
        initLeftQuadrature();
        //RobotMap.robotLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, kTimeoutMs);								// Timeout
		//RobotMap.robotRightTalon.setSelectedSensorPosition(0 , 0 , kTimeoutMs);   
        initRightQuadrature();
        //RobotMap.robotRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, kTimeoutMs);								// Timeout
	}

    public double getLeftDistance(){  
    	double pulseWidthWithoutOverflows =RobotMap.robotLeftTalon.getSensorCollection().getQuadraturePosition(); 
		return ToInch(pulseWidthWithoutOverflows);
	}
	public double getRightDistance(){
		double pulseWidthWithoutOverflows =RobotMap.robotRightTalon.getSensorCollection().getQuadraturePosition();
        return ToInch(pulseWidthWithoutOverflows);
	}
	public double getDistance(){
		return (getLeftDistance() + getRightDistance())/-2;
	}
	
    public void initLeftQuadrature() {
		/* get the absolute pulse width position */
		int pulseWidth = RobotMap.robotLeftTalon.getSensorCollection().getPulseWidthPosition();

		if (kDiscontinuityPresent) {
			int newCenter;
			newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
			newCenter &= 0xFFF;
			pulseWidth -= newCenter;
		}
		pulseWidth = pulseWidth & 0xFFF;

		/* Update Quadrature position */
        RobotMap.robotLeftTalon.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
	}
	public void initRightQuadrature(){
		int pulseWidth = RobotMap.robotRightTalon.getSensorCollection().getPulseWidthPosition();

		if (kDiscontinuityPresent) {
			int newCenter;
			newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
			newCenter &= 0xFFF;
			pulseWidth -= newCenter;
		}
		pulseWidth = pulseWidth & 0xFFF;

		/* Update Quadrature position */
        RobotMap.robotRightTalon.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
	}
	public  void reset(){
		RobotMap.robotLeftTalon.getSensorCollection().setQuadraturePosition(0, 10);
		RobotMap.robotRightTalon.getSensorCollection().setQuadraturePosition(0, 10);
	}
}