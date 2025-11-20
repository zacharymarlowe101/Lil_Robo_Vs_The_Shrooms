package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnemyNPC;
import Level.Healthbar;
import Level.Player;
import Projectiles.AOEAttack;
import Utils.Point;
import Level.NPC;

public class Mushroom2 extends EnemyNPC {

    private final Healthbar healthbar = new Healthbar(this);

    public Mushroom2(int id, Point location, int level) {
        super(
            id,
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("Mushroom2.png"), 32, 32),
            "STAND_RIGHT",
            3,
            1,
            level
        );

        setNonLooping("ATTACK_LEFT");
        setNonLooping("ATTACK_RIGHT");
        setNonLooping("DEATH");

        this.attack = new AOEAttack() {
            @Override
            public void perform(NPC npc, Player player) {
                super.perform(npc, player);

                if (player.getX() > npc.getX()) {
                    npc.setCurrentAnimationName("ATTACK_RIGHT");
                } else {
                    npc.setCurrentAnimationName("ATTACK_LEFT");
                }

                npc.takeDamage(((EnemyNPC) npc).getHp());
            }

            @Override
            public int getCooldown() {
                return 90;
            }
        };
    }

    @Override
    public void performAction(Player player) {
        if (isDead()) {
            if (!currentAnimationName.equals("DEATH")) {
                setCurrentAnimationName("DEATH");
            }

            if (isCurrentAnimationFinished()) {
                setIsHidden(true);
            }

            return;
        }

        if (currentAnimationName.equals("ATTACK_LEFT") || currentAnimationName.equals("ATTACK_RIGHT")) {
            if (!isCurrentAnimationFinished()) {
                return;
            }

            setCurrentAnimationName(player.getX() > getX() ? "STAND_RIGHT" : "STAND_LEFT");
        } else {
            setCurrentAnimationName(player.getX() > getX() ? "STAND_RIGHT" : "STAND_LEFT");

            updateEnemyAttack(player);
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 24).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build()
            });

            put("STAND_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 24).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16).withScale(2).withBounds(3, 5, 16, 16).build()
            });

            put("ATTACK_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(1, 0), 18).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 18).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 18).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 18).withScale(2).withBounds(3, 5, 16, 16).build()
            });

            put("ATTACK_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(1, 0), 18).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 18).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 18).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 18).withScale(2).withBounds(13, 5, 16, 16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build()
            });

            put("DEATH", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(2, 0), 15).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1), 8).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2), 8).withScale(2).withBounds(3, 5, 16, 16).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 3), 5).withScale(2).withBounds(3, 5, 16, 16).build()
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
}
