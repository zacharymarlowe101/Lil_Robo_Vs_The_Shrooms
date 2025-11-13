package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;
import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

    private ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
    private ArrayList<Frame> tileFrames = new ArrayList<>();

    public CommonTileset() {
        super(ImageLoader.load("CommonTileset.png"), 32, 32, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        mapTiles = new ArrayList<>();
        tileFrames = new ArrayList<>();


        /*___________________Grass_______________________*/

        // grass 1
        Frame grass1Frame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();
        tileFrames.add(grass1Frame);
        MapTileBuilder grass1Tile = new MapTileBuilder(grass1Frame);
        mapTiles.add(grass1Tile);

        // grass 2
        Frame grass2Frame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();
        tileFrames.add(grass2Frame);
        MapTileBuilder grass2Tile = new MapTileBuilder(grass2Frame);
        mapTiles.add(grass2Tile);


        /*___________________Mud_______________________*/

        // mud 1
        Frame mud1Frame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();
        tileFrames.add(mud1Frame);
        MapTileBuilder mud1Tile = new MapTileBuilder(mud1Frame);
        mapTiles.add(mud1Tile);

        // mud 2
        Frame mud2Frame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();
        tileFrames.add(mud2Frame);
        MapTileBuilder mud2Tile = new MapTileBuilder(mud2Frame);
        mapTiles.add(mud2Tile);


        /*___________________Half Blocks_______________________*/

        // grass half top
        Frame grassHalfTopFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassHalfTopFrame);
        MapTileBuilder grassHalfTopTile = new MapTileBuilder(grassHalfTopFrame);
        mapTiles.add(grassHalfTopTile);

        // grass half bottom
        Frame grassHalfBottomFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassHalfBottomFrame);
        MapTileBuilder grassHalfBottomTile = new MapTileBuilder(grassHalfBottomFrame);
        mapTiles.add(grassHalfBottomTile);

        // grass half right
        Frame grassHalfRightFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassHalfRightFrame);
        MapTileBuilder grassHalfRightTile = new MapTileBuilder(grassHalfRightFrame);
        mapTiles.add(grassHalfRightTile);

        // grass half left
        Frame grassHalfLeftFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassHalfLeftFrame);
        MapTileBuilder grassHalfLeftTile = new MapTileBuilder(grassHalfLeftFrame);
        mapTiles.add(grassHalfLeftTile);


        /*___________________Grass Corners_______________________*/

        // grass upper left
        Frame grassUpperLeftFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassUpperLeftFrame);
        MapTileBuilder grassUpperLeftTile = new MapTileBuilder(grassUpperLeftFrame);
        mapTiles.add(grassUpperLeftTile);

        // grass upper right
        Frame grassUpperRightFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassUpperRightFrame);
        MapTileBuilder grassUpperRightTile = new MapTileBuilder(grassUpperRightFrame);
        mapTiles.add(grassUpperRightTile);

        // grass lower left
        Frame grassLowerLeftFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassLowerLeftFrame);
        MapTileBuilder grassLowerLeftTile = new MapTileBuilder(grassLowerLeftFrame);
        mapTiles.add(grassLowerLeftTile);

        // grass lower right
        Frame grassLowerRightFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .build();
        tileFrames.add(grassLowerRightFrame);
        MapTileBuilder grassLowerRightTile = new MapTileBuilder(grassLowerRightFrame);
        mapTiles.add(grassLowerRightTile);


        /*___________________Mud Corners_______________________*/

        // mud upper left
        Frame mudUpperLeftFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();
        tileFrames.add(mudUpperLeftFrame);
        MapTileBuilder mudUpperLeftTile = new MapTileBuilder(mudUpperLeftFrame);
        mapTiles.add(mudUpperLeftTile);

        // mud upper right
        Frame mudUpperRightFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();
        tileFrames.add(mudUpperRightFrame);
        MapTileBuilder mudUpperRightTile = new MapTileBuilder(mudUpperRightFrame);
        mapTiles.add(mudUpperRightTile);

        // mud lower left
        Frame mudLowerLeftFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();
        tileFrames.add(mudLowerLeftFrame);
        MapTileBuilder mudLowerLeftTile = new MapTileBuilder(mudLowerLeftFrame);
        mapTiles.add(mudLowerLeftTile);

        // mud lower right
        Frame mudLowerRightFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();
        tileFrames.add(mudLowerRightFrame);
        MapTileBuilder mudLowerRightTile = new MapTileBuilder(mudLowerRightFrame);
        mapTiles.add(mudLowerRightTile);



        /*___________________Sludge_______________________*/

        //sludge
        Frame sludgeFrame = new FrameBuilder(getSubImage(7, 4))
                .withScale(tileScale)
                
                .build();

        MapTileBuilder sludgeTile = new MapTileBuilder(sludgeFrame)
                         .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(sludgeTile);








        // Everything beyond this point must be immovable. add .withTileType(TileType.NOT_PASSABLE); on each new mapTileBuilder line


        /*___________________Grass Cliff Halves_______________________*/

        // grass upper
        Frame grassCliffUpperFrame = new FrameBuilder(getSubImage(6, 0))
                .withScale(tileScale)
                .withBounds(0, 8, 32, 24)
                .build();

        MapTileBuilder grassCliffUpperTile = new MapTileBuilder(grassCliffUpperFrame)
                                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffUpperTile);

       // grass lower
        Frame grassCliffLowerFrame = new FrameBuilder(getSubImage(6, 3))
                .withScale(tileScale)
                .withBounds(0, 10, 32, 1)
                .build();

        MapTileBuilder grassCliffLowerTile = new MapTileBuilder(grassCliffLowerFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffLowerTile);

         // grass left
        Frame grassCliffLeftFrame = new FrameBuilder(getSubImage(6, 2))
                .withScale(tileScale)
                .withBounds(9, 10, 23, 32)
                .build();

        MapTileBuilder grassCliffLeftTile = new MapTileBuilder(grassCliffLeftFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffLeftTile);

       // grass right
        Frame grassCliffRightFrame = new FrameBuilder(getSubImage(6, 1))
                .withScale(tileScale)
                .withBounds(0, 10, 23, 32)
                .build();

        MapTileBuilder grassCliffRightTile = new MapTileBuilder(grassCliffRightFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffRightTile);



        /*___________________Mud Cliff Halves_______________________*/

        // mud upper
        Frame mudCliffUpperFrame = new FrameBuilder(getSubImage(5, 0))
                .withScale(tileScale)
                .withBounds(0, 8, 32, 24)
                .build();

        MapTileBuilder mudCliffUpperTile = new MapTileBuilder(mudCliffUpperFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffUpperTile);

       // mud lower
        Frame mudCliffLowerFrame = new FrameBuilder(getSubImage(5, 3))
                .withScale(tileScale)
                .withBounds(0, 10, 32, 1)
                .build();

        MapTileBuilder mudCliffLowerTile = new MapTileBuilder(mudCliffLowerFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffLowerTile);

         // mud left
        Frame mudCliffLeftFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .withBounds(9, 10, 23, 32)
                .build();

        MapTileBuilder mudCliffLeftTile = new MapTileBuilder(mudCliffLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffLeftTile);

       // mud right
        Frame mudCliffRightFrame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .withBounds(0, 10, 23, 32)
                .build();

        MapTileBuilder mudCliffRightTile = new MapTileBuilder(mudCliffRightFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffRightTile);


     

          /*___________________Grass Cliff Corners_______________________*/

        // grass upper left
        Frame grassCliffUpperLeftFrame = new FrameBuilder(getSubImage(7, 1))
                .withScale(tileScale)
                .withBounds(9, 9, 21, 23)
                .build();

        MapTileBuilder grassCliffUpperLeftTile = new MapTileBuilder(grassCliffUpperLeftFrame)
                        .withTileType(TileType.NOT_PASSABLE); // sludge is not passable
        mapTiles.add(grassCliffUpperLeftTile);

       // grass upper right
        Frame grassCliffUpperRightFrame = new FrameBuilder(getSubImage(7, 2))
                .withScale(tileScale)
                .withBounds(0, 10, 22, 22)
                .build();

        MapTileBuilder grassCliffUpperRightTile = new MapTileBuilder(grassCliffUpperRightFrame)
                         .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(grassCliffUpperRightTile);

         // grass lower left
        Frame grassCliffLowerLeftFrame = new FrameBuilder(getSubImage(7, 0))
                .withScale(tileScale)
                .withBounds(11, 10, 21, 1)
                .build();

        MapTileBuilder grassCliffLowerLeftTile = new MapTileBuilder(grassCliffLowerLeftFrame)
                                 .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffLowerLeftTile);

       // grass lower right
        Frame grassCliffLowerRightFrame = new FrameBuilder(getSubImage(7, 3))
                .withScale(tileScale)
                .withBounds(0, 10, 21, 1)
                .build();

        MapTileBuilder grassCliffLowerRightTile = new MapTileBuilder(grassCliffLowerRightFrame)
                         .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffLowerRightTile);




        /*___________________Mud Cliff Corners_______________________*/

        // mud upper left
        Frame mudCliffUpperLeftFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .withBounds(9, 8, 21, 23)
                .build();

        MapTileBuilder mudCliffUpperLeftTile = new MapTileBuilder(mudCliffUpperLeftFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffUpperLeftTile);

       // mud upper right
        Frame mudCliffUpperRightFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .withBounds(0, 8, 22, 22)
                .build();
               
        MapTileBuilder mudCliffUpperRightTile = new MapTileBuilder(mudCliffUpperRightFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffUpperRightTile);

         // mud lower left
        Frame mudCliffLowerLeftFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
               .withBounds(11, 10, 21, 1)
                .build();

        MapTileBuilder mudCliffLowerLeftTile = new MapTileBuilder(mudCliffLowerLeftFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(mudCliffLowerLeftTile);

       // mud lower right
        Frame mudCliffLowerRightFrame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .withBounds(0, 10, 21, 1)
                .build();

        MapTileBuilder mudCliffLowerRightTile = new MapTileBuilder(mudCliffLowerRightFrame)
                .withTileType(TileType.NOT_PASSABLE);
                
        mapTiles.add(mudCliffLowerRightTile);

         /*___________________Mud Cliff Inverted Corners_______________________*/

        // mud upper left
        Frame mudCliffInvertedUpperLeftFrame = new FrameBuilder(getSubImage(9, 3))
                .withScale(tileScale)
                 .withBounds(6, 10, 33, 1)
                .build();

        MapTileBuilder mudCliffInvertedUpperLeftTile = new MapTileBuilder(mudCliffInvertedUpperLeftFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffInvertedUpperLeftTile);

       // mud upper right
        Frame mudCliffInvertedUpperRightFrame = new FrameBuilder(getSubImage(9, 0))
                .withScale(tileScale)
                  .withBounds(0, 10, 33, 1)
                .build();
               
        MapTileBuilder mudCliffInvertedUpperRightTile = new MapTileBuilder(mudCliffInvertedUpperRightFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mudCliffInvertedUpperRightTile);

         // mud lower left
        Frame mudCliffInvertedLowerLeftFrame = new FrameBuilder(getSubImage(9, 2))
                .withScale(tileScale)
                .withBounds(9, 0, 21, 30)
                .build();

        MapTileBuilder mudCliffInvertedLowerLeftTile = new MapTileBuilder(mudCliffInvertedLowerLeftFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(mudCliffInvertedLowerLeftTile);

       // mud lower right
        Frame mudCliffInvertedLowerRightFrame = new FrameBuilder(getSubImage(9, 1))
                .withScale(tileScale)
                .withBounds(2, 0, 21, 30)
                .build();

        MapTileBuilder mudCliffInvertedLowerRightTile = new MapTileBuilder(mudCliffInvertedLowerRightFrame)
                .withTileType(TileType.NOT_PASSABLE);
                
        mapTiles.add(mudCliffInvertedLowerRightTile);
        

        /*___________________Grass Cliff Inverted Corners_______________________*/

        // grass upper left
        Frame grassCliffInvertedUpperLeftFrame = new FrameBuilder(getSubImage(8, 3))
                .withScale(tileScale)
                 .withBounds(6, 10, 33, 1)
                .build();

        MapTileBuilder grassCliffInvertedUpperLeftTile = new MapTileBuilder(grassCliffInvertedUpperLeftFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffInvertedUpperLeftTile);

        // grass upper right
        Frame grassCliffInvertedUpperRightFrame = new FrameBuilder(getSubImage(8, 0))
                .withScale(tileScale)
                   .withBounds(0, 10, 33, 1)
                .build();

        MapTileBuilder grassCliffInvertedUpperRightTile = new MapTileBuilder(grassCliffInvertedUpperRightFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(grassCliffInvertedUpperRightTile);

        // grass lower left
        Frame grassCliffInvertedLowerLeftFrame = new FrameBuilder(getSubImage(8, 2))
                .withScale(tileScale)
                .withBounds(9, 0, 21, 30)
                .build();

        MapTileBuilder grassCliffInvertedLowerLeftTile = new MapTileBuilder(grassCliffInvertedLowerLeftFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(grassCliffInvertedLowerLeftTile);

        // grass lower right
        Frame grassCliffInvertedLowerRightFrame = new FrameBuilder(getSubImage(8, 1))
                .withScale(tileScale)
                .withBounds(2, 0, 21, 30)
                .build();

        MapTileBuilder grassCliffInvertedLowerRightTile = new MapTileBuilder(grassCliffInvertedLowerRightFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(grassCliffInvertedLowerRightTile);




         /*___________________Teleport Tile_______________________*/

        // Teleport
        Frame teleportFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder teleportTile = new MapTileBuilder(teleportFrame);

        mapTiles.add(teleportTile);


           /*___________________Mud/Grass Cliff Halves_______________________*/

        //top cliffs
        // mud/grass cliff top left
        Frame mgctlFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .withBounds(0, 8, 32, 24)
                .build();

        MapTileBuilder mgctlTile = new MapTileBuilder(mgctlFrame)
                                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgctlTile);

        // mud/grass cliff top right
        Frame mgctrFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 8, 32, 24)
                .build();

        MapTileBuilder mgctrTile = new MapTileBuilder(mgctrFrame)
                                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgctrTile);



        //right cliffs
         // mud/grass cliff right bottom
        Frame mgcrbFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .withBounds(0, 10, 23, 32)
                .build();

        MapTileBuilder mgcrbTile = new MapTileBuilder(mgcrbFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgcrbTile);

       //mud grass cliff right top
        Frame mgcrtFrame = new FrameBuilder(getSubImage(1, 6))
                .withScale(tileScale)
                .withBounds(0, 10, 23, 32)
                .build();

        MapTileBuilder mgcrtTile = new MapTileBuilder(mgcrtFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgcrtTile);


        //left cliffs
        //mud/grass cliff left bottom 
        Frame mgclbFrame = new FrameBuilder(getSubImage(0, 6))
                .withScale(tileScale)
                .withBounds(9, 10, 23, 32)
                .build();

        MapTileBuilder mgclbTile = new MapTileBuilder(mgclbFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgclbTile);

        //mud/grass cliff left top mgclt
        Frame mgcltFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .withBounds(9, 10, 23, 32)
                .build();

        MapTileBuilder mgcltTile = new MapTileBuilder(mgcltFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgcltTile);


        //bottom cliffs
        //mud/grass cliff bottom left 
        Frame mgcblFrame = new FrameBuilder(getSubImage(2, 6))
                .withScale(tileScale)
                .withBounds(0, 10, 32, 1)
                .build();

        MapTileBuilder mgcblTile = new MapTileBuilder(mgcblFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgcblTile);

        //mud/grass cliff bottom right
        Frame mgcbrFrame = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .withBounds(0, 0, 32, 1)
                .build();

        MapTileBuilder mgcbrTile = new MapTileBuilder(mgcbrFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(mgcbrTile);




        //ADD OVERLAYS IN HERE
        addOverlayTiles();




        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*Leaving this in to show how to make scaled objects*/
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        //  // sign
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

    private void addOverlayTiles(){
        for(int i  = 0; i < tileFrames.size(); i ++){

            //sets a frame identical to the other, but allows us to set the bounds without influencing the entire arraylist
            Frame pillarFrame1 = tileFrames.get(i).copy();
            pillarFrame1.setBounds(14, 15, 11, 17);
            Frame pillarFrame2 = tileFrames.get(i).copy();
            pillarFrame2.setBounds(5, 15, 20, 17);


            /*___________________Pillar Bottom 1 Tile_______________________*/
            //tileFrames.get(i).setBounds(14, 15, 11, 17);
            Frame PillarBottomFrame1 = new FrameBuilder(getSubImage(5, 4))
                    .withScale(tileScale)
                    .build();


            MapTileBuilder pillarBottomTile1 = new MapTileBuilder(pillarFrame1)
                    .withTopLayer(PillarBottomFrame1)
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(pillarBottomTile1);
            
            
            /*___________________Pillar Bottom 2 Tile_______________________*/
            //tileFrames.get(i).setBounds(5, 15, 20, 17);
            Frame PillarBottomFrame2 = new FrameBuilder(getSubImage(5, 5))
                    .withScale(tileScale)
                    .build();

            MapTileBuilder pillarBottomTile2 = new MapTileBuilder(pillarFrame2)
                    .withTopLayer(PillarBottomFrame2)
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(pillarBottomTile2);
            
            
            /*___________________Pillar Middle 1 Tile_______________________*/
            Frame PillarMiddleFrame1 = new FrameBuilder(getSubImage(4, 5))
                    .withScale(tileScale)
                    .withBounds(13, 0, 30, 32)
                    .build();

            MapTileBuilder pillarMiddleTile1 = new MapTileBuilder(tileFrames.get(i))
                    .withTopLayer(PillarMiddleFrame1)
                    .withTileType(TileType.PASSABLE);

            mapTiles.add(pillarMiddleTile1);
            
            
            /*___________________Pillar Middle 2 Tile_______________________*/
            Frame PillarMiddleFrame2 = new FrameBuilder(getSubImage(4, 6))
                    .withScale(tileScale)
                    .withBounds(13, 0, 30, 32)
                    .build();

            MapTileBuilder pillarMiddleTile2 = new MapTileBuilder(tileFrames.get(i))
                    .withTopLayer(PillarMiddleFrame2)
                    .withTileType(TileType.PASSABLE);

            mapTiles.add(pillarMiddleTile2);
            
            /*___________________Pillar Middle 3 Tile_______________________*/
            Frame[] PillarMiddleFrame3 = new Frame[]{
                new FrameBuilder(getSubImage(4, 4), 65)
                    .withScale(tileScale)
                    .withBounds(13, 0, 30, 32)
                    .build(),
                new FrameBuilder(getSubImage(5, 6), 65)
                    .withScale(tileScale)
                    .withBounds(13, 0, 30, 32)
                    .build()
            };
            
            MapTileBuilder pillarMiddleTile3 = new MapTileBuilder(tileFrames.get(i))
                    .withTopLayer(PillarMiddleFrame3)
                    .withTileType(TileType.PASSABLE);

            mapTiles.add(pillarMiddleTile3);
                  
                  
                  
            /*___________________Pillar Top 1 Tile_______________________*/
            Frame PillarTopFrame1 = new FrameBuilder(getSubImage(3, 4))
                    .withScale(tileScale)
                    .withBounds(13, 0, 30, 32)
                    .build();

            MapTileBuilder pillarTopTile1 = new MapTileBuilder(tileFrames.get(i))
                    .withTopLayer(PillarTopFrame1)
                    .withTileType(TileType.PASSABLE);

            mapTiles.add(pillarTopTile1);
            
             /*___________________Pillar Top 2 Tile_______________________*/
            Frame PillarTopFrame2 = new FrameBuilder(getSubImage(3, 5))
                    .withScale(tileScale)
                    .withBounds(13, 0, 30, 32)
                    .build();

            MapTileBuilder pillarTopTile2 = new MapTileBuilder(tileFrames.get(i))
                    .withTopLayer(PillarTopFrame2)
                    .withTileType(TileType.PASSABLE);

            mapTiles.add(pillarTopTile2);
            
             /*___________________Pillar Top 3 Tile_______________________*/
            Frame PillarTopFrame3 = new FrameBuilder(getSubImage(3, 6))
                    .withScale(tileScale)
                    .withBounds(13, 0, 30, 32)
                    .build();

            MapTileBuilder pillarTopTile3 = new MapTileBuilder(tileFrames.get(i))
                    .withTopLayer(PillarTopFrame3)
                    .withTileType(TileType.PASSABLE);

            mapTiles.add(pillarTopTile3);
            
        }

    }

}
