/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class InputManager {
    private static final int B_LIME = 0; // 1
    private static final int B_GRAPPLER = 7; // 8

    boolean grapplerExtended = false;
    Boolean lime = false;

    Joystick primary = new Joystick(0);
    Joystick secondary = new Joystick(1);

    /** Call this once each teleopPeriodic. */
    public void update(){
        if(primary.getRawButtonPressed(B_GRAPPLER))
            grapplerExtended = !grapplerExtended;
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
}
