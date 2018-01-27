package org.usfirst.frc.team948.robot.subsystems;

import org.usfirst.frc.team948.robot.commands.ManualCubeAcquire;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeAcquirer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualCubeAcquire());
    }
}

