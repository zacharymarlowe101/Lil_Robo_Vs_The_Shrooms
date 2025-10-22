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
        //scriptActions.add(new LockPlayerScriptAction());
        // scriptActions.add(new TextboxScriptAction() {{
        //     addText("You are totally warping");

        //   }});
        

        
        
        
        scriptActions.add(new ConditionalScriptAction() {{
        
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("enemiesclear", true));
                
                scriptActions.add(new ScriptAction() {
                    
                    @Override
                    public ScriptState execute() {
                        if (map instanceof TutorialMap){
                            var tileset = this.map.getTileset();
                            Frame whitewarp = new FrameBuilder(tileset.getSubImage(9, 4)).withScale(tileset.getTileScale()).build();
                            Point location = map.getMapTile(5, 4).getLocation();
                            MapTile mapTile = new MapTileBuilder(whitewarp).withTileType(TileType.PASSABLE).build(location.x, location.y);
                            this.map.setMapTile(5,4, mapTile);
                        }
                        return ScriptState.COMPLETED;
                    }
                });
                
                scriptActions.add(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        for (GameListener listener: listeners) {
                            listener.onUpdate();
                           
                        }
                        return ScriptState.COMPLETED;
                    }
                });
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
            }});
            scriptActions.add(new UnlockPlayerScriptAction());
         }});
        return scriptActions;
    }
}
