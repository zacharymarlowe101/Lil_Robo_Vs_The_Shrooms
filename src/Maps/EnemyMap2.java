package Maps;

import Level.*;
import NPCs.Mushroom1;
import NPCs.Mushroom2;
import NPCs.Mushroom3;
import Scripts.UpdateTileOnClearScript;
import Scripts.WarpScript;
import Tilesets.CommonTileset;
import Utils.Point;
import Game.GameSession;

import java.util.ArrayList;
import java.util.List;

public class EnemyMap2 extends Map {

    public EnemyMap2() {
        super("EnemyMap2.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 26).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        int level = GameSession.getDifficultyLevel(); 

        List<Point> spawnPoints = List.of(
            new Point(20, 25),
            new Point(14, 18),
            new Point(6, 13),
            new Point(16, 11),
            new Point(21, 15),
            new Point(4, 14),
            new Point(11, 10),
            new Point(9, 16),
            new Point(9, 17),
            new Point(15, 27),
            new Point(18, 12),
            new Point(18, 24)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(1, pos -> new Mushroom1(1000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(1, pos -> new Mushroom2(2000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(1, pos -> new Mushroom3(3000 + pos.hashCode(), pos, level))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(10);

        npcs.addAll(randomEnemies);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(20, 11).getLocation(), 32, 32, new WarpScript(), "haswarped"));
        return triggers;
    }

    @Override
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(20, 11), new Point(2, 4)));
        }};
    }
}
