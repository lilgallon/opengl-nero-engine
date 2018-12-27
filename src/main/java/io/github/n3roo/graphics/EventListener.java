package io.github.n3roo.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import io.github.n3roo.resources.ImageResource;

public class EventListener implements GLEventListener {

    public static GL2 _gl = null;
    public static ImageResource _image = null;

    // When it starts
    public void init(GLAutoDrawable drawable) {
        GL2 _gl = drawable.getGL().getGL2();

        // r g b a -> color we want to use to clear the screen : glClear(..)
        _gl.glClearColor(0, 0, 0, 1);

        _gl.glEnable(GL2.GL_TEXTURE_2D);

        _image = new ImageResource("logo.png");
    }

    // Every time
    public void display(GLAutoDrawable drawable) {
        _gl = drawable.getGL().getGL2();

        _gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        Graphics.drawImage(_image, 0, 0, 1, 1);
    }

    // When the window size changes
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 _gl = drawable.getGL().getGL2();

        _gl.glMatrixMode(GL2.GL_PROJECTION);
        _gl.glLoadIdentity();

        float units_tall = height / (width / Renderer.UNITS_WIDE);

        _gl.glOrtho(- Renderer.UNITS_WIDE / 2, Renderer.UNITS_WIDE / 2, - units_tall / 2f, units_tall / 2f, -1, 1);
        _gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    // When it closes
    public void dispose(GLAutoDrawable drawable) {

    }
}
