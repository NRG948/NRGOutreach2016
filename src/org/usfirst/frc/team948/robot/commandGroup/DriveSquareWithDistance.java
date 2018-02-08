package org.usfirst.frc.team948.robot.commandGroup;

import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.SetDriveScale;
import org.usfirst.frc.team948.robot.subsystems.Drive;
import org.usfirst.frc.team948.robot.subsystems.Drive.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveSquareWithDistance extends CommandGroup {
	public DriveSquareWithDistance() {
		addSequential(new SetDriveScale(Drive.SCALE_HIGH));
		addSequential(new DriveStraightDistance(0.5, 48, Direction.FORWARD));
		addSequential(new DriveStraightDistance(0.5, 48, Direction.RIGHT));
		addSequential(new DriveStraightDistance(0.5, 48, Direction.BACKWARD));
		addSequential(new DriveStraightDistance(0.5, 48, Direction.LEFT));
		addSequential(new SetDriveScale(Drive.SCALE_LOW));
	}
}
