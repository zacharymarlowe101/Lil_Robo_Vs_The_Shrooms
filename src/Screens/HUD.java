package Screens;

import Engine.*;
import SpriteFont.SpriteFont;
import Level.Player;
import Players.Robot;
import Projectiles.Projectile;

import java.awt.*;

// This class is for the win level screen
public class HUD extends Screen {
    protected SpriteFont option1;
    protected SpriteFont option2;
    protected SpriteFont option3;

    private Player robot;

    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected UpgradeScreen upgradeScreen;
    protected PlayLevelScreen playLevelScreen;

    protected Rectangle staminaBar;

    public HUD(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        if(robot != null){
            //System.out.println("Initializing HUD Health: " + robot.getHealth());
            instructions = new SpriteFont("HEALTH: " + robot.getHealth(), 15, 7, "Arial", 30, Color.white);
            staminaBar = new Rectangle(100,100,25,100);
        }
        //option3 = new SpriteFont("Increase Bullet Damage [Press 3]", 270, 359,"Arial", 20, Color.white);
        //keyLocker.lockKey(Key.SPACE);
        //keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        /*if (Keyboard.isKeyUp(Key.ONE)) {
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
        }*/
    }

    public void draw(GraphicsHandler graphicsHandler) {
        //graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        //option1.draw(graphicsHandler);
        //option2.draw(graphicsHandler);
        //option3.draw(graphicsHandler);
        if(instructions != null){
            //System.out.println("Drawing HUD Health: " + robot.getHealth());
            instructions = new SpriteFont("HEALTH: " + robot.getHealth(), 15, 7, "Arial", 30, Color.white);
            instructions.draw(graphicsHandler);
            int x = 10, y = 575, width = 25, height = robot.reflectCount*20;
            graphicsHandler.drawFilledRectangle(x, y - height, width, height, Color.BLUE);
            //graphicsHandler.drawFilledRectangleWithBorder(10,375,25,200,Color.BLUE,Color.BLACK,2);
            graphicsHandler.drawRectangle(10,375,25,200,Color.BLACK);
            
        }
        
    }

    public void setRobot(Player robot){
        this.robot = robot;
    }

    public Player getRobot(){
        return this.robot;
    }
    
}
