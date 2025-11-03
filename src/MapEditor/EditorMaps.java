package MapEditor;

import Level.Map;
import Maps.EnemyMap1;
import Maps.TitleScreenMap;
import Maps.TutorialMap;
import java.util.ArrayList;
import Maps.EnemyMap2;
import Maps.EnemyMap3;
import Maps.EnemyMap4;
import Maps.EnemyMap5;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TutorialMap");
            add("TitleScreen");
            add("EnemyMap1");
            add("EnemyMap2");
            add("EnemyMap3");
            add("EnemyMap4");
            add("EnemyMap5");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TutorialMap":
                return new TutorialMap();
            case "EnemyMap1":
                return new EnemyMap1();
            case "EnemyMap2":
                return new EnemyMap2();
            case "TitleScreen":
                 return new TitleScreenMap();
            case "EnemyMap3":
                  return new EnemyMap3();
            case "EnemyMap4":
                 return new EnemyMap4();
             case "EnemyMap5":
                return new EnemyMap5();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
