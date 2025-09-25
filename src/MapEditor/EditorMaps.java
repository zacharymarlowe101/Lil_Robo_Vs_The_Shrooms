package MapEditor;

import Level.Map;
import Maps.MyMap;
import Maps.TestMap;
import Maps.TitleScreenMap;
import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("MyMap");
            add("TestMap");
            add("TitleScreen");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "MyMap":
                return new MyMap();
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
