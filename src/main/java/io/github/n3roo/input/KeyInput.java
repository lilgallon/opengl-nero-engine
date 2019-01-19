package io.github.n3roo.input;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import java.util.HashMap;
import java.util.Map;

public class KeyInput implements KeyListener {

    private static Map<Short, Boolean> keys = new HashMap<Short, Boolean>();

    /**
     * Called every time a key is pressed.
     * @param keyEvent all the things about the event are stored in this variable.
     */
    public void keyPressed(KeyEvent keyEvent) {
        keys.put(keyEvent.getKeyCode(), true);
    }

    /**
     * Called every time a key is released.
     * @param keyEvent all the things about the event are stored in this variable.
     */
    public void keyReleased(KeyEvent keyEvent) {
        keys.put(keyEvent.getKeyCode(), false);
    }

    /**
     * @param keyCode the code of the key. (jogamp key code, and not something else).
     * @return true if the given key is being pressed.
     */
    public static boolean getKey(short keyCode){
        Boolean isPressed = keys.get(keyCode);
        return isPressed == null ? false : isPressed;
    }
}
