/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private CANSparkMax m_leftFront = new CANSparkMax(Constants.FRONT_LEFT_DRIVE_ID, MotorType.kBrushless);
  private CANSparkMax m_leftRear = new CANSparkMax(Constants.REAR_LEFT_DRIVE_ID, MotorType.kBrushless);
  private SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);

  private CANSparkMax m_rightFront = new CANSparkMax(Constants.FRONT_RIGHT_DRIVE_ID, MotorType.kBrushless);
  private CANSparkMax m_rightRear = new CANSparkMax(Constants.REAR_RIGHT_DRIVE_ID, MotorType.kBrushless);
  private SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);

  //private DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

  public DriveTrain() {

  }

  public void setLeftMotors(double speed) {
    m_left.set(speed);
  }

  public void setRightMotors(double speed) {
    m_right.set(-speed);
  }

  public void setBothMotors(double speed) {
    m_left.set(speed);
    m_right.set(-speed);
  }
  //public void arcadeDrive(double speed, double rotation) {
  //  m_drive.arcadeDrive(speed, rotation);
  //}




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
