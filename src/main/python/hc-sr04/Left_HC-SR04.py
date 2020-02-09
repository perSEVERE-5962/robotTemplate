#!/usr/bin/env python3

import RPi.GPIO as GPIO
import time
import math
import sys
from networktables import NetworkTablesInstance

GPIO.setmode(GPIO.BOARD)

TRIG = 11
ECHO = 13

GPIO.setup(TRIG,GPIO.OUT)
GPIO.setup(ECHO,GPIO.IN)

GPIO.output(TRIG, GPIO.LOW)
time.sleep(0.5)

ntinst = NetworkTablesInstance.getDefault()
if len(sys.argv) == 2:
	ip = sys.argv[1]
	ntinst.startClient(ip)
else:
	ntinst.startClientTeam(5962)

table = ntinst.getTable("HC-SR04")
entry = table.getEntry("Left Distance")   


while True:
        GPIO.output(TRIG, GPIO.HIGH)
        time.sleep(0.00001)
        GPIO.output(TRIG, GPIO.LOW)

        while GPIO.input(ECHO)==0:
            pass
        pulse_start = time.time()

        while GPIO.input(ECHO)==1:
            pass
        pulse_end = time.time()

        pulse_duration = pulse_end - pulse_start

        distance = math.ceil(pulse_duration * 17150 * 0.303701)

        # I found that I was losing 1 inch in distance for every 5 inches 
        # (ie at 5in I lose 1 inch, at 10 in I lose 2 in, at 15 in I lose 3 in, ...)
        fudge_factor = math.ceil(distance/5.0)
        distance = distance + fudge_factor
        #print("Distance:", distance, "in")
        entry.setValue(distance)

        time.sleep(0.5)

GPIO.cleanup()


