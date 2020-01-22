package frc.robot;

import frc.robot.utils.GamePad;


public class OI {
    /**
     * Makes the first controller assigned to the first port. 
     * This is important when you need to map multiple controllers
     * to specific buttons.
     */
    GamePad controller1 = new GamePad(0);

    /**
     * These are established so in the drivetrain you can easily just get
     * the right and left joystick Y's for tank drive.
     */
    public double getDriverGamePadLeftY() {
        double y = controller1.getLeftY();
        return y;
    }
    public double getDriverGamePadRightY() {
        double y = controller1.getRightY();
        return y;
    }
    


}