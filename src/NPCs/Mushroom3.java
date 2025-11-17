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
import Projectiles.LaserAttack;
import Utils.Point;

public class Mushroom3 extends EnemyNPC {

    private float hp = 3f;
    private float maxHp = 3f;
    private final Healthbar healthbar = new Healthbar(this);

    public Mushroom3(int id, Point location, int level) {
        super(
            id,
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("Mushroom3.png"), 32, 65),
            "WALK_RIGHT",
            3,
            1,
            level
        );
        this.attack = new LaserAttack();
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

    @Override
    public void takeDamage(float dmg) {
        hp = Math.max(0f, hp - dmg);
        System.out.println("Mushroom3 HP: " + hp + "/" + maxHp);
    }

    @Override
    public void takeDamage(int dmg) {
        takeDamage((float) dmg);
    }

    public Healthbar getHealthbar() {
        return healthbar;
    }

    @Override
    public void performAction(Player player) {
        if (isDead()) {
            this.isHidden = true;
            return;
        }

        if (player.getX() < this.getX()) {
            if (!currentAnimationName.equals("WALK_LEFT")) {
                currentAnimationName = "WALK_LEFT";
            }
        } else {
            if (!currentAnimationName.equals("WALK_RIGHT")) {
                currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 48)
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
