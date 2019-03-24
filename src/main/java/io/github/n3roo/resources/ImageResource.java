package io.github.n3roo.resources;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;
import io.github.n3roo.graphics.Renderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageResource {

    /**
     * The OpenGL texture object
     */
    private Texture texture = null;

    /**
     * The buffered image of this image
     */
    private BufferedImage image = null;

    /**
     * It reads the image and retrieve the texture.
     * @param image_name the name of the image from res/images/ folder.
     *                   For samples, if your image is in /images/level/background.png, you should give
     *                   "level/background.png".
     */
    public ImageResource(String image_name){
        URL url = ImageResource.class.getResource("/images/" + image_name);

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(image != null){
            // It prevents memory leak
            image.flush();
        }
    }

    /**
     * @return the texture of the loaded image. It can return null if an error happened.
     */
    public Texture getTexture(){
        if(image == null){
            return null;
        }

        if(texture == null){
            texture = AWTTextureIO.newTexture(Renderer.getProfile(), image, true);
        }

        return texture;
    }
}
