package NPCs;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.util.HashMap;

// Mushroom1 Enemy NPC
public class Mushroom1 extends NPC {

    public Mushroom1(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom1.png"), 26, 26), "STAND");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(3)
                        .withBounds(0, 0, 12, 12)
                        .build()
            });
        }};
    }
}