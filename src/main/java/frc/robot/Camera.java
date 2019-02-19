package frc.robot;

//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.CvSink;
// import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;

public class Camera{ //extends Subsystem

HttpCamera camera1;
HttpCamera camera2;
CvSink outputStream;
CameraServer server = CameraServer.getInstance();
boolean gamePadXButtonPressed = false;
NetworkTable table;
NetworkTableInstance inst = NetworkTableInstance.getDefault();
table = inst.getTable("Camera");
NetworkTableEntry myEntry;

void robotInit(){
    camera1 = server.startAutomaticCapture(camera);
    camera2 = server.startAutomaticCapture(camera);
}

void SwitchCams(){
    if (gamePadXButtonPressed){
        System.out.printf("Setting Camera 2\n");
        myEntry = table.getEntry("CameraSelection");
        myEntry.setString(camera2.getName());
    } else if (!gamePadXButtonPressed){
        System.out.printf("Setting Camera 1\n");
        myEntry = table.getEntry("Camera Selection");
        myEntry.setString(camera1.getName());
    }
    gamePadXButtonPressed = Robot.oi.gamePadXButtonPressed();
}

// Mjpeg server function: addSwitchedCamera();

// void camera1Init(){
//     camera1 = server.startAutomaticCapture(camera);
    
// }

// void camera1Off(){
//     camera1 = server.removeServer();
// }

// void camera2Init(){
//     camera2 = server.startAutomaticCapture("10.99.88.35", 1181);
// }

// void camera2Off(){
//     camera2 = server.removeServer();
// }

// void TeleopPeriodic(){
//     if (gamePadXButtonPressed){
//         camera1Off();
//         camera2Init();
//     } else if (!gamePadXButtonPressed){
//         camera2Off();
//         camera1Init();
//     }
//     gamePadXButtonPressed = Robot.oi.gamePadXButtonPressed(); 
// }

// CameraServer server = CameraServer.getInstance();
// int streamport = 1181;

// public void CameraImage(MjpegServer video){
// server.addServer("10.99.88.35", streamport);
// server.startAutomaticCapture(video);
// //server.startAutomaticCapture(streamport);
// }

// public void SwitchCams(){
//     if (Robot.oi.gamePadXButtonPressed())
//     if (streamport == 1181){
//         streamport = 1182;
//         CameraImage(streamport);
//     } else if (streamport == 1182){
//         streamport = 1181;
//         CameraImage(streamport);
//     } else {
//         streamport = 1181;
//         CameraImage(streamport);
//     }
// }

// public static double centerX = 0.0;
// public static double centerY = 0.0;
// public static double area = 0.0;

// public static int IMG_WIDTH = 640;
// public static int IMG_HEIGHT = 360;

// public final static Object imgLock = new Object();

// public HttpCamera setHttpCamera(String cameraName, MjpegServer inputStream){
//     CvSink cvSink1 = server.getVideo(cameraName);
//     CvSource outputStream = server.putVideo("Switcher", 640, 480);

//     Mat image = new Mat();

//     Thread t = new Thread(() -> {
//     cvSink1.setEnabled(true);
//     cvSink1.grabFrame(image);
//     outputStream.putFrame(image);
//     });
//     t.start();
// }

// public Camera(){
// int streamPort = 1181;
//         MjpegServer inputStream = new MjpegServer("MJPEG Server", streamPort);
//         String cameraName = "5190";
//         HttpCamera camera = setHttpCamera(cameraName, inputStream);
//         if (camera == null) {
//             camera = new HttpCamera("CoprocessorCamera", "YourURLHere");
//             inputStream.setSource(camera);
//         }
//     }

// //@Override
// protected void initDefaultCommand(){

// }
}
