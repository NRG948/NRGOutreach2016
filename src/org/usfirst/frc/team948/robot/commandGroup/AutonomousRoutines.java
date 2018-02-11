package org.usfirst.frc.team948.robot.commandGroup;

import static org.usfirst.frc.team948.robot.Robot.AutoPosition.RED_LEFT;
import static org.usfirst.frc.team948.robot.Robot.AutoPosition.RED_RIGHT;
import static org.usfirst.frc.team948.robot.Robot.AutoPosition.RED_CENTER;
import static org.usfirst.frc.team948.robot.Robot.AutoPosition.BLUE_LEFT;
import static org.usfirst.frc.team948.robot.Robot.AutoPosition.BLUE_RIGHT;
import static org.usfirst.frc.team948.robot.Robot.AutoPosition.BLUE_CENTER;



import org.usfirst.frc.team948.robot.OI;
import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.Robot.AutoMovement;
import org.usfirst.frc.team948.robot.Robot.AutoPosition;
import org.usfirst.frc.team948.robot.OI.Side;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistanceNoPID;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.SetDriveScale;
import org.usfirst.frc.team948.robot.commands.TurnToHeadingNoPID;
import org.usfirst.frc.team948.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Selects the auto routine to run based on the SmartDashboard choosers. 
 */
public class AutonomousRoutines extends CommandGroup {
	private AutoMovement autoMovement;
	private AutoPosition autoPosition;

	public AutonomousRoutines() {
		addSequential(new ResetSensors());
		autoMovement = Robot.autoMovementChooser.getSelected();
		autoPosition = Robot.autoPositionChooser.getSelected();
		System.out.println("Auto Movement is : " + autoMovement);
		System.out.println("Auto Position is : " + autoPosition);

		switch (autoMovement) {
		case LEFT_SWITCH:
			if (autoPosition == AutoPosition.RED_LEFT) {
				addSequential(new RedLeftToLeftSwitch());
			} else if (autoPosition == BLUE_LEFT) {
				addSequential(new BlueLeftToLeftSwitch());
			} else if (autoPosition == RED_CENTER) {
				addSequential(new RedMiddleToLeftSwitch());
			} else if (autoPosition == BLUE_CENTER) {
				addSequential(new BlueMiddleToLeftSwitch());
			} else if (autoPosition == RED_RIGHT) {
				addSequential(new RedRightToLeftSwitch());
			} else if (autoPosition == BLUE_RIGHT) {
				addSequential(new BlueRightToLeftSwitch());
			}
			break;
		case RIGHT_SWITCH:
			if (autoPosition == RED_RIGHT) {
				addSequential(new RedRightToRightSwitch());
			} else if (autoPosition == BLUE_RIGHT) {
				addSequential(new BlueRightToRightSwitch());
			} else if (autoPosition == RED_CENTER) {
				addSequential(new RedMiddleToRightSwitch());
			} else if (autoPosition == BLUE_CENTER) {
				addSequential(new BlueMiddleToRightSwitch());
			} else if (autoPosition == RED_LEFT) {
				addSequential(new RedLeftToRightSwitch());
			} else if (autoPosition == BLUE_LEFT) {
				addSequential(new BlueLeftToRightSwitch());
			}
			break;
		case RIGHT_SCALE:
			if (autoPosition == RED_RIGHT) {
				addSequential(new RedRightToRightScale());
			} else if (autoPosition == BLUE_RIGHT) {
				addSequential(new BlueRightToRightScale());
			} else if (autoPosition == RED_CENTER) {
				addSequential(new RedMiddleToRightScale());
			} else if (autoPosition == BLUE_CENTER) {
				addSequential(new BlueMiddleToRightScale());
			}
			break;
		case LEFT_SCALE:
			if (autoPosition==AutoPosition.BLUE_LEFT) {
				addSequential(new BlueLeftToLeftScale());
			} else if (autoPosition == RED_CENTER) {
				addSequential(new RedMiddleToLeftScale());
			} else if (autoPosition == BLUE_CENTER) {
				addSequential(new BlueMiddleToLeftScale());
			} else if (autoPosition == RED_LEFT) {
				addSequential(new RedLeftToLeftScale());
			}
			break;
		}

	}

