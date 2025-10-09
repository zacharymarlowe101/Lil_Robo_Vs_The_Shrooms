package Level;

import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Projectiles.Projectile;
import Utils.Direction;

import java.util.ArrayList;
import java.util.HashMap;

// Base class for all NPCs in the game
public class NPC extends MapEntity {
    protected int id = 0;
    protected boolean isLocked = false;

    // Health field — all NPCs start with 3 health by default
    protected int health = 3;

    // Death/despawn logic
    protected boolean isDead = false;
    protected int deathTimer = 60; // frames (approx. 1 second at 60 FPS)

    // Track which projectiles have already hit this NPC
    protected ArrayList<Projectile> projectilesHit = new ArrayList<>();

    // Constructors
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

    // Health management
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    // Handle taking damage and death
    public void takeDamage(int amount) {
        if (isDead) return; // Ignore if already dead

        health -= amount;

        if (health <= 0) {
            health = 0;
            isDead = true;
            System.out.println("NPC " + id + " died.");
        } else {
            System.out.println("NPC " + id + " took " + amount + " damage. Remaining health: " + this.health);
        }
    }

    // Makes the NPC face the player
    public void facePlayer(Player player) {
        float centerPoint = getBounds().getX() + (getBounds().getWidth() / 2);
        float playerCenterPoint = player.getBounds().getX() + (player.getBounds().getWidth() / 2);
        if (centerPoint < playerCenterPoint) {
            this.currentAnimationName = "STAND_RIGHT";
        } else {
            this.currentAnimationName = "STAND_LEFT";
        }
    }

    public void stand(Direction direction) {
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        } else {
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

    // Update NPC and detect projectile collisions
    public void update(Player player) {
        // Handle death timer and despawn
        if (isDead) {
            deathTimer--;
            if (deathTimer <= 0) {
                this.isHidden = true;
            }
            return;
        }

        if (!isLocked) {
            performAction(player);
        }

        // Check for projectile hits
        if (map != null && map.getProjectiles() != null) {
            for (Projectile p : map.getProjectiles()) {
                if (p == null || p.isHidden()) continue;

                // Only count hits once per projectile
                if (!projectilesHit.contains(p) && this.getBounds().intersects(p.getBounds())) {
                    if (health > 0) {
                        takeDamage(1);
                        projectilesHit.add(p);
                    }
                }
            }
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

    public int getId() {
        return id;
    }
}
