package Projectiles;

import Engine.GraphicsHandler;
//import GameObject.Frame;
//import GameObject.SpriteSheet;
//import Utils.Direction;
import GameObject.Frame;
//import java.util.HashMap;
import Level.MapEntity;
import Level.Player;
import Utils.Direction;

public class Projectile extends MapEntity{
    private Direction direction;

    public Projectile(float x, float y, Frame frame, Direction direction) {
        super(x, y, frame);
        this.direction = direction;
    }

    public Projectile(float x, float y) {
        super(x, y);
    }

    public void update(Player player) {
        //if (!isLocked) {
        this.performAction(player);
        //}
        super.update();
        if(direction != null){
            if(direction == Direction.UP){
                moveY(-5);
            }
            else if(direction == Direction.DOWN){
                moveY(5);
            }
            else if(direction == Direction.LEFT){
                moveX(-5);
            }
            else if(direction == Direction.RIGHT){
                moveX(5);

            }
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
}
