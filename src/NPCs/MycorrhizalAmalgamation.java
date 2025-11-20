package NPCs;

import java.util.HashMap;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnemyNPC;
import Level.HasHealth;
import Level.Healthbar;
import Level.Map;
import Level.Player;
import Projectiles.BossAOEAttack;
import Projectiles.BossProjectileAttack;
import Utils.Point;

public class MycorrhizalAmalgamation extends EnemyNPC implements HasHealth {

    private float hp = 50f;
    private float maxHp = 50f;
    private final Healthbar healthbar = new Healthbar(this);

    private BossProjectileAttack projectileAttack;
    private BossAOEAttack aoeAttack;

    private boolean phaseOneComplete = false;
    private boolean phaseTwoComplete = false;
    private boolean waitingForPhaseSwap = false;
    private int phaseSwapTimer = 0;
    private final int PHASE_SWAP_DELAY = 180;

    private boolean summonedMinions = false;
    private boolean playedDeathAnimation = false;

    public MycorrhizalAmalgamation(int id, Point location, int level) {
        super(id, location.x, location.y,
                new SpriteSheet(ImageLoader.load("MycorrhizalAmalgamation.png"), 128, 128),
                "WALK_RIGHT", 20, 2, level);

        this.maxHp = 10f;
        this.hp = maxHp;

        projectileAttack = new BossProjectileAttack();
        aoeAttack = new BossAOEAttack();
        this.attack = projectileAttack;

        setNonLooping("ATTACK_LEFT");
        setNonLooping("ATTACK_RIGHT");
        setNonLooping("DEATH");
    }

    @Override public float getX() { return super.getX(); }
    @Override public float getY() { return super.getY(); }
    @Override public float getHp() { return hp; }
    @Override public float getMaxHp() { return maxHp; }
    @Override public float getHpRatio() { return hp / maxHp; }

    @Override
    public boolean isDead() {
        return hp <= 0.0001f;
    }

    @Override public float getHealth() { return (int) hp; }

    @Override
    public void takeDamage(float dmg) {
        if (summonedMinions && !areMinionsDefeated()) return;
        hp = Math.max(0f, hp - dmg);
        System.out.println("BOSS HP: " + hp + "/" + maxHp + " | isDead: " + isDead());
    }

    @Override
    public void setHealth(float health) {
        this.hp = health;
    }

    public Healthbar getHealthbar() { return healthbar; }

    private boolean areMinionsDefeated() {
        Map map = getMap();
        if (map == null) return true;
        for (int id = 300; id <= 323; id++) {
            EnemyNPC minion = (EnemyNPC) map.getNPCById(id);
            if (minion != null && !minion.isDead()) return false;
        }
        return true;
    }

    private void summonMinions() {
        Map map = getMap();
        if (map == null) return;

        int cx = (int)(getX() + getBounds().getWidth() / 2);
        int cy = (int)(getY() + getBounds().getHeight() / 2);

        map.queueNPC(new Mushroom1(300, new Point(cx + 50, cy - 150), 1));
        map.queueNPC(new Mushroom1(301, new Point(cx + 50, cy + 300), 1));
        map.queueNPC(new Mushroom1(302, new Point(cx - 200, cy + 100), 1));
        map.queueNPC(new Mushroom1(303, new Point(cx + 275, cy + 100), 1));

        map.queueNPC(new Mushroom3(310, new Point(cx - 625, cy - 310), 1));
        map.queueNPC(new Mushroom3(311, new Point(cx + 700, cy - 310), 1));
        map.queueNPC(new Mushroom3(312, new Point(cx - 625, cy + 450), 1));
        map.queueNPC(new Mushroom3(313, new Point(cx + 700, cy + 450), 1));

        map.queueNPC(new Mushroom2(320, new Point(cx + 50, cy - 310), 1));
        map.queueNPC(new Mushroom2(321, new Point(cx + 50, cy + 500), 1));
        map.queueNPC(new Mushroom2(322, new Point(cx - 650, cy + 100), 1));
        map.queueNPC(new Mushroom2(323, new Point(cx + 700, cy + 100), 1));
    }

