package Scripts;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import Level.Tileset;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.ScriptAction;
import Utils.Point;

public class UpdateTileOnClearScript extends Script {
    
    public Point tileLocation;
    public Point tilesetLocation;

    public UpdateTileOnClearScript(Point tileLocation, Point tilesetLocation) {
        this.tileLocation = tileLocation;
        this.tilesetLocation = tilesetLocation;
    }

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return map.isCleared();
                    }
                });
                addScriptAction(
                    new ScriptAction() {
                        
                        @Override
                        public ScriptState execute() {
                            Tileset tileset = this.map.getTileset();
                            Frame whitewarp = new FrameBuilder(tileset.getSubImage((int)tilesetLocation.x, (int)tilesetLocation.y)).withScale(tileset.getTileScale()).build();
                            MapTile oldTile = map.getMapTile((int) tileLocation.x, (int) tileLocation.y);
                            MapTile mapTile = new MapTileBuilder(whitewarp).withTileType(TileType.PASSABLE).build(oldTile.getLocation().x, oldTile.getLocation().y);
                            // mapTile.setInteractScript(oldTile.getInteractScript());
                            this.map.setMapTile((int) tileLocation.x, (int) tileLocation.y, mapTile);
                            
                            return ScriptState.COMPLETED;
                        }
                    }
                );
            }});
            
        }});
        
        return scriptActions;
    }

}
