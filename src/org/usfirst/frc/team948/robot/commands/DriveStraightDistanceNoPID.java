package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightDistanceNoPID extends Command {
	
	double distance;
	double currentdistance;
	private double startEncoderLeftFront;
	private double startEncoderLeftBack;
	private double startEncoderRightFront;
	private double startEncoderRightBack;

    public DriveStraightDistanceNoPID(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("DriveStraighDistanceNoPID("+distance+")");
    	startEncoderLeftBack = RobotMap.encoderLB.getDistance();
		startEncoderRightBack = RobotMap.encoderRB.getDistance();
		startEncoderLeftFront = RobotMap.encoderLF.getDistance();
		startEncoderRightFront = RobotMap.encoderRF.getDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double distanceTravelledLB = RobotMap.encoderLB.getDistance() - startEncoderLeftBack;
		double distanceTravelledRB = RobotMap.encoderRB.getDistance() - startEncoderRightBack;
		double distanceTravelledLF = RobotMap.encoderLF.getDistance() - startEncoderLeftFront;
		double distanceTravelledRF = RobotMap.encoderRF.getDistance() - startEncoderRightFront;
		double yDisplacement = ((distanceTravelledLF + distanceTravelledRF)
				+ (distanceTravelledLB + distanceTravelledRB)) / 4;
		currentdistance = Math.abs(yDisplacement);
    	Robot.drive.rawDriveCartesian(0, 1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(currentdistance >= distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("DriveStraighDistanceNoPID.end()");
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
