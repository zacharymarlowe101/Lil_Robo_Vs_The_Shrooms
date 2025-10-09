package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.Frame;
import GameObject.ImageEffect;
import Utils.Point;
import Utils.ImageUtils;

import java.awt.Color;

/**
 * Fires a projectile in the direction the NPC is facing (left or right),
 * with enough offset to prevent self-collision.
 */
public class ProjectileAttack implements EnemyAttack {

    @Override
    public void perform(NPC npc, Player player) {
        // Create a red projectile sprite
        Frame projectileFrame = new Frame(
            ImageUtils.createSolidImage(new Color(255, 0, 0), 20, 20),
            ImageEffect.NONE,
            1,
            null
        );

        // Center of NPC
        float spawnX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float spawnY = npc.getY() + npc.getBounds().getHeight() / 2f;

        // Determine facing direction
        boolean facingRight = npc.getCurrentAnimationName().toUpperCase().contains("RIGHT");

        // Offset projectile further in front (to avoid self-collision)
        float offset = npc.getBounds().getWidth() * 1.0f + 20;

        if (facingRight) {
            spawnX += offset;
        } else {
            spawnX -= offset;
        }

        // Starting and target points based on direction
        Point start = new Point(spawnX, spawnY);
        java.awt.Point target = facingRight
            ? new java.awt.Point((int) (spawnX + 100), (int) spawnY)
            : new java.awt.Point((int) (spawnX - 100), (int) spawnY);

        // Create and add projectile to map
        Projectile projectile = new Projectile(spawnX, spawnY, projectileFrame, start, target);
        npc.getMap().addProjectile(projectile);
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return npc.distanceTo(player) < 300;
    }

    @Override
    public int getCooldown() {
        return 90; // frames between attacks (~1.5s at 60fps)
    }
}
