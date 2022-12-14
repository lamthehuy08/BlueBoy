package EntitySetup;

import Main.GamePanel;

import java.awt.*;

public class fireball extends Projectile {
    public fireball(GamePanel gp) {
        super(gp);
        name = "fireball";
        maxLife = 60;
        CurrentLife = maxLife;
        speed = 5;
        costMana = 1;
        attack = 5;
        alive = false;
        setDefaultRectCollision(8,8,32,32);
        getImage();
    }
    public Color getColor(){
        Color color = new Color(240,50,0);
        return color;
    }

    public void getImage(){
        up1 = setup("fireball_up_1" , gp.tileSize,gp.tileSize);
        up2 = setup("fireball_up_2" , gp.tileSize,gp.tileSize);
        down1 = setup("fireball_down_1" , gp.tileSize,gp.tileSize);
        down2 = setup("fireball_down_2" , gp.tileSize,gp.tileSize);
        left1 = setup("fireball_left_1" , gp.tileSize,gp.tileSize);
        left2 = setup("fireball_left_2" , gp.tileSize,gp.tileSize);
        right1 = setup("fireball_right_1" , gp.tileSize,gp.tileSize);
        right2 = setup("fireball_right_2" , gp.tileSize,gp.tileSize);
    }
}
