package Projectiles;

import GameObject.Frame;
import Level.MapCollisionHandler;
import Level.NPC;
import Level.Player;
import Utils.Point;

public class EnemyProjectile extends Projectile {

    private NPC owner;

    public EnemyProjectile(float x, float y, Frame frame, Point start, java.awt.Point target, NPC owner) {
        super(x, y, frame, start, target);
        this.owner = owner;
    }

    @Override
    public void update(Player player) {
        super.performAction(player);
        super.update();

        if (map == null || isHidden()) return;

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
