
package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team948.robot.commands.AcquireClose;
import org.usfirst.frc.team948.robot.commands.AcquireOpen;
import org.usfirst.frc.team948.robot.commands.CubeLift;
import org.usfirst.frc.team948.robot.commands.DefaultDrive;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.SetDriveScale;
import org.usfirst.frc.team948.robot.commands.TimedDrive;
import org.usfirst.frc.team948.robot.commands.TurnToHeading;
import org.usfirst.frc.team948.robot.commands.TurnToHeadingNoPID;
import org.usfirst.frc.team948.robot.subsystems.CubeAcquirer;
import org.usfirst.frc.team948.robot.subsystems.CubeLifter;
import org.usfirst.frc.team948.robot.subsystems.Drive;
import org.usfirst.frc.team948.robot.OI;
import org.usfirst.frc.team948.robot.Robot.AutoMovement;
import org.usfirst.frc.team948.robot.Robot.AutoPosition;
import org.usfirst.frc.team948.robot.commandGroups.AutonomousRoutines;
import org.usfirst.frc.team948.robot.commandGroups.DriveSquareWithDistance;

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
	public static SendableChooser<AutoPosition> autoPositionChooser;
	public static SendableChooser<AutoMovement> autoMovementChooser;
	
	public enum AutoPosition {
		RED_LEFT, RED_CENTER, RED_RIGHT, BLUE_LEFT, BLUE_CENTER, BLUE_RIGHT
	}
	public enum AutoMovement {
		RIGHT_SWITCH, LEFT_SWITCH, LEFT_SCALE, RIGHT_SCALE
	}
	
	public static OI oi;

	private Timer timer = new Timer();

	public static Command autonomousCommand;

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
		SmartDashboard.putData("DriveStraightDistanceF", new DriveStraightDistance(2.0,24,Drive.Direction.FORWARD));
		SmartDashboard.putData("DriveStraightDistanceB", new DriveStraightDistance(2.0,24,Drive.Direction.BACKWARD));
		SmartDashboard.putData("DriveStraightDistanceL", new DriveStraightDistance(2.0,24,Drive.Direction.LEFT));
		SmartDashboard.putData("DriveStraightDistanceR", new DriveStraightDistance(2.0,24,Drive.Direction.RIGHT));
		SmartDashboard.putData("DriveSquareWithDistance", new DriveSquareWithDistance());
		SmartDashboard.putData("Set Scale High", new SetDriveScale(Drive.SCALE_HIGH));
		SmartDashboard.putData("Set Scale Low", new SetDriveScale(Drive.SCALE_LOW));
		SmartDashboard.putData("TurnToHeading 90", new TurnToHeading(90));
		SmartDashboard.putData("TurnToHeadingNoPID 90", new TurnToHeadingNoPID(90));
		
		autoPositionChooser = new SendableChooser<AutoPosition>();
		autoPositionChooser.addDefault("Red left", AutoPosition.RED_LEFT);
		autoPositionChooser.addObject("Red center", AutoPosition.RED_CENTER);
		autoPositionChooser.addObject("Red right", AutoPosition.RED_RIGHT);
		autoPositionChooser.addObject("Blue left", AutoPosition.BLUE_LEFT);
		autoPositionChooser.addObject("Blue center", AutoPosition.BLUE_CENTER);
		autoPositionChooser.addObject("Blue right", AutoPosition.BLUE_RIGHT);

		autoMovementChooser = new SendableChooser<AutoMovement>();
		autoMovementChooser.addObject("Left Scale", AutoMovement.LEFT_SCALE);
		autoMovementChooser.addObject("Left Switch", AutoMovement.LEFT_SWITCH);
		autoMovementChooser.addObject("Right Switch", AutoMovement.RIGHT_SWITCH);
		autoMovementChooser.addDefault("Right Scale", AutoMovement.RIGHT_SCALE);

		SmartDashboard.putData("Choose autonomous position", autoPositionChooser);
		SmartDashboard.putData("Choose autonomous movement", autoMovementChooser);
		
		RobotMap.pixy.startVisionThread();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void autonomousInit() {
		System.out.println("auto init");
		autonomousCommand = new AutonomousRoutines();
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
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void robotPeriodic() {
//		SmartDashboard.putBoolean("catapult switch", RobotMap.catapultSwitch.get());
//		SmartDashboard.putString("Alliance Scale Side", OI.getScaleSide().toString());
//		SmartDashboard.putString("Alliance Switch Side", OI.getAllianceSwitchSide().toString());
//		SmartDashboard.putString("Opposing Switch Side", OI.getOppsingSwitchSide().toString());

		SmartDashboard.putNumber("Xbox left trigger", OI.getTriggerL());
		SmartDashboard.putNumber("Xbox right trigger", OI.getTriggerR());
		
		SmartDashboard.putNumber("get x Joystick", OI.getX());
		SmartDashboard.putNumber("get y Joystick ", OI.getY());
	}
}
