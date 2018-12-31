package io.github.n3roo.graphics;

import io.github.n3roo.resources.ImageResource;

public class Animation {

    // The frames of the animation
    public ImageResource[] frames;

    // The current frame of the animation
    private int currentFrame = 0;

    // The frames per seconds
    public int fps = 8;
    private long lastFrameTime = 0;

    // Should we loop ?
    public boolean loop = true;

    public void play(){
        long currentTime = System.nanoTime();

        if(currentTime > lastFrameTime + 1000000000 / fps){
            currentFrame ++;

            if(currentFrame >= frames.length){
                if(loop){
                    currentFrame = 0;
                }else{
                    currentFrame --;
                }
            }

            lastFrameTime = currentTime;
        }
    }

    public ImageResource getImage(){
        return frames[currentFrame];
    }
}
