package frc.robot.subsystems;

/**
 * Authors: Joshua Hizgiaev, Helios Gayibor, Ethan Friedman
 */

//Imports from completed classes
import frc.robot.OI;
import frc.robot.Constants;
import frc.robot.motors.DiabloTalonFX;

//Subsystem imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Import arrays to list motors b
import java.util.ArrayList;

public class DriveBase extends SubsystemBase {

    public OI oi;
    ArrayList<DiabloTalonFX> motorList;
    ArrayList<DiabloTalonFX> driverMotors;
    
    //Boolean & Double variables to check buttons
    boolean reverseButtonPressed = OI.gamepad.getRawButton(Constants.REVERSE_INTAKE_BUTTON);
    boolean conveyorButtonPressed = OI.gamepad.getRawButton(Constants.CONVEYOR_BUTTON);
    boolean stopLauncherButtonPressed = OI.gamepad.getRawButton(Constants.LAUNCHER_BUTTON);
    boolean autoAlignButtonPressed = OI.gamepad.getRawButton(Constants.AUTO_ALIGN_BUTTON);
    boolean gipButtonPressed = OI.gamepad.getRawButton(Constants.GIP_BUTTON);
    boolean climberButtonPressed = OI.gamepad.getRawButton(Constants.CLIMBER_BUTTON); //Subject to change
    double climberAxis = OI.joystick.getRawAxis(Constants.JOYSTICK); //TODO: Subject to change

    //Constructor, code executed here will only execute when new DriveBase object is made 
    public DriveBase(){
        //Add motors
        addMotors();
    }

