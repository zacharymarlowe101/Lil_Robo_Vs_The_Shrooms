package Projectiles;

import Level.NPC;
import Level.Player;
import GameObject.Frame;
import GameObject.ImageEffect;
import Utils.Point;
import Utils.ImageUtils;
import java.awt.Color;

/**
 * Two thick, stationary laser beams that appear in front of the NPC
 * and last for ~2 seconds.
 */
public class LaserAttack implements EnemyAttack {

    private static final int COOLDOWN_FRAMES = 180;   // ~3 seconds between shots
    private static final int DURATION_FRAMES = 120;   // visible for 2 seconds
    private static final int BEAM_LENGTH = 550;       // px
    private static final int BEAM_THICKNESS = 28;     // px
    private static final int VERTICAL_SPACING = 30;   // distance between beams
    private static final int FRONT_PADDING = 70;      // how far in front to spawn

    @Override
    public void perform(NPC npc, Player player) {
        // Create the laser frame (bright red rectangle)
        Frame beamFrame = new Frame(
            ImageUtils.createSolidImage(new Color(255, 50, 50), BEAM_LENGTH, BEAM_THICKNESS),
            ImageEffect.NONE,
            1,
            null
        );

        // NPC center position
        float centerX = npc.getX() + npc.getBounds().getWidth() / 2f;
        float centerY = npc.getY() + npc.getBounds().getHeight() / 2f;

        // Determine facing direction
        boolean facingRight = npc.getCurrentAnimationName().toUpperCase().contains("RIGHT");

        // Base spawn X: a bit in front of the NPC
        float forwardOffset = npc.getBounds().getWidth() / 2f + FRONT_PADDING;
        float spawnX = facingRight ? centerX + forwardOffset : centerX - forwardOffset - BEAM_LENGTH;

        // Targets (used only for construction; no movement)
        java.awt.Point target = new java.awt.Point((int) spawnX, (int) centerY);

        // Spawn two parallel stationary beams
        spawnStationaryBeam(npc, spawnX, centerY - VERTICAL_SPACING, beamFrame, target);
        spawnStationaryBeam(npc, spawnX, centerY + VERTICAL_SPACING, beamFrame, target);
    }

    private void spawnStationaryBeam(NPC npc, float spawnX, float spawnY, Frame frame, java.awt.Point target) {
        Projectile laser = new Projectile(spawnX, spawnY, frame, new Point(spawnX, spawnY), target) {
            private int framesLeft = DURATION_FRAMES;

            @Override
            public void update(Player player) {
                // No movement, just countdown until hidden
                framesLeft--;
                if (framesLeft <= 0) {
                    this.isHidden = true;
                }
            }
        };

        npc.getMap().addProjectile(laser);
    }

    @Override
    public boolean isInRange(NPC npc, Player player) {
        return npc.distanceTo(player) < 400;
    }

    @Override
    public int getCooldown() {
        return COOLDOWN_FRAMES;
    }
}
