package Projectiles;

import Engine.GraphicsHandler;
//import GameObject.Frame;
//import GameObject.SpriteSheet;
//import Utils.Direction;
import GameObject.Frame;
//import java.util.HashMap;
import Level.MapEntity;
import Level.Player;

public class Projectile extends MapEntity{

    public Projectile(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public Projectile(float x, float y) {
        super(x, y);
    }

    public void update(Player player) {
        //if (!isLocked) {
        this.performAction(player);
        //}
        super.update();
    }

    protected void performAction(Player player) {}

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
