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

    // RANDOM ENEMY SPAWNING LOGIC
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        List<Point> spawnPoints = List.of(
            new Point(12, 20),
            new Point(13, 21),
            new Point(14, 22)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(1, pos -> new Mushroom1(1000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(1, pos -> new Mushroom2(2000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(1, pos -> new Mushroom3(3000 + pos.hashCode(), pos, 3))
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
        triggers.add(new Trigger(getMapTile(20, 21).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }

    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(20,21), new Point(2,4)));
        }};
    }
}
