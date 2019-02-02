package io.github.n3roo.input;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import io.github.n3roo.graphics.Renderer;

public class MouseInput implements MouseListener {

    private static int x = 0;
    private static int y = 0;

    public static int getX(){
        return x;
    }

    public static int getY(){
        return y;
    }

    public static float getWorldX(){
        return (Renderer.unitsWide / Renderer.getWindowWidth() * x - Renderer.unitsWide / 2) + Renderer.cameraX;
    }

    public static float getWorldY(){
        float unitsTall = Renderer.unitsWide * ((float) Renderer.getWindowHeight() / (float) Renderer.getWindowWidth());
        return - (unitsTall / Renderer.getWindowHeight() * y - unitsTall / 2) + Renderer.cameraY;
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
