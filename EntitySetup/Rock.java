package EntitySetup;

import Main.GamePanel;

import java.awt.*;

public class Rock extends Projectile {
    public Rock(GamePanel gp) {
        super(gp);
        name = "rock";
        attack = 7;
        maxLife = 60;
        speed = 5;
        CurrentLife = maxLife;
        alive = false;
        setDefaultRectCollision(8,8,32,32);
        setImage();
    }

    public Color getColor(){
        Color color = new Color(40,50,0);
        return color;
    }

    void setImage(){
        up1 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
        up2 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
        down1 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
        down2 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
        left1 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
        left2 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
        right1 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
        right2 = setup("rock_down_1" , gp.tileSize,gp.tileSize);
    }
}
