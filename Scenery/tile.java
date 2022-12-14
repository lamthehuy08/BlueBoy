package Scenery;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class tile {
     GamePanel gp;
     public int indexMap[][][];
     public BufferedImage imageTile;
     public tile Tile[];
     public tile(GamePanel gp){
          this.gp = gp;
     }
     public boolean collision = false;

     public void setup(int i , String path , boolean collision){
          UtilityTool tools = new UtilityTool();
          Tile[i] = new tile(gp);
          try {
               Tile[i].imageTile = ImageIO.read(getClass().getResourceAsStream("/Picture/" + path+".png"));
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
          Tile[i].imageTile = tools.scaledImage(Tile[i].imageTile,gp.tileSize,gp.tileSize);
          Tile[i].collision=collision;
     }

}
