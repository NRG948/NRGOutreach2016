package org.usfirst.frc.team948.robot.commandGroup;

import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.subsystems.Drive.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveSquareWithDistance extends CommandGroup {
	public DriveSquareWithDistance() {
		addSequential(new DriveStraightDistance(0.5, 470.4, Direction.FORWARD));
		addSequential(new DriveStraightDistance(0.5, 470.4, Direction.RIGHT));
		addSequential(new DriveStraightDistance(0.5, 470.4, Direction.BACKWARD));
		addSequential(new DriveStraightDistance(0.5, 470.4, Direction.LEFT));
	}
}
