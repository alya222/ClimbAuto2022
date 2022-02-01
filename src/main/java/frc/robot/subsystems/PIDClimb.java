// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.subsystems.ClimbAuto;


public class PIDClimb extends PIDSubsystem {

  private ClimbAuto climbAuto;

  /** Creates a new PIDClimb. */
  public PIDClimb(ClimbAuto cAuto) {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.1, 0, 0));
    this.climbAuto = cAuto;
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here

  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
