package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TankDrive extends Command {
  public TankDrive() {
    requires(Robot.drivetrain);
  }
  @Override
  public void execute() {
      /**
       * From the Robot class when this is executed it gets the joystick inputs
       * and translates it into motor speed.
       */
      Robot.drivetrain.tankDrive(Robot.oi.getDriverGamePadLeftY(), Robot.oi.getDriverGamePadRightY());
  }
  @Override
  protected boolean isFinished() {
    return false;
  }


}