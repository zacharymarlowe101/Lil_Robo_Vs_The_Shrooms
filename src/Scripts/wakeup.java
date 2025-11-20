package Scripts;

import Level.Script;
import ScriptActions.*;
import java.util.ArrayList;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class wakeup extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new TextboxScriptAction() {{
            addText("Beep Boop. Initializing Dawn Protocol.");
             addText("All appendages functional. ");
            addText("Please use WASD or arrow keys to move.");
            addText("Press R to temporarily toggle reflect ability.");
        }});
        

        scriptActions.add(new ChangeFlagScriptAction("wakingup", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
