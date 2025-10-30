package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.Frame;
import GameObject.ImageEffect;
import Utils.ImageUtils;

import java.awt.Color;

public class AOEAttack implements EnemyAttack {

    private int projectileCount = 15;
    private int cooldown = 450;

    @Override
    public void perform(NPC npc, Player player) {
        float centerX = npc.getX() + (npc.getBounds().getWidth() / 2f);
        float centerY = npc.getY() + (npc.getBounds().getHeight() / 2f);

        float radius = 30f;

        for (int i = 0; i < projectileCount; i++) {
            Frame projectileFrame = new Frame(
                ImageUtils.createSolidImage(new Color(255, 100, 100), 20, 20),
                ImageEffect.NONE,
                1,
                null
            );
            projectileFrame.setBounds(0, 0, 20, 20);

            double angle = 2 * Math.PI * i / projectileCount;

            float dx = (float) Math.cos(angle);
            float dy = (float) Math.sin(angle);

            float spawnX = centerX + dx * radius;
            float spawnY = centerY + dy * radius;

            EnemyProjectile projectile = new EnemyProjectile(spawnX, spawnY, projectileFrame, dx, dy, npc);
            projectile.setSpeed(2.5f);
            projectile.setLifetime(1500);
            projectile.setDestroyOnWallHit(true);

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