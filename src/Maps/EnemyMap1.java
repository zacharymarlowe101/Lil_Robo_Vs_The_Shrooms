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
import java.util.List;

// Represents a test map to be used in a level
public class EnemyMap1 extends Map {

    public EnemyMap1() {
        super("EnemyMap1.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(3, 20).getLocation();
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
    //     enhancedMapTiles.add(pushableRock);

    //     return enhancedMapTiles;
    // }


    // RANDOM ENEMY SPAWNING LOGIC
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

         List<Point> spawnPoints = List.of(
            new Point(12, 20),
            new Point(13, 21),
            new Point(14, 22)
    );

    EnemySpawner spawner = new EnemySpawner(this, spawnPoints);
    List<NPC> randomEnemies = spawner.spawnMultipleEnemies(6);

   for (NPC enemy : randomEnemies) {
        npcs.add(enemy);
        }
    return npcs;
}

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
       
        triggers.add(new Trigger(getMapTile(20, 21).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(20,21), new Point(2,4)));
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

