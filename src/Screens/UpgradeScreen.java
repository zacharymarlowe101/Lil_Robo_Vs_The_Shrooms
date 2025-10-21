package Screens;

import Engine.*;
import SpriteFont.SpriteFont;
import Level.Player;

import java.awt.*;

// This class is for the win level screen
public class UpgradeScreen extends Screen {
    protected SpriteFont option1;
    protected SpriteFont option2;
    protected SpriteFont option3;

    private Player robot;

    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected UpgradeScreen upgradeScreen;

    public UpgradeScreen(PlayLevelScreen playLevelScreen) {
        this.upgradeScreen = upgradeScreen;
        initialize();
    }

    @Override
    public void initialize() {
        option1 = new SpriteFont("Increase Speed", 350, 239, "Arial", 30, Color.white);
        option2 = new SpriteFont("Increase Health", 450, 239, "Arial", 30, Color.white);
        option3 = new SpriteFont("Increase Bullet Damage", 550, 239, "Arial", 30, Color.white);


        instructions = new SpriteFont("Choose an upgrade to continue (press 1 2 or 3)", 120, 279,"Arial", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.ONE)) {
            keyLocker.unlockKey(Key.ONE);
        }
        if (Keyboard.isKeyUp(Key.TWO)) {
            keyLocker.unlockKey(Key.TWO);
        }
        if (Keyboard.isKeyUp(Key.THREE)) {
            keyLocker.unlockKey(Key.THREE);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.ONE) && !keyLocker.isKeyLocked(Key.ONE)) {
            //increase speed
        } else if (Keyboard.isKeyDown(Key.TWO) && !keyLocker.isKeyLocked(Key.TWO)) {
            //increase health
        } else if (Keyboard.isKeyDown(Key.THREE) && !keyLocker.isKeyLocked(Key.THREE)) {
            //increase bullet damage
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        option1.draw(graphicsHandler);
        option2.draw(graphicsHandler);
        option3.draw(graphicsHandler);

        instructions.draw(graphicsHandler);
    }

    public void setRobot(Player robot){
        this.robot = robot;
    }

    public Player getRobot(){
        return this.robot;
    }
    
}
