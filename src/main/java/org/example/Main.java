package org.example;

import io.github.n3roo.engine.NeroEngine;
import org.example.physics.PhysicsGame;
import org.example.shooter.ShooterGame;

public class Main {

    public static void main(String[] args){
        NeroEngine.start();

        // Shooter game demo
        // ShooterGame.start();

        // Physics demo
        PhysicsGame.start();
    }
}
