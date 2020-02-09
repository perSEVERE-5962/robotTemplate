# HC-SR04 Range Finder 

Left_HC-SR04.py 

    TRIG = 11
    ECHO = 13

Right_HC-SR04.py

    TRIG = 16
    ECHO = 18

## Raspberry Pi
ssh to the raspberry pi and create a folder called *hc-sr04*

scp the **Left_HC-SR04.py** and **Right_HC-SR04.py** to the *hc-sr04* folder on the raspberry pi

To have the python scripts automatically start when the raspberry pi boots do the following:

*assuming that the FRC raspberry pi image is being used*

- edit the **runCamera** file
- add the following before the last line in the file
    ``` python3 ~/hc-sr04/Left_HC-SR04.py &
    python3 ~/hc-sr04/Right_HC-SR04.py & ```
- reboot the raspberry pi
- open the OutlineViewer and you should see the left & right values being written to the networktables once the raspberry pi starts