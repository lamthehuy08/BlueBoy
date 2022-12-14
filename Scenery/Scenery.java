package Scenery;

import Main.GamePanel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Scenery extends tile{
    public Scenery(GamePanel gp ){
        super(gp);
        Tile = new tile[100];
        indexMap = new int[gp.maxMap][51][51];

            setup(0,"grass" , false);
            setup(1,"wall" , true);
            setup(2,"water" , true);
            setup(3,"sand" , false);
            setup(4,"earth" , false);
            setup(5,"tree" , true);
        setup(11,"snow1",false);
        setup(12,"snow2",false);
        setup(20,"ice",false);
        setup(30,"stone",false);
        setup(41,"tree1",false);
        setup(42,"tree2",false);
        setup(43,"tree3",true);
        setup(44,"tree4",true);
        setup(45,"tree5",true);
        setup(46,"tree6",true);
        setup(47,"tree7",true);
        setup(48,"tree8",true);

        setup(51,"board1",true);
        setup(52,"board2",true);
        setup(53,"board3",true);
        setup(54,"board4",true);

        setup(61,"log1",false);
        setup(62,"log2",false);
        setup(63,"log3",true);
        setup(64,"log4",true);

        setup(71,"rock1",false);
        setup(72,"rock2",false);
        setup(73,"rock3",true);
        setup(74,"rock4",true);

        setup(91,"bush1",false);
        setup(92,"bush2",false);
        setup(93,"bush3",true);
        setup(94,"bush4",true);

        setup(21,"sand_1",false);
        setup(22,"sand_2",false);
        setup(23,"sand_3",false);
        setup(24,"sand_4",false);
        setup(25,"sand_5",false);
        setup(26,"sand_6",false);

        setup(31,"water_1",true);
        setup(32,"water_2",true);
        setup(33,"water_3",true);

        setup(55,"grass_1",false);
        setup(56,"grass_2",false);
        setup(57,"grass_3",false);
        setup(58,"grass_4",false);
        setup(59,"grass_5",false);

        setup(65,"tree_1",true);
        setup(66,"tree_2",true);

        setup(75,"wall_1",true);

        setup(95,"bush_1",true);
        loadMap("/Map/beachmap.txt" , 2);
        loadMap("/Map/map1.txt" , 1);
        loadMap("/Map/snowmap.txt" ,3);
    }
    public void loadMap(String filePath , int k){
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = "";

        for(int i = 0;i<50;i++){
            try {
                s = br.readLine();
                String s1[] = s.split(" ");
                for(int j = 0;j<50;j++){
                    indexMap[k][i][j] = Integer.parseInt(s1[j]);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void draw(Graphics2D g2){

        for(int i = 0 ; i < 50 ; i++){
            for(int j = 0 ; j < 50;j++){
                int worldX = j * gp.tileSize;
                int worldY = i * gp.tileSize;
                int screenX = worldX - (gp.player.worldX - gp.playerSetup.screenX);
                int screenY = worldY - (gp.player.worldY - gp.playerSetup.screenY);

                if(gp.player.worldX < gp.playerSetup.screenX){
                    screenX = worldX;
                }
                if(gp.player.worldY < gp.playerSetup.screenY){
                    screenY = worldY;
                }
                if((gp.WorldWidth - gp.player.worldX) < (gp.screenWidth-gp.playerSetup.screenX)){
                      screenX = worldX - (gp.WorldWidth - gp.screenWidth);
                }
                if((gp.WorldHeight - gp.player.worldY) < (gp.screenHeight-gp.playerSetup.screenY)){
                    screenY = worldY - (gp.WorldHeight - gp.screenHeight);
                }

                if(worldX > gp.player.worldX - gp.playerSetup.screenX - 2*gp.tileSize &&
                   worldX < gp.player.worldX + gp.playerSetup.screenX + 2*gp.tileSize &&
                   worldY > gp.player.worldY - gp.playerSetup.screenY - 2*gp.tileSize &&
                   worldY < gp.player.worldY + gp.playerSetup.screenY + 2*gp.tileSize){
                     g2.drawImage(Tile[indexMap[gp.currentMap][i][j]].imageTile,screenX,screenY,null);
                }
                else if((gp.player.worldX < gp.playerSetup.screenX && worldX < gp.screenWidth) ||
                        (gp.player.worldY < gp.playerSetup.screenY && worldY < gp.screenHeight)||
                        ((gp.WorldWidth - gp.player.worldX) < (gp.screenWidth-gp.playerSetup.screenX) && worldX >=0)||
                        ((gp.WorldHeight - gp.player.worldY) < (gp.screenHeight-gp.playerSetup.screenY) && worldY >=0)){
                        g2.drawImage(Tile[indexMap[gp.currentMap][i][j]].imageTile,screenX,screenY,null);
                }
            }
        }
    }
}
