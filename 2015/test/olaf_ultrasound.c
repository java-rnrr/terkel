#pragma config(Hubs,  S1, HTMotor,  HTServo,  none,     none)
#pragma config(Sensor, S2,     irrRight,       sensorI2CCustom)
#pragma config(Sensor, S3,     irrLeft,        sensorI2CCustom)
#pragma config(Sensor, S4,     carrot,         sensorSONAR)
#pragma config(Motor,  mtr_S1_C1_1,     driveBackRight, tmotorTetrix, PIDControl, encoder)
#pragma config(Motor,  mtr_S1_C1_2,     driveBackLeft, tmotorTetrix, PIDControl, reversed, encoder)
#pragma config(Servo,  srvo_S1_C2_1,    leftEye,              tServoNone)
#pragma config(Servo,  srvo_S1_C2_2,    rightEye,             tServoNone)
#pragma config(Servo,  srvo_S1_C2_3,    servo3,               tServoNone)
#pragma config(Servo,  srvo_S1_C2_4,    servo4,               tServoNone)
#pragma config(Servo,  srvo_S1_C2_5,    servo5,               tServoNone)
#pragma config(Servo,  srvo_S1_C2_6,    servo6,               tServoNone)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

task main()
{
    while(SensorValue[carrot] > 5) {
        nxtDisplayTextLine(3,"Sensor value: %d", SensorValue[carrot]);
    }
}
