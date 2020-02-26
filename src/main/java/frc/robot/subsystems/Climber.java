/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  CANSparkMax m_climberMotor = new CANSparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
  public Climber() {

  }

  public void raiseHook() {
    m_climberMotor.set(-0.5);
  }

  public void lowerHook() {
    m_climberMotor.set(0.5);
  }

  public void stop() {
    m_climberMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
