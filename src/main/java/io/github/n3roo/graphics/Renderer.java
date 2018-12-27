package io.github.n3roo.graphics;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public class Renderer {

    private static GLWindow window = null;

    public static int SCREEN_WIDTH = 640;
    public static int SCREEN_HEIGHT = 360;

    // Tells how many units will fit in the width of the window : can be used to zoom in (--) or out (++)
    public static float UNITS_WIDE = 10;

    public static void init(){
        GLProfile.initSingleton();
        // We tell what version we will use (not the most recent, but it has all we want)
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        window = GLWindow.create(caps);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setResizable(false);

        window.addGLEventListener(new EventListener());

        FPSAnimator animator = new FPSAnimator(window, 60);
        // start in an other thread
        animator.start();

        window.setVisible(true);
    }

    public static int getWindowWidth(){
        return window.getWidth();
    }

    public static int getWindowHeight(){
        return window.getHeight();
    }

    public static void main(String[] args){
        init();
    }
}
