package samples.physics.world.entities;

import io.github.n3roo.graphics.Graphics;
import io.github.n3roo.math.Polygon;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;
import io.github.n3roo.world.components.RigidBody;

import java.util.ArrayList;

public class Platform extends GameObject {

    public Platform(){
        this.position = new Vec2f(0, -3);

        ArrayList<Vec2f> points = new ArrayList<>();
        points.add(new Vec2f(-4, -0.2f));
        points.add(new Vec2f(4, -0.2f));
        points.add(new Vec2f(4, 0.2f));
        points.add(new Vec2f(-4, 0.2f));

        rigidBody = new RigidBody(new Polygon(points), -1);
    }

    @Override
    public void render() {
        super.render();
        float x = this.position.x + this.rigidBody.getPolygon().points.get(0).x;
        float y = this.position.y + this.rigidBody.getPolygon().points.get(0).y;
        float width = this.rigidBody.getPolygon().points.get(1).x - this.rigidBody.getPolygon().points.get(0).x;
        float height = this.rigidBody.getPolygon().points.get(3).y - this.rigidBody.getPolygon().points.get(0).y;

        Graphics.setColor(0, 1, 0, 1);
        Graphics.fillRect(x, y, width, height);
    }
}
