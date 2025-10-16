package MapEditor;

import Level.Map;
import Maps.TutorialMap;
import Maps.EnemyMap1;
import Maps.TitleScreenMap;
import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TutorialMap");
            add("EnemyMap1");
            add("TitleScreen");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TutorialMap":
                return new TutorialMap();
            case "EnemyMap1":
                return new EnemyMap1();
            case "TitleScreen":
                return new TitleScreenMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
