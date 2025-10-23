package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.Frame;
import GameObject.ImageEffect;
import Utils.Point;
import Utils.ImageUtils;

import java.awt.Color;

public class AOEAttack implements EnemyAttack {

    private int projectileCount = 24;
    private int range = 600;
    private int cooldown = 450;

    @Override
    public void perform(NPC npc, Player player) {
        Frame projectileFrame = new Frame(
            ImageUtils.createSolidImage(new Color(255, 100, 100), 20, 20),
            ImageEffect.NONE,
            1,
            null
        );
        projectileFrame.setBounds(0, 0, 20, 20);

        // Use NPC's visual center
        float centerX = npc.getX() + (npc.getBounds().getWidth() / 2f);
        float centerY = npc.getY() + (npc.getBounds().getHeight() / 2f);

        float npcRadius = (float) (Math.max(npc.getBounds().getWidth(), npc.getBounds().getHeight()) / 2f);
        float spawnDistance = npcRadius + 50; // Increased to reduce initial overlap

        for (int i = 0; i < projectileCount; i++) {
            double angle = (2 * Math.PI / projectileCount) * i;

            float spawnX = (float) (centerX + Math.cos(angle) * spawnDistance);
            float spawnY = (float) (centerY + Math.sin(angle) * spawnDistance);
            Point start = new Point(spawnX, spawnY);

            // Radial targets with range cap
            float targetX = (float) (centerX + Math.cos(angle) * range);
            float targetY = (float) (centerY + Math.sin(angle) * range);
            Point target = new Point(targetX, targetY);

            EnemyProjectile projectile = new EnemyProjectile(spawnX, spawnY, projectileFrame, start, target, npc);
            projectile.setOwner(npc);
            projectile.setSpeed(2.0f);
            projectile.setLifetime(1500);
            npc.getMap().addProjectile(projectile);
        }
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return npc.distanceTo(player) < 300;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }
}