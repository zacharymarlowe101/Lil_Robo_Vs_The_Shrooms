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
import Projectiles.LaserAttack;
import Utils.Direction;
import Utils.Point;

public class Mushroom3 extends NPC {

    public Mushroom3(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom3.png"), 32, 65), "STAND_RIGHT");
        this.setHealth(3);
        this.attack = new LaserAttack();
    }

    @Override
    public void performAction(Player player) {
        if (player.getX() < this.getX()) {
            currentAnimationName = "STAND_LEFT";
        } else {
            currentAnimationName = "STAND_RIGHT";
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .build()
            });
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 20)
                    .withScale(3)
                    .withBounds(3, 5, 26, 65)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
