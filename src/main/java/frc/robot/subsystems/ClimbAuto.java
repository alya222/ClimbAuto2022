// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
// import information on CANSparkMAx motor controller
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import information from other files
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ClimbAuto extends SubsystemBase {
  /** Creates a new ClimAuto subsystem */
 
  // add a limit switch on stationary hook
  DigitalInput hangSwitch = new DigitalInput(0);
  
  // add a limit switch on lift (to detect full extension)
  DigitalInput liftSwitch = new DigitalInput(1);

  // add a compressor
  private Compressor airow = new Compressor(0, PneumaticsModuleType.CTREPCM);

  // add a piston for moving arm
  private Solenoid piston = new Solenoid(compressorModule, armMoverPort);
  
  // add a motor for lift
  private CANSparkMax lift = new CANSparkMax(liftPort, MotorType.kBrushless);

  private RelativeEncoder liftEncoder = lift.getEncoder();
  
  private SparkMaxPIDController liftController = lift.getPIDController();



  public ClimbAuto () {

    //liftEncoder.setPositionConversionFactor(5);

    // Spark PID Stuff
    liftController.setP(0.01);
    liftController.setI(0);
    liftController.setD(0); 
    liftController.setFF(0);

    //launcherEncoder.setVelocityConversionFactor(factor)
  }

  // add a method that moves lift up
  public void move(double speed) {
    lift.set(speed);
  }

  // method checks whether stationary hook limit switch is pressed
  public boolean isHookEngaged() {
    return hangSwitch.get();
  }

  // method checks whether higher lift limit switch is pressed
  public boolean isLiftExtended() {
    return liftSwitch.get();
  }

  // check if arm is fully reached backwards (if piston value = true)
  public boolean isReaching() {
    return piston.get();
  }

  // method makes piston extend (makes the arm reach)
  public void reaching(boolean pistonReach) {
    piston.set(pistonReach);
  }

  public void setSpeedLift (double setPoint) {
    liftController.setReference(setPoint, CANSparkMax.ControlType.kPosition);
    
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
