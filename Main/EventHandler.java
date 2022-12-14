package Main;

import java.awt.*;

public class EventHandler {
    private GamePanel gp;
    private Rectangle EventRect;
    private int EventRectDefaultX,EventRectDefaultY;
    private int preX = 0,preY = 0;
    private boolean canMove = false;
    public EventHandler(GamePanel gp){
        this.gp = gp;
        EventRect = new Rectangle(0,0,48,48);
        EventRectDefaultX = EventRect.x;
        EventRectDefaultY = EventRect.y;
    }
    public void checkEvent(){
        if(hit(8,1,"any")) {
            healingPool();
        }
        telePort();
    }
    private void healingPool(){
        if(gp.KeyH.enterPressed == true){
            gp.playerSetup.attackCanceled = true;
            gp.player.CurrentLife = gp.player.maxLife;
            gp.player.currentMana = gp.player.maxMana;
            gp.playShort(2);
            gp.play = gp.diaglogState;
            gp.ui.currentDialog = "   Your life has been recovered!";
           // gp.aSetter.setMonster();
        }
    }
    private void telePort(){
        distance();

        if(hit(29,2,"up") && gp.currentMap == 1 && canMove == true){
            gp.player.worldX = 2 * gp.tileSize;
            gp.player.worldY = 13 * gp.tileSize;
            gp.currentMap = 2;
            preX = gp.player.worldX;
            preY = gp.player.worldY;
            canMove = false;
        }
        if(hit(2,13,"down") && gp.currentMap == 2 && canMove == true){
            gp.currentMap = 1;
            gp.player.worldX = 29 * gp.tileSize;
            gp.player.worldY = 2 * gp.tileSize;
            preX = gp.player.worldX;
            preY = gp.player.worldY;
            canMove = false;
        }
        if(hit(25,29,"any") && gp.currentMap == 2 && canMove == true){
            gp.currentMap = 3;
            gp.player.worldX = 22 * gp.tileSize;
            gp.player.worldY = 18 * gp.tileSize;
            preX = gp.player.worldX;
            preY = gp.player.worldY;
            canMove = false;
        }
        if(hit(22,18,"any") && gp.currentMap == 3 && canMove == true){
            gp.currentMap = 2;
            gp.player.worldX = 25 * gp.tileSize;
            gp.player.worldY = 29 * gp.tileSize;
            preX = gp.player.worldX;
            preY = gp.player.worldY;
            canMove = false;
        }
    }
    private void distance(){
        int xDis = Math.abs(gp.player.worldX - preX);
        int yDis = Math.abs(gp.player.worldY - preY);
        int max = Math.max(xDis,yDis);
        if(max > gp.tileSize-10)canMove = true;
    }
    private boolean hit(int EventCol , int EventRow, String reqDirection){

        boolean b = false;

        EventRect.x += EventCol * gp.tileSize;
        EventRect.y += EventRow * gp.tileSize;

        gp.playerSetup.rectCollision.x += gp.player.worldX;
        gp.playerSetup.rectCollision.y += gp.player.worldY;

        if(EventRect.intersects(gp.playerSetup.rectCollision)){
            if(reqDirection.contentEquals(gp.playerSetup.direction) || reqDirection.contentEquals("any")){
                b = true;
            }
        }
        EventRect.x = EventRectDefaultX;
        EventRect.y = EventRectDefaultY;
        gp.playerSetup.rectCollision.x = gp.playerSetup.rectDefaultX;
        gp.playerSetup.rectCollision.y = gp.playerSetup.rectDefaultY;
        return b;
    }
}
