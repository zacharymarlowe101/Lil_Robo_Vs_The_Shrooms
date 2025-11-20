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

public class EnemyMap4 extends Map {

    public EnemyMap4() {
        super("EnemyMap4.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(3, 22).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        int level = GameSession.getDifficultyLevel();

        List<Point> spawnPoints = List.of(
            new Point(7, 13),
            new Point(16, 14),
            new Point(13, 19),
            new Point(18, 16),
            new Point(15, 21),
            new Point(20, 18),
            new Point(15, 17),
            new Point(13, 16),
            new Point(13, 13),
            new Point(8, 12),
            new Point(9, 10),
            new Point(5, 11)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom1(1000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(45, pos -> new Mushroom2(2000 + pos.hashCode(), pos, level)),
            new EnemySpawner.WeightedFactory(10, pos -> new Mushroom3(3000 + pos.hashCode(), pos, level))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(6);

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
