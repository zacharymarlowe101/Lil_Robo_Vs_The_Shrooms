package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class ForceField extends GameObject {
        //private static HashMap<String, Frame[]> forceFieldHashMap;
        private int animationDelay;
        private static SpriteSheet forceFieldSpriteSheet = new SpriteSheet(ImageLoader.load("ForceFieldTransparent.png"), 32, 35);
        private static HashMap forceFieldHashMap = new HashMap<String, Frame[]>() {{
                put("FORCE_FIELD", new Frame[] {
                        new FrameBuilder(forceFieldSpriteSheet.getSprite(0, 0), 32)
                                .withScale(3)
                                .withBounds(0, 0, 32 , 35)
                                .build(),
                        new FrameBuilder(forceFieldSpriteSheet.getSprite(1, 0), 32)
                                .withScale(3)
                                .withBounds(0, 0, 32 , 35)
                                .build()
                });
            }};
        private GameObject forceField = new GameObject(getCalibratedXLocation(),getCalibratedYLocation(),forceFieldHashMap,"FORCE_FIELD");
        // used to draw graphics to the panel
        // x and y are the position of the hitbox of the robot, 0,0 being the uper left corner of the robot
        // Width and Height are likely the size of the hitbox, very finnicky at the moment, be careful with the numbers but shrink them in a good way,
        // im thinking for each height lowered it changed the y by that much as well, same for x and width
        // If I adjust them right they can feel better to play with, allowing for a very slight bit of overlap with enemie projectiles without taking damage,
        // the grace period as I called it

        //Originall Sizes (X=4, Y=0, Width=22, Height=32)
        //Adjusted Sizes (X=8, Y=10, Width=18, Height=22)


    public ForceField(float x, float y) {
        super(x, y, forceFieldHashMap,  "FORCE_FIELD");
        animationDelay = 8;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {

        return new HashMap<String, Frame[]>() {{
                put("FORCE_FIELD", new Frame[] {
                        new FrameBuilder(forceFieldSpriteSheet.getSprite(0, 0), 32)
                                .withScale(3)
                                .withBounds(0, 0, 32 , 35)
                                .build(),
                        new FrameBuilder(forceFieldSpriteSheet.getSprite(1, 0), 32)
                                .withScale(3)
                                .withBounds(0, 0, 32 , 35)
                                .build()
                });
            }};
    }

    //private method to change the animation speed in the super class
    private void setAnimationSpeed(int delay){
        Frame[] frames = getAnimations().get(getCurrentAnimationName());
        for (Frame frame : frames) {
	        frame.setDelay(delay);
        }
    }
  
}
