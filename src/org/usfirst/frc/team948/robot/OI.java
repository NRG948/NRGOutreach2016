package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team948.robot.commands.DefaultDrive;
import org.usfirst.frc.team948.robot.commands.Shoot;
import org.usfirst.frc.team948.robot.commands.SwitchDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final double Sensitivity = Preferences.getInstance().getDouble("Sensitivity", 1/5);
	public static final Joystick leftJoystick = new Joystick(2);
	public static final Button shootButton = new JoystickButton(leftJoystick, 1);
	public static final Button switchButton = new JoystickButton(leftJoystick, 2);
	
	public static void buttonInit() {
		shootButton.whenPressed(new Shoot(0.4));
		switchButton.whenPressed(new SwitchDrive());
	}
	
	public static double getX(){
		return Math.abs(leftJoystick.getRawAxis(0))>0.05? leftJoystick.getRawAxis(0)*Sensitivity: 0;
	}  
	public static double getY(){
		return Math.abs(leftJoystick.getRawAxis(1))>0.05? leftJoystick.getRawAxis(1)*Sensitivity : 0;
	}
	public static double getRot(){
		if(Math.abs(leftJoystick.getRawAxis(2))>0.0){
			return leftJoystick.getRawAxis(2)/2;
		}else{
			return -leftJoystick.getRawAxis(2)/2;
		}
	}
}

