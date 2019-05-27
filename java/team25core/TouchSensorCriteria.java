package team25core;

import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.RobotLog;


public class TouchSensorCriteria implements SensorCriteria {

    private TouchSensor touch;
    private Boolean pressed;

    public TouchSensorCriteria(TouchSensor touch)
    {
        // TODO: Determine criteria for motion
        this.touch= touch;
    }

    @Override
    public boolean satisfied()
    {
        pressed = touch.isPressed();
        RobotLog.d("satisfied:touch.isPressed:%s", Boolean.toString(pressed));
        if (pressed) {
            return true;
        } else {
            return false;
        }
    }
}
