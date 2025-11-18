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
import Maps.BossMap;
import Maps.EnemyMap6;
import Maps.EnemyMap7;
import Maps.EnemyMap8;

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
            add("EnemyMap6");
            add("EnemyMap7");
            add("EnemyMap8");
            add("BossMap");
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
            case "EnemyMap6":
                return new EnemyMap6();
            case "EnemyMap7":
                return new EnemyMap7();
            case "EnemyMap8":
                return new EnemyMap8();
            case "BossMap":
                return new BossMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
