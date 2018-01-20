package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedDrive extends Command {
	
	private double velX;
	private double velY;
	private double omega;
	private double duration;
	private Timer timer = new Timer();

    public TimedDrive(double velX, double velY, double omega, double duration) {
    	requires(Robot.drive);
       this.velX = velX;
       this.velY = velY;
       this.omega = omega;
       this.duration = duration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.rawDrive(velX, velY, omega);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.hasPeriodPassed(duration);
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	RobotMap.mecanumDrive.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
