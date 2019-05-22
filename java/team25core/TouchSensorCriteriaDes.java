package team25core;

import com.qualcomm.robotcore.hardware.TouchSensor;


public class TouchSensorCriteriaDes implements SensorCriteria {

    TouchSensor touchGo;
    TouchSensor touchStop;


    Boolean can_move;

    public TouchSensorCriteriaDes(TouchSensor touchGo, TouchSensor touchStop)
    {
        // TODO: Determine criteria for motion
        this.touchGo = touchGo;
    }

    @Override
    public boolean satisfied()
    {
        can_move = touchGo.isPressed();
        //telemetry.addData("goispressed:",Boolean.toString(can_move));
        if (can_move) {
            return true;
        } else {
            return false;
        }
    }


}
