package io.github.n3roo.hud.components;

import io.github.n3roo.engine.GameLoop;
import io.github.n3roo.graphics.EventListener;
import io.github.n3roo.graphics.Graphics;
import io.github.n3roo.hud.HudComponent;

public class FpsCounter extends HudComponent {

    @Override
    public void update() {

    }

    @Override
    public void render() {
        Graphics.drawText(Integer.toString(GameLoop.currentFps),
                - EventListener.glAutoDrawable.getSurfaceWidth() / 2,
                EventListener.glAutoDrawable.getSurfaceHeight() / 2 - (int) Graphics.textHeight);
    }
}
