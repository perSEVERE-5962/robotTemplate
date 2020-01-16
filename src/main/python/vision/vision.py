import os
import cv2
import numpy as np
from time import time
from datetime import datetime

# directory to save images
img_dir = 'img/'

# set up webcam capture source
webcam = cv2.VideoCapture(0)

#####################################

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
    lower = np.array([0, 0, 251])
    upper = np.array([177, 24, 255])
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