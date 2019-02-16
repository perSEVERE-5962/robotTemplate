// package frc.robot.subsystems;
// import frc.robot.RobotMap;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

// public class holdArm{
// public void startArmSetAngle() {
//     if (controlMode != ControlMode.Position) {
//         RobotMap.robotLeftTalon.changeControlMode(ControlMode.Position);
//         controlMode = ControlMode.Position;
//     }
//     configureSetAnglePID();
//     RobotMap.robotLeftTalon.set(ControlMode.Position , 90);
// }
// // called every 20ms
// public void execute() {
//     RobotMap.robotLeftTalon.set(ControlMode.Position , 90);
// }

// private void configureSetAnglePID() {
// double p = 0.1;
// double i = 0;
// double d = 0;
// double rampRate = prefs.getDouble("arm.set.angle.ramp.rate", ARM_SET_ANGLE_RAMP_RATE);
// int izone = prefs.getInt("arm.set.angle.izone", ARM_SET_ANGLE_IZONE);
// int profile = prefs.getInt("arm.set.angle.profile",  ARM_SET_ANGLE_PROFILE);
// RobotMap.robotLeftTalon.setPID(p, i, d, 0, izone, rampRate, profile);
// }
// }