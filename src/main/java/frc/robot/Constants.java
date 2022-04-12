// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * Authors: Ethan Friedman, Joshua Hizgiaev, and Helios Gayibor as of Robotics year 2022
 */

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
//Each constant with an assigned value should be final 
public abstract class Constants {
    //Each individual motor 
    public static final int NORTHMOTOR = 1;
    public static final int EASTMOTOR = 2;
    public static final int SOUTHMOTOR = 3;
    public static final int WESTMOTOR = 4;

    //The individual intake motor and its button placement
    public static int INTAKE_MOTOR;
    public static int REVERSE_INTAKE_BUTTON = 2;

    //Joystick usb value and Gamepad usb value derived from FRC Drive Station
    public static final int JOYSTICK = 1;
    public static final int GAMEPAD = 0;

    //Each individual axies for Gamepad
    public static final int STICK_X = 0;
    public static final int STICK_Y = 1;
    public static final int ROTATE_AXIS = 4;

    //The button that when pressed would allow the robot to auto-align
    public static final int AUTO_ALIGN_BUTTON = 1;

    //Get-Into-Position (GIP) button
    public static final int GIP_BUTTON = 4;

    //The DPad button values in degrees
    public static final int DPAD_UP = 0;
    public static final int DPAD_DOWN = 180;
    public static final int DPAD_LEFT = 270;
    public static final int DPAD_RIGHT = 90; 

    //Climber motors
    public static int CLIMBER_LEFT;
    public static int CLIMBER_RIGHT;
    public static int CLIMBER_BUTTON;
    
     //Sparkmax/NEO's ID
     public static final int SPARKMAX_ID1 = 1;
     public static final int SPARKMAX_ID2 = 2;

     //Conveyor buttons
     public static int CONVEYOR_MOTOR;
     public static final int CONVEYOR_BUTTON = 4;

     //Launchers
     public static int LEFT_LAUNCHER;
     public static int RIGHT_LAUNCHER;
     public static final int LAUNCHER_BUTTON = 6;

     //Shooting distance (TODO: Convert to cm if need be)
     //Highly suggest this value to be changed for each competition if distance is changed 
     public static final double shootDistance = 2.7844;
}
