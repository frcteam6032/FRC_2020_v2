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

public class Belts extends SubsystemBase {
  /**
   * Creates a new Belts.
   */
  CANSparkMax m_beltMotor = new CANSparkMax(Constants.BELT_MOTOR_ID, MotorType.kBrushless);

  public Belts() {

  }

  public void suck() {
    m_beltMotor.set(0.5);
  }

  public void yeet() {
    m_beltMotor.set(-0.5);
  }

  public void stop() {
    m_beltMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
