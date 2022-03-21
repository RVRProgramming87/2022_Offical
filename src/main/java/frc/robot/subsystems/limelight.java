package frc.robot.subsystems;

/**
 * Authors: Joshua Hizgiaev, Ethan Friedman, and Helios Gayibor as of Robotics Year 2022
 *
 * Description: Limelight subsystem, remember, the limelight tests within periodic are *ONLY* 
 * within that method, the actual limelight code goes here, and will be used in the teleoperated method
 * See this link if you want to understand network tables more:
 * https://docs.limelightvision.io/en/latest/networktables_api.html
 */


import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class limelight extends Subsystem {
    
    //The height of the target (Subject to change each year)
    private static final double height = 30; //TODO: Change this

    //Make a new Network Table
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    //Create new Network Table entries (May be subject to change depending on what is needed)
    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry tx = table.getEntry("tx");
    static NetworkTableEntry ty = table.getEntry("ty"); //Static because it is used in a static method
    NetworkTableEntry ta = table.getEntry("ta");

    //Create variables to store Limelight data with default value as 0.0
    double v = tv.getDouble(0.0); //Determine whether the Limelight has valid targets (0 or 1)
    double x = tx.getDouble(0.0); //Determine horizontal offset from crosshair to target
    static double y = ty.getDouble(0.0); //Determine vertical offset from crosshair to target
    double a = ta.getDouble(0.0); //Actual percentage of target image to Limelight (0 - 100)

    //Get the distance from the Limelight to target directly with a method
    public static double limelightDistance(){
        double distance = (98.25 - height) / Math.tan(0 + y);
        return distance;
    }

    //TODO: Look into the usefulness of these following methods, dosen't vertical distance = height?
    //Get the horozontial distance from Limelight to target directly with a method
    public static double horizontalDistance(){
        double horizontal = Math.cos(Math.PI/4)*limelightDistance();
        return horizontal;
    }

    //Get the vertical distance from Limelight to target direct
    public static double verticalDistance(){
        double vertical = Math.sin(Math.PI/4)*limelightDistance();
        return vertical;
    }

    //TODO: If we are using a tracker for velocity, why do we need this?
    //Initial velocity of cargo 
    public static double initalVelocity(){
        double velocity = Math.sqrt((Math.pow(horizontalDistance(), 2)*385.826771654)/(verticalDistance()*Math.sin(Math.PI/2)-2*verticalDistance()*Math.pow(Math.cos(Math.PI/4), 2)));
        return velocity;
    }

    //TODO: Why do we have this?
    //RPM of launcher
    public static double launcherRPM(){
        double rpm = (initalVelocity()) / (0.2094333333);
        return rpm;
    }

    //Must be added to avoid any syntax errors 
    @Override
    protected void initDefaultCommand(){}
}
