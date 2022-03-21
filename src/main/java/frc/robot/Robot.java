// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * Authors: Joshua Hizgiaev, Helios Gayibor, and Ethan Friedman as of Robotics Year 2022
 * If this project is to be used again in continuing years, please place the new author names below the previous years authors
 * Ex: Authors: X, Y, Z, etc as of Robotics year XXXX 
 */
 
package frc.robot;

//Pre-imports after project was initially created
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.*;

//CTRE Imports so we can use TalonFX (Talon 500) motors
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

//REV Robotics imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//Smartdashboard imports, however, please use shuffleboard to view live data when robot is running 
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;

//Import for Joystick
import edu.wpi.first.wpilibj.Joystick;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  //Pre-Coded statements after project creation 
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  //Create an instance/objects for our gamepad and joystick seperatly, access modifier is set as default
   Joystick gamepad = new Joystick(Constants.GAMEPAD); 
   Joystick joystick = new Joystick(Constants.JOYSTICK);

  //Create new instances/objects of our Talon motors (These are for testing purposes ONLY)
  TalonFX talon1 = new TalonFX(8);
  TalonFX talon2 = new TalonFX(0);

  //Create new instances of SparkMAX NEO motors
  CANSparkMax neoMotor1;
  CANSparkMax neoMotor3;
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  /**
   * This method should be exclusivley used for testing individual 
   * robot properties, such as motors, limelight, Smartdashboard, etc
   */
  @Override
  public void testPeriodic() {

    //Establish a network table for limelight connection
    NetworkTable table = NetworkTableInstance.getDefault().getTable("lightlight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
  
    //Set limelight variables with default values
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);

    /**
     * The following lines are used to test Smartdashboard and its functions
     * Please use the shuffleboard software during live testing to view real time data
     */
    int counter = 0;
    SmartDashboard.putNumber("Counter", counter++);
    if(counter >= 1000){
      counter = 0;
    }
    SmartDashboard.putBoolean("Controller input", gamepad.getRawButton(2));
    SmartDashboard.putNumber("Joystick value", gamepad.getRawAxis(1));
    SmartDashboard.putNumber("tx", x);
    SmartDashboard.putNumber("ty", y);

    //Individual stick and button values to test motor functions
    double stick = gamepad.getRawAxis(1);
    boolean button = gamepad.getRawButton(2);

    //TODO: Look into SlewRateLimiters and https://www.chiefdelphi.com/t/acceleration-curve-java-help/142128/10 for any possible solutions 
    //Acceleration curve (Subject to major change)
    double accelCurveValue = 0.05;
    double accelCurveStart = 0.05;
    double accelCurveFactor = 40;
   
    while(accelCurveValue <= 0.4){
      accelCurveValue = accelCurveValue * accelCurveStart * accelCurveFactor;
    }

    //Test to see how code works with Talon motors
    talon1.set(ControlMode.PercentOutput, stick);
    talon2.set(ControlMode.PercentOutput, stick);

    //A test to see how to use limelight values to control robot motors
    if(button == true){
        if (x > 0) {
          talon1.set(ControlMode.PercentOutput, 0.2);
        } else if (x < 0) {
          talon1.set(ControlMode.PercentOutput, -0.2);
        } 
      } else {
        talon1.set(ControlMode.PercentOutput, 0);
      }

      //The proceeding code is created in order to test Spark MAX motors and NEO's (Subject to change) 

      //Create two NEO motor instances
      neoMotor1 = new CANSparkMax(Constants.SPARKMAX_ID1, MotorType.kBrushless);
      neoMotor3 = new CANSparkMax(Constants.SPARKMAX_ID3, MotorType.kBrushless);

  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
