package Scripts;

import Level.GameListener;
import Level.Script;
import Level.ScriptState;
import ScriptActions.*;
import java.util.ArrayList;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class WarpScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        //scriptActions.add(new LockPlayerScriptAction());
        // scriptActions.add(new TextboxScriptAction() {{
        //     addText("You are totally warping");

        //   }});

        scriptActions.add(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        for (GameListener listener: listeners) {
                        
                            listener.onClear();
                        }
                        return ScriptState.COMPLETED;
                    }
                });
        
        

        

        scriptActions.add(new ChangeFlagScriptAction("haswarped", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
