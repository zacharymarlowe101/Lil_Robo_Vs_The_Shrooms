package Maps;

import Level.*;
import NPCs.Mushroom1;
import NPCs.Mushroom2;
import NPCs.Mushroom3;
import NPCs.MycorrhizalAmalgamation;
import Scripts.UpdateTileOnClearScript;
import Scripts.WarpScript;
import Tilesets.CommonTileset;
import Utils.Point;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class BossMap extends Map {

    public BossMap() {
        super("BossMap.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(10, 26).getLocation();
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
    //     enhancedMapTiles.add(pushableRock);

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
        // walrus.setInteractScript(new WalrusScript());
        // npcs.add(walrus);

        // Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        // dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        // dinosaur.setInteractScript(new DinoScript());
        // npcs.add(dinosaur);
        
        // Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        // bug.setInteractScript(new BugScript());
        // npcs.add(bug);

        // Mushroom1 mushroom1 = new Mushroom1(101, getMapTile(10, 10).getLocation(),3);
        // npcs.add(mushroom1);

        // Mushroom2 mushroom2 = new Mushroom2(102, getMapTile(10, 23).getLocation(),3);
        // npcs.add(mushroom2);

        // Mushroom3 mushroom3 = new Mushroom3(103, getMapTile(12, 20).getLocation(),3);
        // npcs.add(mushroom3);

        Point tile = getMapTile(8, 11).getLocation();
        Point centeredLocation = new Point(tile.x + 58, tile.y);
        MycorrhizalAmalgamation boss = new MycorrhizalAmalgamation(102, centeredLocation, 1);
        npcs.add(boss);


        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
       
        triggers.add(new Trigger(getMapTile(10, 9).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(10,9), new Point(2,4)));
        }};
    }
    
    
    

    // @Override
    // public void loadScripts() {
    //     getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

    //     getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

    //     getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

    //     getMapTile(2, 6).setInteractScript(new TreeScript());
    // }
}

