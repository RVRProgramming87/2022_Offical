/**
 * Authors: Joshua Hizgiaev, Ethan Friedman, and Helios Gayibor as of Robotics Year 2022
 * 
 * Description: OI goes hand in hand with Constants, since they both serve similar purposes, 
 * the main purpose is for all of your inputs (such as joysticks) to go into OI and the main 
 * robot program will call functions from OI.
 * This class acts as a replacement class for the pre-generated RobotContainer.java class
 * 
 * Originally this class was created in order to create methods to check if certain buttons were 
 * pressed, such as intake button, launcher, conveyor, etc. However this is useless as long as constants
 * are well defined hence why the OI class is so empty compared to previous years and recommended it stays 
 * that way to avoid confusion, have proper efficency, and actually makes sense - Josh.H 2022
 * 
*/
package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveBase;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.motors.DiabloTalonFX;

public class OI {

    //Just keep this pregenerated code to avoid any potential issues 
    private final ExampleCommand m_autocommand;
    private final ExampleSubsystem m_exampleSubsystem;
    
    //Create a new gamepad instance, this must only be used with game controllers (XBox, Logitech, etc)
    public static Joystick gamepad = new Joystick(Constants.GAMEPAD);
    //Create a new joystick instance, same rules apply but different type of controller 
    public static Joystick joystick = new Joystick(Constants.JOYSTICK);

    //Create mapping declarations for the motors for holonomic drive 
    public static DiabloTalonFX westMotor = new DiabloTalonFX(Constants.NORTHMOTOR);
    public static DiabloTalonFX eastMotor = new DiabloTalonFX(Constants.SOUTHMOTOR);
    public static DiabloTalonFX northMotor = new DiabloTalonFX(Constants.EASTMOTOR);
    public static DiabloTalonFX southMotor = new DiabloTalonFX(Constants.WESTMOTOR);

    //Create mapping declarations for basic motor functions to be used with SPARK Max's (Launchers, conveyors, etc)
    public static DiabloTalonFX intakeMotor = new DiabloTalonFX(Constants.INTAKE_MOTOR);
    public static DiabloTalonFX conveyorMotor = new DiabloTalonFX(Constants.CONVEYOR_BUTTON);
    public static DiabloTalonFX leftLauncher = new DiabloTalonFX(Constants.LEFT_LAUNCHER);
    public static DiabloTalonFX rightLauncher = new DiabloTalonFX(Constants.RIGHT_LAUNCHER);
    public static DiabloTalonFX leftClimber = new DiabloTalonFX(Constants.CLIMBER_LEFT);
    public static DiabloTalonFX rightClimber = new DiabloTalonFX(Constants.CLIMBER_RIGHT);

    //OI Constructor, this code executes when OI itself is called 
    public OI(){
       
        m_exampleSubsystem = new ExampleSubsystem();
        m_autocommand = new ExampleCommand(m_exampleSubsystem);
       
        DriveBase drivebase = new DriveBase();
    }

    //Pre generated code (Do not edit)
    public Command getAutonomousCommand(){
        return m_autocommand;
    }

   
}
