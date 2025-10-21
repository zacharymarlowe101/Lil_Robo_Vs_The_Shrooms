package MapEditor;

import Level.Map;
import Maps.EnemyMap1;
import Maps.TitleScreenMap;
import Maps.TutorialMap;
import java.util.ArrayList;
import Maps.EnemyMap2;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TutorialMap");
            add("EnemyMap1");
            add("TitleScreen");
            add("EnemyMap2");
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
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
