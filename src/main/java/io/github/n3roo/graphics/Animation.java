package io.github.n3roo.graphics;

import io.github.n3roo.resources.ImageResource;

import java.util.ArrayList;

public class Animation {

    // The frames of the animation
    private ImageResource[] frames;

    // The current frame of the animation
    private int currentFrame;

    // The frames per seconds
    private int fps;
    private long lastFrameTime;

    // Should we loop ?
    private boolean loop;

    /**
     * It creates an animation with all the specified sprites. Those sprites need to be sorted in the spriteNames
     * array.
     * Transparency is taken in account.
     * By default, loop is set to true.
     * @param spriteNames all the sprites sorted,
     * @param fps the number of frames (sprites) per seconds.
     */
    public Animation(ArrayList<String> spriteNames, int fps){
        resetAnimation();
        changeSprites(spriteNames);

        this.fps = fps;
        this.loop = true;
    }

    /**
     * It creates an animation with all the specified sprites. Those sprites need to be sorted in the spriteNames
     * array.
     * Transparency is taken in account.
     * By default, loop is set to true.
     * @param spriteNames all the sprites sorted,
     * @param fps the number of frames (sprites) per seconds,
     * @param loop should the animation loop ? If set to false, the animation will stop when the last sprite is reached.
     */
    public Animation(ArrayList<String> spriteNames, int fps, boolean loop){
        resetAnimation();
        changeSprites(spriteNames);

        this.fps = fps;
        this.loop = loop;
    }

    /**
     * It plays the animation.
     */
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

    /**
     * It resets the animation : it will go to the start, and redo the animation.
     */
    public void resetAnimation(){
        currentFrame = 0;
        lastFrameTime = 0;
    }

    /**
     * It changes the sprites of the animation. It also reset the animation to the start since the sprites are changing.
     * @param spriteNames all the sprites sorted.
     */
    public void changeSprites(ArrayList<String> spriteNames) {
        if(spriteNames == null){
            throw new NullPointerException("The spriteNames list can't be null!");
        }

        if(spriteNames.size() == 0){
            throw new IllegalArgumentException("The spriteNames list needs to contain at least one sprite.");
        }

        resetAnimation();

        ImageResource[] sprites = new ImageResource[spriteNames.size()];

        for(int i = 0; i < spriteNames.size(); i ++){
            String spriteName = spriteNames.get(i);
            sprites[i] = new ImageResource(spriteName);
        }

        frames = sprites;
    }

    // GETTERS

    public ImageResource getImage(){
        return frames[currentFrame];
    }

    public ImageResource[] getFrames() {
        return frames;
    }

    public int getFps() {
        return fps;
    }

    public boolean isLooping() {
        return loop;
    }

    // SETTERS

    public void setFps(int fps) {
        this.fps = fps;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