    @Override
    public void performAction(Player player) {
        float npcCX = getX() + getBounds().getWidth() / 2f;
        float playCX = player.getX() + player.getBounds().getWidth() / 2f;

        if (isDead()) {
            if (!playedDeathAnimation) {
                playedDeathAnimation = true;
                setCurrentAnimationName("DEATH");
            }

            if (isCurrentAnimationFinished()) {
                this.isHidden = true;
            }
            return;
        }

        if (currentAnimationName.startsWith("ATTACK") && isCurrentAnimationFinished()) {
            currentAnimationName = playCX > npcCX ? "WALK_RIGHT" : "WALK_LEFT";
            if (attack == projectileAttack) projectileAttack.startCooldown();
            else if (attack == aoeAttack) aoeAttack.startCooldown();
        }

        boolean animatingAttack = currentAnimationName.startsWith("ATTACK") && !isCurrentAnimationFinished();
        if (animatingAttack) return;

        currentAnimationName = playCX > npcCX ? "WALK_RIGHT" : "WALK_LEFT";

        if (!summonedMinions && hp <= maxHp * 0.5f) {
            summonedMinions = true;
            summonMinions();
        }

        if (summonedMinions && !areMinionsDefeated()) return;

        if (!phaseOneComplete) {
            projectileAttack.tickCooldown();
            if (projectileAttack.isInRange(this, player) && projectileAttack.isOffCooldown()) {
                setCurrentAnimationName(playCX > npcCX ? "ATTACK_RIGHT" : "ATTACK_LEFT");
                projectileAttack.perform(this, player);
            }

            if (projectileAttack.isPhaseOneComplete() && !waitingForPhaseSwap) {
                waitingForPhaseSwap = true;
                phaseSwapTimer = 0;
            }

            if (waitingForPhaseSwap && ++phaseSwapTimer >= PHASE_SWAP_DELAY) {
                phaseOneComplete = true;
                this.attack = aoeAttack;
                waitingForPhaseSwap = false;
            }

            return;
        }

        if (!phaseTwoComplete) {
            aoeAttack.tickCooldown();
            if (aoeAttack.hasUsesRemaining() && aoeAttack.isInRange(this, player) && aoeAttack.isOffCooldown()) {
                setCurrentAnimationName(playCX > npcCX ? "ATTACK_RIGHT" : "ATTACK_LEFT");
                aoeAttack.perform(this, player);
            }

            if (!aoeAttack.hasUsesRemaining() && !waitingForPhaseSwap) {
                waitingForPhaseSwap = true;
                phaseSwapTimer = 0;
            }

            if (waitingForPhaseSwap && ++phaseSwapTimer >= PHASE_SWAP_DELAY + 300) {
                phaseOneComplete = false;
                aoeAttack = new BossAOEAttack();
                projectileAttack = new BossProjectileAttack();
                this.attack = projectileAttack;
                waitingForPhaseSwap = false;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 40).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build()
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 40).withScale(3).withBounds(32, 32, 64, 64).build()
            });

            put("ATTACK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 30).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 30).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 30).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 30).withScale(3).withBounds(32, 32, 64, 64).build()
            });

            put("ATTACK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 30).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 30).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 30).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 30).withScale(3).withBounds(32, 32, 64, 64).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build()
            });

            put("DEATH", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0), 35).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1), 30).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2), 25).withScale(3).withBounds(32, 32, 64, 64).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 3), 25).withScale(3).withBounds(32, 32, 64, 64).build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        float camX = this.getMap().getCamera().getX();
        float camY = this.getMap().getCamera().getY();

        healthbar.draw(graphicsHandler.getGraphics(), camX, camY);
    }

    @Override public int getHealthbarWidth() { return 120; }
    @Override public int getHealthbarYOffset() { return -60; }
}
