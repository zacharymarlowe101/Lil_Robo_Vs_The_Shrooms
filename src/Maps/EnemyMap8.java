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
public class EnemyMap8 extends Map {

    public EnemyMap8() {
        super("EnemyMap8.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(3, 26).getLocation();
    }

    // RANDOM ENEMY SPAWNING LOGIC
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        List<Point> spawnPoints = List.of(
            new Point(9, 26),
            new Point(17, 26),
            new Point(17, 19),
            new Point(21, 15),
            new Point(21, 7),
            new Point(17, 2),
            new Point(11, 2),
            new Point(3, 5),
            new Point(3, 11),
            new Point(8, 9),
            new Point(6, 7),
            new Point(8, 16),
            new Point(3, 16),
            new Point(3, 20),
            new Point(9, 23),
            new Point(11, 20),
            new Point(13, 15),
            new Point(15, 9),
            new Point(11, 6),
            new Point(1, 7)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom1(1000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom2(2000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(10, pos -> new Mushroom3(3000 + pos.hashCode(), pos, 3))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(10);

        for (NPC enemy : randomEnemies) {
            npcs.add(enemy);
        }

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(3, 5).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }

    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(3,5), new Point(2,4)));
        }};
    }
}