	public class RedLeftToLeftSwitch extends CommandGroup {
		public RedLeftToLeftSwitch() {
			addSequential(new SetDriveScale(1));
			addSequential(new DriveStraightDistanceNoPID(149.5));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(20.875));
			// drop the cube
		}
	}

	public class RedLeftToLeftScale extends CommandGroup {
		public RedLeftToLeftScale() {
			addSequential(new SetDriveScale(0.6));
			addSequential(new DriveStraightDistanceNoPID(305.5));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(20.875));
		}
	}

	public class RedLeftToRightSwitch extends CommandGroup {
		public RedLeftToRightSwitch() {
			addSequential(new DriveStraightDistanceNoPID(207));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(212.75));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(45));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}

	public class RedMiddleToRightSwitch extends CommandGroup {
		public RedMiddleToRightSwitch() {
			addSequential(new DriveStraightDistanceNoPID(108.75));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(149.5));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}

	public class RedMiddleToLeftSwitch extends CommandGroup {
		public RedMiddleToLeftSwitch() {
			addSequential(new DriveStraightDistanceNoPID(84.75));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(148.5));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}

	public class RedMiddleToRightScale extends CommandGroup {
		public RedMiddleToRightScale() {

		}
	}

	public class RedMiddleToLeftScale extends CommandGroup {
		public RedMiddleToLeftScale() {

		}
	}

	public class RedRightToRightSwitch extends CommandGroup {
		public RedRightToRightSwitch() {
			addSequential(new DriveStraightDistanceNoPID(149.5));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20.875));
		}
	}

	public class RedRightToRightScale extends CommandGroup {
		public RedRightToRightScale() {
			addSequential(new DriveStraightDistanceNoPID(305.5));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(7.68));
		}
	}

	public class RedRightToLeftSwitch extends CommandGroup {
		public RedRightToLeftSwitch() {
			addSequential(new DriveStraightDistanceNoPID(207));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(212.7));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(45));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}

	public class BlueLeftToLeftSwitch extends CommandGroup {
		public BlueLeftToLeftSwitch() {
			addSequential(new DriveStraightDistanceNoPID(149.5));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20.875));
			// drop the cube
		}
	}

	public class BlueLeftToLeftScale extends CommandGroup {
		public BlueLeftToLeftScale() {
			addSequential(new DriveStraightDistanceNoPID(305.5));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20.875));
		}
	}

	public class BlueLeftToRightSwitch extends CommandGroup {
		public BlueLeftToRightSwitch() {
			addSequential(new DriveStraightDistanceNoPID(207));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(212.75));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(45));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}

	public class BlueMiddleToRightSwitch extends CommandGroup {
		public BlueMiddleToRightSwitch() {
			addSequential(new DriveStraightDistanceNoPID(108.75));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(149.5));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}

	public class BlueMiddleToLeftSwitch extends CommandGroup {
		public BlueMiddleToLeftSwitch() {
			addSequential(new DriveStraightDistanceNoPID(84.75));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(148.5));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}

	public class BlueMiddleToRightScale extends CommandGroup {
		public BlueMiddleToRightScale() {

		}
	}

	public class BlueMiddleToLeftScale extends CommandGroup {
		public BlueMiddleToLeftScale() {

		}
	}

	public class BlueRightToRightSwitch extends CommandGroup {
		public BlueRightToRightSwitch() {
			addSequential(new DriveStraightDistanceNoPID(149.5));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(20.875));
		}
	}

	public class BlueRightToRightScale extends CommandGroup {
		public BlueRightToRightScale() {
			addSequential(new DriveStraightDistanceNoPID(305.5));
			addSequential(new TurnToHeadingNoPID(90));
			addSequential(new DriveStraightDistanceNoPID(7.68));
		}
	}

	public class BlueRightToLeftSwitch extends CommandGroup {
		public BlueRightToLeftSwitch() {
			addSequential(new DriveStraightDistanceNoPID(207));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(212.7));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(45));
			addSequential(new TurnToHeadingNoPID(-90));
			addSequential(new DriveStraightDistanceNoPID(20));
		}
	}
}
