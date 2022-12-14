package EntitySetup;

import AI_Find_Path.Node;
import Main.GamePanel;
import Main.UtilityTool;
import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EntitySetup {

    public GamePanel gp;
    public EntitySetup(GamePanel gp) {
        this.gp = gp;
    }
    public int worldX;
    public int worldY;
    public int defaultWorX;
    public int defaultWorY;
    public int speed;
    public String name;
    public int strength;
    public int level;
    public int dexterity;
    public int attack;    // the total of attack
    public int defence;   // the total of defence
    public int exp;
    public int coin;
    public int nextLevel;
    public int maxLife;            // HP
    public int CurrentLife;         // Current HP
    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
    public BufferedImage img;        // hình ảnh để vẽ all of entity
    public BufferedImage image,image1,image2; // image, image1, image2 means that heart status
    public String dialogs[] = new String[10];  // hội thoại của nhân vật ,ex NPC
    public String direction = "down";
    public EntitySetup projectile;
    public String description = "";  // the description of the object
    public int attackValueOfObject;      // the attack of each weapon (Nội công)
    public int defenceValueOfObject;      // the defence of each shield (power thủ )
    public int indexDialog = 0;       //  dialog of NPC
    public int turnDirection = 0;     // turn the direction by random
    public int typeObject;            // the family of the object
    public boolean invincible = false;  // if entity is invincible
    public int invincibleCounter = 0;  // counter invincible
    public boolean alive = true;         // entity alive now?
    public boolean die = false;         // entity die now ?
    private int countDie = 0;
    public boolean HpBarOn = false;
    public int HpBarOnCount = 0;
    public double keyCount = 0;        // bộ đếm thay đổi hoạt ảnh (dáng đi) của nhân vật
    public int foot = 1;           // thể hiện trạng thái hình ảnh nhân vật hiện tại
    public Rectangle rectCollision = new Rectangle();        // collision
    public int rectDefaultX,rectDefaultY;          // default coordinate of rectCollision
    public Rectangle rectAttack = new Rectangle(); // the scale attack of each weapon
    public boolean CollisionOn = false;        // dùng để check liệu có đi được không
    public boolean collision = false;        // check xem thực thể đó có cho đi qua không, ví dụ water, wall
    public boolean onPath = false;
    public boolean knockBack = false;
    Entity mons;
    Entity npc;
    public BufferedImage setup(String path , int weight, int height  ){
        BufferedImage img = null;
        UtilityTool ult = new UtilityTool();
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/Picture/" + path + ".png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ult.scaledImage(img , weight , height);
    }
    public void update(){}       // update status of each entity (player, NPC, monster,...)
    public void use(){}         // using the consumable
    public void setObj(String name , int typeObject){};
    public void GeneratorParticle(EntitySetup entity , Color color){}
    public void speak(){}
    void checkCollision(){
        gp.cChecker.CollisionCheckerTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkPlayer(this);
        gp.cChecker.CheckEntity(this, gp.npcSetup[gp.currentMap]);
        gp.cChecker.CheckEntity(this,gp.monsterSetup[gp.currentMap]);
    }
    int counter = 0;
    public void searchPath(int startRow, int startCol, int goalRow, int goalCol){
        counter++;

            gp.AI.resetNode(startRow, startCol, goalRow, goalCol);
            gp.AI.FindPath(goalRow, goalCol);
            gp.AI.tracePath();

        Node current = gp.AI.currentNode;

        int nextX = current.col * gp.tileSize;
        int nextY = current.row * gp.tileSize;
        int nextRight = nextX + gp.tileSize;
        int nextBottom = nextY + gp.tileSize;

        int enX = worldX + rectCollision.x;
        int enRight = enX + rectCollision.width;
        int enY = worldY + rectCollision.y;
        int enBottom = enY + rectCollision.height;

        if(nextBottom <= enY){
            direction = "up";
            if(enRight >= nextRight){
                checkCollision();
                if(CollisionOn){
                    direction = "left";
                }
            }
        }
        else if(nextRight <= enX){
            direction = "left";
            if(enBottom >= nextBottom){
                checkCollision();
                if(CollisionOn){
                    direction = "up";
                }
            }
        }
        else if(nextY >= enY){
            direction = "down";
            if(enRight >= nextRight){
                checkCollision();
                if(CollisionOn){
                    direction = "left";
                }
            }
        }
        else if(nextX >= enX){
            direction = "right";
            if(enBottom >= nextBottom){
                checkCollision();
                if(CollisionOn){
                    direction = "up";
                }
            }
        }
        if(startRow == goalRow && startCol == goalCol  && counter > 80 && name != "Monster"){
            counter =0;
            onPath = false;
        }
    }
    public void draw(Graphics2D g2) {
        switch (direction) {
            case "up":
                if (foot == 1) {
                    img = up1;
                } else img = up2;
                break;
            case "down":
                if (foot == 1) {
                    img = down1;
                } else img = down2;
                break;
            case "left":
                if (foot == 1) {
                    img = left1;
                } else img = left2;
                break;
            case "right":
                if (foot == 1) {
                    img = right1;
                } else img = right2;
                break;
        }
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
                worldY < gp.player.worldY + gp.playerSetup.screenY + 2*gp.tileSize) {
            if(invincible){
                ChangAlpha(g2,0.4f);
            }
            if(die == true){
                setAnimation(g2);
            }
            g2.drawImage(img, screenX, screenY, null);
            ChangAlpha(g2,1f);
        }
        else if((gp.player.worldX < gp.playerSetup.screenX && worldX < gp.screenWidth) ||
                (gp.player.worldY < gp.playerSetup.screenY && worldY < gp.screenHeight)||
                ((gp.WorldWidth - gp.player.worldX) < (gp.screenWidth-gp.playerSetup.screenX) && worldX >=0)||
                ((gp.WorldHeight - gp.player.worldY) < (gp.screenHeight-gp.playerSetup.screenY) && worldY >=0)){
            if(invincible){
                ChangAlpha(g2,0.4f);
            }
            g2.drawImage(img, screenX, screenY, null);
            ChangAlpha(g2,1f);
        }
        if(name == "Monster" && HpBarOn == true ){      // set Health Bar
            HpBarOnCount++;
            double oneScale = (double)gp.tileSize/mons.maxLife;
            double HpCurrent = oneScale * mons.CurrentLife;
            g2.setColor(Color.BLACK);
            g2.fillRect(screenX,screenY, gp.tileSize ,12);
            g2.setColor(Color.red);
            g2.fillRect(screenX+1,screenY+1,(int)(HpCurrent-2),10);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("MV_Boli" , Font.PLAIN , 30));
            g2.drawString("??" , screenX +5 , screenY +30);
            if(HpBarOnCount > 400){
                HpBarOn = false;
                HpBarOnCount = 0;
            }

        }
    }  // draw all of entity : NPC, player, object,...
    public void setDefaultRectCollision(int x, int y,int width, int height){
        rectCollision.x = x;
        rectCollision.y = y;
        rectCollision.width = width;
        rectCollision.height = height;
        rectDefaultX = x;
        rectDefaultY = y;
    }  //set default rectangle
    public void setAnimation(Graphics2D g2){
        countDie++;
        int i = 5;   // any but i<8
        if(countDie <= i)ChangAlpha(g2,0f);
        if(countDie > i && countDie < i*2 )ChangAlpha(g2,1f);
        if(countDie > i*2 && countDie < i*3)ChangAlpha(g2,0f);
        if(countDie >= i*3 && countDie < i*4)ChangAlpha(g2,1f);
        if(countDie >= i*4 && countDie < i*5)ChangAlpha(g2,0f);
        if(countDie >= i*5 && countDie < i*6)ChangAlpha(g2,1f);
        if(countDie >= i*6 && countDie < i*7)ChangAlpha(g2,0f);
        if(countDie >= i*7 && countDie < i*8)ChangAlpha(g2,1f);
        if(countDie >= i*8) {
            this.dropItem();
            die = false;
            countDie = 0;
            alive = false;
        }
    }
    public void DropItem(EntitySetup entity){
        for(int i = 0 ; i < gp.obj[1].length ; i ++){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = entity;
                gp.obj[gp.currentMap][i].worldX = this.worldX;
                gp.obj[gp.currentMap][i].worldY = this.worldY;
                break;
            }
        }
    }
    public void dropItem(){}
    public void ChangAlpha(Graphics2D g2 , float value){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER , value));
    }
    protected void set(int worldX, int worldY, String direction) {   // set projectile of entity
    }

    protected Color getColor() {  // set Color of projectile
        return new Color(0,0,0);
    }
    protected void setDefault(String name , int curr , int maxL , int attack, int def , int exp , int speed){
    }
}
