#!/usr/bin/env python3

import os
import cv2
import numpy as np
import configparser
from time import time
from datetime import datetime

##########
# CONFIG #
##########

# directory to save images
img_dir = 'img/'

# set up webcam capture source
webcam = cv2.VideoCapture(0)

#####################################

# create image dir if it doesn't exist
if not os.path.exists(img_dir):
    os.makedirs(img_dir)
    print("[VISION]: Image directory created.")

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


# vison processing
last_recorded_time = time()

while True:
    # get current time
    current_time = time()

    # read captured frame
    _, frame = webcam.read()
    key = cv2.waitKey(1)

    # quit program once q is entered
    if key == ord('q'):
        cv2.destroyAllWindows()
        break

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
    contours, _ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_NONE)

    # process contour
    if contours:
        c = max(contours, key = cv2.contourArea)

        # find centroid
        x,y,w,h = cv2.boundingRect(c)
        x += w/2
        y += h/2
        print("CENTROID X: " + str(x) + " Y: " + str(y))

        # find area
        area = cv2.contourArea(c)
        print("AREA: " + str(area))

        # get dimensions of image
        img_height, img_width, img_channels = frame.shape

        # find x center of image and print
        img_center_x = int(img_width/2)
        print("IMG CENTER: " + str(img_center_x))

        # make decision based on relative position of centroid to image center
        if (img_center_x - 5) <= x <= (img_center_x + 5):
            print("ACTION: None")
        elif x < img_center_x:
            print("ACTION: Turn Left")
        elif x > img_center_x:
            print("ACTION: Turn Right")

        # create processed contour image
        inrange_frame = cv2.bitwise_and(frame, frame, mask=mask)
        cv2.drawContours(frame, c, -1, (0, 25, 0), 3)
    else:
        inrange_frame = frame
    
    # show processed image
    cv2.imshow("Keypoints", frame)

    # save image every second
    if current_time - last_recorded_time >= 1.0:
        date = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        file = img_dir + date + ".jpg"

        cv2.imwrite(file, frame)
        print("saved image")

        last_recorded_time = current_time