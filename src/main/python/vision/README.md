# Vision Subsystem

The vision subsystem is further split into two main functions: `vision` and
`calibration.`

## Vision

### vision.py

Automonously identifies the reflective tape on the LOADING BAY and writes data
to the NetworkTable.

Saves one processed image per second by default.

Under the `Vision` table in the `Action` entry, the relevant action to take is
written.

Reads a configuration file created by the calibration function. If it cannot be
found, it will use default HSV bound values.

## Calibration

The calibration tool consists of two components: one to be run on the driver
station and one to be run on the robot. The calibration tool allows us to
fine-tune our HSV lower and upper bounds between matches in order to best
detect the reflective tape on the LOADING BAY.

### calibration_driver.py

To be run on the driver station. This is a tool that takes the camera feed from
the robot and processes the video through the driver station. From here, the
driver or technician can fine tune the HSV bounds. These values will be written
to the network table.

### calibration_pi.py

To be run on the robot. This script is to be run after the values have been
dialed in. It will read the network table for the HSV bounds and write it to a
configuration file for `vision.py` to read.

Example configuration file: `vision_cfg.ini`

```
[HSV BOUNDS]
h_l = 0
s_l = 0
v_l = 0
h_u = 255
s_u = 255
v_u = 255
```