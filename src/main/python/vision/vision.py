#!/usr/bin/env python3

import os
import cv2
from cscore import CameraServer
import numpy as np
import configparser
from time import time as Time # prevents conflict w/ `time` variable
from datetime import datetime
from networktables import NetworkTablesInstance

##########
# CONFIG #
##########

# directory to save images
img_dir = 'img/'

# image capture interval (in seconds)
image_capture_interval = 1.0

################################################################################

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

print("[VISION]: Camera initialized.")

""" INIT NETWORK TABLES """

# set up network tables
ntinst = NetworkTablesInstance.getDefault()
ntinst.startClient("rei.local")
table = ntinst.getTable("Vision")

print("[VISION]: Network Tables initialized.")

""" INIT IMAGE DIR """

# create image dir if it doesn't exist
if not os.path.exists(img_dir):
    os.makedirs(img_dir)
    print("[VISION]: Image directory created.")

""" SET HSV BOUNDS """

# read config file for HSV bounds
if os.path.exists("vision_cfg.ini"):
    print("[VISION]: Reading configuration file.")

    config = configparser.ConfigParser()
    config.read_file(open(r'vision_cfg.ini'))

    h_l = int(config.get("HSV BOUNDS", "h_l"))
    s_l = int(config.get("HSV BOUNDS", "s_l"))
    v_l = int(config.get("HSV BOUNDS", "v_l"))
    h_u = int(config.get("HSV BOUNDS", "h_u"))
    s_u = int(config.get("HSV BOUNDS", "s_u"))
    v_u = int(config.get("HSV BOUNDS", "v_u"))
else:
    print("[VISION]: Configuration file doesn't exist; using default settings.")

    h_l = 0
    s_l = 0
    v_l = 251
    h_u = 177
    s_u = 24
    v_u = 255

""" PROCESS CAMERA FEED """

print("[VISION]: Vision processing initialized.")

last_recorded_time = Time()

while True:
    # Tell the CvSink to grab a frame from the camera and put it
    # in the source image.  If there is an error notify the output.
    time, frame = cvSink.grabFrame(frame)
    if time == 0:
        # Send the output the error.
        outputStream.notifyError(cvSink.getError());
        # skip the rest of the current iteration
        continue

    # convert frame to hsv color space
    hsv_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    #cv2.imshow("Original Frame", frame)
    #cv2.imshow("HSV Frame", hsv_frame)

    # apply Gaussian Blur to frame
    blur_frame = cv2.GaussianBlur(hsv_frame, (11,11), 0)
    #cv2.imshow("Blur Image", blur_frame)

    # set lower and upper HSV bounds for mask
    lower = np.array([h_l, s_l, v_l])
    upper = np.array([h_u, s_u, v_u])
    mask = cv2.inRange(blur_frame, lower, upper)
    #cv2.imshow("Mask", mask)

    # find contours
    # NOTE: On the rPi, `_, contours, _` works but `contours, _` works on local
    # machines. Change this line accordingly.
    _, contours, _ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_NONE)

    # process contour
    if contours:
        c = max(contours, key = cv2.contourArea)

        # find centroid
        x,y,w,h = cv2.boundingRect(c)
        x += w/2
        y += h/2
        #print("[VISION]: CENTROID X: " + str(x) + " Y: " + str(y))
        table.putNumber("Centroid X", x)
        table.putNumber("Centroid Y", y)

        # find area
        area = cv2.contourArea(c)
        #print("[VISION]: AREA: " + str(area))
        table.putNumber("Area", area)

        # get dimensions of image
        img_height, img_width, img_channels = frame.shape

        # find x center of image
        img_center_x = int(img_width/2)
        img_center_y = int(img_width/2)
        table.putNumber("Image Center X", img_center_x)
        table.putNumber("Image Center Y", img_center_y)

        # make decision based on relative position of centroid to image center
        if (img_center_x - 5) <= x <= (img_center_x + 5):
            #print("[VISION]: ACTION: None")
            table.putString("Action", "None")
        elif x < img_center_x:
            #print("[VISION]: ACTION: Left")
            table.putString("Action", "Left")
        elif x > img_center_x:
            #print("[VISION]: ACTION: Right")
            table.putString("Action", "Right")

        # create processed contour image
        inrange_frame = cv2.bitwise_and(frame, frame, mask=mask)
        cv2.drawContours(frame, c, -1, (0, 25, 0), 3)
    else:
        inrange_frame = frame

    # send some image back to the dashboard
    outputStream.putFrame(frame)

    # get current time
    current_time = Time()

    # save image every time interval
    if (current_time - last_recorded_time) >= image_capture_interval:
        date = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        file = img_dir + date + ".jpg"

        cv2.imwrite(file, frame)
        print("[VISION]: Image saved:" + file)

        last_recorded_time = current_time