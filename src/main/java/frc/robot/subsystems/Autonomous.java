package frc.robot.subsystems;

import frc.robot.subsystems.*;
import frc.robot.sensors.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Autonomous {
    private static boolean Step1_done = false;

    public boolean isStep1Done() {
        return Step1_done;
    }

    public boolean Step1_Started = false;
    private static boolean Step2_done = false;

    public boolean isStep2Done() {
        return Step2_done;
    }

    public boolean Step2_Started = false;
    private static boolean Step3_done = false;

    public boolean isStep3Done() {
        return Step3_done;
    }

    public boolean Step3_Started = false;
    private static boolean Step4_done = false;

    public boolean isStep4Done() {
        return Step4_done;
    }

    public boolean Step4_Started = false;
    private static boolean Step5_done = false;

    public boolean isStep5Done() {
        return Step5_done;
    }

    public boolean Step5_Started = false;
    private static boolean Step6_done = false;

    public boolean isStep6Done() {
        return Step6_done;
    }

    public boolean Step6_Started = false;
    private static boolean Step7_done = false;

    public boolean isStep7Done() {
        return Step7_done;
    }

    public boolean Step7_Started = false;
    private static boolean Step8_done = false;// game piece placement

    public double angle = 0;

    public void turnLeft(double speed) {
        RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, speed);
        RobotMap.robotRightTalon.set(ControlMode.PercentOutput, -speed);
    }

    public void turnRight(double speed) {
        RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, -speed);
        RobotMap.robotRightTalon.set(ControlMode.PercentOutput, speed);
    }

    private static int i = 0;

    final double diameter = 7.5;
    final double circumferance = Math.PI * diameter;
    final double ticksPerRotation = 4096;

    private double ToInch(double units) {
        double deg = units * 360.0 / 4096.0;

        /* truncate to 0.1 res */
        deg *= 10;
        deg = (int) deg;
        deg /= 10;
        double inchConversion = (deg / 360) * circumferance;

        return inchConversion;
    }

    private double getTicks(double inches) {
        return (inches * (4096.0 / circumferance));
    }

    public boolean onTarget() {
        if (Math.abs(RobotMap.robotLeftTalon.getClosedLoopError()) < 100
                && Math.abs(RobotMap.robotRightTalon.getClosedLoopError()) < 100) {
            return true;
        }
        return false;
    }

    public boolean onPosition() {
        return true;
    }

    public void stopDrive() {
        RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
        RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
    }

    public double goStraight(double distance) {
        return (distance / circumferance) * ticksPerRotation;
    }

    public void Step1() {
        if (Step1_Started == false) {
            RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(48));
            RobotMap.robotRightTalon.set(ControlMode.Position, goStraight(48));
            Step1_Started = true;
        } else if (onTarget()) {
            stopDrive();
            Step1_done = true;
        } else {
        }
    }

    public void Step2() {
        if (Step2_Started == false) {
            RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(59));
            RobotMap.robotRightTalon.set(ControlMode.Position, goStraight(59));
            Step2_Started = true;
        } else if (onTarget()) {
            stopDrive();
            Step2_done = true;
        }
    }

    public void Step3() {
        angle = Robot.gyro.getGyroAngle();
        if (angle >= 17.3532) {// Should turn 67.29 degrees towards right
            // Robot.gyro.reset();
            Step3_done = true;
        } else {
            if (Robot.getIsRight() == true) {
                turnRight(0.5);
            } else if (Robot.getIsLeft() == true) {
                turnLeft(0.5);
            }
        }
    }

    public void Step4() {
        if (Step4_Started == false) {
            RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(161.134));
            RobotMap.robotRightTalon.set(ControlMode.Position, goStraight(161.134));
            Step4_Started = true;
        } else if (onTarget()) {
            stopDrive();
            Step4_done = true;
        }
    }

    public void Step5() {
        angle = Robot.gyro.getGyroAngle();
        if (angle >= -90) {
            Step5_done = true;

        } else {
            if (Robot.getIsRight() == true) {
                turnLeft(0.5);
            } else if (Robot.getIsLeft() == true) {
                turnRight(0.5);
            }
        }
    }

    public void Step6() {
        if (Step6_Started == false) {
            RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(48.06));
            RobotMap.robotRightTalon.set(ControlMode.Position, goStraight(48.06));
            Step6_Started = true;
        } else if (onTarget()) {
            stopDrive();
            Step6_done = true;
        }
    }

    public void Step7() {
        if (Step7_Started == false) {
            RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(21.75));
            RobotMap.robotRightTalon.set(ControlMode.Position, goStraight(21.75));
            Step7_Started = true;
        } else {
            stopDrive();
            Step7_done = true;
        }
    }

}