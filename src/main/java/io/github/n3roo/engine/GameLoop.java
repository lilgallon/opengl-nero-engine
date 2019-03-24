package io.github.n3roo.engine;

import io.github.n3roo.graphics.Renderer;
import io.github.n3roo.world.World;

public class GameLoop {

    /**
     * This variable keeps the game loop running.
     */
    private static boolean running = false;

    /**
     * Number of updates before rendering.
     */
    private static int updates = 0;

    /**
     * Maximum amount of updates allowed before rendering.
     * Low value : smoother render in case of lag, but the game is slower.
     */
    private static final int MAX_UPDATES = 5;

    /**
     * Last time in nanoseconds when we performed an update.
     */
    private static long lastUpdateTime = 0;

    /**
     * Number of FPS that we want out game to run.
     */
    private static int targetFps = 60;

    /**
     * Amount of nanoseconds for one loop to reach the targeted fps.
     */
    private static int targetTime = 1000000000 / targetFps;

    /**
     * A simple FPS counter that stores the current FPS value.
     */
    public static int currentFps = 0;

    /**
     * It starts the game loop.
     */
    static void start(){
        final Thread thread = new Thread(() -> {
            running = true;
            lastUpdateTime = System.nanoTime();

            int fps = 0;
            long lastFpsCheck = System.nanoTime();

            while(running){
                long startTime = System.nanoTime();
                updates = 0;

                // if the difference between now and last time we updated is still greater or equal to the amount of
                // time that we are supposed to wait between updates -> we need an update
                while(startTime - lastUpdateTime >= targetTime){
                    World.update();
                    lastUpdateTime += targetTime;
                    updates ++;

                    if(updates > MAX_UPDATES){
                        break;
                    }
                }

                Renderer.render();

                // Count fps
                fps ++;
                if(System.nanoTime() >= lastFpsCheck + 1000000000){
                    currentFps = fps;
                    fps = 0;
                    lastFpsCheck = System.nanoTime();
                }

                // Make sure that we don't go too fast
                long timeTaken = System.nanoTime() - startTime;
                if(timeTaken < targetTime){
                    try {
                        Thread.sleep((targetTime - timeTaken) / 1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.setName("Nero Engine - Game loop");
        thread.start();
    }

    /**
     * TODO: I know what it is (obviously), but I can't explain it correctly.
     * @return a coefficient that you should use to get constant variable according to update rate.
     */
    public static float updateDelta(){
        return 1.0f / 1000000000 * targetTime;
    }
}
