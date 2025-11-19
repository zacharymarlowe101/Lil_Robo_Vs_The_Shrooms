package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.Frame;
import Utils.Point;
import Engine.ImageLoader;
import java.util.HashMap;
import GameObject.SpriteSheet;

public class LaserAttack implements EnemyAttack {

    private static final int COOLDOWN_FRAMES = 240;
    private static final int DURATION_FRAMES = 90;
    private static final int BEAM_LENGTH = 300;
    private static final int BEAM_THICKNESS = 24;
    private static final int VERTICAL_SPACING = 12;
    private static final int FRONT_PADDING = 8;
    private static final float LASER_SPEED = 1.4f;
    private int bulletwidth = 300;
    private int bulletHeight = 24;


    @Override
    public void perform(NPC npc, Player player) {
        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Laser.png"), bulletwidth, bulletHeight);


        float centerX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float centerY = npc.getY() + npc.getBounds().getHeight() / 2f;

        boolean facingRight = npc.getCurrentAnimationName().toUpperCase().contains("RIGHT");

        float forwardOffset = npc.getBounds().getWidth() / 2f + FRONT_PADDING;
        float spawnX = facingRight ? centerX + forwardOffset : centerX - forwardOffset - BEAM_LENGTH;

        float targetX = facingRight ? spawnX + 50 : spawnX - 50;

        spawnMovingBeam(npc, spawnX, centerY - VERTICAL_SPACING, spriteSheet, new java.awt.Point((int) targetX, (int) centerY));
        spawnMovingBeam(npc, spawnX, centerY + VERTICAL_SPACING, spriteSheet, new java.awt.Point((int) targetX, (int) centerY));
    }

    private void spawnMovingBeam(NPC npc, float spawnX, float spawnY, SpriteSheet spriteSheet, java.awt.Point target) {
        EnemyProjectile laser = new EnemyProjectile(
            spriteSheet,
            spawnX,
            spawnY,
            new Point(spawnX, spawnY),
            new Point(target.x, target.y),
            npc
        ) {
            private int framesLeft = DURATION_FRAMES;

            {
                this.setSpeed(LASER_SPEED);
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
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return npc.distanceTo(player) < 350;
    }

    @Override
    public int getCooldown() {
        return COOLDOWN_FRAMES;
    }
}
