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
import Projectiles.AOEAttack;
import Utils.Point;

public class Mushroom2 extends NPC {

    public Mushroom2(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom2.png"), 32, 32), "STAND_RIGHT");
        this.setHealth(3);
        this.attack = new AOEAttack();
    }

    @Override
    public void performAction(Player player) {
        if (player.getX() < this.getX()) {
            if (!currentAnimationName.equals("STAND_LEFT")) {
                currentAnimationName = "STAND_LEFT";
            }
        } else {
            if (!currentAnimationName.equals("STAND_RIGHT")) {
                currentAnimationName = "STAND_RIGHT";
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 24)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 24)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
