package Screens;
import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.EnemyMap1;
import Maps.TutorialMap;
import Players.Robot;
import Utils.Direction;







// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen implements GameListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected UpgradeScreen upgradeScreen;
    protected FlagManager flagManager;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("haswarped", false);

        // define/setup map
        map = new TutorialMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Robot(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // add this screen as a "game listener" so other areas of the game that don't normally have direct access to it (such as scripts) can "signal" to have it do something
        // this is used in the "onWin" method -- a script signals to this class that the game has been won by calling its "onWin" method
        map.addListener(this);

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();

        winScreen = new WinScreen(this);
        upgradeScreen = new UpgradeScreen(this);
        upgradeScreen.setRobot(player);
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            case UPGRADE_SCREEN:
                upgradeScreen.update();
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }
    }

    @Override
    public void onWin() {
        // when this method is called within the game, it signals the game has been "won"
        playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
    }
   
    

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
            case UPGRADE_SCREEN:
                map.draw(player, graphicsHandler);
                upgradeScreen.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, UPGRADE_SCREEN
    }

    @Override
    public void onClear() {
      // Re-initialize everything with EnemyMap1
    flagManager = new FlagManager();
    // Add any flags your game needs
    flagManager.addFlag("hasLostBall", false);
    flagManager.addFlag("hasTalkedToWalrus", false);
    flagManager.addFlag("hasTalkedToDinosaur", false);
    flagManager.addFlag("hasFoundBall", false);
    flagManager.addFlag("haswarped", false);

    map = new EnemyMap1();
    map.setFlagManager(flagManager);

    player = new Robot(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
    player.setMap(map);
    playLevelScreenState = PlayLevelScreenState.RUNNING;
    player.setFacingDirection(Direction.LEFT);

    map.setPlayer(player);
    map.getTextbox().setInteractKey(player.getInteractKey());
    map.addListener(this);
    map.preloadScripts();

    winScreen = new WinScreen(this);
    upgradeScreen = new UpgradeScreen(this);
    upgradeScreen.setRobot(player);
    }

    
}
