/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

/**
 * Add your docs here.
 */
public class MotorController {
    private static final double GRAPPLER_EXTEND_MIN = 0;
    private static final double GRAPPLER_EXTEND_MAX = 10;
    //Testing out limelight code, the below driving code can be deleted. - Maili 1/9/20
    // Re: Not all this code is for driving. Be careful about deleting. - Silas 2020 Jan 10
    private static final int LTID = 1;
    private static final int LBID = 2;
    private static final int RTID = 3;
    private static final int RBID = 4;
    private CANSparkMax leftT = new CANSparkMax(LTID, MotorType.kBrushless);
    private CANSparkMax leftB = new CANSparkMax(LBID, MotorType.kBrushless);
    private CANSparkMax rightT = new CANSparkMax(RTID, MotorType.kBrushless);
    private CANSparkMax rightB = new CANSparkMax(RBID, MotorType.kBrushless);

    private CANSparkMax grappler = new CANSparkMax(5, MotorType.kBrushless);
    PIDMotor grapplerPID = new PIDMotor(grappler, "Grappler"); // default PID parameters

    SpeedControllerGroup m_left = new SpeedControllerGroup(leftT, leftB);
    SpeedControllerGroup m_right = new SpeedControllerGroup(rightT, rightB);
    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    double limeDrive = 0.0d;
    double limeSteer = 0.0d;
    boolean limeValidTarget = false;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

        //TUNE THESE COLORS BEFORE MATCHES DURING COMPETITION
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public void setLime(boolean starter, double x, double y, double v, double area){
        m_left.setInverted(true);   //Test to see which side is inverted first!!

        setLimeTrack(x, y, v, area);

        double drive = 0.0d;
        double steer = 0.0d;

        steer *= 0.70;
        drive *= 0.70;

        if(starter){
            if(limeValidTarget){
                //use limeDrive and limeSteer to drive. I don't know how to add steering into this. - Maili
                m_left.set(limeDrive * limeSteer);
                m_right.set(limeDrive * limeSteer);
            } else {
                m_left.set(0);
                m_right.set(0);
            }
        } else {
            //use drive & steer to drive. I don't know how to add steering into this. - Maili
            m_left.set(drive * steer);
            m_right.set(drive * steer);
        }

    }
    public void setLimeTrack(double x, double y, double v, double area){
            //MAKE SURE TO TUNE THESE FOR THE ROBOT (They haven't been tuned yet)
        final double STEER_K = 0.03;    //How hard to turn toward vision target
        final double DRIVE_K = 0.26;    //How hard to drive fwd toward vision target
        final double DESIRED_TARGET_AREA = 13.0;    //Area of target when robot reaches wall
        final double MAX_DRIVE = 0.7;   //Simple spd limit so we don't overshoot drive
        
        if(v < 1.0){
            limeValidTarget = false;
            limeDrive = 0.0;
            limeSteer = 0.0;
            return;
        }

        limeValidTarget = true;

        //proportional steering
        double steer_cmd = x * STEER_K;
        limeSteer = steer_cmd;

        //try drive fwd until target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - area) * DRIVE_K;

        //don't let robot drive too fast into the goal
        if(drive_cmd > MAX_DRIVE){
            drive_cmd = MAX_DRIVE;
        }
        limeDrive = drive_cmd;
    }

    // CLIMBING --------------------------------------------------------------------------------------------------
    public void setGrapplerExtended(boolean extended)
    {
        grappler.getEncoder().setPosition(extended ?GRAPPLER_EXTEND_MAX :GRAPPLER_EXTEND_MIN);

    }
    
    public void setPositionControl(){
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);

        Color detectedColor = m_colorSensor.getColor();
        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if(match.color == kBlueTarget){
            colorString = "Blue";
        } else if(match.color == kGreenTarget){
            colorString = "Green";
        } else if(match.color == kRedTarget){
            colorString = "Red";
        } else if(match.color == kYellowTarget){
            colorString = "Yellow";
        } else {
            colorString = "Unknown :(";
        }

        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
        
    }
}
