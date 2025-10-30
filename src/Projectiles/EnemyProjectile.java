package Projectiles;

import GameObject.Frame;
import Level.MapCollisionHandler;
import Level.NPC;
import Level.Player;
import Utils.Point;

public class EnemyProjectile extends Projectile {

    private NPC owner;
    private float velocityX;
    private float velocityY;
    private float speed = 3.0f;
    private boolean destroyOnWallHit = false;
    private long startTime;
    private double lifetime = -1;

    public EnemyProjectile(float x, float y, Frame frame, Point start, Point target, NPC owner) {
        super(x, y, frame, start, new java.awt.Point((int) target.x, (int) target.y));
        this.owner = owner;
        this.startTime = System.currentTimeMillis();

        float dx = target.x - start.x;
        float dy = target.y - start.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance != 0) {
            this.velocityX = (dx / distance) * speed;
            this.velocityY = (dy / distance) * speed;
        } else {
            this.velocityX = 0;
            this.velocityY = 0;
        }

        this.setOwner(owner);
    }

    public EnemyProjectile(float x, float y, Frame frame, float dirX, float dirY, NPC owner) {
        super(x, y, frame, new Point(x, y), new java.awt.Point((int) x + 1, (int) y + 1));
        this.owner = owner;
        this.startTime = System.currentTimeMillis();

        float length = (float) Math.sqrt(dirX * dirX + dirY * dirY);
        if (length != 0) {
            this.velocityX = (dirX / length) * speed;
            this.velocityY = (dirY / length) * speed;
        } else {
            this.velocityX = 0;
            this.velocityY = 0;
        }

        this.setOwner(owner);
    }

    @Override
    public void update(Player player) {
        moveX(velocityX);
        moveY(velocityY);

        this.getBounds().setX((int) getX());
        this.getBounds().setY((int) getY());

        if (map == null || isHidden()) return;

        if (lifetime > 0 && System.currentTimeMillis() - startTime > lifetime) {
            this.isHidden = true;
            map.getProjectiles().remove(this);
            return;
        }

        if (this.getBounds().intersects(player.getBounds())) {
            player.takeDamage(1);
            this.isHidden = true;
            map.getProjectiles().remove(this);
            return;
        }

        if (destroyOnWallHit && MapCollisionHandler.isCollidingWithMapEntity(this, map, null)) {
            this.isHidden = true;
            map.getProjectiles().remove(this);
        }
    }

    public void setSpeed(float newSpeed) {
        this.speed = newSpeed;
        float length = (float) Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        if (length != 0) {
            velocityX = (velocityX / length) * speed;
            velocityY = (velocityY / length) * speed;
        }
    }

    public void setDestroyOnWallHit(boolean destroy) {
        this.destroyOnWallHit = destroy;
    }

    public void setLifetime(double lifetime) {
        this.lifetime = lifetime;
    }
}