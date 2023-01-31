// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class FieldConstants {

    // A measure of the distance from an alliance wall to the nearest edge of the charge station. (In inches)
    public static final double chargeStationDistance = 60.69;

  }

  public static class OperatorConstants {
    public static final int joyLeftPort = 0;
    public static final int joyRightPort = 1;
    public static final int AUTO_LEVEL_BUTTON = 1;
    public static final double PERCENT = 0.75;
    public static final double SECS = 3;

  }

  public static class LimelightConstants {

    public static final double HEIGHT_TO_HIGH = 0;
    public static final double HEIGHT_TO_LOW = 0;
    public static final double HEIGHT_TO_MED = 0;
    public static final double MAX_VERT_OFFSET_FOR_LOW = 30.0;
    public static final double MAX_VERT_OFFSET_FOR_MED = 60.0;
    public static final double MAX_VERT_OFFSET_FOR_HIGH = 90.0;
    
    }

}
