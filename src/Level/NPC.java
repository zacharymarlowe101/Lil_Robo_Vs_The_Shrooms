package Level;

import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Projectiles.EnemyAttack;
import Projectiles.Projectile;
import Utils.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class NPC extends MapEntity {
    protected int id = 0;
    protected boolean isLocked = false;
    protected int health = 3;
    protected boolean isDead = false;
    protected int deathTimer = 60;
    protected ArrayList<Projectile> projectilesHit = new ArrayList<>();

    // Attack System
    protected EnemyAttack attack;
    protected int attackCooldown = 0;


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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void takeDamage(int amount) {
        if (isDead) return;

        health -= amount;

        if (health <= 0) {
            health = 0;
            isDead = true;
            System.out.println("NPC " + id + " died.");
        } else {
            System.out.println("NPC " + id + " took " + amount + " damage. Remaining health: " + this.health);
        }
    }

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
        if (direction == Direction.UP) moveY(-speed);
        else if (direction == Direction.DOWN) moveY(speed);
        else if (direction == Direction.LEFT) moveX(-speed);
        else if (direction == Direction.RIGHT) moveX(speed);
    }

    public void updateEnemyAttack(Player player) {
        if (isDead || isHidden) return;

        if (attackCooldown > 0) {
            attackCooldown--;
        } else if (attack != null && attack.isInRange(this, player)) {
            attack.perform(this, player);
            attackCooldown = attack.getCooldown();
        }
    }

    public void update(Player player) {
        if (isDead) {
            deathTimer--;
            if (deathTimer <= 0) this.isHidden = true;
            return;
        }

        if (!isLocked) performAction(player);

        updateEnemyAttack(player);

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

    public void draw(GraphicsHandler graphicsHandler, float camX, float camY) {
        draw(graphicsHandler);
    }

    public int getId() {
        return id;
    }

    public Map getMap() {
        return this.map;
    }

    public float distanceTo(Player player) {
        float dx = player.getX() - this.getX();
        float dy = player.getY() - this.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public void setAttack(EnemyAttack attack) {
        this.attack = attack;
    }

    public Frame getCurrentFrame() {
        return this.getCurrentAnimation()[this.getCurrentFrameIndex()];
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        this.isHidden = hidden;
    }
}
