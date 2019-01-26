package frc.robot.sensors;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class srxMagEncoder{
    final int kTimeoutMs = 10;
    final boolean kDiscontinuityPresent = true;
    final int kBookEnd_0 = 910;		/* 80 deg */
	final int kBookEnd_1 = 1137;	/* 100 deg */
	final double diameter = 7.5;
	final double circumferance = Math.PI*diameter;
    String ToInch(int units) {
		double deg = units * 360.0 / 4096.0;

		/* truncate to 0.1 res */
		deg *= 10;
		deg = (int) deg;
		deg /= 10;
		double inchConversion = (deg/360)*circumferance;
		
		return "" + inchConversion;
    }
    
    
	public void init() {
        RobotMap.robotLeftTalon.setSelectedSensorPosition(0 , 0 , kTimeoutMs);   
        initQuadrature();
        RobotMap.robotLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, kTimeoutMs);								// Timeout

	}

    public int getDistance(){  
    	int pulseWidthWithoutOverflows =RobotMap.robotLeftTalon.getSensorCollection().getQuadraturePosition();
        SmartDashboard.putString("Encoder Value " , ToInch(pulseWidthWithoutOverflows));
		return -1;
    }
    
    public void initQuadrature() {
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
	

	public  void reset(){
		RobotMap.robotLeftTalon.getSensorCollection().setQuadraturePosition(0, 10);

	}
}