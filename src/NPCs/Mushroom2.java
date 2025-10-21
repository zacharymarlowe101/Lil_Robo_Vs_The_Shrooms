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
import Utils.Direction;
import Utils.Point;

// Mushroom2 NPC - stands still, looks around, and periodically fires an AOE attack
public class Mushroom2 extends NPC {
    private int directionTimer = 0; // Timer for flipping direction
    private Direction direction = Direction.RIGHT;

    public Mushroom2(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom2.png"), 32, 32), "STAND_RIGHT");
        this.setHealth(3);

        this.attack = new AOEAttack();
    }

    // Makes the mushroom turn in place periodically
    @Override
    public void performAction(Player player) {
        // Flip direction every 150 frames (~2.5 seconds at 60 FPS)
        directionTimer++;

        if (directionTimer >= 150) {
            directionTimer = 0;
            direction = (direction == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
        }

        // Change animation based on facing direction
        if (direction == Direction.RIGHT) {
            currentAnimationName = "STAND_RIGHT";
        } else {
            currentAnimationName = "STAND_LEFT";
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            // Idle/stand animations
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 15)
                    .withScale(3)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 15)
                    .withScale(3)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 9)
                    .withScale(3)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 6)
                    .withScale(3)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 15)
                    .withScale(3)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 15)
                    .withScale(3)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 9)
                    .withScale(3)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 6)
                    .withScale(3)
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
