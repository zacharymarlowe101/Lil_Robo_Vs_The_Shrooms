package Level;

import NPCs.Mushroom1;
import NPCs.Mushroom2;
import NPCs.Mushroom3;
import Utils.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class EnemySpawner {

    private final Map map;
    private final List<Point> spawnPoints;
    private final List<Function<Point, NPC>> weightedEnemyFactories;
    private final Random random = new Random();
    private int nextId = 1000;

    public EnemySpawner(Map map, List<Point> spawnPoints, List<WeightedFactory> weightedFactories) {
        this.map = map;
        this.spawnPoints = new ArrayList<>(spawnPoints);
        this.weightedEnemyFactories = buildWeightedList(weightedFactories);
    }

    public List<NPC> spawnMultipleEnemies(int count) {
        List<NPC> enemies = new ArrayList<>();
        List<Point> availablePoints = new ArrayList<>(spawnPoints);

        for (int i = 0; i < count && !availablePoints.isEmpty(); i++) {
            int index = random.nextInt(availablePoints.size());
            Point tileCoord = availablePoints.remove(index);
            MapTile tile = map.getMapTile((int) tileCoord.x, (int) tileCoord.y);
            if (tile == null) continue;

            Point spawnLocation = tile.getLocation();
            NPC enemy = createRandomEnemy(spawnLocation);
            enemies.add(enemy);
        }

        return enemies;
    }

    private NPC createRandomEnemy(Point position) {
        Function<Point, NPC> factory = weightedEnemyFactories.get(random.nextInt(weightedEnemyFactories.size()));
        return factory.apply(position);
    }

    private List<Function<Point, NPC>> buildWeightedList(List<WeightedFactory> weightedFactories) {
        List<Function<Point, NPC>> result = new ArrayList<>();
        for (WeightedFactory wf : weightedFactories) {
            for (int i = 0; i < wf.weight; i++) {
                result.add(wf.factory);
            }
        }
        return result;
    }

    public static class WeightedFactory {
        public final int weight;
        public final Function<Point, NPC> factory;

        public WeightedFactory(int weight, Function<Point, NPC> factory) {
            this.weight = weight;
            this.factory = factory;
        }
    }
}
