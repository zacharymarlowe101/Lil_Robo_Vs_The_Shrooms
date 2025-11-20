package Projectiles;

import GameObject.Frame;
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

        super.owner = owner;
        this.startTime = System.currentTimeMillis();

        float dx = target.x - start.x;
        float dy = target.y - start.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance != 0) {
            dirX = dx / distance; dirY = dy / distance;
            this.velocityX = (dx / distance) * speed;
            this.velocityY = (dy / distance) * speed;
        } else {
            dirX = 0; dirY = 0;
            this.velocityX = 0;
            this.velocityY = 0;
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

        if (lifetime > 0 && System.currentTimeMillis() - startTime > lifetime) {
            this.isHidden = true;
            map.getProjectiles().remove(this);
            return;
        }

        // if (this.getBounds().intersects(player.getBounds()) && !player.isReflecting) {
        //     player.takeDamage(1);
        //     this.isHidden = true;
        //     map.getProjectiles().remove(this);
        //     return;
        // }

        if (map != null && map.getProjectiles() != null) {
            for (NPC npcs : map.getActiveNPCs()) { //checks if projectile hits npc
                //System.out.println("Checking if Exists");
                if (this == null || this.isHidden()) continue;
                if (!projectilesHit.contains(this) && this.getBounds().intersects(npcs.getBounds()/*<<<Should be list of all NPCs*/) && this.getOwner() != npcs) {
                    if (npcs.getHealth() > 0) {
                        npcs.takeDamage(playerDamage);
                        projectilesHit.add(this);
                        this.isHidden = true; // Hide the projectile upon hitting the NPC
                        map.getProjectiles().remove(this);
                    }
                }
                //System.out.println("Checking projectile collisions for Wall " + MapCollisionHandler.isCollidingWithMapEntity(p, map, null));
            }
            //System.out.println("Player Reflecting = " + player.isReflecting);
            if(!projectilesHit.contains(this) && this.getBounds().intersects(player.getBounds()) && this.getOwner() != player && !player.isReflecting){ //checks if projectile hits player
                if (player.getHealth() > 0) {
                        player.takeDamage(enemyDamage);
                        projectilesHit.add(this);
                        this.isHidden = true; // Hide the projectile upon hitting the NPC
                        map.getProjectiles().remove(this);
                    }
            }
        }

        for(Projectile p : map.getProjectiles()){
            if(p.getOwner() != player && p.getBounds().intersects(player.getBounds()) && player.isReflecting){
                player.reflectCount--;
                p.setOwner(player);
                p.reverseDirection();
            }
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

    public void reverseDirection() {
        this.velocityX = (float)(dirX * -1);
        this.velocityY = (float)(dirY * -1);
    }
}
