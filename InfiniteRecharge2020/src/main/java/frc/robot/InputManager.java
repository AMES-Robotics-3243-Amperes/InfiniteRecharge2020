/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class InputManager {
    private static final int B_LIME = 0; // 1
    private static final int B_GRAPPLER = 7; // 8

    boolean grapplerExtended = false;
    private static final int lID = 0;
    private static final int rID = 1;
    
    Boolean lime = false;
    Double[] driveVar = new Double[2];
    
    double varCP = 0.0;

    Joystick primary = new Joystick(0);
    Joystick secondary = new Joystick(1);

    /** Call this once each teleopPeriodic. */
    public void update(){
        if(primary.getRawButtonPressed(B_GRAPPLER))
            grapplerExtended = !grapplerExtended;
    }
    
    public Double[] getDrive(){
        driveVar[0] = primary.getRawAxis(lID);    //Test to see if driveVar[0] should be lID
        driveVar[1] = primary.getRawAxis(rID);    //Test to see if driveVar[1] should be rID
        return driveVar;
    }

    public boolean getLime(){
        if(primary.getRawButtonPressed(B_LIME)){
            lime = !lime;
        }
        return lime;   
    }

    public boolean getGrapplerExtended()
    {
        return grapplerExtended;
    }
    public double getControlPanel(){

        return varCP;
    }

}
