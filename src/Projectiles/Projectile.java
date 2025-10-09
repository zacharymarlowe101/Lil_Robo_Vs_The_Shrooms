package Projectiles;

import java.awt.Point;

import Engine.GraphicsHandler;
//import GameObject.Frame;
//import GameObject.SpriteSheet;
//import Utils.Direction;
import GameObject.Frame;
import GameObject.GameObject;
//import java.util.HashMap;
import Level.MapEntity;
import Level.Player;
import Utils.Direction;

public class Projectile extends MapEntity{
    //private Direction direction;
    private double dx, dy;
    private double length;
    private double dirX, dirY;
    private int speed = 5;
    private float velocityX, velocityY;
    private double lifetime = 1_000; // milliseconds
    private double deltaTime = 16.67; // milliseconds, approx 60 FPS
    private boolean isExpired = false;

    public Projectile(float x, float y, Frame frame, Utils.Point p1, Point p2) { //P1 is start point, P2 is target point
        super(x, y, frame);
        //this.direction = direction;
        dx = p2.x - p1.x;
        dy = p2.y - p1.y;
        length = Math.sqrt(dx*dx + dy*dy);
        dirX = dx / length;
        dirY = dy / length;
        velocityX = (float)(dirX * speed);
        velocityY = (float)(dirY * speed);
    }

    public Projectile(float x, float y) {
        super(x, y);
    }

    public void update(Player player) {
        this.performAction(player);
        super.update();
        moveY(velocityY);
        moveX(velocityX);
        lifetime -= deltaTime;
        if(isExpired()){ //if its been a second it despawns
            this.isHidden = true;
            isExpired = true;
        }
    }

    protected void performAction(Player player) {}

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public void walk(Direction direction, float speed) {
        /*if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        }
        else {
            if (this.currentAnimationName.contains("RIGHT")) {
                this.currentAnimationName = "WALK_RIGHT";
            }
            else {
                this.currentAnimationName = "WALK_LEFT";
            }
        }*/
        if (direction == Direction.UP) {
            moveY(-speed);
        }
        else if (direction == Direction.DOWN) {
            moveY(speed);
        }
        else if (direction == Direction.LEFT) {
            moveX(-speed);
        }
        else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }

    public boolean isExpired() {
        return lifetime <= 0;
    }

    private GameObject owner;

    public void setOwner(GameObject owner) {
        this.owner = owner;
    }

    public Object getOwner() {
        return owner;
    }
}
