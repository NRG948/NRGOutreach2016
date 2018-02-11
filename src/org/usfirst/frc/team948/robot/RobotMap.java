package org.usfirst.frc.team948.robot;

import org.usfirst.frc.team948.robot.vision.IPixyLink;
import org.usfirst.frc.team948.robot.vision.PixyCam;
import org.usfirst.frc.team948.robot.vision.SPIwrapper;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static AnalogGyro gyro;
	public static Victor victorRF;
	public static Victor victorRB;
	public static Victor victorLF;
	public static Victor victorLB;
	public static Encoder encoderRF;
	public static Encoder encoderRB;
	public static Encoder encoderLF;
	public static Encoder encoderLB;
	public static MecanumDrive mecanumDrive;
	
	public static Victor aqVictorR;
	public static Victor aqVictorL;
	public static DoubleSolenoid cubeAcquirer;
	
	public static Victor liftVictor;
	
	public static Compressor c;
	
	public static IPixyLink link;
	public static PixyCam pixy;

	public static void init(){
		c = new Compressor(0);
		victorRB = new Victor(0);
		victorRF = new Victor(1);
		victorLF = new Victor(3);
		victorLB = new Victor(2);
		victorRB.setInverted(true);
		victorRF.setInverted(true);
		victorLB.setInverted(true);
		victorLF.setInverted(true);
		mecanumDrive = new MecanumDrive(victorLF, victorLB, victorRF, victorRB);
		
		cubeAcquirer = new DoubleSolenoid(2, 3);
		aqVictorL = new Victor(5);
		aqVictorR = new Victor(6);
		
		liftVictor = new Victor(4);

		encoderRF = new Encoder(4, 5, true);
		encoderRF.setDistancePerPulse(1.0/107.7);
		encoderRB = new Encoder(6, 7, true);
		encoderRB.setDistancePerPulse(1.0/107.7);
		encoderLF = new Encoder(8, 9, false);
		encoderLF.setDistancePerPulse(1.0/107.7);
		encoderLB = new Encoder(2, 3, false);
		encoderLB.setDistancePerPulse(1.0/107.7);
		gyro = new AnalogGyro(0);
		
		link = new SPIwrapper(SPI.Port.kOnboardCS0);
		pixy = new PixyCam(link);
	}
}
