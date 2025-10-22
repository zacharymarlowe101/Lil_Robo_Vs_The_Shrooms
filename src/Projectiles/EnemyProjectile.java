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
    private float speed = 5.0f;

    public EnemyProjectile(float x, float y, Frame frame, Point start, Point targetFloat, NPC owner) {
        super(x, y, frame, start, new java.awt.Point((int) targetFloat.x, (int) targetFloat.y));
        this.owner = owner;

        float dx = targetFloat.x - start.x;
        float dy = targetFloat.y - start.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance != 0) {
            this.velocityX = (dx / distance) * speed;
            this.velocityY = (dy / distance) * speed;
        } else {
            this.velocityX = 0;
            this.velocityY = 0;
        }
    }

    @Override
    public void update(Player player) {
        moveX(velocityX);
        moveY(velocityY);

        if (map == null || isHidden()) return;

        if (owner != null && this.getBounds().intersects(owner.getBounds())) return;

        if (this.getBounds().intersects(player.getBounds())) {
            player.takeDamage(1);
            this.isHidden = true;
            map.getProjectiles().remove(this);
            return;
        }

        if (MapCollisionHandler.isCollidingWithMapEntity(this, map, null)) {
            this.isHidden = true;
            map.getProjectiles().remove(this);
        }
    }
}
