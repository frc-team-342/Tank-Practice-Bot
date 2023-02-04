// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.auto.ManualDrive;
import frc.robot.commands.auto.TimedDrive;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
  private DriveSystem drive;
  private Limelight limelight;

  private DriveWithJoysticks driveWithJoysticks;

  private Command autoLevel;
  
  private InstantCommand togglePipline;
  private JoystickButton togglePipelineButton;


  private TimedDrive timedDriveCommand;
  private TimedDrive timedDrive;

  private ManualDrive manualDriveCommand;

  private JoystickButton trigger;

  private Joystick leftJoy;
  private Joystick rightJoy;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    drive = new DriveSystem();
    limelight = new Limelight();

    leftJoy = new Joystick(Constants.OperatorConstants.joyLeftPort);
    rightJoy = new Joystick(Constants.OperatorConstants.joyRightPort);

    trigger = new JoystickButton(leftJoy, Constants.OperatorConstants.AUTO_LEVEL_BUTTON);

    driveWithJoysticks = new DriveWithJoysticks(drive, leftJoy, rightJoy);
    drive.setDefaultCommand(driveWithJoysticks);

    autoLevel = new RepeatCommand(drive.autoBalance());
    
    timedDriveCommand = new TimedDrive(drive, Constants.OperatorConstants.PERCENT, Constants.OperatorConstants.SECS);


    togglePipline = new InstantCommand(limelight::togglePipeline);
    togglePipelineButton = new JoystickButton(leftJoy, 5);


    SmartDashboard.putData(drive);
    SmartDashboard.putData(limelight);

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
      trigger.whileTrue(autoLevel);
      togglePipelineButton.whileTrue(togglePipline);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    SequentialCommandGroup commandGroupAuto = 
    new SequentialCommandGroup(
      drive.rotateAngle(135).repeatedly().withTimeout(.5)
      // .withTimeout(5)
      // autoLevel.
      // withTimeout(10).
      // until(() -> drive.balanced())
      );
      
    return commandGroupAuto;
    
  }
}
