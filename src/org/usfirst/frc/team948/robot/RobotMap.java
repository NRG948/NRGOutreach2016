package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static SpeedController talonRF;
	public static SpeedController talonRB;
	public static SpeedController talonLF;
	public static SpeedController talonLB;
	public static SpeedController shooterArmTalon;
	public static Compressor c;
	public static DigitalInput catapultSwitch;
	public static Solenoid blue, green, lol;
	public static void init(){
		c = new Compressor(0);
		lol = new Solenoid(0);
		blue = new Solenoid(5);
		green = new Solenoid(6);
		lol.set(true);
		blue.set(true);
		green.set(true);
		talonRF = new Talon(0);
		talonRB = new Talon(4);
		talonLF = new Talon(3);
		talonLB = new Talon(2);
		shooterArmTalon = new Talon(1);
		catapultSwitch = new DigitalInput(9);
		
	}
}
