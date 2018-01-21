package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team948.robot.commands.AcquireClose;
import org.usfirst.frc.team948.robot.commands.AcquireOpen;
import org.usfirst.frc.team948.robot.commands.CubeLift;
import org.usfirst.frc.team948.robot.commands.DefaultDrive;
import org.usfirst.frc.team948.robot.commands.MotorAcquire;
import org.usfirst.frc.team948.robot.commands.SetDriveScale;
import org.usfirst.frc.team948.robot.commands.SwitchDrive;
import org.usfirst.frc.team948.robot.subsystems.Drive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final double Sensitivity = Preferences.getInstance().getDouble("Sensitivity", 1/5);
	public static final Joystick leftJoystick = new Joystick(2);
	public static final Button shiftButton = new JoystickButton(leftJoystick, 1);
	public static final Button acquireButton = new JoystickButton(leftJoystick, 2);
	public static final Button liftUpButton = new JoystickButton(leftJoystick, 3);
	public static final Button liftDownButton = new JoystickButton(leftJoystick, 4);
	

	public static final Joystick xboxController = new Joystick(1);
	public static final Button xboxA = new JoystickButton(xboxController, 1);
	public static final Button xboxB = new JoystickButton(xboxController, 2);
	public static final Button xboxX = new JoystickButton(xboxController, 3);
	public static final Button xboxY = new JoystickButton(xboxController, 4);
	public static final Button rightBumper = new JoystickButton(xboxController, 5);
	public static final Button leftBumper = new JoystickButton(xboxController, 6);	
	

	
	public static void buttonInit() {
		shiftButton.whenPressed(new SetDriveScale(Drive.SCALE_HIGH));
		shiftButton.whenReleased(new SetDriveScale(Drive.SCALE_LOW));
		rightBumper.whenPressed(new MotorAcquire(1));
		rightBumper.whenReleased(new MotorAcquire(0));
		leftBumper.whenPressed(new MotorAcquire(-1));
		leftBumper.whenReleased(new MotorAcquire(0));
		xboxA.whenPressed(new CubeLift(0.5));
		xboxA.whenReleased(new CubeLift(0));
		xboxY.whenPressed(new CubeLift(-1));
		xboxY.whenReleased(new CubeLift(0));
		
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