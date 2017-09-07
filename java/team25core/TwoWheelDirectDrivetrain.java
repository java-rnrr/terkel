package team25core;
/*
 * FTC Team 25: cmacfarl, January 23, 2017
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.util.RobotLog;

public class TwoWheelDirectDrivetrain extends DrivetrainBase implements Drivetrain {

    DcMotor frontLeft;
    DcMotor frontRight;

    double multiplier;
    boolean alternate;

    public TwoWheelDirectDrivetrain(int encoderTicksPerInch, DcMotor frontRight, DcMotor frontLeft)
    {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;

        this.encoderTicksPerInch = encoderTicksPerInch;
        this.encoderTarget = 0;
        this.multiplier = 1.0;
        this.alternate = true;

        frontRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public TwoWheelDirectDrivetrain(int encoderTicksPerInch, double pivotMultiplier, DcMotor frontRight, DcMotor frontLeft)
    {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;

        this.encoderTicksPerInch = encoderTicksPerInch;
        this.encoderTarget = 0;
        this.multiplier = pivotMultiplier;
        this.alternate = true;

        frontRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void resetEncoders()
    {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void encodersOn()
    {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void logEncoderCounts() {

    }

    @Override
    public int getCurrentPosition()
    {
        return 0;
    }

    @Override
    public void straight(double speed)
    {
        /*
         * Alternate which motor gets power first in an attempt to reduce startup jerk.
         */
        if (alternate) {
            alternate = false;
            frontRight.setPower(speed);
            frontLeft.setPower(speed);
        } else {
            alternate = true;
            frontLeft.setPower(speed);
            frontRight.setPower(speed);
        }
    }

    @Override
    public void strafe(double speed)
    {

    }

    @Override
    public void leftDiagonal(double speed)
    {

    }

    @Override
    public void rightDiagonal(double speed)
    {

    }

    @Override
    public void turn(double speed)
    {
        frontRight.setPower(-speed);
        frontLeft.setPower(speed);
    }

    @Override
    public void pivotTurn(PivotSide side, double speed)
    {
        switch (side) {
        case LEFT_OVER_RIGHT:
            frontLeft.setPower(-speed);
            frontRight.setPower((1/multiplier) * speed);
            break;
        case LEFT_OVER_LEFT:
            frontLeft.setPower((1/multiplier) * -speed);
            frontRight.setPower(speed);
            break;
        case RIGHT_OVER_RIGHT:
            frontLeft.setPower(speed);
            frontRight.setPower((1/multiplier) * -speed);
            break;
        case RIGHT_OVER_LEFT:
            frontLeft.setPower((1/multiplier) * speed);
            frontRight.setPower(-speed);
            break;
        }
    }

    @Override
    public void setPivotMultiplier(double pivotMultiplier)
    {

    }

    @Override
    public void setPowerLeft(double speed)
    {

    }

    @Override
    public void setPowerRight(double speed)
    {

    }

    @Override
    public void stop()
    {
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
    }

    @Override
    public void setMasterMotor(DcMotor motor)
    {

    }

    @Override
    public DcMotor getMasterMotor()
    {
        return null;
    }

    @Override
    public void move(double axial, double lateral, double yaw)
    {

    }

    @Override
    public double percentComplete()
    {
        RobotLog.i("Percent complete: %d, %f, %d", frontLeft.getCurrentPosition(),
                        (Math.abs(frontLeft.getCurrentPosition()) / (double)encoderTarget), encoderTarget);
        return (Math.abs(frontLeft.getCurrentPosition()) / (double)encoderTarget);
    }

    @Override
    public void setTargetInches(double inches)
    {
        encoderTarget = (int)(inches * encoderTicksPerInch);
    }

    @Override
    public void setTargetRotation(double degrees)
    {
        encoderTarget = (int)(degrees * encoderTicksPerDegree);
    }

    @Override
    public boolean isBusy()
    {
        if (Math.abs(frontLeft.getCurrentPosition()) <= encoderTarget) {
            return true;
        } else {
            return false;
        }
    }
}
