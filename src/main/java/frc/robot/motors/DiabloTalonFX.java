package frc.robot.motors;
/**
 * Authors: Joshua Hizgiaev, Helios Gayibor, Ethan Friedman as of Robotics Year 2022
 * Description: This is mearly a class to define methods and certain functions to motors
 */

 //CTRE imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

//Motor controller imports
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class DiabloTalonFX extends TalonFX implements MotorController {
    private ControlMode controlMode = ControlMode.PercentOutput;

    public DiabloTalonFX(int deviceid){
        super(deviceid);
    }

    @Override
    public void set(double motorSpeed){
        this.set(controlMode, motorSpeed);
    }

    @Override
    public double get(){
        return this.getStatorCurrent();
    }

    @Override
    public void disable(){
        this.disable();
    }

    @Override
    public void stopMotor(){
        this.stopMotor();
    }
}
