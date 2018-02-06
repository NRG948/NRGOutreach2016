
package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team948.robot.commandGroup.DriveSquareWithDistance;
import org.usfirst.frc.team948.robot.commands.AcquireClose;
import org.usfirst.frc.team948.robot.commands.AcquireOpen;
import org.usfirst.frc.team948.robot.commands.CubeLift;
import org.usfirst.frc.team948.robot.commands.DefaultDrive;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.TimedDrive;
import org.usfirst.frc.team948.robot.subsystems.CubeAcquirer;
import org.usfirst.frc.team948.robot.subsystems.CubeLifter;
import org.usfirst.frc.team948.robot.subsystems.Drive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	public static final Drive drive = new Drive();
	public static final CubeAcquirer acquirer = new CubeAcquirer();
	public static final CubeLifter cubeLifter = new CubeLifter();
	
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
		SmartDashboard.putData("Spin", new TimedDrive(0, 0, 0.5, 10));
		SmartDashboard.putData("Strafe Right", new TimedDrive(0.8, 0, 0, 2));
		SmartDashboard.putData("Strafe Left", new TimedDrive(-0.8, 0, 0, 2));
		SmartDashboard.putData("Straight", new TimedDrive(0, 0.8, 0, 2));
		SmartDashboard.putData("Cube Acquirer Close", new AcquireClose());
		SmartDashboard.putData("Cube Acquirer Open", new AcquireOpen());
		SmartDashboard.putData("DriveStraightDistanceF", new DriveStraightDistance(2.0,2000,Drive.Direction.FORWARD));
		SmartDashboard.putData("DriveStraightDistanceB", new DriveStraightDistance(2.0,2000,Drive.Direction.BACKWARD));
		SmartDashboard.putData("DriveStraightDistanceL", new DriveStraightDistance(2.0,2000,Drive.Direction.LEFT));
		SmartDashboard.putData("DriveStraightDistanceR", new DriveStraightDistance(2.0,2000,Drive.Direction.RIGHT));
		SmartDashboard.putData("DriveStraightDistanceR", new DriveSquareWithDistance());

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
		periodicAll();
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
		periodicAll();
		SmartDashboard.putNumber("Xbox left trigger", OI.getTriggerL());
		SmartDashboard.putNumber("Xbox right trigger", OI.getTriggerR());
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
