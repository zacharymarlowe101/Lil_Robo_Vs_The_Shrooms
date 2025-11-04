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
import Maps.TutorialMap;
import Maps.EnemyMap2;
import Maps.EnemyMap3;
import Maps.EnemyMap4;  
import Maps.EnemyMap5;



// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen implements GameListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected UpgradeScreen upgradeScreen;
    protected LoseScreen loseScreen;
    protected HUD HUDScreen;
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
        flagManager.addFlag("wakingup", false);
        flagManager.addFlag("diagnostics", false);
        flagManager.addFlag("weaponcheck", false);
        flagManager.addFlag("eradicate", false);


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
        loseScreen = new LoseScreen(this);
        upgradeScreen.setRobot(player);
        HUDScreen = new HUD(this);
        HUDScreen.setRobot(player);
        HUDScreen.initialize();
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                //HUDScreen.update();
                break;
            case UPGRADE_SCREEN:
                upgradeScreen.update();
                break;
            case LOSE_SCREEN:
                loseScreen.update();
                break;
            case HUD:
                //update HUD here later
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
   
     @Override
    public void onUpdate(){
        playLevelScreenState = PlayLevelScreenState.UPGRADE_SCREEN;
    }

    @Override
    public void onLose(){
        playLevelScreenState = PlayLevelScreenState.LOSE_SCREEN;
    }
    

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                HUDScreen.draw(graphicsHandler);
                break;
            case UPGRADE_SCREEN:
                map.draw(player, graphicsHandler);
                upgradeScreen.draw(graphicsHandler);
                break;
            case LOSE_SCREEN:
                map.draw(player, graphicsHandler);
                loseScreen.draw(graphicsHandler);
                break;
            case HUD:
                //update HUD here later
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
        RUNNING, LEVEL_COMPLETED, UPGRADE_SCREEN, LOSE_SCREEN, HUD
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
    flagManager.addFlag("enemiesclear", false);
    flagManager.addFlag("haswarped", false);

    int Rmap = 0;
    Rmap = (int)(Math.random() * 5) + 1; // Randomly choose between 1 and 2
    System.out.println(Rmap);
        if (Rmap == 1) {
            map = new EnemyMap1();
        } else if (Rmap == 2) {
            map = new EnemyMap2();
        } else if (Rmap == 3) {
            map = new EnemyMap3();
        } else if (Rmap == 4) {
            map = new EnemyMap4();
        } else if (Rmap == 5) {
            map = new EnemyMap5();
        }
        
    

    
    map.setFlagManager(flagManager);

    //hold the player values
    int tempPlayerHealth = player.getHealth();
    float tempPlayerSpeed = player.getWalkSpeed();

    //reset the player
    player = new Robot(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
    player.setMap(map);
    playLevelScreenState = PlayLevelScreenState.RUNNING;
    player.setFacingDirection(Direction.LEFT);

    //add the lost values to player
    player.setHealth(tempPlayerHealth);
    player.setWalkSpeed(tempPlayerSpeed);

    map.setPlayer(player);
    map.getTextbox().setInteractKey(player.getInteractKey());
    map.addListener(this);
    map.preloadScripts();

    winScreen = new WinScreen(this);
    upgradeScreen = new UpgradeScreen(this);
    upgradeScreen.setRobot(player);
    loseScreen = new LoseScreen(this);
    HUDScreen = new HUD(this);
    HUDScreen.setRobot(player);
    HUDScreen.initialize();
    }

    
}
