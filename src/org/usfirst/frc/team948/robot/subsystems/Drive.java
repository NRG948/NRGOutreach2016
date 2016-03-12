
package org.usfirst.frc.team948.robot.subsystems;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.commands.DefaultDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	private static boolean driveEnabled = true;
    private static final double a = 0.6;
  
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultDrive());
    }
    
      public void rawDrive(double velX, double velY, double omega){
    	if (driveEnabled) {
    		velY *= 0.7;
    		RobotMap.talonRF.set(-(velX+velY+omega*a));
    		RobotMap.talonRB.set(-(velY-velX+omega*a));
    		RobotMap.talonLB.set((velX+velY-omega*a));
    		RobotMap.talonLF.set(velY-velX-omega*a);
    	}
    	else {
    		RobotMap.talonRF.set(0);
    		RobotMap.talonRB.set(0);
    		RobotMap.talonLF.set(0);
    		RobotMap.talonLB.set(0);
    	}
    }
      
    public boolean getDrive() {
    	return driveEnabled;
    }
    
    public void switchDrive() {
    	driveEnabled = !driveEnabled;
    }
}

