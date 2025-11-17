package Projectiles;

import Level.NPC;
import Level.Player;
import Utils.Point;
import Engine.ImageLoader;
import GameObject.SpriteSheet;

public class ProjectileAttack implements EnemyAttack {
    private int bulletLength = 16;

    @Override
    public void perform(NPC npc, Player player) {
        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Mushroom1Bullet.png"), bulletLength, bulletLength);

        float npcCenterX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float npcCenterY = npc.getY() + npc.getBounds().getHeight() / 2f;

        float offset = 20f;
        float spawnX = npcCenterX + (player.getX() > npcCenterX ? offset : -offset);
        float spawnY = npcCenterY;

        Point start = new Point(spawnX, spawnY);

        float targetX = player.getX() + player.getBounds().getWidth() / 2f;
        float targetY = player.getY() + player.getBounds().getHeight() / 2f;
        Point target = new Point(targetX, targetY);


        EnemyProjectile projectile = new EnemyProjectile(spriteSheet, spawnX, spawnY, start, target, npc);
        npc.getMap().addProjectile(projectile);
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return npc.distanceTo(player) < 300;
    }

    @Override
    public int getCooldown() {
        return 150;
    }
}
