package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.Frame;
import GameObject.ImageEffect;
import Utils.Point;
import Utils.ImageUtils;

import java.awt.Color;

/**
 * Fires projectiles outward in a circular AOE pattern.
 * Projectiles spawn just outside the NPC's hitbox (30px buffer).
 */
public class AOEAttack implements EnemyAttack {

    private int projectileCount = 16;   // number of projectiles in the ring
    private int range = 200;            // how far projectiles travel outward
    private int cooldown = 180;         // 3 seconds at 60 fps

    @Override
    public void perform(NPC npc, Player player) {
        // Create projectile sprite
        Frame projectileFrame = new Frame(
            ImageUtils.createSolidImage(new Color(255, 100, 100), 20, 20),
            ImageEffect.NONE,
            1,
            null
        );

        // Center of NPC
        float centerX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float centerY = npc.getY() + npc.getBounds().getHeight() / 2f;

        // Effective spawn distance: just outside hitbox + 30px
        float npcRadius = (float) (Math.max(npc.getBounds().getWidth(), npc.getBounds().getHeight()) / 2f);
        float spawnDistance = npcRadius + 30;

        // Create projectiles in a ring
        for (int i = 0; i < projectileCount; i++) {
            double angle = (2 * Math.PI / projectileCount) * i;

            // Spawn position (30px beyond hitbox)
            float spawnX = (float) (centerX + Math.cos(angle) * spawnDistance);
            float spawnY = (float) (centerY + Math.sin(angle) * spawnDistance);

            // Target point (farther in same direction)
            java.awt.Point target = new java.awt.Point(
                (int) (centerX + Math.cos(angle) * (spawnDistance + range)),
                (int) (centerY + Math.sin(angle) * (spawnDistance + range))
            );

            // Create and add projectile
            Projectile projectile = new Projectile(
                spawnX,
                spawnY,
                projectileFrame,
                new Point(spawnX, spawnY),
                target
            );
            npc.getMap().addProjectile(projectile);
        }
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        // Trigger if player is close enough
        return npc.distanceTo(player) < 300;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }
}
