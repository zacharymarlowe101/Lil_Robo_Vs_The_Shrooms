package Maps;

import Level.*;
import NPCs.Mushroom1;
import NPCs.Mushroom2;
import NPCs.Mushroom3;
import Scripts.UpdateTileOnClearScript;
import Scripts.WarpScript;
import Tilesets.CommonTileset;
import Utils.Point;
import java.util.List;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class EnemyMap4 extends Map {

    public EnemyMap4() {
        super("EnemyMap4.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(3, 22).getLocation();
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

        Mushroom3 mushroom1 = new Mushroom3(101, getMapTile(7, 13).getLocation(), 1);
        npcs.add(mushroom1);

        Mushroom3 mushroom2 = new Mushroom3(101, getMapTile(16, 14).getLocation(), 1);
        npcs.add(mushroom2);

        Mushroom3 mushroom3 = new Mushroom3(101, getMapTile(13, 19).getLocation(), 1);
        npcs.add(mushroom3);

        Mushroom3 mushroom4 = new Mushroom3(101, getMapTile(18, 16).getLocation(), 1);
        npcs.add(mushroom4);

        Mushroom3 mushroom5 = new Mushroom3(101, getMapTile(15, 21).getLocation(), 1);
        npcs.add(mushroom5);

        Mushroom3 mushroom6 = new Mushroom3(101, getMapTile(20, 18).getLocation(), 1);
        npcs.add(mushroom6);




        List<Point> spawnPoints = List.of(
            new Point(15, 17),
            new Point(13, 16),
            new Point(13, 13),
            new Point(8, 12),
            new Point(9, 10),
            new Point(5, 11)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom1(1000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom2(2000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(10, pos -> new Mushroom3(3000 + pos.hashCode(), pos, 3))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(3);

        for (NPC enemy : randomEnemies) {
            npcs.add(enemy);
        }
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
       
        triggers.add(new Trigger(getMapTile(19, 21).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(19,21), new Point(2,4)));
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

