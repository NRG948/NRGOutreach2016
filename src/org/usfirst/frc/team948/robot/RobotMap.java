package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
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
	public static Encoder encoderRF;
	public static Encoder encoderRB;
	public static Encoder encoderLF;
	public static Encoder encoderLB;	
	public static SpeedController shooterArmTalon;
	public static Compressor c;
	public static DigitalInput catapultSwitch;
	public static Solenoid blue, green, lol;
	public static Solenoid cubeAcquirer;
	public static MecanumDrive mecanumDrive;
	public static Victor liftVictor; 
	public static void init(){
		c = new Compressor(0);
		cubeAcquirer = new Solenoid(4);
		lol = new Solenoid(0);
		blue = new Solenoid(5);
		green = new Solenoid(6);
		lol.set(true);
		blue.set(true);
		green.set(true);
		victorRF = new Victor(0);
		victorRB = new Victor(4);
		victorLF = new Victor(3);
		victorLB = new Victor(2);
		shooterArmTalon = new Talon(1);
//		catapultSwitch = new DigitalInput(9);
		mecanumDrive = new MecanumDrive(victorLF, victorLB, victorRF, victorRB);
		encoderRF = new Encoder(4, 5, false);
		encoderRB = new Encoder(6, 7, false);
		encoderLF = new Encoder(9, 8, true);
		encoderLB = new Encoder(2, 3, true);
		liftVictor = new Victor(5);
	}
}
