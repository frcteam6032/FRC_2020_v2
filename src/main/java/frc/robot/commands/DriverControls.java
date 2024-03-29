/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import java.lang.Math;

public class DriverControls extends CommandBase {
  /**
   * Creates a new DriverControls.
   */
  public DriverControls() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //double speed = Robot.robotContainer.getDriverRawAxis(1);
    //double rotation = Robot.robotContainer.getDriverRawAxis(0);
    //RobotContainer.driveTrain.arcadeDrive(speed, rotation);

    double triggerVal = Robot.robotContainer.getDriverRawAxis(Constants.RIGHT_TRIGGER) - Robot.robotContainer.getDriverRawAxis(Constants.LEFT_TRIGGER);
    double stick = (Robot.robotContainer.getDriverRawAxis(Constants.LEFT_STICK_X) * Constants.TURNING_RATE);

    SmartDashboard.putNumber("Stick Value", stick);

    if (Math.abs(stick) < 0.05 ) {
      stick = 0;
    }

    RobotContainer.driveTrain.setLeftMotors(triggerVal + stick);
    RobotContainer.driveTrain.setRightMotors(triggerVal - stick);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driveTrain.setLeftMotors(0);
    RobotContainer.driveTrain.setRightMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
