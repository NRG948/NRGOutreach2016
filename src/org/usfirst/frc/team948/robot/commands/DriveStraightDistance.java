package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Units are in inches
public class DriveStraightDistance extends Command {
	private boolean powerInY = false;
	private double startEncoderLeftFront;
	private double startEncoderLeftBack;
	private double startEncoderRightFront;
	private double startEncoderRightBack;
	private double distanceTravelled = 0.0;

	private double power;
	private double distance;
	private Drive.Direction direction;
	private double currentHeading;

	public DriveStraightDistance(double power, double distance, Drive.Direction direction) {
		this.direction = direction;
		this.distance = Math.abs(distance);

		switch (direction) {
		case FORWARD:
			this.power = Math.abs(power);
			powerInY = true;
			break;
		case BACKWARD:
			this.power = -Math.abs(power);
			powerInY = true;
			break;
		case LEFT:
			this.power = -Math.abs(power);
			powerInY = false;
			break;
		case RIGHT:
			this.power = Math.abs(power);
			powerInY = false;
			break;
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		currentHeading = RobotMap.gyro.getAngle();
		distanceTravelled = 0.0;
		startEncoderLeftBack = RobotMap.encoderLB.getDistance();
		startEncoderRightBack = RobotMap.encoderRB.getDistance();
		startEncoderLeftFront = RobotMap.encoderLF.getDistance();
		startEncoderRightFront = RobotMap.encoderRF.getDistance();
		Robot.drive.driveHeadingPIDInit(RobotMap.gyro.getAngle(), 2.0);
		System.out.println(String.format("DriveStraightDistance()", power, distance, direction));
	}

	// According to RobotDrive.mecanumDrive_Cartesian in WPILib:
	//
	// LF = x + y + rot RF = -x + y - rot
	// LR = -x + y + rot RR = x + y - rot
	//
	// (LF + RR) - (RF + LR) = (2x + 2y) - (-2x + 2y)
	// => (LF + RR) - (RF + LR) = 4x
	// => x = ((LF + RR) - (RF + LR))/4
	//
	// LF + RF + LR + RR = 4y
	// => y = (LF + RF + LR + RR)/4
	//
	// (LF + LR) - (RF + RR) = (2y + 2rot) - (2y - 2rot)
	// => (LF + LR) - (RF + RR) = 4rot
	// => rot = ((LF + LR) - (RF + RR))/4
	protected void execute() {
		double distanceTravelledLB = RobotMap.encoderLB.getDistance() - startEncoderLeftBack;
		double distanceTravelledRB = RobotMap.encoderRB.getDistance() - startEncoderRightBack;
		double distanceTravelledLF = RobotMap.encoderLF.getDistance() - startEncoderLeftFront;
		double distanceTravelledRF = RobotMap.encoderRF.getDistance() - startEncoderRightFront;
		double xDisplacement = ((distanceTravelledLF + distanceTravelledRB)
				- (distanceTravelledRF + distanceTravelledLB)) / 4;
		double yDisplacement = ((distanceTravelledLF + distanceTravelledRF)
				+ (distanceTravelledLB + distanceTravelledRB)) / 4;
		// double rot = ((distanceTravelledLF + distanceTravelledLB) - (distanceTravelledRF + distanceTravelledRB)) / 4;
		SmartDashboard.putNumber("y displacement", yDisplacement);
		SmartDashboard.putNumber("x displacement", xDisplacement);
		if (powerInY) {
			Robot.drive.driveHeadingPIDExecute(0, power);
			distanceTravelled = Math.abs(yDisplacement);// If the command is to move robot forward or
												// backward, then only the y distance counts
		} else {
			Robot.drive.driveHeadingPIDExecute(power, 0);
			distanceTravelled = Math.abs(xDisplacement);// If the command is to move robot left or
														// right, then only the x distance counts
		}
		SmartDashboard.putNumber("distance travelled", distanceTravelled);
	}

	protected boolean isFinished() {
		return  (distanceTravelled >= distance); 
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.driveHeadingPIDEnd();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
