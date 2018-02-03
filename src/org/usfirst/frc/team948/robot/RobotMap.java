package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Victor victorRF;
	public static Victor victorRB;
	public static Victor victorLF;
	public static Victor victorLB;
	public static Victor aqVictorR;
	public static Victor aqVictorL;
	public static Encoder encoderRF;
	public static Encoder encoderRB;
	public static Encoder encoderLF;
	public static Encoder encoderLB;	
	public static Compressor c;
	public static DoubleSolenoid cubeAcquirer;
	public static MecanumDrive mecanumDrive;
	public static Victor liftVictor; 
	public static void init(){
		c = new Compressor(0);
		cubeAcquirer = new DoubleSolenoid(2, 3);
		victorRB = new Victor(0);
		victorRF = new Victor(1);
		victorLF = new Victor(3);
		victorLB = new Victor(2);
		liftVictor = new Victor(4);
		aqVictorL = new Victor(5);
		aqVictorR = new Victor(6);
		
		mecanumDrive = new MecanumDrive(victorLF, victorLB, victorRF, victorRB);
		encoderRF = new Encoder(4, 5, false);
		encoderRB = new Encoder(6, 7, false);
		encoderLF = new Encoder(9, 8, true);
		encoderLB = new Encoder(2, 3, true);

	}
}
