package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.SpriteSheet;
import Engine.ImageLoader;
public class AOEAttack implements EnemyAttack {

    private int projectileCount = 12;
    private int cooldown = 450;
    private int bulletLength = 16;

    @Override
    public void perform(NPC npc, Player player) {
        float centerX = npc.getX() + (npc.getBounds().getWidth() / 2f);
        float centerY = npc.getY() + (npc.getBounds().getHeight() / 2f);

        float radius = 30f;

        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("PuffballBullet.png"), bulletLength, bulletLength);

        for (int i = 0; i < projectileCount; i++) {
            double angle = 2 * Math.PI * i / projectileCount;

            float dx = (float) Math.cos(angle);
            float dy = (float) Math.sin(angle);

            float spawnX = centerX + dx * radius;
            float spawnY = centerY + dy * radius;

            EnemyProjectile projectile = new EnemyProjectile(spriteSheet, spawnX, spawnY, dx, dy, npc);
            projectile.setSpeed(2.5f);
            projectile.setLifetime(2250);
            projectile.setDestroyOnWallHit(true);

            npc.getMap().addProjectile(projectile);
        }
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return npc.distanceTo(player) < 350;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    
}