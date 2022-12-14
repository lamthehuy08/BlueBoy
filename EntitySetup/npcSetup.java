package EntitySetup;

import Main.GamePanel;
import entity.Entity;

import java.util.Random;

public class npcSetup extends EntitySetup{
    public npcSetup(GamePanel gp, Entity npc) {
        super(gp);
        this.npc = npc;
        name = "NPC";
        this.speed = npc.speed;
        this.worldX = npc.worldX;
        this.worldY = npc.worldY;
        setDialogs();
        setDefaultRectCollision(10,16,28,32);
    }
    public void setDialogs(){
        dialogs[0] = "Hello, guy.";
        dialogs[1] = "I'm here from afternoon.";
        dialogs[2] = "I used to be a wizard person but I'm a bit old to \nadventure the world";
        dialogs[3] = "So, let's take adventure the whole world and it's \nso interesting, good luck!!";
    }
    public void speak(){
        indexDialog++;
        if(dialogs[indexDialog]==null)indexDialog=0;
        gp.ui.currentDialog = dialogs[indexDialog];
        String dir = gp.playerSetup.direction;
        switch (dir){
            case "up" :
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
        onPath = true;
    }
    public void setAct(){
        if(onPath){
            int startCol = (worldX+rectCollision.x)/gp.tileSize;
            int startRow = (worldY+rectCollision.y)/gp.tileSize;
            int goadRow = (gp.player.worldY + gp.playerSetup.rectCollision.y)/gp.tileSize;
            int goalCol = (gp.player.worldX + gp.playerSetup.rectCollision.x)/gp.tileSize;

            searchPath(startRow,startCol,goadRow,goalCol);
        }
        else {
            turnDirection++;
            if(turnDirection>=60){
                Random random = new Random();
                int r = random.nextInt(100) + 1;
                if (r <= 25) direction = "up";
                else if (r > 25 && r <= 50) direction = "down";
                else if (r > 50 && r <= 75) direction = "left";
                else if (r > 75 && r <= 100) direction = "right";
                turnDirection = 0;
            }
        }
    }  // random loop to set up status

    public void update(){
        setAct();
        CollisionOn = false;
        checkCollision();
        keyCount++;
        if(!CollisionOn){
            switch (direction){
                case "up" :
                    this.worldY-=speed;
                    if(worldY < 0)worldY = 0;
                    break;
                case "down" :
                    this.worldY+=speed;
                    if(worldY > gp.WorldHeight )worldY = gp.WorldHeight;
                    break;
                case "left" :
                    this.worldX-=speed;
                    if(worldX < 0) worldX = 0;
                    break;
                case "right" :
                    this.worldX+=speed;
                    if(worldX > gp.WorldWidth)worldX = gp.WorldWidth;
                    break;
            }
        }
        if(keyCount>=15){
            if(foot == 1){
                foot=2;
            }
            else foot=1;
            keyCount=0;
        }
    }  // update the status of NPC
}
