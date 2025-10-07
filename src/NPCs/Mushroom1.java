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
import Utils.Direction;
import Utils.Point;

// Mushroom1 NPC
public class Mushroom1 extends NPC {
    private int totalAmountMoved = 0;
    private Direction direction = Direction.RIGHT;
    private float speed = 1.0f; // Adjust speed here

    public Mushroom1(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom1.png"), 32, 32), "WALK_RIGHT");
        this.setHealth(3);
    }

    // Makes the mushroom walk left and right
    @Override
    public void performAction(Player player) {
        float amountMoved = moveXHandleCollision(speed * direction.getVelocity());

        if (amountMoved == 0) {
            direction = (direction == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
            totalAmountMoved = 0;
        } else {
            totalAmountMoved += Math.abs(amountMoved);
            if (totalAmountMoved >= 55) {
                totalAmountMoved = 0;
                direction = (direction == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
            }
        }

        if (direction == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else {
            currentAnimationName = "WALK_LEFT";
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build()
            });
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 25)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 25)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 25)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 25)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 25)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 25)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 25)
                    .withScale(2)
                    .withBounds(3, 5, 26, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 25)
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
