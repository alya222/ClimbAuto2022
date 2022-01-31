// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import java.util.function.BooleanSupplier;

public class ClimbAuto extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
 
  //add a limit switch
  
  DigitalInput hangSwitch = new DigitalInput(0);
  DigitalInput liftSwitch = new DigitalInput(1);

  private Compressor airow = new Compressor(0, PneumaticsModuleType.CTREPCM);

  private Solenoid piston = new Solenoid(compressorModule, leftPoseMoverPort);

  public ClimbAuto() {

  }

  private CANSparkMax lift = new CANSparkMax(liftPort, MotorType.kBrushless);

  public void move(double speed) {
    lift.set(speed);

  }

  public boolean isHookEngaged() {
    return hangSwitch.get();
  }

  public boolean isLiftExtended() {
    return liftSwitch.get();
  }

  public boolean isReaching() {
    return piston.get();
  }

  public void reaching(boolean pistonReach) {
    piston.set(pistonReach);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
