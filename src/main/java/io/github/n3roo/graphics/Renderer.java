package io.github.n3roo.graphics;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import io.github.n3roo.engine.NeroEngine;
import io.github.n3roo.input.KeyInput;
import io.github.n3roo.input.MouseInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Renderer {

    private static final Logger LOGGER = LogManager.getLogger(NeroEngine.class.getName());

    private static GLProfile profile = null;
    private static GLWindow window = null;

    // Tells how many units will fit in the width of the window : can be used to zoom in (--) or out (++)
    public static float UNITS_WIDE = 10;

    public static float cameraX = 0;
    public static float cameraY = 0;

    public static void init(){
        LOGGER.debug("Initializing OpenGL resources");
        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        LOGGER.debug("Creating OpenGL window");
        window = GLWindow.create(caps);
        window.setSize(640, 420);
        window.setResizable(false);

        LOGGER.debug("Creating listeners");
        window.addGLEventListener(new EventListener());
        window.addMouseListener(new MouseInput());
        window.addKeyListener(new KeyInput());
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent arg0) {
                LOGGER.debug("Closing OpenGL window");
                // If we need to do stuff before closing, do it right here
                System.exit(0);
            }
        });

        LOGGER.debug("Showing OpenGL window");
        // window.setFullscreen(true);
        window.setVisible(true);
        window.requestFocus(); // this is what photoshop spams when it starts
    }

    public static void render(){
        if(window == null){
            return;
        }

        window.display();
    }

    public static int getWindowWidth(){
        return window.getWidth();
    }

    public static int getWindowHeight(){
        return window.getHeight();
    }

    public static GLProfile getProfile(){
        return profile;
    }

}
