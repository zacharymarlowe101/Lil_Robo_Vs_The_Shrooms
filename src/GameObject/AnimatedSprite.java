package GameObject;

import Engine.GraphicsHandler;
import Utils.Point;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AnimatedSprite implements IntersectableRectangle {
    protected float x, y;

    protected HashMap<String, Frame[]> animations;
    protected String currentAnimationName = "";
    protected String previousAnimationName = "";
    protected int currentFrameIndex;
    protected boolean hasAnimationLooped;
    protected Frame currentFrame;
    private int frameDelayCounter;

    private Set<String> nonLoopingAnimations = new HashSet<>();

    public AnimatedSprite(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        this.x = x;
        this.y = y;
        this.animations = loadAnimations(spriteSheet);
        this.currentAnimationName = startingAnimationName;
        updateCurrentFrame();
    }

    public AnimatedSprite(float x, float y, HashMap<String, Frame[]> animations, String startingAnimationName) {
        this.x = x;
        this.y = y;
        this.animations = animations;
        this.currentAnimationName = startingAnimationName;
        updateCurrentFrame();
    }

    public AnimatedSprite(float x, float y, Frame[] frames) {
        this.x = x;
        this.y = y;
        this.animations = new HashMap<String, Frame[]>() {{
            put("DEFAULT", frames);
        }};
        this.currentAnimationName = "DEFAULT";
        updateCurrentFrame();
    }

    public AnimatedSprite(float x, float y, Frame frame) {
        this.x = x;
        this.y = y;
        this.animations = new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] { frame });
        }};
        this.currentAnimationName = "DEFAULT";
        updateCurrentFrame();
    }

    public void update() {
        if (!previousAnimationName.equals(currentAnimationName)) {
            resetAnimation();
        } else {
            if (getCurrentAnimation().length > 1 && currentFrame.getDelay() > 0) {
                frameDelayCounter--;

                if (frameDelayCounter == 0) {
                    currentFrameIndex++;
                    if (currentFrameIndex >= animations.get(currentAnimationName).length) {
                        if (isNonLooping(currentAnimationName)) {
                            currentFrameIndex = animations.get(currentAnimationName).length - 1;
                            hasAnimationLooped = true;
                        } else {
                            currentFrameIndex = 0;
                            hasAnimationLooped = true;
                        }
                    }
                    frameDelayCounter = getCurrentFrame().getDelay();
                    updateCurrentFrame();
                }
            }
        }
        previousAnimationName = currentAnimationName;
    }

    private void resetAnimation() {
        currentFrameIndex = 0;
        updateCurrentFrame();
        frameDelayCounter = getCurrentFrame().getDelay();
        hasAnimationLooped = false;
    }

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return null;
    }

    public HashMap<String, Frame[]> getAnimations() {
        return this.animations;
    }

    protected void updateCurrentFrame() {
        currentFrame = getCurrentFrame();
        currentFrame.setX(x);
        currentFrame.setY(y);
    }

    protected Frame getCurrentFrame() {
        return animations.get(currentAnimationName)[currentFrameIndex];
    }

    public Frame[] getCurrentAnimation() {
        return animations.get(currentAnimationName);
    }

    public String getCurrentAnimationName() {
        return this.currentAnimationName;
    }

    public int getCurrentFrameIndex() {
        return this.currentFrameIndex;
    }

    public void setCurrentAnimationName(String animationName) {
        this.currentAnimationName = animationName;
        if (!previousAnimationName.equals(currentAnimationName)) {
            resetAnimation();
        }
    }

    public void setCurrentAnimationFrameIndex(int frameIndex) {
        this.currentFrameIndex = frameIndex;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        currentFrame.draw(graphicsHandler);
    }

    public void drawBounds(GraphicsHandler graphicsHandler, Color color) {
        currentFrame.drawBounds(graphicsHandler, color);
    }

    public float getX() {
        return currentFrame.getX();
    }

    public float getY() {
        return currentFrame.getY();
    }

    public float getX1() {
        return currentFrame.getX1();
    }

    public float getY1() {
        return currentFrame.getY1();
    }

    public float getX2() {
        return currentFrame.getX2();
    }

    public float getY2() {
        return currentFrame.getY2();
    }

    public Point getLocation() {
        return currentFrame.getLocation();
    }

    public void setX(float x) {
        this.x = x;
        currentFrame.setX(x);
    }

    public void setY(float y) {
        this.y = y;
        currentFrame.setY(y);
    }

    public void setLocation(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public void moveX(float dx) {
        this.x += dx;
        currentFrame.moveX(dx);
    }

    public void moveRight(float dx) {
        this.x += dx;
        currentFrame.moveRight(dx);
    }

    public void moveLeft(float dx) {
        this.x -= dx;
        currentFrame.moveLeft(dx);
    }

    public void moveY(float dy) {
        this.y += dy;
        currentFrame.moveY(dy);
    }

    public void moveDown(float dy) {
        this.y += dy;
        currentFrame.moveDown(dy);
    }

    public void moveUp(float dy) {
        this.y -= dy;
        currentFrame.moveUp(dy);
    }

    public float getScale() {
        return currentFrame.getScale();
    }

    public void setScale(float scale) {
        currentFrame.setScale(scale);
    }

    public void setWidth(int width) {
        currentFrame.setWidth(width);
    }

    public void setHeight(int height) {
        currentFrame.setHeight(height);
    }

    public int getWidth() {
        return currentFrame.getWidth();
    }

    public int getHeight() {
        return currentFrame.getHeight();
    }

    public Rectangle getBounds() {
        return currentFrame.getBounds();
    }

    public void setBounds(Rectangle bounds) {
        currentFrame.setBounds(bounds);
    }

    @Override
    public Rectangle getIntersectRectangle() {
        return currentFrame.getIntersectRectangle();
    }

    public boolean intersects(IntersectableRectangle other) {
        return currentFrame.intersects(other);
    }

    public boolean touching(IntersectableRectangle other) {
        return currentFrame.touching(other);
    }

    public float getAreaOverlapped(IntersectableRectangle other) {
        return currentFrame.getAreaOverlapped(other);
    }

    @Override
    public String toString() {
        return String.format(
            "Current Sprite: x=%s y=%s width=%s height=%s bounds=(%s, %s, %s, %s)",
            x, y, getWidth(), getHeight(),
            getBounds().getX(), getBounds().getY(),
            getBounds().getWidth(), getBounds().getHeight()
        );
    }

    public void setNonLooping(String animationName) {
        nonLoopingAnimations.add(animationName);
    }

    private boolean isNonLooping(String animationName) {
        return nonLoopingAnimations.contains(animationName);
    }

    public boolean isCurrentAnimationFinished() {
        return hasAnimationLooped;
    }
}
