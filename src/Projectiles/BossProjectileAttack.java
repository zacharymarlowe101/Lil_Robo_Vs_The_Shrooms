package Projectiles;

import GameObject.Frame;
import GameObject.ImageEffect;
import Level.NPC;
import Level.Player;
import Utils.ImageUtils;
import Utils.Point;

import java.awt.Color;

public class BossProjectileAttack implements EnemyAttack {

    private final int cooldown = 90;
    private int cooldownCounter = 0;
    private int shotsRemaining = 15;

    @Override
    public void perform(NPC npc, Player player) {
        if (cooldownCounter > 0 || shotsRemaining <= 0) return;

        Frame projectileFrame = new Frame(
            ImageUtils.createSolidImage(new Color(255, 0, 0), 50, 50),
            ImageEffect.NONE,
            1,
            null
        );

        float npcCenterX = npc.getX() + npc.getCurrentFrame().getWidth() / 2f;
        float npcCenterY = npc.getY() + npc.getCurrentFrame().getHeight() / 2f;

        float offset = 30f;
        float spawnX = npcCenterX + (player.getX() > npcCenterX ? offset : -offset);
        float spawnY = npcCenterY;

        Point start = new Point(spawnX, spawnY);

        float targetX = player.getX() + player.getBounds().getWidth() / 2f;
        float targetY = player.getY() + player.getBounds().getHeight() / 2f;
        Point target = new Point(targetX, targetY);

        EnemyProjectile projectile = new EnemyProjectile(spawnX, spawnY, projectileFrame, start, target, npc);
        projectile.setDestroyOnWallHit(true);
        npc.getMap().addProjectile(projectile);

        cooldownCounter = cooldown;
        shotsRemaining--;
    }

    public void tickCooldown() {
        if (cooldownCounter > 0) {
            cooldownCounter--;
        }
    }

    public boolean isPhaseOneComplete() {
        return shotsRemaining <= 0;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        float npcCenterX = npc.getX() + npc.getCurrentFrame().getWidth() / 2f;
        float npcCenterY = npc.getY() + npc.getCurrentFrame().getHeight() / 2f;

        float playerCenterX = player.getX() + player.getBounds().getWidth() / 2f;
        float playerCenterY = player.getY() + player.getBounds().getHeight() / 2f;

        float dx = playerCenterX - npcCenterX;
        float dy = playerCenterY - npcCenterY;

        float distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= 600f * 600f;
    }
}
