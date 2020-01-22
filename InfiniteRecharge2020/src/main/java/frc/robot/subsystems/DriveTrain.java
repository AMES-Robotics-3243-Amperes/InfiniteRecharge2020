/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

<<<<<<< HEAD
import frc.robot.Robot;
import frc.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.*;
public class DriveTrain extends Subsystem {
    /* Establishes the variables and the right and left sides of the motor for tank drive
     * Keep in mind that the integers that are assigned to each motor are subject to change based
     * on the Robo Rio.
     */
    WPI_VictorSPX RFdrive = new WPI_VictorSPX(8); //Right Front
    WPI_VictorSPX LFdrive = new WPI_VictorSPX(9); //Left Front
    WPI_VictorSPX RBdrive = new WPI_VictorSPX(10); //Right Back
    WPI_VictorSPX LBdrive = new WPI_VictorSPX(7); //Left Back

    SpeedControllerGroup LeftDrive = new SpeedControllerGroup(LFdrive, LBdrive);
    SpeedControllerGroup RightDrive = new SpeedControllerGroup(RFdrive, RBdrive);

    DifferentialDrive Drive = new DifferentialDrive(LeftDrive, RightDrive);

@Override
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void tankDrive(double left, double right) {
        Drive.tankDrive(left, right);
    }

    
    // establishing the motor variables.
    //The best thing that I'm seeing to do is something on the longs of this:
    //Private Motor RFdrive = new Motor(int) the int is the port of the motor defined in a different subsystem
=======
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; // TEMP disbled Silas 2019 Jan 18

public class DriveTrain extends SubsystemBase {
    // establishing the motor variables.
    //The best thing that I'm seeing to do is something on the longs of this:
    //Private Motor RFdrive = new Motor(int) the int is the speed of the motor defined in a different subsystem
>>>>>>> e64e04508da7e4f9ee97d25ddf734a201c899e30
    //Private Motor LFdrive = new Motor(int)
    //Private Motor RMdrive = new Motor(int)
    //Private Motor LMdrive = new Motor(int)
    //Private Motor RBdrive = new Motor(int)
    //Private Motor LBdrive = new Motor(int)
    //Groups Motors
    //Private SpeedControllerGroup LeftM = new SpeedControllerGroup(LFdrive, LMdrive, LBdrive)
    //Private SpeedControllerGroup RightM = new SpeedControllerGroup(RFdrive, RMdrive, RBdrive)
<<<<<<< HEAD
    //
=======
>>>>>>> e64e04508da7e4f9ee97d25ddf734a201c899e30
    


}