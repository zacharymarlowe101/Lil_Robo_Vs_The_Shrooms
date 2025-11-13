package Scripts;

import Level.Script;
import ScriptActions.*;
import java.util.ArrayList;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class twist extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new TextboxScriptAction() {{
            addText("ERROR ERROR ERROR");
             addText("Allied Unit Compromised.");
            addText("Mycellium infection detected.");
        }});

        

        scriptActions.add(new ChangeFlagScriptAction("twist", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
