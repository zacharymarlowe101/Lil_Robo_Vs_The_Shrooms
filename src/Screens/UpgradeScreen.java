package Screens;

import Engine.*;
import Level.Player;
import Players.Robot;
import Projectiles.Projectile;
import GameObject.SpriteSheet;
import GameObject.Sprite;
import Engine.GameWindow;
import java.awt.*;

// This class is for the win level screen
public class UpgradeScreen extends Screen {
    protected Sprite option1;
    protected Sprite option2;
    protected Sprite option3;

    private Player robot;

    private int timeSinceSelect;//allows card animation to play
    private int selectionTimeBuffer;//this makes sure that the upgrades can't accidentally be pressed while waiting for it to load
    private boolean isOptionChosen;//prevents spamming the upgrade

    protected KeyLocker keyLocker = new KeyLocker();
    protected UpgradeScreen upgradeScreen;
    protected PlayLevelScreen playLevelScreen;

    public UpgradeScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.load("UpgradeCards.png"), 64, 100);
        
        //note that the last 2 values are broken and do nothing
        option2 = new Sprite(sheet.getSubImage(0, 1), 64, 128);
        option1 = new Sprite(sheet.getSubImage(0, 0), 64, 128);
        option3 = new Sprite(sheet.getSubImage(0, 2), 64, 128);

        option1.setLocation(100, 100);
        option2.setLocation(Config.GAME_WINDOW_WIDTH/2 -64, 100);
        option3.setLocation(Config.GAME_WINDOW_WIDTH - 228, 100);

    
        option1.setScale(3.0f);
        option2.setScale(3.0f);
        option3.setScale(3.0f);
        

        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);

        timeSinceSelect = 25;
        selectionTimeBuffer = 20;
        isOptionChosen = false;
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

        //a little time buffer to prevent accidentally choosing an upgrade while the screen is loading
        if(selectionTimeBuffer > 0){
            selectionTimeBuffer --;
            return;
        }


        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.ONE) && !keyLocker.isKeyLocked(Key.ONE)) {
            option1.setScale(2.8f);
            increaseSpeed();

        } else if (Keyboard.isKeyDown(Key.TWO) && !keyLocker.isKeyLocked(Key.TWO)) {
            option2.setScale(2.8f);
            increaseHealth();

        } else if (Keyboard.isKeyDown(Key.THREE) && !keyLocker.isKeyLocked(Key.THREE)) {
            option3.setScale(2.8f);
            increaseDamage();
        } 
        
        if(GamePanel.isMouseClicked() && option1.pointInBounds(GamePanel.getMousePositionPoint().x,GamePanel.getMousePositionPoint().y)){
                option1.setScale(2.8f);
                increaseSpeed();
        }

        if(GamePanel.isMouseClicked() && option2.pointInBounds(GamePanel.getMousePositionPoint().x, GamePanel.getMousePositionPoint().y)){
                option2.setScale(2.8f);
                increaseHealth();
        }

        if(GamePanel.isMouseClicked() && option3.pointInBounds(GamePanel.getMousePositionPoint().x, GamePanel.getMousePositionPoint().y)){
                option3.setScale(2.8f);
                increaseDamage();
            }

        //controls the time since the upgrade was chosen
        if(isOptionChosen){
            timeSinceSelect --;
            if(timeSinceSelect <= 0)
                playLevelScreen.onClear();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        option1.draw(graphicsHandler);
        option2.draw(graphicsHandler);
        option3.draw(graphicsHandler);
    }

    public void setRobot(Player robot){
        this.robot = robot;
    }

    public Player getRobot(){
        return this.robot;
    }

    private void increaseSpeed(){

        if(!isOptionChosen) {
            robot.setWalkSpeed(robot.getWalkSpeed() + 0.5f);
            if(robot.getWalkSpeed()%5 == 0) 
                robot.setAnimationDelay(robot.getAnimationDelay() - 1);
            isOptionChosen = true;
        }
    }

    private void increaseHealth(){
        if(!isOptionChosen){
            robot.setHealth(robot.getHealth() + 1);
            isOptionChosen = true;
        }

    }

    private void increaseDamage(){
        if(!isOptionChosen){
            Projectile.setPlayerDamage(Projectile.getPlayerDamage() + 1);
            isOptionChosen = true;
        }
    }

}
