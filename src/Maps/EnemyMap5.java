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

public class EnemyMap5 extends Map {

    public EnemyMap5() {
        super("EnemyMap5.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(7, 20).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        int level = GameSession.getDifficultyLevel();

        List<Point> spawnPoints = List.of(
            new Point(7, 25),
            new Point(10, 23),
            new Point(12, 20),
            new Point(8, 23),
            new Point(8, 24),
            new Point(7, 24),
            new Point(10, 24),
            new Point(11, 25),
            new Point(12, 21),
            new Point(12, 23)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom1(1000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom2(2000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(10, pos -> new Mushroom3(3000 + pos.hashCode(), pos, level))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(3);

        npcs.addAll(randomEnemies);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(12, 25).getLocation(), 32, 32, new WarpScript(), "haswarped"));
        return triggers;
    }

    @Override
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(12, 25), new Point(2, 4)));
        }};
    }
}
