// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;
import frc.robot.Limelight;

public class DriveDistanceFromTag extends CommandBase {

  private DriveSystem drive;
  private Limelight limelight;

  /** Creates a new DriveDistanceFromTag. */
  public DriveDistanceFromTag(DriveSystem drive, Limelight limelight, double distance) {

    drive = new DriveSystem();
    limelight = new Limelight();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    limelight.getHorizontalDistance();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
