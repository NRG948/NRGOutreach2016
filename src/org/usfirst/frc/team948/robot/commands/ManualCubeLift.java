package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.OI;
import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ManualCubeLift extends Command{
	private final double LIFT_POWER_SCALE_UP = 1;
	private final double LIFT_POWER_SCALE_DOWN = 0.3;	
	public ManualCubeLift() {
    	requires(Robot.cubeLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    			
    	double upSpeed = OI.getTriggerL() * LIFT_POWER_SCALE_UP;
    	double downSpeed = OI.getTriggerR() * LIFT_POWER_SCALE_DOWN;
    	
    	
    	if(upSpeed > 0) {
//    		System.out.println("SOMEBODY ONCE TOLD ME THE WORLD IS GONNA ROLL ME");
    		RobotMap.liftVictor.set(-upSpeed);
    	} else {
    		RobotMap.liftVictor.set(downSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
