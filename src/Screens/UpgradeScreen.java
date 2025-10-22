package Screens;

import Engine.*;
import SpriteFont.SpriteFont;
import Level.Player;
import Players.Robot;
import Projectiles.Projectile;

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
     protected PlayLevelScreen playLevelScreen;

    public UpgradeScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        instructions = new SpriteFont("Choose an Upgrade!", 250, 239, "Arial", 30, Color.white);
        option1 = new SpriteFont("Increase speed [press 1]", 270, 279,"Arial", 20, Color.white);
        option2 = new SpriteFont("Increase Health [press 2]", 270, 319,"Arial", 20, Color.white);
        option3 = new SpriteFont("Increase Bullet Damage [Coming Soon]", 270, 359,"Arial", 20, Color.white);

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
            robot.setWalkSpeed(robot.getWalkSpeed() + 0.5f);
            if(robot.getWalkSpeed()%5 == 0) robot.setAnimationDelay(robot.getAnimationDelay() - 1);
            playLevelScreen.onClear();
            
            
        } else if (Keyboard.isKeyDown(Key.TWO) && !keyLocker.isKeyLocked(Key.TWO)) {
            robot.setHealth(robot.getHealth() + 1);
            playLevelScreen.onClear();

        } else if (Keyboard.isKeyDown(Key.THREE) && !keyLocker.isKeyLocked(Key.THREE)) {
            Projectile.setPlayerDamage(Projectile.getPlayerDamage() + 1);
            playLevelScreen.onClear();
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
