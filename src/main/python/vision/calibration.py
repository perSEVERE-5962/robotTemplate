#!/usr/bin/env python3

import sys
import cv2
import numpy as np
import configparser
from cscore import CameraServer

##########
# CONFIG #
##########

# directory to save config file
config_dir='config/'

#####################################

""" INIT CAMERA """

# Init camera server
cs = CameraServer.getInstance()
cs.enableLogging()

# Capture from the first USB Camera on the system
camera = cs.startAutomaticCapture()
camera.setResolution(320, 240)

# Get a CvSink. This will capture images from the camera
cvSink = cs.getVideo()

# Setup a CvSource. This will send images back to the Dashboard
outputStream = cs.putVideo("Processed Camera Feed", 320, 240)

# Allocating new images is very expensive, always try to preallocate
frame = np.zeros(shape=(240, 320, 3), dtype=np.uint8)

print("[CALIBRATION]: Camera initialized.")

""" INIT NETWORK TABLES """

# set up network tables
ntinst = NetworkTablesInstance.getDefault()
ntinst.startClient("rei.local")
table = ntinst.getTable("Calibration")

print("[CALIBRATION]: Network Tables initialized.")

""" CAMERA FEED """

print("[CALIBRATION]")

while True:
    # Tell the CvSink to grab a frame from the camera and put it
    # in the source image.  If there is an error notify the output.
    time, frame = cvSink.grabFrame(frame)
    if time == 0:
        # Send the output the error.
        outputStream.notifyError(cvSink.getError());
        # skip the rest of the current iteration
        continue

    # Get values from calibrations
    h_l = table.getNumber('H-lower')
    v_l = table.getNumber('V-lower')
    s_l = table.getNumber('S-lower')
    h_u = table.getNumber('H-upper')
    s_u = table.getNumber('S-upper')
    v_u = table.getNumber('V-upper')

    hsv_lower = np.array([h_l, s_l, v_l])
    hsv_upper = np.array([h_u, s_u, v_u])

    # apply hsv filter to image
    frame = cv2.inRange(hsv, hsv_lower, hsv_upper)

    # send some image back to the dashboard
    outputStream.putFrame(frame)

"""

def callback(self):
    pass

if not len(sys.argv) ==  2:
    print("[Calibration]: ERROR: calibration.py requires you to pass an image file as a command line argument.")
    print("	Please run: python calibration.py [path/to/file.format]")
    sys.exit()

# Default blank image
img = np.zeros((300,512,3), np.uint8)
name = 'HSV Bounds Tool'
cv2.namedWindow(name)

# Calibrations to change HSV upper/lower bounds
cv2.createTrackbar('H-lower', name, 0, 255, callback)
cv2.createTrackbar('S-lower', name, 0, 255, callback)
cv2.createTrackbar('V-lower', name, 0, 255, callback)

cv2.createTrackbar('H-upper', name, 255, 255, callback)
cv2.createTrackbar('S-upper', name, 255, 255, callback)
cv2.createTrackbar('V-upper', name, 255, 255, callback)

print("[Calibration]: Adjust the HSV range by dragging the calibrations.")
print("[Calibration]: Values will be output in the terminal when exiting.")
print("[Calibration]: Press 'ESC' to quit!")

while(1):
    _, src = webcam.read()
    hsv = cv2.cvtColor(src, cv2.COLOR_BGR2HSV)

    cv2.imshow(name, img)
    cv2.imshow('Original Image', src)

    # Get values from calibrations
    h_l = cv2.getTrackbarPos('H-lower', name)
    s_l = cv2.getTrackbarPos('S-lower', name)
    v_l = cv2.getTrackbarPos('V-lower', name)
    h_u = cv2.getTrackbarPos('H-upper', name)
    s_u = cv2.getTrackbarPos('S-upper', name)
    v_u = cv2.getTrackbarPos('V-upper', name)

    hsv_lower = np.array([h_l, s_l, v_l])
    hsv_upper = np.array([h_u, s_u, v_u])

    img = cv2.inRange(hsv, hsv_lower, hsv_upper)

    # If 'ESC' pressed then print and exit
    k = cv2.waitKey(1) & 0xFF
    if k == 27:
        config = configparser.ConfigParser()
        config["HSV BOUNDS"] = {
            "h_l": str(h_l),
            "s_l": str(s_l),
            "v_l": str(v_l),
            "h_u": str(h_u),
            "s_u": str(s_u),
            "v_u": str(v_u)
        }

        with open("vision_cfg.ini", 'w') as configfile:
            config.write(configfile)

        print("[Calibration]: Lower H: " + str(h_l) + " S: " + str(s_l) + " V: " + str(v_l))
        print("[Calibration]: Upper H: " + str(h_u) + " S: " + str(s_u) + " V: " + str(v_u))
        print("[Calibration]: Configuration file saved.")

        break

cv2.destroyAllWindows()

"""