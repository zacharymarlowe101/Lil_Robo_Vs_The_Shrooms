package Level;

import NPCs.Mushroom1;
import NPCs.Mushroom2;
import NPCs.Mushroom3;
import Utils.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {

    private final Map map;
    private final List<Point> spawnPoints;
    private final Random random = new Random();
    private int nextId = 1000;

    public EnemySpawner(Map map, List<Point> spawnPoints) {
        this.map = map;
        this.spawnPoints = new ArrayList<>(spawnPoints);
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
        int type = random.nextInt(3);
        int health = 3;

        switch (type) {
            case 0:
                return new Mushroom1(nextId++, position, health);
            case 1:
                return new Mushroom2(nextId++, position, health);
            case 2:
            default:
                return new Mushroom3(nextId++, position, health);
        }
    }
}
