package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

/**
 * A container class for SPARK MAX closed-loop PID parameters
 */
public class PIDMotor
{
	private CANSparkMax motor;
	public final CANPIDController pidController;
	public final CANEncoder encoder;
	private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput; // PID coefficients
	//private double maxRPM;
	private String name;

	public PIDMotor(CANSparkMax motor)
	{
		this(motor, "M"+motor.getDeviceId());
	}

	public PIDMotor(CANSparkMax motor, String name)
	{
		this(motor, name, 0.1, 0.0001, 1, 0, 0, 1, -1, 5700);
	}

	public PIDMotor(CANSparkMax motor, double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput, double kMinOutput, double maxRPM)
	{
		this(motor, "M"+motor.getDeviceId(), kP, kI, kD, kIz, kFF, kMinOutput, kMaxOutput, maxRPM);
	}

	public PIDMotor(CANSparkMax motor, String name, double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput, double kMinOutput, double maxRPM)
	{
		this.motor = motor;
		this.name = name;
		this.pidController = motor.getPIDController();
		setP(kP); 
        setI(kI);
        setD(kD); 
        setIZone(kIz); 
        setFF(kFF); 
        setMaxOutput( kMaxOutput );
		setMinOutput( kMinOutput );
		//setOutputRange(kMinOutput, kMaxOutput);
		
		//this.maxRPM = maxRPM;

		encoder = motor.getEncoder();
		//pidController.setFeedbackDevice(encoder); // SPARK MAX Example code doesn't bother doing this. IS NEEDED? We'll see. - Silas 2020 Jan 21
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setP(double p)
	{ kP = p; pidController.setP(p); }
	public double getP()
	{ return kP; }

	public void setI(double i)
	{ kI = i; pidController.setI(i); }
	public double getI()
	{ return kI; }

	public void setD(double d)
	{ kD = d; pidController.setD(d); }
	public double getD()
	{ return kD; }

	public void setIZone(double iz)
	{ kIz = iz; pidController.setIZone(iz); }
	public double getIZone()
	{ return kIz; }

	public void setFF(double ff)
	{ kFF = ff; pidController.setFF(ff); }
	public double getFF()
	{ return kFF; }

	public void setOutputRange(double min, double max)
	{ kMinOutput = min; kMaxOutput = max; pidController.setOutputRange(min, max); }

	public void setMinOutput(double min)
	{ setOutputRange(min, kMaxOutput); }
	public double getMinOutput()
	{ return kMinOutput; }

	public void setMaxOutput(double max)
	{ setOutputRange(kMinOutput, max); }
	public double getMaxOutput()
	{ return kMaxOutput; }

	/** Output PID parameters to the dashboard.*/
	public void dashboardPut()
	{
		SmartDashboard.putNumber(name+" P Gain", kP);
		SmartDashboard.putNumber(name+" I Gain", kI);
		SmartDashboard.putNumber(name+" D Gain", kD);
		SmartDashboard.putNumber(name+" I Zone", kIz);
		SmartDashboard.putNumber(name+" Feed Forward", kFF);
		SmartDashboard.putNumber(name+" Max Output", kMaxOutput);
		SmartDashboard.putNumber(name+" Min Output", kMinOutput);
	}

	/** Retrieve PID parameters from the dashboard. Call this once each periodic step. */
	public void dashboardGet()
	{
		double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);
	}
}