package io.github.n3roo.engine;

import io.github.n3roo.graphics.Renderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NeroEngine {

    /**
     * Lo4j2 logger.
     * Its properties are found in "log4j2.properties" in res/ folder.
     */
    private static final Logger LOGGER = LogManager.getLogger(NeroEngine.class.getName());

    /**
     * This function initializes the engine and starts the game loop.
     */
    public static void start(){
        LOGGER.info("Starting renderer");
        Renderer.init();

        LOGGER.info("Starting game loop");
        GameLoop.start();
    }
}
