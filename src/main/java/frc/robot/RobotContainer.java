/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.commands.BeltSuck;
import frc.robot.commands.BeltYeet;
import frc.robot.commands.ClimberLowerHook;
import frc.robot.commands.ClimberRaiseHook;
import frc.robot.commands.CloseGate;
import frc.robot.commands.DriveStop;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.DriveTurnInPlace;
import frc.robot.commands.DriverControls;
import frc.robot.commands.IntakeSuck;
import frc.robot.commands.IntakeYeet;
import frc.robot.commands.OpenGate;
import frc.robot.commands.WheelSpin;
import frc.robot.subsystems.Belts;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gate;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WheelOfFortune;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  final Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER_PORT);
  final Joystick operatorController = new Joystick(Constants.OPERATOR_CONTROLLER_PORT);

  final Button oButtonA = new JoystickButton(this.operatorController, Constants.BUTTON_A);
  final Button oButtonB = new JoystickButton(this.operatorController, Constants.BUTTON_B);
  final Button oButtonX = new JoystickButton(this.operatorController, Constants.BUTTON_X);
  final Button oButtonY = new JoystickButton(this.operatorController, Constants.BUTTON_Y);

  final Button oDPadUp = new POVButton(this.operatorController, Constants.DPAD_UP);
  final Button oDPadDown = new POVButton(this.operatorController, Constants.DPAD_DOWN);

  final Button oButtonRightBumper = new JoystickButton(this.operatorController, Constants.RIGHT_BUMPER);
  final Button oButtonLeftBumper = new JoystickButton(this.operatorController, Constants.LEFT_BUMPER);

  public static DriveTrain driveTrain;
  public static Gate gate;
  public static Intake intake;
  public static Belts belts;
  public static Climber climber;
  public static WheelOfFortune wheelOfFortune;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    gate = new Gate();
    intake = new Intake();
    belts = new Belts();
    climber = new Climber();
    wheelOfFortune = new WheelOfFortune();

    CommandScheduler.getInstance().setDefaultCommand(driveTrain, new DriverControls());

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    oButtonA.whenPressed(new OpenGate().withTimeout(1));
    oButtonA.whenReleased(new CloseGate().withTimeout(1));

    oButtonRightBumper.whileActiveContinuous(new BeltSuck());

    oButtonLeftBumper.whileActiveContinuous(new BeltYeet());

    oDPadUp.whileActiveContinuous(new ClimberRaiseHook());

    oDPadDown.whileActiveContinuous(new ClimberLowerHook());

    oButtonB.whileActiveContinuous(new BeltYeet());
    oButtonB.whileActiveContinuous(new IntakeYeet());

    oButtonY.whileActiveContinuous(new WheelSpin());

    oButtonX.whileActiveContinuous(new BeltSuck());
    oButtonX.whileActiveContinuous(new IntakeSuck());
  }

  public double getDriverRawAxis(int axis) {
    return this.driverController.getRawAxis(axis);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return new DriveStraight(0.2).withTimeout(3);
    return new SequentialCommandGroup(new DriveStraight(0.3).withTimeout(3), new DriveStop().withTimeout(0.5),
     new DriveTurnInPlace(0.3).withTimeout(2),
      new ParallelCommandGroup(new IntakeYeet().withTimeout(1), new BeltYeet().withTimeout(1)));
  }
}
