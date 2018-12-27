package io.github.n3roo.graphics;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
import io.github.n3roo.input.MouseInput;

public class Renderer {

    private static GLProfile _profile = null;
    private static GLWindow _window = null;

    // Tells how many units will fit in the width of the _window : can be used to zoom in (--) or out (++)
    public static float UNITS_WIDE = 10;

    public static void init(){
        GLProfile.initSingleton();
        _profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(_profile);

        _window = GLWindow.create(caps);
        _window.setSize(640, 420);
        _window.setResizable(false);
        _window.addGLEventListener(new EventListener());
        _window.addMouseListener(new MouseInput());

        _window.setFullscreen(true);
        _window.setVisible(true);
        _window.requestFocus(); // this is what photoshop spams when it starts
    }

    public static void render(){
        if(_window == null){
            return;
        }

        _window.display();
    }

    public static GLProfile getProfile(){
        return _profile;
    }
}
