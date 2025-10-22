package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Projectiles.ProjectileAttack;
import Utils.Direction;
import Utils.Point;

public class Mushroom1 extends NPC {

    public Mushroom1(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom1.png"), 32, 32), "WALK_RIGHT");
        this.setHealth(3);
        this.attack = new ProjectileAttack();
    }

    @Override
    public void performAction(Player player) {
        if (player.getX() > this.getX()) {
            currentAnimationName = "WALK_RIGHT";
        } else {
            currentAnimationName = "WALK_LEFT";
        }

        updateEnemyAttack(player);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build()
            });
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 25)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
