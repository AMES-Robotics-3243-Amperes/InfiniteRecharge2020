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
    Boolean lime = false;

    Joystick primary = new Joystick(0);
    Joystick secondary = new Joystick(1);

    public boolean getLime(){
        if(primary.getRawButtonPressed(0)){
            lime = !lime;
        }
        return lime;   
    }

}
