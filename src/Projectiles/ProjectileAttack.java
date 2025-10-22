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

        float npcCenterX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float npcCenterY = npc.getY() + npc.getBounds().getHeight() / 2f;

        float offset = 20f;
        float spawnX = npcCenterX + (player.getX() > npcCenterX ? offset : -offset);
        float spawnY = npcCenterY;

        Point start = new Point(spawnX, spawnY);
        Point target = new Point(
            player.getCalibratedXLocation(),
            player.getCalibratedYLocation()
        );

        EnemyProjectile projectile = new EnemyProjectile(spawnX, spawnY, projectileFrame, start, target, npc);
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
