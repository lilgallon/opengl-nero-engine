package samples;

import io.github.n3roo.engine.NeroEngine;
import samples.physics.PhysicsGame;
import samples.shooter.ShooterGame;

public class Main {

    public static void main(String[] args){
        NeroEngine.start();

        // Shooter game demo
        // ShooterGame.start();

        // Physics demo
        PhysicsGame.start();
    }
}
