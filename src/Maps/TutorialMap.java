package Maps;

import Level.*;
import NPCs.Mushroom1;
import Scripts.UpdateTileOnClearScript;
import Scripts.WarpScript;
import Scripts.*;
import Tilesets.CommonTileset;
import Utils.Point;
import java.util.ArrayList;

// Represents a test map to be used in a level
public class TutorialMap extends Map {

    public TutorialMap() {
         super("TutorialMap", new CommonTileset());
        this.playerStartPosition = getMapTile(15, 9).getLocation();
    }
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(15, 5).getLocation(), 32,600, new wakeup(), "wakingup"));
      //  triggers.add(new Trigger(getMapTile(12, 5).getLocation(), 32,600, new diagnostics(), "diagnostics"));
        triggers.add(new Trigger(getMapTile(10, 5).getLocation(), 32,600, new weaponcheck(), "weaponcheck"));
        triggers.add(new Trigger(getMapTile(8, 5).getLocation(), 32,600, new ERADICATE(), "eradicate"));
        triggers.add(new Trigger(getMapTile(5, 4).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Mushroom1 mushroom1 = new Mushroom1(101, getMapTile(5, 5).getLocation(), 1);
        npcs.add(mushroom1);

        

        return npcs;
    }

    @Override
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(5,4), new Point(2,4)));
        }};
    }
    
    
}

//     @Override
//     public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
//         ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
//
//         PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
//         enhancedMapTiles.add(pushableRock);
//
//         return enhancedMapTiles;
//     }
//
    // @Override
    // public ArrayList<NPC> loadNPCs() {
    //     ArrayList<NPC> npcs = new ArrayList<>();

        

    //     return npcs;
    // }
//
//     @Override
//     public ArrayList<Trigger> loadTriggers() {
//         ArrayList<Trigger> triggers = new ArrayList<>();
//         triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
//         triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
//         triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
//         return triggers;
//     }
//
//     @Override
//     public void loadScripts() {
//         getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));
//
//         getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));
//
//         getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));
//
//         getMapTile(2, 6).setInteractScript(new TreeScript());
//     }
// }
