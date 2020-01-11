/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  MotorController MC = new MotorController();
  InputManager IM = new InputManager();

  // ------------------------- START LIMELIGHT CODE ------------------------------- //
  NetworkTable table = NetworkTableInstance.getDefault().getTable("Limelight");
  NetworkTableEntry camMode;  //Sets the limelight's operation mode
  //Operation modes: 0 = Vision processor.  1 = Driver camera (increases exposure, disables vision processing).
  
  NetworkTableEntry pipeline; //What camera calibration settings we are on
  NetworkTableEntry tx; //Horizontal offset from crosshair to target (-27 to 27 degrees)
  NetworkTableEntry ty; //Vertical offset from crosshair to target (-20.5 to 20.5 degrees)
  NetworkTableEntry ta; //The rectangular area of the vision target
  NetworkTableEntry tv; //Whether the limelight has sany valid targets (0 or 1)
  NetworkTableEntry da; //Target area (0% of image to 100% of image
  int cM = 1, pL = 0; //Default values for the camMode and pipeline

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);


    camMode = table.getEntry("camMode");
    pipeline = table.getEntry("pipeline");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    tv = table.getEntry("tv");
    ta = table.getEntry("ta");

    camMode.setFlags(cM);
    pipeline.setNumber(pL);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    IM.update();
    
    // -------------------- START LIMELIGHT CODE ------------------- //
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double v = tv.getDouble(0.0);
    double area = ta.getDouble(0.0);

    SmartDashboard.putNumber("L X", x);
    SmartDashboard.putNumber("L Y", y);
    SmartDashboard.putNumber("L AREA", area);
    SmartDashboard.putNumber("L Pipeline", (double) pipeline.getNumber(pL));
    SmartDashboard.putNumber("Vision Target?", v);

    MC.setLime(IM.getLime(), x, y, v, area);
    MC.setLimeTrack(x, y, v, area);
    MC.setPositionControl();

    // CLIMBING --------------------------------------------------------
    MC.setGrapplerExtended(IM.getGrapplerExtended());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
    Joystick joystick1 = new Joystick(0);
    SmartDashboard.putNumber("Joystick X value", joystick1.getX());


  }
}
