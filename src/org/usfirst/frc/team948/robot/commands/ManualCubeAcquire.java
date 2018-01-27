package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.OI;
import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;

public class ManualCubeAcquire extends Command{
	
	public ManualCubeAcquire() {
    	requires(Robot.acquirer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double rSpeed = OI.xboxController.getY(Hand.kRight);
    	double lSpeed = OI.xboxController.getY(Hand.kLeft);
    	setAcquireSpeed(rSpeed, RobotMap.aqVictorR);
    	setAcquireSpeed(lSpeed, RobotMap.aqVictorL);
    }

	private void setAcquireSpeed(double speed, Victor victor) {
		if(Math.abs(speed) > 0.05) {
    		victor.set(speed);
    	} else {
    		victor.set(0);
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
    	RobotMap.aqVictorL.set(0);
    	RobotMap.aqVictorR.set(0);
    }

}
