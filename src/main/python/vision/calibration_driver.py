#!/usr/bin/env python3

import sys
import cv2
import time
import numpy as np
import configparser
from networktables import NetworkTables

##########
# CONFIG #
##########

# camera source
#webcam = 'http://10.99.88.15:1181/stream.mjpg'
#webcam = 'http://frcvision.local:1181/stream.mjpg'
webcam = 'http://10.59.62.55:1181/stream.mjpg'

# network table server
#nt_server = "10.99.88.2"
#nt_server = "rei.local"
nt_server = "10.59.62.2"

# True = doesn't connect to networktables // False = connect to networktables
is_competition = False

#####################################

""" INIT NETWORK TABLES """

if is_competition == False:
    NetworkTables.initialize(server=nt_server)
    table = NetworkTables.getTable("Calibration")
    print("[CALIBRATION]: Network Tables initialized.")
    
    # wait for connection to settle
    time.sleep(1)

""" INIT CALIBRATION GUI """

# Set up window
img = np.zeros((300,512,3), np.uint8)
name = 'Calibration Tool'
cv2.namedWindow(name)

def callback(self):
    pass

# Calibrations to change HSV upper/lower bounds
cv2.createTrackbar('H-lower', name, 0, 255, callback)
cv2.createTrackbar('S-lower', name, 0, 255, callback)
cv2.createTrackbar('V-lower', name, 0, 255, callback)

cv2.createTrackbar('H-upper', name, 255, 255, callback)
cv2.createTrackbar('S-upper', name, 255, 255, callback)
cv2.createTrackbar('V-upper', name, 255, 255, callback)

""" CALIBRATION GUI """

# set up webcam source
webcam = cv2.VideoCapture(webcam)

while True:
    # read and show frame
    _, src = webcam.read()
    hsv = cv2.cvtColor(src, cv2.COLOR_BGR2HSV)
    cv2.imshow(name, img)

    # get values from calibrations
    h_l = cv2.getTrackbarPos('H-lower', name)
    s_l = cv2.getTrackbarPos('S-lower', name)
    v_l = cv2.getTrackbarPos('V-lower', name)
    h_u = cv2.getTrackbarPos('H-upper', name)
    s_u = cv2.getTrackbarPos('S-upper', name)
    v_u = cv2.getTrackbarPos('V-upper', name)

    hsv_lower = np.array([h_l, s_l, v_l])
    hsv_upper = np.array([h_u, s_u, v_u])

    # process image to show pixel in hsv bounds
    img = cv2.inRange(hsv, hsv_lower, hsv_upper)

    if is_competition == False:
        # write trackbar values to network table
        table.putNumber("H-lower", h_l)
        table.putNumber("S-lower", s_l)
        table.putNumber("V-lower", v_l)
        table.putNumber("H-upper", h_u)
        table.putNumber("S-upper", s_u)
        table.putNumber("V-upper", v_u)

    # If 'ESC' pressed then print and exit
    k = cv2.waitKey(1) & 0xFF
    if k == 27:
        print("[CALIBRATION]: Lower H: " + str(h_l) + " S: " + str(s_l) + " V: " + str(v_l))
        print("[CALIBRATION]: Upper H: " + str(h_u) + " S: " + str(s_u) + " V: " + str(v_u))

        break

cv2.destroyAllWindows()