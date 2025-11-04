package Scripts;

import Level.Script;
import ScriptActions.*;
import java.util.ArrayList;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class weaponcheck extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new TextboxScriptAction() {{
            addText("Weapons online. ");
            addText("Please use SPACEBAR or LEFT CLICK to fire.");
        }});

        

        scriptActions.add(new ChangeFlagScriptAction("weaponcheck", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
