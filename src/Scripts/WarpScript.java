package Scripts;

import Level.GameListener;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import Maps.TutorialMap;
import ScriptActions.*;
import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Utils.Point;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class WarpScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        // scriptActions.add(new LockPlayerScriptAction());
        // scriptActions.add(new TextboxScriptAction() {{
        //     addText("You are totally warping");

        //   }});
        

        
        
        
        scriptActions.add(new ConditionalScriptAction() {{
        
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return map.isCleared();
                    }
                });
            
                
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        for (GameListener listener: listeners) {
                            listener.onUpdate();
                           
                        }
                        return ScriptState.COMPLETED;
                    }
                });
                addScriptAction(new ScriptAction() {
    
                    @Override
                    public ScriptState execute() {
                        for (GameListener listener: listeners) {
                            listener.onClear();
                           
                        }
                        return ScriptState.COMPLETED;
                    }
                });

                addScriptAction(new ChangeFlagScriptAction("haswarped", true));
            }});
            scriptActions.add(new UnlockPlayerScriptAction());
         }});
        return scriptActions;
    }
}
