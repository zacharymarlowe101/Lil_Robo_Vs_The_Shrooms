package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.Frame;
import GameObject.ImageEffect;
import Utils.Point;
import Utils.ImageUtils;

import java.awt.Color;

public class ProjectileAttack implements EnemyAttack {

    @Override
    public void perform(NPC npc, Player player) {
        Frame projectileFrame = new Frame(
            ImageUtils.createSolidImage(new Color(255, 0, 0), 20, 20),
            ImageEffect.NONE,
            1,
            null
        );

        float spawnX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float spawnY = npc.getY() + npc.getBounds().getHeight() / 2f;

        boolean facingRight = npc.getCurrentAnimationName().toUpperCase().contains("RIGHT");

        float offset = npc.getBounds().getWidth() * 1.0f + 20;

        if (facingRight) {
            spawnX += offset;
        } else {
            spawnX -= offset;
        }

        Point start = new Point(spawnX, spawnY);
        java.awt.Point target = facingRight
            ? new java.awt.Point((int) (spawnX + 100), (int) spawnY)
            : new java.awt.Point((int) (spawnX - 100), (int) spawnY);

        Projectile projectile = new Projectile(spawnX, spawnY, projectileFrame, start, target);
        npc.getMap().addProjectile(projectile);
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return npc.distanceTo(player) < 300;
    }

    @Override
    public int getCooldown() {
        return 90;
    }
}
