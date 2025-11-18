package Screens;

import Engine.*;
import SpriteFont.SpriteFont;
import Level.Player;
import Players.Robot;
import Projectiles.Projectile;
import GameObject.Sprite;

import java.awt.*;

// This class is for the win level screen
public class LoseScreen extends Screen {
    protected SpriteFont option1;
    private Sprite deathCard;

    private Player robot;

    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected UpgradeScreen upgradeScreen;
     protected PlayLevelScreen playLevelScreen;

    public LoseScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        deathCard = new Sprite(ImageLoader.load("DeathCard.png"), 74, 128);
        deathCard.setScale(3);
        deathCard.setLocation((Config.GAME_WINDOW_WIDTH-deathCard.getWidth())/2, (Config.GAME_WINDOW_HEIGHT-deathCard.getHeight())/2);

        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
      
        if(Keyboard.isKeyUp(Key.SPACE)){
            keyLocker.unlockKey(Key.SPACE);
        }

        if(Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playLevelScreen.resetLevel();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        deathCard.draw(graphicsHandler);
        
    }

    /*public void setRobot(Player robot){
        this.robot = robot;
    }

    public Player getRobot(){
        return this.robot;
    }*/
    
}
