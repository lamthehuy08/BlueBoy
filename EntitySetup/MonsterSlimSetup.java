package EntitySetup;
import Main.GamePanel;

import java.util.Random;
import Object.*;
import entity.Entity;

public class MonsterSlimSetup extends EntitySetup{
    private boolean hit;
    private int counterDamage = 0;
    private int knockBackCounter = 0;
    private int defaultSpeed;

    public MonsterSlimSetup(GamePanel gp , Entity mons) {
        super(gp);
        this.mons = mons;
        projectile = new Rock(gp);
        setDefaultRectCollision(3,18,42,30);
        worldX = mons.worldX;
        worldY = mons.worldY;
        defaultWorX = worldX;
        defaultWorY = worldY;
        setDefault(mons.name , mons.CurrentLife , mons.maxLife , mons.attack , mons.defence , mons.exp , mons.speed);
    }
    public void checkCollision(){
        gp.cChecker.CollisionCheckerTile(this);
        gp.cChecker.checkObject(this,false);
        hit = gp.cChecker.checkPlayer(this);
        gp.cChecker.CheckEntity(this,gp.monsterSetup[gp.currentMap]);
        gp.cChecker.CheckEntity(this,gp.npcSetup[gp.currentMap]);
    }
    protected void setDefault(String name , int curr , int maxL , int attack, int def , int exp , int speed ) {
        this.name = name;
        this.CurrentLife = curr;
        this.maxLife = maxL;
        this.attack = attack;
        defence = def;
        this.exp =exp;
        defaultSpeed = speed;
        this.speed = defaultSpeed;
    }
    public void update(){
        setAct();
        CollisionOn = false;
        checkCollision();
        keyCount++;
        if(hit == true && gp.playerSetup.invincible == false){
            int damage = attack - gp.player.defence;
            gp.playShort(6);
            if(damage<0)damage = 0;
            gp.player.CurrentLife -= damage;
            gp.playerSetup.invincible = true;
        }
        if(!CollisionOn){
            switch (direction){
                case "up" :
                    this.worldY-=speed;
                    mons.worldY = worldY;
                    if(worldY < 0)worldY = 0;
                    break;
                case "down" :
                    this.worldY+=speed;
                    mons.worldY = worldY;
                    if(worldY > gp.WorldHeight )worldY = gp.WorldHeight;
                    break;
                case "left" :
                    this.worldX-=speed;
                    mons.worldX = worldX;
                    if(worldX < 0) worldX = 0;
                    break;
                case "right" :
                    this.worldX+=speed;
                    mons.worldX = worldX;
                    if(worldX > gp.WorldWidth)worldX = gp.WorldWidth;
                    break;
            }
        } // move
        if(keyCount>=15){
            if(foot == 1){
                foot=2;
            }
            else foot=1;
            keyCount=0;
        }

        if(setActProjectile() && projectile.alive == false){
            projectile.set(worldX,worldY, direction);
            projectile.alive = true;
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 15){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void dropItem(){
        Random random = new Random();
        int i = random.nextInt(100)+1;
        if(i < 50){
            DropItem(new obj_coin_bronze(gp));
        }
        else if(i>=50 && i < 75){
            DropItem(new obj_Heart(gp));
        }
        else{
            DropItem(new obj_mana(gp));
        }
    }
    public void setAct(){

        if(knockBack){
            checkCollision();
            if(CollisionOn){
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            }
            else{
                beKnocked();
            }
        }
        else{
            if(onPath){
                counterDamage++;
                if(counterDamage == 1)speed +=1;
                counterDamage = 1;
                int startCol = (worldX+rectCollision.x)/gp.tileSize;
                int startRow = (worldY+rectCollision.y)/gp.tileSize;
                int goadRow = (gp.player.worldY + gp.playerSetup.rectCollision.y)/gp.tileSize;
                int goalCol = (gp.player.worldX + gp.playerSetup.rectCollision.x)/gp.tileSize;

                searchPath(startRow,startCol,goadRow,goalCol);
            }
            else {
                turnDirection++;
                if(turnDirection>=60){
                    turnDirection = 0;
                    Random random = new Random();
                    int r = random.nextInt(100) + 1;
                    if (r <= 25) direction = "up";
                    else if (r > 25 && r <= 50) direction = "down";
                    else if (r > 50 && r <= 75) direction = "left";
                    else if (r > 75 && r <= 100) direction = "right";
                }
            }
        }
    }
    private void beKnocked(){
        speed = -10;
        switch (gp.playerSetup.direction){
            case "up" :
                direction = "down";
                break;
            case "down" :
                direction = "up";
                break;
            case "left" :
                direction = "right";
                break;
            case "right" :
                direction = "left";
                break;
        }
        knockBackCounter++;
        if(knockBackCounter == 10){
            knockBack = false;
            knockBackCounter = 0;
            speed = defaultSpeed;
        }
    }
    public boolean setActProjectile() {
        Random random = new Random();
        int r = random.nextInt(100) + 1;
        if (r < 2) {
            return true;
        }
        return false;
    }
}
