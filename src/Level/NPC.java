package Level;

import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.Direction;

import java.util.HashMap;

// This class is a base class for all NPCs in the game -- all NPCs should extend from it
public class NPC extends MapEntity {
    protected int id = 0;
    protected boolean isLocked = false;

    // Add health field
    protected int health = 1;

    public NPC(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        this.id = id;
    }

    public NPC(int id, float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
        this.id = id;
    }

    public NPC(int id, float x, float y, Frame[] frames) {
        super(x, y, frames);
        this.id = id;
    }

    public NPC(int id, float x, float y, Frame frame) {
        super(x, y, frame);
        this.id = id;
    }

    public NPC(int id, float x, float y) {
        super(x, y);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // Set and get health
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    // Take damage method
    public void takeDamage(int amount) {
        this.health -= amount;
        System.out.println("NPC " + id + " took " + amount + " damage. Remaining health: " + this.health);
    }

    public void facePlayer(Player player) {
        float centerPoint = getBounds().getX() + (getBounds().getWidth() / 2);
        float playerCenterPoint = player.getBounds().getX() + (player.getBounds().getWidth() / 2);
        if (centerPoint < playerCenterPoint) {
            this.currentAnimationName = "STAND_RIGHT";
        } else if (centerPoint >= playerCenterPoint) {
            this.currentAnimationName = "STAND_LEFT";
        }
    }

    public void stand(Direction direction) {
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        } else if (direction == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        }
    }

    public void walk(Direction direction, float speed) {
        if (direction == Direction.UP) {
            moveY(-speed);
        } else if (direction == Direction.DOWN) {
            moveY(speed);
        } else if (direction == Direction.LEFT) {
            moveX(-speed);
        } else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }

    public void update(Player player) {
        if (!isLocked) {
            this.performAction(player);
        }
        super.update();
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    protected void performAction(Player player) {}

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
