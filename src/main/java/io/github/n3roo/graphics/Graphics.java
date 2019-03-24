package io.github.n3roo.graphics;

import io.github.n3roo.resources.ImageResource;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The origin of all those methods are the middle of the screen.
 */
public class Graphics {

    // Color values (from 0 to 1)
    private static float red = 1;
    private static float green = 1;
    private static float blue = 1;
    private static float alpha = 1;

    // Rotation in degrees (clockwise)
    private static float rotation = 0;

    // Translation
    private static float xTranslation;
    private static float yTranslation;

    // Text renderer with its default value
    private static TextRenderer textRenderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 36));

    // It corresponds to the height of the last text drawn
    public static float textHeight = 0;

    // It corresponds to the width of the last text drawn
    public static float textWidth = 0;

    /**
     * It draws a rectangle (width ; height).
     * The position is the center of the rectangle.
     * @param x horizontal position of the top left corner,
     * @param y vertical position of the top left corner,
     * @param width width of the rectangle,
     * @param height height of the rectangle.
     */
    @SuppressWarnings("Duplicates")
    public static void fillRect(float x, float y, float width, float height){
        GL2 gl = EventListener.gl;

        // Rotate the openGL context
        gl.glTranslatef(xTranslation, yTranslation, 0);
        gl.glTranslatef(x, y, 0);
        gl.glRotatef(- rotation, 0, 0, 1);

        // Draw the rectangle
        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0, 0);
        gl.glVertex2f( width, 0);
        gl.glVertex2f(width,   height);
        gl.glVertex2f(0,   height);
        gl.glEnd();
        gl.glFlush();

        // Restore the openGL context
        gl.glRotatef(rotation, 0, 0, 1);
        gl.glTranslatef(- x, - y, 0);
        gl.glTranslatef(-xTranslation, -yTranslation, 0);
    }

    /**
     * It draws outlines of the specified rectangle.
     * The position is the center of the rectangle.
     * @param x horizontal position of the top left corner,
     * @param y vertical position of the top left corner,
     * @param width width of the rectangle,
     * @param height height of the rectangle,
     * @param thickness thickness of the outline.
     */
    public static void fillStrokeRect(float x, float y, float width, float height, float thickness){
        // top
        fillRect(x, y, width, thickness);
        // bottom
        fillRect(x, y - height, width, - thickness);
        // left
        fillRect(x, y, - thickness, - height);
        // right
        fillRect(x + width, y, thickness, - height);
    }

    /**
     * It draws a line.
     * @param x1 horizontal position of first point
     * @param y1 vertical position of first point
     * @param x2 horizontal position of second point
     * @param y2 vertical position of second point
     */
    @SuppressWarnings("Duplicates")
    public static void fillLine(float x1, float y1, float x2, float y2){
        GL2 gl = EventListener.gl;

        // Rotate the openGL context
        gl.glTranslatef(xTranslation, yTranslation, 0);
        gl.glRotatef(- rotation, 0, 0, 1);

        // Draw the rectangle
        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x1, y1);
        gl.glVertex2f(x2, y2);
        gl.glEnd();
        gl.glFlush();

        // Restore the openGL context
        gl.glRotatef(rotation, 0, 0, 1);
        gl.glTranslatef(-xTranslation, -yTranslation, 0);
    }

    /**
     * It draws an image to the screen. It is compatible with color changing.
     * @param image the image to draw,
     * @param x horizontal position of the top left corner,
     * @param y vertical position of the top left corner,
     * @param width width of the image,
     * @param height height of the image.
     */
    @SuppressWarnings("Duplicates")
    public static void drawImage(ImageResource image, float x, float y, float width, float height){
        GL2 gl = EventListener.gl;

        // Get the texture
        Texture texture = image.getTexture();

        // if our image is too far on the right OR
        // if our image is too far on the left OR
        // if our image is too far on the top OR
        // if our image is too far on the bottom,
        // we won't render
        if(x - width / 2 - Renderer.camera.x > Renderer.unitsWide / 2 ||
                x + width / 2 - Renderer.camera.x < - Renderer.unitsWide / 2 ||
                y - height / 2 - Renderer.camera.y > Renderer.unitsTall / 2 ||
                y + height / 2 - Renderer.camera.y < - Renderer.unitsTall / 2){
            return;
        }

        // Bind the texture
        if(texture != null){
            gl.glBindTexture(GL2.GL_TEXTURE_2D, texture.getTextureObject());
        }

        // Rotate the openGL context
        gl.glTranslatef(xTranslation, yTranslation, 0);
        gl.glTranslatef(x, y, 0);
        gl.glRotatef(- rotation, 0, 0, 1);

        // Draw the rectangle
        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(- width / 2, - height / 2);

        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(  width / 2, - height / 2);

        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(  width / 2,   height / 2);

        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(- width / 2,   height / 2);
        gl.glEnd();
        gl.glFlush();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);

        // Restore the openGL context
        gl.glRotatef(rotation, 0, 0, 1);
        gl.glTranslatef(- x, - y, 0);
        gl.glTranslatef(-xTranslation, -yTranslation, 0);
    }

    /**
     * It draws a text in the specified position.
     * The position corresponds to the bottom left corner.
     * @param text content to draw,
     * @param x horizontal position of the bottom left corner,
     * @param y vertical position of the bottom left corner,
     */
    public static void drawText(String text, int x, int y){
        // The origin is the bottom left of the screen, but we want the origin to be at the center of the screen.
        int xShift = Renderer.getWindowWidth() / 2;
        int yShift = Renderer.getWindowHeight() / 2;

        // Draw the text
        textRenderer.beginRendering(
                Renderer.getWindowWidth(),
                Renderer.getWindowHeight()
        );
        textRenderer.setColor(red, green, blue, alpha);
        textRenderer.draw(text, x + (int) xTranslation + xShift, y + (int) yTranslation + yShift);
        textRenderer.endRendering();

        // Update textWidth and textHeight values with the last text drawn
        Rectangle2D bounds = textRenderer.getBounds(text);
        textWidth = (float) bounds.getWidth();
        textHeight = (float) bounds.getHeight();
    }

    /**
     * It changes the color.
     * Values need to be in [0; 1].
     * @param r red value,
     * @param g green value,
     * @param b blue value,
     * @param a alpha value.
     */
    public static void setColor(float r, float g, float b, float a){
        red = Math.max(0, Math.min(1, r));
        green = Math.max(0, Math.min(1, g));
        blue = Math.max(0, Math.min(1, b));
        alpha = Math.max(0, Math.min(1, a));
    }

    /**
     * Rotates the shapes (clockwise).
     * @param r rotation in degrees [0; 360[.
     */
    public static void setRotation(float r){
        rotation = r;
    }

    /**
     * Translate the shapes.
     * @param tx horizontal translation,
     * @param ty vertical translation.
     */
    public static void setTranslation(float tx, float ty){
        xTranslation = tx;
        yTranslation = ty;
    }

    /**
     * It changes the font used by the text renderer.
     * @param name name of the font,
     * @param style style of the font,
     * @param size size of the font.
     */
    public static void setFont(String name, int style, int size){
        textRenderer = new TextRenderer(new Font(name, style, size));
    }

    /**
     * It changes the name of the current font.
     * @param name new name.
     */
    public static void setFontName(String name){
        setFont(name, textRenderer.getFont().getStyle(), textRenderer.getFont().getSize());
    }

    /**
     * It changes the style of the current font.
     * @param style new style.
     */
    public static void setFontStyle(int style){
        setFont(textRenderer.getFont().getName(), style, textRenderer.getFont().getSize());
    }

    /**
     * It changes the size of the current font.
     * @param size new size.
     */
    public static void setFontSize(int size){
        setFont(textRenderer.getFont().getName(), textRenderer.getFont().getStyle(), size);
    }
}
