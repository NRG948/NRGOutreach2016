package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team948.robot.commands.AcquireClose;
import org.usfirst.frc.team948.robot.commands.AcquireOpen;
import org.usfirst.frc.team948.robot.commands.DefaultDrive;
import org.usfirst.frc.team948.robot.commands.SetDriveScale;
import org.usfirst.frc.team948.robot.commands.Shoot;
import org.usfirst.frc.team948.robot.commands.SwitchDrive;
import org.usfirst.frc.team948.robot.subsystems.Drive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final double Sensitivity = Preferences.getInstance().getDouble("Sensitivity", 1/5);
	public static final Joystick leftJoystick = new Joystick(2);
//	public static final Button shootButton = new JoystickButton(leftJoystick, 1);
//	public static final Button switchButton = new JoystickButton(leftJoystick, 2);
	public static final Button shiftButton = new JoystickButton(leftJoystick, 1);
	public static final Button acquireButton = new JoystickButton(leftJoystick, 2);
	
	public static void buttonInit() {
//		shootButton.whileHeld(new Shoot(0.5));
//		switchButton.whenPressed(new SwitchDrive());
		shiftButton.whenPressed(new SetDriveScale(Drive.SCALE_HIGH));
		shiftButton.whenReleased(new SetDriveScale(Drive.SCALE_LOW));
		acquireButton.whenPressed(new AcquireOpen());
		acquireButton.whenReleased(new AcquireClose());
	}
	
	public static double getX(){
		return leftJoystick.getX();
	}  
	public static double getY(){
		return leftJoystick.getY();
	}
	public static double getRot(){
		if(Math.abs(leftJoystick.getRawAxis(2))>0.0){
			return -leftJoystick.getRawAxis(2)/2;
		}else{
			return leftJoystick.getRawAxis(2)/2;
		}
	}
}

;