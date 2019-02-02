package org.example.world.tiles;

import io.github.n3roo.graphics.Animation;
import io.github.n3roo.world.Tile;

import java.util.ArrayList;

public class GrassTile extends Tile {

    public GrassTile(){
        ArrayList<String> frames = new ArrayList<String>();
        frames.add("grass.png");

        animations = new Animation[1];
        animations[0] = new Animation(frames, 1);
    }
}
