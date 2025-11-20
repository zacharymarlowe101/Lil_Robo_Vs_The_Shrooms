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

public class EnemyMap7 extends Map {

    public EnemyMap7() {
        super("EnemyMap7.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(7, 24).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        int level = GameSession.getDifficultyLevel();

        Mushroom1 guaranteed = new Mushroom1(101, getMapTile(12, 25).getLocation(), level);
        npcs.add(guaranteed);

        List<Point> spawnPoints = List.of(
            new Point(15, 17),
            new Point(16, 13),
            new Point(11, 17),
            new Point(12, 13),
            new Point(8, 13),
            new Point(7, 17),
            new Point(4, 13),
            new Point(3, 17),
            new Point(6, 7),
            new Point(7, 3),
            new Point(10, 7),
            new Point(11, 3),
            new Point(14, 7),
            new Point(15, 3),
            new Point(18, 7),
            new Point(19, 3)
        );

        List<EnemySpawner.WeightedFactory> enemyWeights = List.of(
            new EnemySpawner.WeightedFactory(100, pos -> new Mushroom2(2000 + pos.hashCode(), pos, level))
        );

        EnemySpawner spawner = new EnemySpawner(this, spawnPoints, enemyWeights);
        List<NPC> randomEnemies = spawner.spawnMultipleEnemies(8);

        npcs.addAll(randomEnemies);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(21, 5).getLocation(), 32, 32, new WarpScript(), "haswarped"));
        return triggers;
    }

    @Override
    protected ArrayList<Script> loadUpdateScripts() {
        return new ArrayList<Script>() {{
            add(new UpdateTileOnClearScript(new Point(21, 5), new Point(2, 4)));
        }};
    }
}
