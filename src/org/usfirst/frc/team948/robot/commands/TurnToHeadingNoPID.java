package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToHeadingNoPID extends Command {
	
	double currentHeading;
	double heading;

    public TurnToHeadingNoPID(double heading) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.heading = heading;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("TurnToHeading("+heading+")");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentHeading = RobotMap.gyro.getAngle();
    	Robot.drive.rawDriveCartesian(0, 0, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(currentHeading >= heading) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("TurnToHeading.end() currentHeading:"+currentHeading);
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
