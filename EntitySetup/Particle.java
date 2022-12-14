package EntitySetup;
import Main.GamePanel;
import java.awt.*;

public class Particle extends Projectile {
    int xd;
    int yd;
    Color color;
    public Particle(GamePanel gp,int x , int y , int xd, int yd , Color c) {
        super(gp);
        color = c;
        alive = true;
        maxLife = 20;
        CurrentLife = maxLife;
        speed = 1;
        worldX = x;
        worldY = y;
        this.xd = xd;
        this.yd = yd;
    }
    public void update(int i){
        CurrentLife--;
        worldX += xd * speed;
        worldY += yd * speed;
        if(CurrentLife % 4 == 0)yd++;
        if( CurrentLife == 0 ){
            gp.particles.remove(i);
            alive = false;
        }
    }
    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.playerSetup.screenX;
        int screenY = worldY - gp.player.worldY + gp.playerSetup.screenY;
        if(gp.player.worldX < gp.playerSetup.screenX)screenX = worldX;
        if(gp.player.worldY < gp.playerSetup.screenY)screenY = worldY;
        if(gp.WorldWidth - gp.player.worldX < gp.screenWidth - gp.playerSetup.screenX){
            screenX = worldX - gp.WorldWidth + gp.screenWidth;
        }
        if(gp.WorldHeight - gp.player.worldY < gp.screenHeight - gp.playerSetup.screenY){
            screenY = worldY - gp.WorldHeight + gp.screenHeight;
        }

        g2.setColor(color);
        g2.fillRect(screenX,screenY,6,6);
    }
}
