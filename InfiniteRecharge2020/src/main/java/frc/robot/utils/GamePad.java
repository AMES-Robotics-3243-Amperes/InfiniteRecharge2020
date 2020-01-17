package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class GamePad extends Joystick {
    
    //establishes the gamebad axis ports
    private static final int AXIS_RIGHT_Y = 1;
    private static final int AXIS_LEFT_Y = 5;
    
    //Lets other classes get the left and right Y values of the joysticks
    public double getLeftY() {
        return getRawAxis(AXIS_LEFT_Y);
    }
    
    public double getRightY() {
        return getRawAxis(AXIS_RIGHT_Y);
    }


    public GamePad(int gamepadAccess) {
        super(gamepadAccess);
    }

}