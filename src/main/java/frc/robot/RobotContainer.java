// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import devices connected to driver station
import edu.wpi.first.wpilibj.GenericHID;

// import xbox controller
import edu.wpi.first.wpilibj.XboxController;

// import commands
import frc.robot.subsystems.ClimbAuto;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

// import joystick buttons
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static edu.wpi.first.wpilibj.XboxController.Axis.*;
import static edu.wpi.first.wpilibj.XboxController.Button.*;

import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  // define ClimAuto subsystem
  private final ClimbAuto climbAuto = new ClimbAuto();

  // define xbox controller
  private XboxController xbox = new XboxController(kXboxPort);

  //auto climb with limit switches (version 1.0)

  private SequentialCommandGroup climb = new SequentialCommandGroup(
    
    // sets arm piston to true, reaching arm out fully
    new InstantCommand(()-> climbAuto.reaching(true)),
    
    /*could use encoders and a PID loop for smoother movements on lift*/
    
    // moves lift up at 40% speed until lift limit switch is hit
    new RunCommand(() -> climbAuto.move(liftUpSpeed)).withInterrupt(climbAuto::isLiftExtended),

    // sets piston to false, moving arm back fully until it is vertical
    new InstantCommand(()-> climbAuto.reaching(false)),

    // moves lift down at 40% speed until lift limit switch is pressed
    new RunCommand(() -> climbAuto.move(liftDownSpeed)).withInterrupt(climbAuto::isHookEngaged),
    
  /**sequential command is repeated**/

    // sets arm piston to true, reaching arm out fully
    new InstantCommand(()-> climbAuto.reaching(true)),
        
    // moves lift up at 40% speed until lift limit switch is hit
    new RunCommand(() -> climbAuto.move(liftUpSpeed)).withInterrupt(climbAuto::isLiftExtended),

    // sets piston to false, moving arm back fully until it is vertical
    new InstantCommand(()-> climbAuto.reaching(false)),

    // moves lift down at 40% speed until lift limit switch is pressed
    new RunCommand(() -> climbAuto.move(liftDownSpeed)).withInterrupt(climbAuto::isHookEngaged)
    

  );


  private Command moveArm = new RunCommand(
  
  // move the lift up and down with right and left triggers, respectively
  // checking for whether hook is engaged, 
    // if it is: run the climb sequential command group
    // if it is not: run an empty instant command group (does nothing)
    () -> climbAuto.move(xbox.getRawAxis(kRightTrigger.value) - xbox.getRawAxis(kLeftTrigger.value)), climbAuto)
    .andThen(new ConditionalCommand(climb, new InstantCommand(), climbAuto::isHookEngaged));

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    climbAuto.setDefaultCommand(moveArm);
    

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
}
