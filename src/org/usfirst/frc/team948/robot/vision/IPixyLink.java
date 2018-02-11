package org.usfirst.frc.team948.robot.vision;

public interface IPixyLink {
	int getWord();

	byte getByte();

	void send(byte[] data); // i2c data send method
}
