package Projectiles;

import Level.NPC;
import Level.Player;
import Utils.Point;
import GameObject.SpriteSheet;
import Engine.ImageLoader;
public class BossLaserAttack implements EnemyAttack {

    private static final int COOLDOWN_FRAMES = 300;
    private static final int DURATION_FRAMES = 90;
    private static final int BEAM_WIDTH = 600;
    private static final int BEAM_HEIGHT = 120;

    private int cooldownCounter = 0;
    private int usesRemaining = 5;
    private int bulletwidth = 300;
    private int bulletHeight = 24;

    @Override
    public void perform(NPC npc, Player player) {
        if (cooldownCounter > 0 || usesRemaining <= 0) return;

        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Laser.png"), bulletwidth, bulletHeight);

        float centerX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float centerY = npc.getY() + npc.getBounds().getHeight() / 2f;

        float spawnY = centerY - BEAM_HEIGHT / 2f + 150;

        boolean playerIsRight = player.getX() > centerX;

        float spawnX = playerIsRight
            ? centerX + 100
            : centerX - BEAM_WIDTH - 100;

        EnemyProjectile laser = new EnemyProjectile(
            spriteSheet,
            spawnX,
            spawnY,
            new Point(spawnX, spawnY),
            new Point(spawnX, spawnY),
            npc
        ) {
            private int framesLeft = DURATION_FRAMES;

            {
                this.setSpeed(0f);
                this.setDestroyOnWallHit(false);
            }

            @Override
            public void update(Player player) {
                framesLeft--;
                if (framesLeft <= 0) {
                    this.isHidden = true;
                }
                super.update(player);
            }
        };

        npc.getMap().addProjectile(laser);
        cooldownCounter = COOLDOWN_FRAMES;
        usesRemaining--;
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
        return COOLDOWN_FRAMES;
    }
}
