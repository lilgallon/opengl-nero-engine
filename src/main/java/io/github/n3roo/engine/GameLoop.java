package io.github.n3roo.engine;

import io.github.n3roo.graphics.Renderer;
import io.github.n3roo.world.World;

public class GameLoop {

    private static boolean running = false;

    private static int updates = 0;
    // low value : smoother render in case of lag, but the game is slower
    private static final int MAX_UPDATES = 5;

    private static long lastUpdateTime = 0;

    private static int targetFps = 60;
    private static int targetTime = 1000000000 / targetFps; // nb of nanoseconds for one loop
    public static int currentFps = 0;

    public static void start(){
        final Thread thread = new Thread(){
            public void run(){
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

                    // Count and print fps
                    fps ++;
                    if(System.nanoTime() >= lastFpsCheck + 1000000000){
                        // print?
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
            }
        };

        thread.setName("GameLoop");
        thread.start();
    }

    public static float updateDelta(){
        return 1.0f / 1000000000 * targetTime;
    }
}
