package NPCs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnemyNPC;
import Level.Player;
import Projectiles.EnemyAttack;
import Projectiles.LaserAttack;
import Projectiles.AOEAttack;
import Projectiles.ProjectileAttack;
import Utils.Point;

public class MycorrhizalAmalgamation extends EnemyNPC {

    private final List<EnemyAttack> attacks = new ArrayList<>();

    public MycorrhizalAmalgamation(int id, Point location, int level) {
        super(
            id,
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("MycorrhizalAmalgamation.png"), 128, 128),
            "WALK_RIGHT",
            5,
            2,
            level
        );

        loadAttacks();
        this.attack = attacks.get(0);
    }

    private void loadAttacks() {
        attacks.add(new LaserAttack());
        attacks.add(new AOEAttack());
        attacks.add(new ProjectileAttack() {
            @Override
            public int getCooldown() {
                return 60;
            }
        });
    }

    @Override
    public void performAction(Player player) {
        if (player.getX() > this.getX()) {
            if (!currentAnimationName.equals("WALK_RIGHT")) {
                currentAnimationName = "WALK_RIGHT";
            }
        } else {
            if (!currentAnimationName.equals("WALK_LEFT")) {
                currentAnimationName = "WALK_LEFT";
            }
        }

        List<EnemyAttack> validAttacks = new ArrayList<>();
        for (EnemyAttack candidate : attacks) {
            if (candidate.isInRange(this, player)) {
                validAttacks.add(candidate);
            }
        }

        if (!validAttacks.isEmpty()) {
            int index = (int)(Math.random() * validAttacks.size());
            this.attack = validAttacks.get(index);
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build()
            });
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 35)
                    .withScale(3)
                    .withBounds(32, 32, 64, 64)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
