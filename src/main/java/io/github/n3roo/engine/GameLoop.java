package io.github.n3roo.engine;

import io.github.n3roo.graphics.Renderer;

public class GameLoop {

    private static boolean running = false;

    private static int max_fps = 60;
    private static int target_time = 1000000000 / max_fps; // nb of nanoseconds for one loop

    public static void start(){
        final Thread thread = new Thread(){
            public void run(){
                running = true;

                while(running){
                    long start_time = System.nanoTime();

                    // Poll input

                    // Update game

                    // Render game
                    Renderer.render();

                    // It makes sure that the gameloop is not running too fast
                    long time_taken = System.nanoTime() - start_time;
                    if(time_taken < target_time){
                        try {
                            Thread.sleep(target_time - time_taken / 1000000); // ms
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
}
