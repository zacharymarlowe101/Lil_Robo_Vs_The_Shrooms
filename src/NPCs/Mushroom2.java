package NPCs;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.util.HashMap;

// Mushroom1 NPC with red square sprite
public class Mushroom2 extends NPC {

    public Mushroom2(int id, Point location) {
        // Load red square image as sprite
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom2.png"), 26, 26), "STAND");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(3)
                        .withBounds(0, 0, 13, 13)
                        .build()
            });
        }};
    }
}