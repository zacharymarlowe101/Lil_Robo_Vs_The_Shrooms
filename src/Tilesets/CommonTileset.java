package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

    public CommonTileset() {
        super(ImageLoader.load("CommonTileset.png"), 32, 32, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();


        /*___________________Grass_______________________*/

        // grass 1
        Frame grass1Frame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grass1Tile = new MapTileBuilder(grass1Frame);

        mapTiles.add(grass1Tile);

       // grass 2
        Frame grass2Frame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder grass2Tile = new MapTileBuilder(grass2Frame);

        mapTiles.add(grass2Tile);

        
        /*___________________Mud_______________________*/

        // mud 1
        Frame mud1Frame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder mud1Tile = new MapTileBuilder(mud1Frame);

        mapTiles.add(mud1Tile);

        // mud 2
        Frame mud2Frame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder mud2Tile = new MapTileBuilder(mud2Frame);

        mapTiles.add(mud2Tile);


        /*___________________Half Blocks_______________________*/

        //grass half top
        Frame grassHalfTopFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassHalfTopTile = new MapTileBuilder(grassHalfTopFrame);

        mapTiles.add(grassHalfTopTile);

        //grass half bottom
        Frame grassHalfBottomFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassHalfBottomTile = new MapTileBuilder(grassHalfBottomFrame);

        mapTiles.add(grassHalfBottomTile);

        //grass half right
        Frame grassHalfRightFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassHalfRightTile = new MapTileBuilder(grassHalfRightFrame);

        mapTiles.add(grassHalfRightTile);

        //grass half left
        Frame grassHalfLeftFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassHalfLeftTile = new MapTileBuilder(grassHalfLeftFrame);

        mapTiles.add(grassHalfLeftTile);


        /*___________________Grass Corners_______________________*/

        // grass upper left
        Frame grassUpperLeftFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassUpperLeftTile = new MapTileBuilder(grassUpperLeftFrame);

        mapTiles.add(grassUpperLeftTile);

       // grass upper right
        Frame grassUpperRightFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassUpperRightTile = new MapTileBuilder(grassUpperRightFrame);

        mapTiles.add(grassUpperRightTile);

         // grass lower left
        Frame grassLowerLeftFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassLowerLeftTile = new MapTileBuilder(grassLowerLeftFrame);

        mapTiles.add(grassLowerLeftTile);

       // grass lower right
        Frame grassLowerRightFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassLowerRightTile = new MapTileBuilder(grassLowerRightFrame);

        mapTiles.add(grassLowerRightTile);



        /*___________________Mud Corners_______________________*/

        // mud upper left
        Frame mudUpperLeftFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudUpperLeftTile = new MapTileBuilder(mudUpperLeftFrame);

        mapTiles.add(mudUpperLeftTile);

       // mud upper right
        Frame mudUpperRightFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudUpperRightTile = new MapTileBuilder(mudUpperRightFrame);

        mapTiles.add(mudUpperRightTile);

         // mud lower left
        Frame mudLowerLeftFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudLowerLeftTile = new MapTileBuilder(mudLowerLeftFrame);

        mapTiles.add(mudLowerLeftTile);

       // mud lower right
        Frame mudLowerRightFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudLowerRightTile = new MapTileBuilder(mudLowerRightFrame);

        mapTiles.add(mudLowerRightTile);


        /*___________________Sludge_______________________*/

        //sludge
        Frame sludgeFrame = new FrameBuilder(getSubImage(7, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder sludgeTile = new MapTileBuilder(sludgeFrame);

        mapTiles.add(sludgeTile);








        // Everything beyond this point must be immovable. add .withTileType(TileType.NOT_PASSABLE); on each new mapTileBuilder line


        /*___________________Grass Cliff Halves_______________________*/

        // grass upper
        Frame grassCliffUpperFrame = new FrameBuilder(getSubImage(6, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffUpperTile = new MapTileBuilder(grassCliffUpperFrame);

        mapTiles.add(grassCliffUpperTile);

       // grass lower
        Frame grassCliffLowerFrame = new FrameBuilder(getSubImage(6, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffLowerTile = new MapTileBuilder(grassCliffLowerFrame);

        mapTiles.add(grassCliffLowerTile);

         // grass left
        Frame grassCliffLeftFrame = new FrameBuilder(getSubImage(6, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffLeftTile = new MapTileBuilder(grassCliffLeftFrame);

        mapTiles.add(grassCliffLeftTile);

       // grass right
        Frame grassCliffRightFrame = new FrameBuilder(getSubImage(6, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffRightTile = new MapTileBuilder(grassCliffRightFrame);

        mapTiles.add(grassCliffRightTile);



        /*___________________Mud Cliff Halves_______________________*/

        // mud upper
        Frame mudCliffUpperFrame = new FrameBuilder(getSubImage(5, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffUpperTile = new MapTileBuilder(mudCliffUpperFrame);

        mapTiles.add(mudCliffUpperTile);

       // mud lower
        Frame mudCliffLowerFrame = new FrameBuilder(getSubImage(5, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffLowerTile = new MapTileBuilder(mudCliffLowerFrame);

        mapTiles.add(mudCliffLowerTile);

         // mud left
        Frame mudCliffLeftFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffLeftTile = new MapTileBuilder(mudCliffLeftFrame);

        mapTiles.add(mudCliffLeftTile);

       // mud right
        Frame mudCliffRightFrame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffRightTile = new MapTileBuilder(mudCliffRightFrame);

        mapTiles.add(mudCliffRightTile);



          /*___________________Grass Cliff Corners_______________________*/

        // grass upper left
        Frame grassCliffUpperLeftFrame = new FrameBuilder(getSubImage(7, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffUpperLeftTile = new MapTileBuilder(grassCliffUpperLeftFrame);

        mapTiles.add(grassCliffUpperLeftTile);

       // grass upper right
        Frame grassCliffUpperRightFrame = new FrameBuilder(getSubImage(7, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffUpperRightTile = new MapTileBuilder(grassCliffUpperRightFrame);

        mapTiles.add(grassCliffUpperRightTile);

         // grass lower left
        Frame grassCliffLowerLeftFrame = new FrameBuilder(getSubImage(7, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffLowerLeftTile = new MapTileBuilder(grassCliffLowerLeftFrame);

        mapTiles.add(grassCliffLowerLeftTile);

       // grass lower right
        Frame grassCliffLowerRightFrame = new FrameBuilder(getSubImage(7, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassCliffLowerRightTile = new MapTileBuilder(grassCliffLowerRightFrame);

        mapTiles.add(grassCliffLowerRightTile);




        /*___________________Mud Cliff Corners_______________________*/

        // mud upper left
        Frame mudCliffUpperLeftFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffUpperLeftTile = new MapTileBuilder(mudCliffUpperLeftFrame);

        mapTiles.add(mudCliffUpperLeftTile);

       // mud upper right
        Frame mudCliffUpperRightFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffUpperRightTile = new MapTileBuilder(mudCliffUpperRightFrame);

        mapTiles.add(mudCliffUpperRightTile);

         // mud lower left
        Frame mudCliffLowerLeftFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffLowerLeftTile = new MapTileBuilder(mudCliffLowerLeftFrame);

        mapTiles.add(mudCliffLowerLeftTile);

       // mud lower right
        Frame mudCliffLowerRightFrame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder mudCliffLowerRightTile = new MapTileBuilder(mudCliffLowerRightFrame);

        mapTiles.add(mudCliffLowerRightTile);


        


        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*Leaving this in to show how to make scaled objects*/
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

         // sign
        // Frame signFrame = new FrameBuilder(getSubImage(3, 0))
        //         .withScale(tileScale)
        //         .withBounds(1, 2, 14, 14)
        //         .build();

        // MapTileBuilder signTile = new MapTileBuilder(signFrame)
        //         .withTileType(TileType.NOT_PASSABLE);

        // mapTiles.add(signTile);


        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*Leaving this in to show how to flip AND how to make top layer*/
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        // // right end branch
        // Frame rightEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
        //         .withScale(tileScale)
        //         .withBounds(0, 6, 16, 4)
        //         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //         .build();

        // MapTileBuilder rightEndBranchTile = new MapTileBuilder(grassFrame)
        //         .withTopLayer(rightEndBranchFrame)
        //         .withTileType(TileType.PASSABLE);

        // mapTiles.add(rightEndBranchTile);
        

        

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        //leaving this in to show how animations work
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/


        // yellow flower
        // Frame[] yellowFlowerFrames = new Frame[] {
        //         new FrameBuilder(getSubImage(1, 2), 65)
        //             .withScale(tileScale)
        //             .build(),
        //         new FrameBuilder(getSubImage(1, 3), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(1, 2), 65)
        //                 .withScale(tileScale)
        //                 .build(),
        //         new FrameBuilder(getSubImage(1, 4), 65)
        //                 .withScale(tileScale)
        //                 .build()
        // };

        // MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

        // mapTiles.add(yellowFlowerTile);


        return mapTiles;
    }
}
