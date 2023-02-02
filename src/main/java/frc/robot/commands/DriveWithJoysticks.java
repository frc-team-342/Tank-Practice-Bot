// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSystem;

public class DriveWithJoysticks extends CommandBase {

  private DriveSystem drive;
  private Joystick joyLeft;
  private Joystick joyRight;
  

  /** Creates a new DriveWithJoysticks. */
  public DriveWithJoysticks(DriveSystem drive, Joystick joyLeft, Joystick joyRight) {

    this.drive = drive;
    this.joyLeft = new Joystick(Constants.OperatorConstants.joyLeftPort);
    this.joyRight = new Joystick(Constants.OperatorConstants.joyRightPort);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double leftDeadband = MathUtil.applyDeadband(joyLeft.getY(), 0.05);
    double rightDeadband = MathUtil.applyDeadband(joyRight.getY(), 0.05);

    drive.drive(leftDeadband, rightDeadband);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
