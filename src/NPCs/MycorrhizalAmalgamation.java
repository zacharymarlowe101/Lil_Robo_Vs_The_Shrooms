package NPCs;

import java.util.HashMap;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnemyNPC;
import Level.Map;
import Level.Player;
import NPCs.Mushroom1;
import NPCs.Mushroom2;
import NPCs.Mushroom3;
import Projectiles.BossAOEAttack;
import Projectiles.BossLaserAttack;
import Projectiles.BossProjectileAttack;
import Utils.Point;

public class MycorrhizalAmalgamation extends EnemyNPC {

    private BossProjectileAttack projectileAttack;
    private BossAOEAttack aoeAttack;
    private BossLaserAttack laserAttack;

    private boolean phaseOneComplete = false;
    private boolean phaseTwoComplete = false;
    private boolean waitingForPhaseSwap = false;
    private int phaseSwapTimer = 0;
    private final int PHASE_SWAP_DELAY = 180;

    private boolean summonedMinions = false;

    private final boolean skipToLaserPhase = false;

    public MycorrhizalAmalgamation(int id, Point location, int level) {
        super(
            id,
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("MycorrhizalAmalgamation.png"), 128, 128),
            "WALK_RIGHT",
            20,
            2,
            level
        );

        projectileAttack = new BossProjectileAttack();
        aoeAttack = new BossAOEAttack();
        laserAttack = new BossLaserAttack();
        this.attack = projectileAttack;
    }

    @Override
    public void performAction(Player player) {
        float npcCenterX = this.getX() + this.getBounds().getWidth() / 2f;
        float playerCenterX = player.getX() + player.getBounds().getWidth() / 2f;

        if (playerCenterX > npcCenterX) {
            if (!currentAnimationName.equals("WALK_RIGHT")) {
                currentAnimationName = "WALK_RIGHT";
            }
        } else {
            if (!currentAnimationName.equals("WALK_LEFT")) {
                currentAnimationName = "WALK_LEFT";
            }
        }

        if (skipToLaserPhase) {
            phaseOneComplete = true;
            phaseTwoComplete = true;
            this.attack = laserAttack;
        }

        if (!summonedMinions && getHealth() <= getMaxHealth() / 2) {
            summonedMinions = true;
            summonMinions();
        }

        if (summonedMinions && !areMinionsDefeated()) {
            return;
        }

        if (!phaseOneComplete) {
            projectileAttack.tickCooldown();
            if (projectileAttack.isInRange(this, player)) {
                projectileAttack.perform(this, player);
            }
            if (projectileAttack.isPhaseOneComplete() && !waitingForPhaseSwap) {
                waitingForPhaseSwap = true;
                phaseSwapTimer = 0;
            }
            if (waitingForPhaseSwap) {
                phaseSwapTimer++;
                if (phaseSwapTimer >= PHASE_SWAP_DELAY) {
                    phaseOneComplete = true;
                    this.attack = aoeAttack;
                    waitingForPhaseSwap = false;
                }
            }
        } else if (!phaseTwoComplete) {
            aoeAttack.tickCooldown();
            if (aoeAttack.hasUsesRemaining() && aoeAttack.isInRange(this, player)) {
                aoeAttack.perform(this, player);
            }
            if (!aoeAttack.hasUsesRemaining() && !waitingForPhaseSwap) {
                waitingForPhaseSwap = true;
                phaseSwapTimer = 0;
            }
            if (waitingForPhaseSwap) {
                phaseSwapTimer++;
                if (phaseSwapTimer >= PHASE_SWAP_DELAY + 300) {
                    phaseOneComplete = false;
                    aoeAttack = new BossAOEAttack();
                    projectileAttack = new BossProjectileAttack();
                    this.attack = projectileAttack;
                    waitingForPhaseSwap = false;
                }
            }
        }
    }

    private boolean areMinionsDefeated() {
        if (getMap() == null) return true;
        for (int id = 300; id <= 323; id++) {
            EnemyNPC minion = (EnemyNPC) getMap().getNPCById(id);
            if (minion != null && !minion.isDead()) {
                return false;
            }
        }
        return true;
    }

    private void summonMinions() {
        Map map = getMap();
        if (map == null) return;

        int centerX = (int)(getX() + getBounds().getWidth() / 2);
        int centerY = (int)(getY() + getBounds().getHeight() / 2);

        map.queueNPC(new Mushroom1(300, new Point(centerX + 50, centerY - 150), 1));
        map.queueNPC(new Mushroom1(301, new Point(centerX + 50, centerY + 300), 1));
        map.queueNPC(new Mushroom1(302, new Point(centerX - 200, centerY + 100), 1));
        map.queueNPC(new Mushroom1(303, new Point(centerX + 275, centerY + 100), 1));

        map.queueNPC(new Mushroom3(310, new Point(centerX - 625, centerY - 310), 1));
        map.queueNPC(new Mushroom3(311, new Point(centerX + 700, centerY - 310), 1));
        map.queueNPC(new Mushroom3(312, new Point(centerX - 625, centerY + 450), 1));
        map.queueNPC(new Mushroom3(313, new Point(centerX + 700, centerY + 450), 1));

        map.queueNPC(new Mushroom2(320, new Point(centerX + 50, centerY - 310), 1));
        map.queueNPC(new Mushroom2(321, new Point(centerX + 50, centerY + 500), 1));
        map.queueNPC(new Mushroom2(322, new Point(centerX - 650, centerY + 100), 1));
        map.queueNPC(new Mushroom2(323, new Point(centerX + 700, centerY + 100), 1));
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build()
            });
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
