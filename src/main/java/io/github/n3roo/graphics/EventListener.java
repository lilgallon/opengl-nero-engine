package io.github.n3roo.graphics;

import io.github.n3roo.engine.NeroEngine;
import io.github.n3roo.world.World;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventListener implements GLEventListener {

    /**
     * Lo4j2 logger.
     * Its properties are found in "log4j2.properties" in res/ folder.
     */
    private static final Logger LOGGER = LogManager.getLogger(NeroEngine.class.getName());

    /**
     * GL2 variable that can be used to draw things.
     */
    static GL2 gl = null;

    // When the window is created
    public void init(GLAutoDrawable drawable) {
        LOGGER.debug("Setting up OpenGL settings");

        // Init gl variable
        GL2 gl = drawable.getGL().getGL2();

        // r g b a -> color we want to use to clear the screen : glClear(..)
        gl.glClearColor(0, 0, 0, 1);

        // It makes sure that no fragment shader is active, so two-dimensional texturing is performed
        gl.glEnable(GL2.GL_TEXTURE_2D);

        // This is used to display images with alpha (transparency)
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
    }

    // Every time the window is updated
    public void display(GLAutoDrawable drawable) {
        // Update gl variable
        gl = drawable.getGL().getGL2();

        // It clears buffers about color writing.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // We will render things that the camera sees. We can move around the world.
        gl.glTranslatef(- Renderer.camera.x, - Renderer.camera.y, 0);
        World.render();
        gl.glTranslatef(Renderer.camera.x, Renderer.camera.y, 0);
    }

    // When the window size changes
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        LOGGER.debug("Setting up OpenGL settings with the new window size");
        // Update gl variable
        GL2 gl = drawable.getGL().getGL2();

        // It says that we will apply subsequent matrix operations to the projection matrix stack
        gl.glMatrixMode(GL2.GL_PROJECTION);
        // It replaces the current matrix with the identity matrix
        gl.glLoadIdentity();

        Renderer.unitsTall = height / (width / Renderer.unitsWide);

        // Multiply the current matrix with an orthographic matrix
        gl.glOrtho(
                - Renderer.unitsWide / 2f,
                Renderer.unitsWide / 2f,
                - Renderer.unitsTall / 2f,
                Renderer.unitsTall / 2f,
                -1,
                1
        );

        // We go back to our model view matrix
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    // When it closes
    public void dispose(GLAutoDrawable drawable) {
        // No need to dispose the text renderer because it will be cleaned up automatically when the OpenGL context is
        // destroyed.
    }
}
