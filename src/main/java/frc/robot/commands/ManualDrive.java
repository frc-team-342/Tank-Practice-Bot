// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class ManualDrive extends CommandBase {

  private DriveSystem drive;
  private double velocity;

  /** Creates a new ManualDrive. */
  public ManualDrive(DriveSystem drive, double velocity) {

    drive = this.drive;
    velocity = this.velocity;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drive.drive(velocity, velocity);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    drive.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
