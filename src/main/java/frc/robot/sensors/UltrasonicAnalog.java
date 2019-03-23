package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicAnalog extends UltrasonicBase {
	AnalogInput ultrasonic;
	final double valueToInches = 2.3;

	public UltrasonicAnalog(int channel) {
		ultrasonic = new AnalogInput(channel);
	}

	public double getRange() {
		return ultrasonic.getVoltage() * valueToInches;
	}

	public double getVoltage() {
		return ultrasonic.getVoltage();
	}
	public boolean isEnabled() {
		return true;
	}
	
	public void setBackwards(boolean backwards) {
		// this is only used for testing
		// DO NOT ADD PRODUCTION CODE HERE!
	}
	
	public void reset() {
		// this is only used for testing
		// DO NOT ADD PRODUCTION CODE HERE!
	}
}
