// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSystem extends SubsystemBase {

  private AHRS navX;

  private WPI_TalonSRX motorLeftOne;
  private WPI_TalonSRX motorLeftTwo;
  private MotorControllerGroup leftMotorGroup;
  
  private WPI_TalonSRX motorRightOne;
  private WPI_TalonSRX motorRightTwo;
  private MotorControllerGroup rightMotorGroup;

  private final DifferentialDrive drive;

  private double speedMultiplier;


  /** Creates a new DriveSystem. */
  public DriveSystem() {

    motorLeftOne = new WPI_TalonSRX(1);
    motorLeftTwo = new WPI_TalonSRX(2);
    leftMotorGroup = new MotorControllerGroup(motorLeftOne, motorLeftTwo);

    motorRightOne = new WPI_TalonSRX(3);
    motorRightTwo = new WPI_TalonSRX(4);
    rightMotorGroup = new MotorControllerGroup(motorRightOne, motorRightTwo);

    navX = new AHRS();

    leftMotorGroup.setInverted(true);

    drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

    speedMultiplier = 0.8;
  }

  public void drive(double leftSpeed, double rightSpeed){

    double leftVelocity = leftSpeed * speedMultiplier;
    double rightVelocity = rightSpeed * speedMultiplier;

    drive.tankDrive(leftVelocity, rightVelocity);

  }

  public CommandBase autoBalance(){


    return runEnd(

      // what it do when it run
      () -> {

        double maxPercentOutput = 0.85;
        double angle = -navX.getRoll(); // Negative because of robot orientation
        double maxAngle = 20;
        double speed = (angle / maxAngle) * maxPercentOutput; // Speed is proportional to the angle
       
        if (speed > 1) {
          speed = 1;
        }
        
        if (speed < -1) {
          speed = -1;
        }
        
        double tolerance = 3;
        //Add a variable called "tolerance" in degrees

        //Change the logig of oyur if statement to say if the angle is inside tolerance, don't move, otherwise move.

        System.out.println("Angle: " + angle);
        System.out.println("Speed: " + speed);

        if (angle < tolerance && angle > -tolerance) {
            drive(0, 0);
        } 
        else {
          drive(speed, speed);
        }

      },

      //what it do when it end
      () -> {
        drive(0, 0);
      }

    );
  }

  @Override
  public void initSendable(SendableBuilder builder){
    builder.addDoubleProperty(
      "Speed Multiplier", 
      () -> speedMultiplier, 
      (double mult) -> {speedMultiplier = mult;}
    );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
