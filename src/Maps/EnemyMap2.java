package Maps;

import Level.*;
import NPCs.Mushroom1;
import NPCs.Mushroom2;
import NPCs.Mushroom3;
import Scripts.UpdateTileOnClearScript;
import Scripts.WarpScript;
import Tilesets.CommonTileset;
import Utils.Point;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class EnemyMap2 extends Map {

    public EnemyMap2() {
        super("EnemyMap2.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 26).getLocation();
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

        Mushroom1 mushroom1 = new Mushroom1(101, getMapTile(20, 25).getLocation(),3);
        npcs.add(mushroom1);

        Mushroom1 mushroom12 = new Mushroom1(101, getMapTile(14, 18).getLocation(),3);
        npcs.add(mushroom12);

        Mushroom1 mushroom5 = new Mushroom1(101, getMapTile(6, 13).getLocation(),3);
        npcs.add(mushroom5);

        Mushroom1 mushroom6 = new Mushroom1(101, getMapTile(16, 11).getLocation(),3);
        npcs.add(mushroom6);

        Mushroom1 mushroom8 = new Mushroom1(101, getMapTile(21, 15).getLocation(),3);
        npcs.add(mushroom8);

         Mushroom1 mushroom9 = new Mushroom1(101, getMapTile(4, 14).getLocation(),3);
        npcs.add(mushroom9);

        Mushroom2 mushroom10 = new Mushroom2(101, getMapTile(11, 10).getLocation(),3);
        npcs.add(mushroom10);

        Mushroom2 mushroom2 = new Mushroom2(102, getMapTile(9, 16).getLocation(),3);
        npcs.add(mushroom2);

        Mushroom2 mushroom4 = new Mushroom2(102, getMapTile(9, 17).getLocation(),3);
        npcs.add(mushroom4);

        Mushroom3 mushroom7 = new Mushroom3(102, getMapTile(15, 27).getLocation(),3);
        npcs.add(mushroom7);

        Mushroom3 mushroom11 = new Mushroom3(102, getMapTile(18, 12).getLocation(),3);
        npcs.add(mushroom11);


        Mushroom2 mushroom3 = new Mushroom2(103, getMapTile(18, 24).getLocation(),3);
        npcs.add(mushroom3);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
       
        triggers.add(new Trigger(getMapTile(20, 11).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(20,11), new Point(2,4)));
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

