#!/usr/bin/env python3

import os
import cv2
import time
import numpy as np
import configparser
from networktables import NetworkTables

##########
# CONFIG #
##########

# directory to save config file
#config_dir='/mnt/storage/config/'
config_dir='config/'

# networktable server
#nt_server = '10.99.88.2'
#nt_server = "rei.local"
nt_server = "10.59.62.2"

# True = don't connect to networktables, False = connect to networktables
is_competition = False

#####################################

#if is_competition == True:
#    """ PROMPT TO ENTER BOUNDS """
#    

else:
    """ INIT NETWORK TABLES """

    NetworkTables.initialize(server=nt_server)
    table = NetworkTables.getTable("Calibration")

    print("[CALIBRATION]: Network Tables initialized.")

    # wait for connection to settle
    time.sleep(1)

    """ GET NETWORK TABLE VALUES """

    h_l = int(table.getNumber("H-lower", 1))
    s_l = int(table.getNumber("S-lower", 1))
    v_l = int(table.getNumber("V-lower", 1))
    h_u = int(table.getNumber("H-upper", 1))
    s_u = int(table.getNumber("S-upper", 1))
    v_u = int(table.getNumber("V-upper", 1))

    print("[CALIBRATION]: Fetched HSV Bounds from Network Tables.")

""" SAVE CONIFG FILE """

# create config directory if it doesn't already exist
if not os.path.exists(config_dir):
    os.makedirs(config_dir)
    print("[CALIBRATION]: Config directory created.")

# generate config content
config = configparser.ConfigParser()
config["HSV BOUNDS"] = {
    "h_l": h_l,
    "s_l": s_l,
    "v_l": v_l,
    "h_u": h_u,
    "s_u": s_u,
    "v_u": v_u
}

# save config file
with open(config_dir + "vision_cfg.ini", 'w') as configfile:
    config.write(configfile)

print("[CALIBRATION]: Lower H: " + str(h_l) + " S: " + str(s_l) + " V: " + str(v_l))
print("[CALIBRATION]: Upper H: " + str(h_u) + " S: " + str(s_u) + " V: " + str(v_u))
print("[CALIBRATION]: Configuration file saved.")