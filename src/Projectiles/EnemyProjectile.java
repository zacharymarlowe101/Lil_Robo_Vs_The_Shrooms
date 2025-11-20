package Projectiles;

import GameObject.Frame;
import Level.EnemyNPC;
import Level.MapCollisionHandler;
import Level.NPC;
import Level.Player;
import Utils.Point;
import GameObject.SpriteSheet;

public class EnemyProjectile extends Projectile {

    private float velocityX;
    private float velocityY;
    private float speed = 3.0f;
    private boolean destroyOnWallHit = true;
    private long startTime;
    private double lifetime = -1;
    private float dirX, dirY;

    public EnemyProjectile(SpriteSheet spriteSheet, float x, float y, Point start, Point target, NPC owner) {
        super(
            spriteSheet,
            x - spriteSheet.getSpriteWidth() / 2f,
            y - spriteSheet.getSpriteHeight() / 2f,
            start,
            new java.awt.Point((int) target.x, (int) target.y)
        );

        this.owner = owner;
        this.startTime = System.currentTimeMillis();

        float dx = target.x - start.x;
        float dy = target.y - start.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance != 0) {
            dirX = dx / distance;
            dirY = dy / distance;
            this.velocityX = dirX * speed;
            this.velocityY = dirY * speed;
        } else {
            dirX = velocityX = 0;
            dirY = velocityY = 0;
        }

        this.setOwner(owner);
    }

    public EnemyProjectile(SpriteSheet spriteSheet, float x, float y, float dirX, float dirY, NPC owner) {
        super(
            spriteSheet,
            x - spriteSheet.getSpriteWidth() / 2f,
            y - spriteSheet.getSpriteHeight() / 2f,
            dirX,
            dirY,
            owner
        );

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

        // Lifetime expiration
        if (lifetime > 0 && System.currentTimeMillis() - startTime > lifetime) {
            this.isHidden = true;
            map.getProjectiles().remove(this);
            return;
        }

        // Check collision with NPCs (enemy damage)
        for (NPC npc : map.getActiveNPCs()) {
            if (this.isHidden()) continue;
            
            if (this.getOwner() instanceof EnemyNPC && npc instanceof EnemyNPC) {
                continue;
            }

            if (projectilesHit.contains(this)) continue;

            if (this.getBounds().intersects(npc.getBounds())) {
                if (npc.getHealth() > 0) {
                    npc.takeDamage(playerDamage);
                    projectilesHit.add(this);

                    if (!isPersistentBullet) {
                        this.isHidden = true;
                        map.getProjectiles().remove(this);
                    }
                }
            }
        }

        // Check collision with Player (player damage)
        if (!projectilesHit.contains(this) &&
            this.getBounds().intersects(player.getBounds()) &&
            this.getOwner() != player &&
            !player.isReflecting) {

            if (player.getHealth() > 0) {
                player.takeDamage(enemyDamage);
                projectilesHit.add(this);

                if (!isPersistentBullet) {
                    this.isHidden = true;
                    map.getProjectiles().remove(this);
                }
            }
        }

        // Reflect logic
        for (Projectile p : map.getProjectiles()) {
            if (p.getOwner() != player &&
                p.getBounds().intersects(player.getBounds()) &&
                player.isReflecting) {

                p.setOwner(player);
                p.reverseDirection();
            }
        }

        // Wall collision
        if (destroyOnWallHit && MapCollisionHandler.isCollidingWithMapEntity(this, map, null)) {
            this.isHidden = true;
            map.getProjectiles().remove(this);
        }

        super.update();
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

    public void reverseDirection() {
        this.velocityX = -dirX * speed;
        this.velocityY = -dirY * speed;
    }
}
