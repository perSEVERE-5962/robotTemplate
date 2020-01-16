import sys
import cv2
import numpy as np

def callback(self):
    pass

if not len(sys.argv) ==  2:
    print("[Slider]: ERROR: slider.py requires you to pass an image file as a command line argument.")
    print("	Please run: python slider.py [path/to/file.format]")
    sys.exit()

# Default blank image
img = np.zeros((300,512,3), np.uint8)
name = 'HSV Bounds Tool'
cv2.namedWindow(name)

src = cv2.imread(sys.argv[1])
hsv = cv2.cvtColor(src, cv2.COLOR_BGR2HSV)

# Sliders to change HSV upper/lower bounds
cv2.createTrackbar('H-lower', name, 0, 255, callback)
cv2.createTrackbar('S-lower', name, 0, 255, callback)
cv2.createTrackbar('V-lower', name, 0, 255, callback)

cv2.createTrackbar('H-upper', name, 255, 255, callback)
cv2.createTrackbar('S-upper', name, 255, 255, callback)
cv2.createTrackbar('V-upper', name, 255, 255, callback)

print("[Slider]: Adjust the HSV range by dragging the sliders.")
print("[Slider]: Values will be output in the terminal when exiting.")
print("[Slider]: Press 'ESC' to quit!")

while(1):
    cv2.imshow(name, img)
    cv2.imshow('Original Image', src)

    # Get values from sliders
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
        print("[Slider]: Lower H: " + str(h_l) + " S: " + str(s_l) + " V: " + str(v_l))
        print("[Slider]: Upper H: " + str(h_u) + " S: " + str(s_u) + " V: " + str(v_u))
        break

cv2.destroyAllWindows()
