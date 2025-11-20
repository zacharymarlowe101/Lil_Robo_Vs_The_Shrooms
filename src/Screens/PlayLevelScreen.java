package Screens;
import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Rectangle;
import Level.*;
import Maps.EnemyMap1;
import Maps.TutorialMap;
import NPCs.MycorrhizalAmalgamation;
import Players.Robot;
import Utils.Direction;
import Maps.TutorialMap;
import Maps.EnemyMap2;
import Maps.EnemyMap3;
import Maps.EnemyMap4;  
import Maps.EnemyMap5;
import Maps.EnemyMap6;
import Maps.EnemyMap7;
import Maps.EnemyMap8;
import Maps.BossMap;



// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen implements GameListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected int mapn;
    protected int mapcount;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected int Rmap = 3;
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
        flagManager.addFlag("showdown", false);
        flagManager.addFlag("twist", false);
        flagManager.addFlag("battle", false);


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
                player.update(); //uh oh
                map.update(player);
                //HUDScreen.update();
                break;
            case UPGRADE_SCREEN:
                upgradeScreen.update();
                break;
            case LOSE_SCREEN:
                loseScreen.update();
                mapcount = 0;
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
        mapcount = 0;
        System.out.println(mapcount);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                HUDScreen.draw(graphicsHandler);
                if (mapcount == 8){
                    playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
                    Rmap = (int)(Math.random() * 8) + 1; // Randomly choose between 1 and 2
                    mapcount = 0;
                }

                MycorrhizalAmalgamation boss = (MycorrhizalAmalgamation) map.getNPCById(102);
                if (boss != null && !boss.isDead()) {
                    boss.getHealthbar().draw(graphicsHandler.getGraphics(), 0, 0);
                }
                

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
        playLevelScreenState = PlayLevelScreenState.RUNNING;
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
      flagManager.addFlag("showdown", false);
        flagManager.addFlag("twist", false);
        flagManager.addFlag("battle", false);


   
   
    // if(mapn == 3){
    //     Rmap =4;
    // }else if(mapn == 4){
    //     Rmap =5;
    // }else if(mapn == 5){
    //     Rmap =6;
    // }
        mapcount++;
        System.out.println("mapcount:"+mapcount);
        if(mapcount <7){
           Rmap = (int)(Math.random() * 8) + 1; // Randomly choose between 1 and 2
        }else{
            Rmap =9;
        }
    // Rmap = 9;
       

    System.out.println(Rmap);
     //Rmap = 9;
        if (Rmap == 1) {
            map = new EnemyMap1();
        } else if (Rmap == 2) {
            map = new EnemyMap2();
        } else if (Rmap == 3) {
            map = new EnemyMap3();
            mapn = 3;
        } else if (Rmap == 4) {
            map = new EnemyMap4();
            mapn = 4;
        } else if (Rmap == 5) {
            map = new EnemyMap5();
            mapn = 5;

         } else if( Rmap == 6){
            map = new EnemyMap6();
        }
            else if( Rmap == 7){
            map = new EnemyMap7();
          }
            else if( Rmap == 8){
                map = new EnemyMap8();
            }
          else if (Rmap == 9){
            map = new BossMap();
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
