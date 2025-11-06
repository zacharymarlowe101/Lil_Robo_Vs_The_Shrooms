package Projectiles;

import GameObject.Frame;
import GameObject.ImageEffect;
import Level.NPC;
import Level.Player;
import Utils.ImageUtils;

import java.awt.Color;

public class BossAOEAttack implements EnemyAttack {

    private int cooldown = 260;
    private int cooldownCounter = 0;
    private int usesRemaining = 3;

    @Override
    public void perform(NPC npc, Player player) {
        if (cooldownCounter > 0 || usesRemaining <= 0) return;

        float centerX = npc.getX() + npc.getCurrentFrame().getWidth() / 2f;
        float centerY = npc.getY() + npc.getCurrentFrame().getHeight() / 2f;
        float radius = 32f;
        float projectileSize = 30f;
        int projectileCount = 64;

        for (int i = 0; i < projectileCount; i++) {
            double angle = 2 * Math.PI * i / projectileCount;
            float dx = (float) Math.cos(angle);
            float dy = (float) Math.sin(angle);

            float spawnX = centerX + dx * radius - (projectileSize / 2f);
            float spawnY = centerY + dy * radius - (projectileSize / 2f);

            Frame projectileFrame = new Frame(
                ImageUtils.createSolidImage(new Color(255, 100, 100), (int) projectileSize, (int) projectileSize),
                ImageEffect.NONE,
                1,
                null
            );
            projectileFrame.setBounds(0, 0, (int) projectileSize, (int) projectileSize);

            EnemyProjectile projectile = new EnemyProjectile(spawnX, spawnY, projectileFrame, dx, dy, npc);
            projectile.setSpeed(1.8f);
            projectile.setLifetime(10000);
            projectile.setDestroyOnWallHit(true);

            npc.getMap().addProjectile(projectile);
        }

        usesRemaining--;
        cooldownCounter = cooldown;
    }

    public void tickCooldown() {
        if (cooldownCounter > 0) {
            cooldownCounter--;
        }
    }

    public boolean hasUsesRemaining() {
        return usesRemaining > 0;
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return true;
    }

    @Override
    public int getCooldown() {
        return 0;
    }
}
