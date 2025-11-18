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
public class EnemyMap7 extends Map {

    public EnemyMap7() {
        super("EnemyMap7.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(7, 24).getLocation();
    }

    
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // fixed single mushroom that always spawns
        Mushroom1 mushroom1 = new Mushroom1(101, getMapTile(12, 25).getLocation(), 1);
        npcs.add(mushroom1);

        // all the original Mushroom2 spawn points
        List<Point> spawnPoints = List.of(
            // lower level
            new Point(15, 17),
            new Point(16, 13),
            new Point(11, 17),
            new Point(12, 13),
            new Point(8, 13),
            new Point(7, 17),
            new Point(4, 13),
            new Point(3, 17),

            // upper level
            new Point(6, 7),
            new Point(7, 3),
            new Point(10, 7),
            new Point(11, 3),
            new Point(14, 7),
            new Point(15, 3),
            new Point(18, 7),
            new Point(19, 3)
        );
    
        // only Mushroom2 type enemies
        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(
                100, 
                pos -> new Mushroom2(2000 + pos.hashCode(), pos, 1)
            )
        );
    
        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
    
        // spawn exactly 8 random Mushroom2s
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(8);
    
        npcs.addAll(randomEnemies);
    
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(21, 5).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }

    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(21,5), new Point(2,4)));
        }};
    }
}
