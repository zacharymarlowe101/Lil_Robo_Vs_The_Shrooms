package NPCs;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.util.HashMap;

// Mushroom3 NPC
public class Mushroom3 extends NPC {

    public Mushroom3(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom3.png"), 12, 12), "STAND");

        // Set starting health to 3
        this.setHealth(3);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(4)
                        .withBounds(0, 0, 26, 13)
                        .build()
            });
        }};
    }
}
