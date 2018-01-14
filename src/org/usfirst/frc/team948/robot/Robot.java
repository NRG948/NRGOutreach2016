
package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team948.robot.commands.DefaultDrive;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.Shoot;
import org.usfirst.frc.team948.robot.subsystems.Drive;
import org.usfirst.frc.team948.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static final Drive drive = new Drive();
	public static final Shooter shooter = new Shooter();
	public static OI oi;

	private Timer timer = new Timer();

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		// instantiate the command used for the autonomous period
		RobotMap.init();
		OI.buttonInit();
		SmartDashboard.putData("Reset Sensors", new ResetSensors());
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		periodicAll();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		timer.reset();
		timer.start();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		if (timer.get() >= 3) {
			timer.reset();
			RobotMap.green.set(false);
			RobotMap.blue.set(true);
		} else if (timer.get() >= 2) {
			RobotMap.green.set(true);
			RobotMap.blue.set(false);
		} else if (timer.get() >= 1) {
			RobotMap.green.set(true);
			RobotMap.blue.set(true);
		} else if (timer.get() >= 0) {
			RobotMap.green.set(false);
			RobotMap.blue.set(true);
		}
		periodicAll();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void periodicAll() {
//		SmartDashboard.putBoolean("catapult switch", RobotMap.catapultSwitch.get());
	}
}
