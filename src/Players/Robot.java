package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class Robot extends Player {
        private int animationDelay;

        // x and y are the position of the hitbox of the robot, 0,0 being the uper left corner of the robot
        // Width and Height are likely the size of the hitbox, very finnicky at the moment, be careful with the numbers but shrink them in a good way,
        // im thinking for each height lowered it changed the y by that much as well, same for x and width
        // If I adjust them right they can feel better to play with, allowing for a very slight bit of overlap with enemie projectiles without taking damage,
        // the grace period as I called it

        //Originall Sizes (X=4, Y=0, Width=22, Height=32)
        //Adjusted Sizes (X=8, Y=10, Width=18, Height=22)


    public Robot(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Robot.png"), 32, 32), x, y, "STAND_RIGHT");
        walkSpeed = 6.0f;
        animationDelay = 8;
    }

    @Override
    public void update() {
        super.update();
        setAnimationSpeed(animationDelay);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                     new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 32)
                            .withScale(3)
                            .withBounds(8, 10, 18 , 22)
                            .build()
            });
        }};
    }

        //public setter for the animation delay
        @Override
        public void setAnimationDelay(int animationDelay){
                this.animationDelay = animationDelay;
        }

        @Override
        public int getAnimationDelay(){
                return animationDelay;
        }


    @Override
    public void setWalkSpeed(float walkSpeed){
        super.walkSpeed = walkSpeed;
    }

    @Override
    public float getWalkSpeed(){
        return this.walkSpeed;
    }

//private method to change the animation speed in the super class
private void setAnimationSpeed(int delay){
        Frame[] frames = getAnimations().get(getCurrentAnimationName());
        for (Frame frame : frames) {
	        frame.setDelay(delay);
        }
    }
  
}