    protected void addMotors(){
        //For Spark MAX motors
        motorList = new ArrayList<>();
        motorList.add(OI.intakeMotor);
        motorList.add(OI.conveyorMotor);
        motorList.add(OI.rightLauncher);
        motorList.add(OI.leftLauncher);
       
        //TODO: Add climbers as well?
        motorList.add(OI.leftClimber);
        motorList.add(OI.rightClimber);

        //For Falcon motors
        driverMotors = new ArrayList<>();
        driverMotors.add(OI.northMotor);
        driverMotors.add(OI.southMotor);
        driverMotors.add(OI.eastMotor);
        driverMotors.add(OI.westMotor);
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic(){
    }

    // Maximum speed of the Robot (Acceleration curve)
    //TODO: This needs to be changed so it can work with any value
    public static double maxSpeed(){

        double accelCurveValue = 0.05;
        double accelCurveStart = 0.05;
        double accelCurveFactor = 40;

        while(accelCurveValue <= 0.4){
            accelCurveValue = accelCurveValue * accelCurveStart * accelCurveFactor;
        } return accelCurveValue;
    }

    /**
     * The basic intake method, basically it will always be running when called 
     * and if the reverse button is pressed the motors will spin in the opposite direction
     * @param speed
     */
    public void intake(double speed){
        if(reverseButtonPressed == true){
            OI.intakeMotor.set(-speed);
        } OI.intakeMotor.set(speed);
    }

    /**
     * The basic conveyor method, basically it will always be running when called
     * and if the button is not pressed, then the conveyor will stop running
     */
    public void conveyor(){
        if(conveyorButtonPressed == true){
            OI.conveyorMotor.set(0.5);
        } OI.conveyorMotor.set(0);
    }
    
    /**
     * The basic launcher method, basically it will always be running when called 
     * and if the button is not pressed, then the launcher will just keep running
     * @param launcherSpeed
     */
    public void launcher(double launcherSpeed){
        if(stopLauncherButtonPressed){
            OI.leftLauncher.set(0);
            OI.rightLauncher.set(0);
        } else {
            OI.leftLauncher.set(launcherSpeed);
            OI.rightLauncher.set(-launcherSpeed);
        }
    }

    /**
     * The basic climber method, basically it will always run, meaning it will not be
     * activated until the button is pressed therefore the motors will start running based
     * on the joystick axis value
     */
    public void climber(){
        if(climberButtonPressed){
            OI.leftClimber.set(climberAxis * 0.2);
            OI.rightClimber.set(climberAxis * 0.2);
        } else{
            OI.leftClimber.set(0);
            OI.rightClimber.set(0);
        }
    }
    //Deprecated method
    public void holonomicDrive(double stickX, double stickY, double pRotate, double maxSpeed, boolean dPadUp, boolean dPadDown, boolean dPadLeft, boolean dPadRight){

        stickX += Math.pow(2, -100);
        stickY += Math.pow(2, -100);

        pRotate /= 4;

        stickX *= Math.sqrt(Math.pow(1-Math.abs(pRotate), 2)/2);
        stickY *= Math.sqrt(Math.pow(1-Math.abs(pRotate), 2)/2);
        
        if (dPadRight) {
            stickX = .5;
          } else if (dPadLeft) {
            stickX = -.5;
          }

        if (dPadUp){
          stickY = .5;
          } else if (dPadDown) {
          stickY = -.5;
          }

        double angle = Math.abs(Math.atan2(stickY, stickX));
        double powerX = Math.sqrt(Math.pow(stickX, 2) + Math.pow(stickY, 2)) * Math.cos(angle) * (stickX/Math.abs(stickX)) * maxSpeed;
        double powerY = Math.sqrt(Math.pow(stickX, 2) + Math.pow(stickY, 2)) * Math.sin(angle) * (stickY/Math.abs(stickY)) * maxSpeed;

        OI.northMotor.set(powerX - pRotate);
        OI.southMotor.set(powerX - pRotate);
        OI.eastMotor.set(powerY + pRotate);
        OI.westMotor.set(powerY + powerX);
    }
    //TODO: Change motor names to fit the desired directions
    public void holonomicDrive(double stickX, double stickY, double rightStickX){
             
    double headingAngle = Math.atan2(stickY, stickX);

    // northMotor.set((-stickY + stickX - rightStickX) * 0.5); //West
    // eastMotor.set((-stickY - stickX - rightStickX) * 0.5);  //North
    // southMotor.set((stickY - stickX - rightStickX) * 0.5); //East
    // westMotor.set((stickY + stickX - rightStickX) * 0.5); //South
     
    //Original
    // northMotor.set((-stickY - stickX - rightStickX) * 0.4); //West
    // eastMotor.set((stickY - stickX - rightStickX) * 0.4); //North
    // southMotor.set((stickY + stickX - rightStickX) * 0.4); //East
    // westMotor.set((-stickY + stickX - rightStickX) * 0.4); //South
  
    //General formula: Power (Magnitude of vector) * (Its relavent position based on unit circle (Varies) + pi/4) - Turn speed (To make it rotate with right stick only)
    OI.northMotor.set((((Math.sqrt(Math.pow(stickX, 2) + Math.pow(stickY, 2))) * (-Math.sin(headingAngle + (Math.PI / 4)))) * 0.6) - (rightStickX * 0.6));
    OI.eastMotor.set((((Math.sqrt(Math.pow(stickX, 2) + Math.pow(stickY, 2))) * (Math.cos(headingAngle + (Math.PI / 4)))) * 0.6) - (rightStickX * 0.6));
    OI.southMotor.set((((Math.sqrt(Math.pow(stickX, 2) + Math.pow(stickY, 2))) * (Math.sin(headingAngle + (Math.PI / 4)))) * 0.6) - (rightStickX * 0.6));
    OI.westMotor.set((((Math.sqrt(Math.pow(stickX, 2) + Math.pow(stickY, 2))) * (-Math.cos(headingAngle + (Math.PI / 4)))) * 0.6) - (rightStickX * 0.6));
         
    }

    /**
     * Basic auto align method, this is used to basically align the robot
     * with the target marked by special tape only detected by limelight
     */
    public void autoAlign(){
        if(autoAlignButtonPressed){
            if(limelight.x > 0){
              holonomicDrive(0, 0, -0.75, 0.4, false, false, false, false);
            } else if (limelight.x < 0){
                holonomicDrive(0, 0, 0.75, 0.4, false, false, false, false);
            }
        }
    }

    /**
     * Basic GIP method, this will correct the distance from robot to target 
     * as long as button is pressed
     */
    public void getIntoPosition(){
        if(gipButtonPressed){
            if(limelight.horizontalDistance() > Constants.shootDistance){
                holonomicDrive(0, 0.5, 0, 0.4, false, false, false, false);
            } else if(limelight.horizontalDistance() < Constants.shootDistance){
                holonomicDrive(0, -0.5, 0, 0.4, false, false, false, false);
            }
        }
    }


}