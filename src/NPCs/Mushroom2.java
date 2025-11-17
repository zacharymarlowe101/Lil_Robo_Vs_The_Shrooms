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

public class Mushroom2 extends EnemyNPC {

    private float hp = 3f;
    private float maxHp = 3f;
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
        this.attack = new AOEAttack();
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
        System.out.println("Mushroom2 HP: " + hp + "/" + maxHp);
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
    public void updateEnemyAttack(Player player) {
        super.updateEnemyAttack(player);

        if (attack != null && attack.isInRange(this, player)) {
            attack.perform(this, player);

            this.hp = 0f;
            this.isHidden = true;

            System.out.println("Mushroom2 used AOE and self-destructed.");
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                    .withScale(2)
                    .withBounds(13, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                    .withScale(2)
                    .withBounds(13, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 24)
                    .withScale(2)
                    .withBounds(13, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
                    .withScale(2)
                    .withBounds(13, 5, 16, 16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 24)
                    .withScale(2)
                    .withBounds(3, 5, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
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
