package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel;
    protected SpriteFont andByLabel1, andByLabel2, andByLabel3, andByLabel4;
    protected SpriteFont returnInstructionsLabel;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        creditsLabel = new SpriteFont("Credits", 15, 7, "Times New Roman", 30, Color.white);
        createdByLabel = new SpriteFont("Created by Alex Thimineur,", 130, 121, "Times New Roman", 20, Color.white);
        andByLabel1 = new SpriteFont("Aiden Agas,", 130, 141, "Times New Roman", 20, Color.white);
        andByLabel2 = new SpriteFont("Reed Scampoli,", 130, 161, "Times New Roman", 20, Color.white);
        andByLabel3 = new SpriteFont("Zachary Nedell,", 130, 181, "Times New Roman", 20, Color.white);
        andByLabel4 = new SpriteFont("And Zachary Marlowe", 130, 201, "Times New Roman", 20, Color.white);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 532, "Times New Roman", 30, Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    @Override
    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        creditsLabel.draw(graphicsHandler);
        createdByLabel.draw(graphicsHandler);
        andByLabel1.draw(graphicsHandler);
        andByLabel2.draw(graphicsHandler);
        andByLabel3.draw(graphicsHandler);
        andByLabel4.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
