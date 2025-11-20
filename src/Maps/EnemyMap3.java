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

public class EnemyMap3 extends Map {

    public EnemyMap3() {
        super("EnemyMap3.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(3, 20).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        int level = GameSession.getDifficultyLevel();

        List<Point> spawnPoints = List.of(
            new Point(12, 26),
            new Point(14, 24),
            new Point(11, 21),
            new Point(12, 19),
            new Point(14, 17),
            new Point(11, 14),
            new Point(14, 15),
            new Point(13, 16),
            new Point(21, 23)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom1(1000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom2(2000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(10, pos -> new Mushroom3(3000 + pos.hashCode(), pos, level))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(5);

        npcs.addAll(randomEnemies);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(19, 21).getLocation(), 32, 32, new WarpScript(), "haswarped"));
        return triggers;
    }

    @Override
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(19, 21), new Point(2, 4)));
        }};
    }
}
