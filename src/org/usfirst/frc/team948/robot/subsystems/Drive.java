
package org.usfirst.frc.team948.robot.subsystems;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.commands.DefaultDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {
	
	// According to RobotDrive.mecanumDrive_Cartesian in WPILib:
    //
    // LF =  x + y + rot    RF = -x + y - rot
    // LR = -x + y + rot    RR =  x + y - rot
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
	public enum Direction {
		FORWARD, BACKWARD, LEFT, RIGHT
	}
	@Override
	public void periodic() {
		// TODO Auto-generated method stub
		SmartDashboard.putData("encoderLF", RobotMap.encoderLF);
		SmartDashboard.putData("encoderRF", RobotMap.encoderRF);
		SmartDashboard.putData("encoderRB", RobotMap.encoderRB);
		SmartDashboard.putData("encoderLB", RobotMap.encoderLB);

//		SmartDashboard.putData("xPosition", Drive.xPosition);
//		SmartDashboard.putData("yPosition", Drive.yPosition);
//		SmartDashboard.putData("rotationPosition", Drive.rotationPosition);
		
	}

	private static boolean driveEnabled = true;
	public static final double SCALE_HIGH = 1.0;
	public static final double SCALE_LOW = 0.5;

	public static double encoderLF = RobotMap.encoderLF.get();
	public static double encoderLB = RobotMap.encoderLB.get();
	public static double encoderRF = RobotMap.encoderRF.get();
	public static double encoderRB = RobotMap.encoderRB.get();
	
//	 xPos = ((lfEnc + rrEnc) - (rfEnc + lrEnc))*xScale/4.0;
//    yPos = (lfEnc + lrEnc + rfEnc + rrEnc)*yScale/4.0;
//    rotPos = ((lfEnc + lrEnc) - (rfEnc + rrEnc))*rotScale/4.0;
	
	public static double xPosition = ((encoderLF + encoderRB) - (encoderRF + encoderLB)) / 4.0;
	public static double yPosition = (encoderLF + encoderLB + encoderRF + encoderRB) / 4.0;
	public static double rotationPosition = ((encoderLF + encoderLB) - (encoderRF + encoderRB)) / 4.0;
	
    private static final double a = 0.6;
    private double scale = SCALE_LOW;
    
    
    public void setScale(double s) {
    	scale = s;
    }
  
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultDrive());
    }
    
      public void rawDrive(double velX, double velY, double omega){
    	if (driveEnabled) {
    		velY *= scale;
    		velX *= scale;
    		RobotMap.mecanumDrive.driveCartesian(-velX, velY, omega);
//    		RobotMap.talonRF.set(-(velX+velY+omega*a));
//    		RobotMap.talonRB.set(-(velY-velX+omega*a));
//    		RobotMap.talonLB.set((velX+velY-omega*a));
//    		RobotMap.talonLF.set(velY-velX-omega*a);
    	}
    	else {
    		RobotMap.victorRF.set(0);
    		RobotMap.victorRB.set(0);
    		RobotMap.victorLF.set(0);
    		RobotMap.victorLB.set(0);
    	}
    }
      
    public boolean getDrive() {
    	return driveEnabled;
    }
    
    public void switchDrive() {
    	driveEnabled = !driveEnabled;
    }
}

