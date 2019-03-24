package io.github.n3roo.input;

import io.github.n3roo.graphics.Renderer;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

public class MouseInput implements MouseListener {

    private static int x = 0;
    private static int y = 0;

    public static int getX(){
        return x;
    }

    public static int getY(){
        return y;
    }

    /**
     * From the a position on the screen, it returns a position in the world.
     * @return x position in the world.
     */
    public static float getWorldX(){
        return (Renderer.unitsWide / Renderer.getWindowWidth() * x - Renderer.unitsWide / 2) + Renderer.camera.x;
    }

    /**
     * From the a position on the screen, it returns a position in the world.
     * @return y position in the world.
     */
    public static float getWorldY(){
        float unitsTall = Renderer.unitsWide * ((float) Renderer.getWindowHeight() / (float) Renderer.getWindowWidth());
        return - (unitsTall / Renderer.getWindowHeight() * y - unitsTall / 2) + Renderer.camera.y;
    }
    
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseWheelMoved(MouseEvent e) {

    }
}
