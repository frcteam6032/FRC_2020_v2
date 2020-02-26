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

public class Gate extends SubsystemBase {
  /**
   * Creates a new Gate.
   */
  CANSparkMax m_gateMotor = new CANSparkMax(Constants.GATE_MOTOR_ID, MotorType.kBrushed);
  public Gate() {

  }

  public void open() {
    m_gateMotor.set(.2);
  }

  public void close() {
    m_gateMotor.set(-.2);
  }

  public void passiveOpen() {
    m_gateMotor.set(0.05);
  }

  public void stop() {
    m_gateMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
