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
import Projectiles.ProjectileAttack;
import Utils.Point;

public class Mushroom1 extends EnemyNPC {

    private final Healthbar healthbar = new Healthbar(this);

    public Mushroom1(int id, Point location, int level) {
        super(
            id,
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("Mushroom1.png"), 32, 32),
            "WALK_RIGHT",
            3,
            1,
            level
        );

        this.attack = new ProjectileAttack() {
            @Override
            public int getCooldown() {
                return 90;
            }
        };
    }

    @Override
    public void performAction(Player player) {
        if (isDead()) {
            this.isHidden = true;
            return;
        }

        if (player.getX() > this.getX()) {
            if (!currentAnimationName.equals("WALK_RIGHT")) {
                currentAnimationName = "WALK_RIGHT";
            }
        } else {
            if (!currentAnimationName.equals("WALK_LEFT")) {
                currentAnimationName = "WALK_LEFT";
            }
        }
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
                new FrameBuilder(spriteSheet.getSprite(0, 0), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 35)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build()
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
