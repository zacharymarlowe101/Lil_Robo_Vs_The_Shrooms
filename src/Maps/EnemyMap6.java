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
public class EnemyMap6 extends Map {

    public EnemyMap6() {
        super("EnemyMap6.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(21, 18).getLocation();
    }

    // RANDOM ENEMY SPAWNING LOGIC
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        List<Point> spawnPoints = List.of(
            new Point(15, 16),
            new Point(21, 14),
            new Point(17, 9),
            new Point(21, 7),
            new Point(21, 1),
            new Point(16, 3),
            new Point(11, 1),
            new Point(4, 1),
            new Point(3, 5),
            new Point(7, 7),
            new Point(9, 9),
            new Point(4, 13),
            new Point(4, 19),
            new Point(4, 26),
            new Point(1, 22),
            new Point(6, 26),
            new Point(15, 25),
            new Point(19, 28)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom1(1000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom2(2000 + pos.hashCode(), pos, 3)),
            new EnemySpawner.WeightedFactory(10, pos -> new Mushroom3(3000 + pos.hashCode(), pos, 3))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(12);

        for (NPC enemy : randomEnemies) {
            npcs.add(enemy);
        }

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(22, 27).getLocation(), 32,32, new WarpScript(), "haswarped"));
        return triggers;
    }

    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(22,27), new Point(2,4)));
        }};
    }
}
