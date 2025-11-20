package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.SpriteSheet;
import Engine.ImageLoader;

public class BossAOEAttack implements EnemyAttack {

    private int cooldown = 260;
    private int cooldownCounter = 0;
    private int usesRemaining;
    private int bulletLength = 16;
    private int maxUses = 3;


    @Override
    public void perform(NPC npc, Player player) {
        if (cooldownCounter > 0 || usesRemaining <= 0) return;

        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Mushroom1Bullet.png"), bulletLength, bulletLength);

        float centerX = npc.getX() + npc.getCurrentFrame().getWidth() / 2f;
        float centerY = npc.getY() + npc.getCurrentFrame().getHeight() / 2f;
        float radius = 32f;
        float projectileSize = 30f;
        int projectileCount = 86;

        for (int i = 0; i < projectileCount; i++) {
            double angle = 2 * Math.PI * i / projectileCount;
            float dx = (float) Math.cos(angle);
            float dy = (float) Math.sin(angle);

            float spawnX = centerX + dx * radius - (projectileSize / 2f);
            float spawnY = centerY + dy * radius - (projectileSize / 2f);

            EnemyProjectile projectile = new EnemyProjectile(spriteSheet, spawnX, spawnY, dx, dy, npc);
            projectile.setSpeed(1.8f);
            projectile.setLifetime(10000);
            projectile.setDestroyOnWallHit(true);

            npc.getMap().addProjectile(projectile);
        }

        usesRemaining--;
        cooldownCounter = cooldown;
    }

    public void startCooldown() {
        cooldownCounter = cooldown;
    }

    public void tickCooldown() {
        if (cooldownCounter > 0) cooldownCounter--;
    }

    public void reset() {
        usesRemaining = maxUses;
    }



    public boolean hasUsesRemaining() {
        return usesRemaining > 0;
    }

    public boolean isOffCooldown() {
    return cooldownCounter <= 0;
    }


    @Override
    public boolean isInRange(NPC npc, Player player) {
        return true;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }
}
