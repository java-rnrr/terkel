package team25core;

import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.RobotLog;


public class TouchSensorCriteria implements SensorCriteria {

    private TouchSensor touch;
    private Boolean pressed;
    String  sensorName;
    String  twoStrings;

    public TouchSensorCriteria(TouchSensor touch, String sensorName)
    {
        // TODO: Determine criteria for motion
        this.touch= touch;
        this.sensorName = sensorName;
    }

    @Override
    public boolean satisfied()
    {
        pressed = touch.isPressed();
        twoStrings = Boolean.toString(pressed) + " " + sensorName;
        RobotLog.d("coda:satisfied:touch.isPressed:%s", twoStrings);
        if (pressed) {
            return true;
        } else {
            return false;
        }
    }
}
