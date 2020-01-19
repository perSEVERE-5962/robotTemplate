#!/usr/bin/env python3

import sys
import cv2
import numpy as np
import configparser

##########
# CONFIG #
##########

# set up webcam capture source
webcam = cv2.VideoCapture(0)

#####################################

def callback(self):
    pass

"""
if not len(sys.argv) ==  2:
    print("[Calibration]: ERROR: calibration.py requires you to pass an image file as a command line argument.")
    print("	Please run: python calibration.py [path/to/file.format]")
    sys.exit()
"""

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