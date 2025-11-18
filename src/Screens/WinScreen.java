package Screens;

import Engine.*;
import SpriteFont.SpriteFont;
import GameObject.Sprite;
import GameObject.SpriteSheet;

import java.awt.*;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;
    protected Sprite winCard;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        winCard = new Sprite(ImageLoader.load("WinCard.png"), 74, 128);
        winCard.setScale(3);
        winCard.setLocation((Config.GAME_WINDOW_WIDTH-winCard.getWidth())/2, (Config.GAME_WINDOW_HEIGHT-winCard.getHeight())/2);

        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
        keyLocker.lockKey(Key.ONE);
        keyLocker.lockKey(Key.TWO);
        keyLocker.lockKey(Key.THREE);

    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            playLevelScreen.goBackToMenu();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        winCard.draw(graphicsHandler);
    }
}
