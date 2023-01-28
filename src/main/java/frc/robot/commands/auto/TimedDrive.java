// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class TimedDrive extends CommandBase {

  private DriveSystem drive;
  private Timer timer;

  private double percent;
  private double secs;

    /** Creates a new AutoDrive. */
    public TimedDrive(DriveSystem drive, double percent, double secs) {
    
    this.drive = drive;
    this.timer = new Timer();

    this.percent = percent;
    this.secs = secs;

    addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

      timer.reset();
      timer.start();
    }
    

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

      drive.drive(-percent, -percent); // Temporary fix. Fix drivesystem + drive with joystick
      System.out.println("Command ran");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

      drive.drive(0,0);

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {

      return timer.hasElapsed(secs);
    
    }
}
