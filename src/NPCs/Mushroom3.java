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
import Level.NPC;
import Level.Player;
import Projectiles.LaserAttack;
import Utils.Point;

public class Mushroom3 extends EnemyNPC {

    private final Healthbar healthbar = new Healthbar(this);

    public Mushroom3(int id, Point location, int level) {
        super(
            id,
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("Mushroom3.png"), 32, 64),
            "WALK_RIGHT",
            3,
            1,
            level
        );

        setNonLooping("ATTACK_LEFT");
        setNonLooping("ATTACK_RIGHT");
        setNonLooping("DEATH");

        this.attack = new LaserAttack() {
            @Override
            public void perform(NPC npc, Player player) {
                super.perform(npc, player);
                if (player.getX() > npc.getX()) {
                    npc.setCurrentAnimationName("ATTACK_RIGHT");
                } else {
                    npc.setCurrentAnimationName("ATTACK_LEFT");
                }
            }

            @Override
            public int getCooldown() {
                return 1200;
            }
        };
    }

    @Override
    public void performAction(Player player) {
        if (isDead()) {
            if (!currentAnimationName.equals("DEATH")) {
                setCurrentAnimationName("DEATH");
            } else if (isCurrentAnimationFinished()) {
                setIsHidden(true);
            }
            return;
        }

        boolean isAttacking = currentAnimationName.equals("ATTACK_LEFT") || currentAnimationName.equals("ATTACK_RIGHT");

        if (isAttacking && isCurrentAnimationFinished()) {
            setCurrentAnimationName(player.getX() > getX() ? "WALK_RIGHT" : "WALK_LEFT");
        }

        if (!isAttacking) {
            String walkAnim = player.getX() > getX() ? "WALK_RIGHT" : "WALK_LEFT";
            if (!currentAnimationName.equals(walkAnim)) {
                setCurrentAnimationName(walkAnim);
            }
            updateEnemyAttack(player);
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 40).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build()
            });
            put("WALK_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 40).withScale(2).withBounds(3, 5, 16, 48).build()
            });
            put("ATTACK_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(1, 0), 18).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 18).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 18).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 18).withScale(2).withBounds(3, 5, 16, 48).build()
            });
            put("ATTACK_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(1, 0), 18).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 18).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 18).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 18).withScale(2).withBounds(3, 5, 16, 48).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build()
            });
            put("DEATH", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(2, 0), 25).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1), 18).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2), 12).withScale(2).withBounds(3, 5, 16, 48).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 3), 12).withScale(2).withBounds(3, 5, 16, 48).build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        float camX = getMap().getCamera().getX();
        float camY = getMap().getCamera().getY();
        healthbar.draw(graphicsHandler.getGraphics(), camX, camY);
    }
}
