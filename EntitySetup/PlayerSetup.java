package EntitySetup;

import Main.GamePanel;
import Main.MyKeyAdapter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSetup extends EntitySetup{
    GamePanel gp;
    public final int screenX;
    public final int screenY;
    private BufferedImage img ;  // the picture of player
    public int hasKey = 0;
    int keyCount = 0;
    int foot = 1;           // thể hiện trạng thái hình ảnh nhân vật hiện tại
    MyKeyAdapter KeyH;
    boolean attacking = false;
    public boolean attackCanceled = false;
    int counterFireball = 0;
    public PlayerSetup(GamePanel gp , MyKeyAdapter KeyH ){
        super(gp);
        this.KeyH = KeyH;
        this.gp = gp;
        screenX = gp.screenWidth/2- gp.tileSize;
        screenY = gp.screenHeight/2- gp.tileSize;
        setDefaultPlayer();
    }
    public void setDefaultPlayer(){
        this.speed = gp.player.speed;
         gp.player.worldX = 12*gp.tileSize;
         gp.player.worldY = 10* gp.tileSize;
         worldX = gp.player.worldX;
         worldY = gp.player.worldY;
        maxLife = gp.player.maxLife;
        CurrentLife = gp.player.maxLife;
        level = gp.player.level;
        exp = gp.player.exp;
        strength = gp.player.strength;
        dexterity = gp.player.dexterity;
        coin = gp.player.coin;
        nextLevel = gp.player.nextLevel;
        gp.player.currentShield = new Shield_wood(gp);
        gp.player.currentWeapon = new Sword_wood(gp);
        attack = strength + gp.player.currentWeapon.attackValueOfObject;
        defence = dexterity + gp.player.currentShield.defenceValueOfObject;
        gp.player.attack= attack;
        gp.player.defence = defence;
        gp.player.projectile = new fireball(gp);
        setDefaultRectCollision(10,16,28,32);
        gp.player.inventory.add(gp.player.currentWeapon);
        gp.player.inventory.add(gp.player.currentShield);
    }
    public void update(){
        if(attacking){
            setAttackingPlayer();
        }
        else if(KeyH.down || KeyH.right || KeyH.left || KeyH.up || KeyH.enterPressed == true || KeyH.spacePressed == true){

            keyCount = keyCount + 1;
            if(KeyH.up) direction = "up";
            if(KeyH.down) direction = "down";
            if(KeyH.left) direction = "left";
            if(KeyH.right) direction = "right";

            //Check collision
            CollisionOn = false;
            gp.cChecker.CollisionCheckerTile(this);

            // check Object

            int objIndex = gp.cChecker.checkObject(this,true);
            pickOBJ(objIndex);

            // check NPC and monster

            int npcI = gp.cChecker.CheckEntity(this,gp.npcSetup[gp.currentMap]);
            interactNPC(npcI);

            // check Monster

            int monsterCheck = gp.cChecker.CheckEntity(this, gp.monsterSetup[gp.currentMap]);
            interactMonster(monsterCheck);

            // Check Event

            gp.cCheckEventHandler.checkEvent();

            if(attackCanceled == false && KeyH.enterPressed == true ){
                gp.playShort(5);
                attacking = true;
                counterFireball = 0;
            }
            attackCanceled = false;

            // if statement to move

            if(!CollisionOn && KeyH.enterPressed == false && KeyH.spacePressed == false){
                switch (direction){
                    case "up" :
                        gp.player.worldY-=speed;
                        worldY = gp.player.worldY;
                        break;
                    case "down" :
                        gp.player.worldY+=speed;
                        worldY = gp.player.worldY;
                        break;
                    case "left" :
                        gp.player.worldX-=speed;
                        worldX = gp.player.worldX;
                        break;
                    case "right" :
                        gp.player.worldX+=speed;
                        worldX = gp.player.worldX;
                        break;
                }
            }
            KeyH.enterPressed = false;  // reset the enter Key

            if(keyCount>=5){
                if(foot == 1){
                    foot=2;
                }
                else foot=1;
                keyCount=0;
            }
        }
        else {
            foot = 1;
        }
        counterFireball++;
        if(KeyH.shotPressed == true && gp.player.projectile.alive == false && counterFireball > 29 && gp.player.currentMana > 0){
            gp.playShort(10);
            gp.player.currentMana-=10;
            gp.player.projectile.set(worldX,worldY,direction);
            gp.player.projectile.alive = true;
            counterFireball = 0;
        }
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void setAttackingPlayer(){
        keyCount++;
        if(keyCount <= 5){
            foot = 1;
        }
        else if(keyCount > 5 && keyCount <=20){

            // save the current x,y,w,h to restore

            int currentX = worldX;
            int currentY = worldY;
            int x = rectCollision.x;
            int y = rectCollision.y;
            int currentW = rectCollision.width;
            int currentH = rectCollision.height;

            switch (direction){
                case "up" : worldY -= rectCollision.height;break;
                case "down" : worldY += rectCollision.height;break;
                case "left" : worldX  = worldX - gp.tileSize + rectCollision.x;break;
                case "right" : worldX = worldX + gp.tileSize - rectCollision.x;break;
            }

            rectCollision.x = gp.player.currentWeapon.rectAttack.x;
            rectCollision.y = gp.player.currentWeapon.rectAttack.y;
            rectCollision.width = gp.player.currentWeapon.rectAttack.width;
            rectCollision.height = gp.player.currentWeapon.rectAttack.height;

            int damageMonster = gp.cChecker.CheckEntity(this,gp.monsterSetup[gp.currentMap]);
            System.out.println(damageMonster);
            DamageMonster(damageMonster , gp.player.attack);
            if(damageMonster!=-1)gp.monsterSetup[gp.currentMap][damageMonster].knockBack = true;

            for(int i = 0 ; i < gp.monsterSetup[1].length ; i++){
                if(gp.monsterSetup[gp.currentMap][i] != null && gp.monsterSetup[gp.currentMap][i].projectile.alive == true){
                    int hit = gp.cChecker.CheckSingleEntity(this,gp.monsterSetup[gp.currentMap][i].projectile);
                    if(hit == 1)damageProjectile(i);
                }
            }

            foot = 2;

            worldX = currentX;
            worldY = currentY;
            rectCollision.x = x;
            rectCollision.y = y;
            rectCollision.width = currentW;
            rectCollision.height = currentH;
        }
        else if(keyCount > 20){
            foot = 1;
            keyCount=0;
            attacking = false;
        }
    }
    public void damageProjectile(int i){
             EntitySetup e = gp.monsterSetup[gp.currentMap][i].projectile;
             e.GeneratorParticle(e,e.getColor());
             e.alive = false;
    }
    public void pickOBJ(int id){
        if(id!=-2) {
            String name = gp.obj[gp.currentMap][id].name;
            // 1 means weapon
            if(gp.obj[gp.currentMap][id].typeObject == 1){
                if(name == "axe") {
                    gp.playShort(1);
                    gp.player.inventory.add(gp.obj[gp.currentMap][id]);
                    gp.ui.addMessage("+1 axe !");
                    gp.obj[gp.currentMap][id] = null;
                }
            }
            // 2 means shield
            else if(gp.obj[gp.currentMap][id].typeObject == 2){
                if(name == "blue Shield") {
                    gp.playShort(1);
                    gp.player.inventory.add(gp.obj[gp.currentMap][id]);
                    gp.ui.addMessage("+1 shield");
                    gp.obj[gp.currentMap][id] = null;
                }
            }
            // 3 means obstacle
            else if(gp.obj[gp.currentMap][id].typeObject == 3){
                if(name == "Door"){
                    if(KeyH.enterPressed){
                        attackCanceled = true;
                        interactObstacle();
                    }
                }
            }
            // 4 means consumable
            else if(gp.obj[gp.currentMap][id].typeObject == 4){
                gp.playShort(1);
                if(name == "coin bronze"){
                    gp.ui.addMessage("+1 coin");
                    gp.player.coin++;
                    gp.obj[gp.currentMap][id] = null;
                }
                else if(name == "Heart"){
                    gp.ui.addMessage("+1 heart");
                    gp.player.CurrentLife+=20;
                    if(gp.player.CurrentLife > gp.player.maxLife) gp.player.CurrentLife = gp.player.maxLife;
                    gp.obj[gp.currentMap][id]= null;
                }
                else if(name == "mana"){
                    gp.ui.addMessage("+1 mana");
                    gp.player.currentMana+=10;
                    if(gp.player.currentMana> gp.player.maxMana) gp.player.currentMana = gp.player.maxMana;
                    gp.obj[gp.currentMap][id]= null;
                }
                else if(name == "red potion") {
                    gp.player.inventory.add(gp.obj[gp.currentMap][id]);
                    gp.ui.addMessage("+1 red potion!");
                    gp.obj[gp.currentMap][id] = null;
                }
                else if(name == "Boots"){
                    gp.playShort(2);
                    gp.player.speed+=2;
                    speed+=2;
                    gp.obj[gp.currentMap][id] = null;
                }
                else if(name == "Key"){
                    gp.playShort(1);
                    gp.ui.addMessage("+1 Key!");
                    gp.player.inventory.add(gp.obj[gp.currentMap][id]);
                    hasKey++;
                    gp.obj[gp.currentMap][id] = null;
                }
            }
            else {
                switch (name) {
                    case "Key":

                        break;
                    case "Door":
                        if (hasKey > 0) {
                            gp.playShort(3);
                            gp.obj[gp.currentMap][id] = null;
                            hasKey--;
                        }
                        break;
                    case "Chest":
                        gp.playShort(4);
                        gp.stopClip();
                        gp.ui.gameFinished = true;
                        break;
                }
            }
        }
    }
    public int getDetected( String name){
        int worX = worldX + rectCollision.x;
        int worY = worldY + rectCollision.y;
        int worBottom = worY + rectCollision.height;
        int worRight = worX + rectCollision.width;

        switch (direction){
            case "up":worY -= 1;break;
            case"down" : worBottom +=1;break;
            case "left" : worX-=1;break;
            case "right" : worRight += 1; break;
        }

        int col = worX / gp.tileSize;
        int row = worY / gp.tileSize;
        int rowBottom = worBottom / gp.tileSize;
        int colRight = worRight / gp.tileSize;

        for(int i = 0; i<gp.obj[1].length ; i++){
            if(gp.obj[gp.currentMap][i]!=null) {
                int objCol = gp.obj[gp.currentMap][i].worldX / gp.tileSize;
                int objRow = gp.obj[gp.currentMap][i].worldY / gp.tileSize;
                if ((col == objCol && row == objRow)
                        || (objRow == rowBottom && col == objCol)
                        || (colRight == objCol && row == objRow)
                        || (colRight == objCol && rowBottom == objRow)
                        && gp.obj[gp.currentMap][i].name == name) {
                    return i;
                }
            }
        }
        return -1;
    }
    public void interactObstacle(){
        gp.play = gp.diaglogState;
        gp.ui.currentDialog = "You need a key to open";
    }
    public void interactNPC(int i) {
        if (gp.KeyH.enterPressed == true) {
            if (i != -1) {
                attackCanceled = true;
                gp.play = gp.diaglogState;
                gp.npcSetup[gp.currentMap][i].speak();
            }
        }
    }
    public void interactMonster(int i ){
        if(i!=-1){
            if(invincible == false && gp.monsterSetup[gp.currentMap][i].die == false){
                gp.playShort(6);
                int damage = gp.monsterSetup[gp.currentMap][i].attack - gp.player.defence;
                if(damage < 0 )damage = 0;
                gp.player.CurrentLife -= damage;
                checkGameOver();
                if(gp.player.CurrentLife < 0)gp.player.CurrentLife=0;
                invincible = true;
            }
        }
    }
    public void DamageMonster(int i , int attack){
        if(i!=-1){
            if(gp.monsterSetup[gp.currentMap][i].invincible == false) {
                int damage = attack - gp.monster[gp.currentMap][i].defence;
                if(damage < 0) damage = 0;
                gp.monster[gp.currentMap][i].CurrentLife -= damage;
                gp.ui.addMessage("-" + damage + " !");
                gp.monsterSetup[gp.currentMap][i].invincible = true;
                gp.monsterSetup[gp.currentMap][i].HpBarOn = true;
                if (gp.monster[gp.currentMap][i].CurrentLife <= 0) {
                    gp.monsterSetup[gp.currentMap][i].die = true;
                    gp.monsterSetup[gp.currentMap][i].setDefaultRectCollision(0,0,0,0);
                    gp.ui.addMessage("Killed " + gp.monster[gp.currentMap][i].name);
                    gp.ui.addMessage("EXP + " + gp.monster[gp.currentMap][i].exp);
                    gp.player.exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelPlayer(level);
                }
            }
            gp.monsterSetup[gp.currentMap][i].onPath = true;
        }
    }
    public void checkLevelPlayer(int x){
        if(gp.player.exp >= gp.player.nextLevel){
            gp.playShort(9);
            gp.player.nextLevel = gp.player.nextLevel * (x+1);
            gp.player.level++;
            gp.player.strength++;
            gp.player.dexterity++;
            gp.player.attack = gp.player.getAttack();
            gp.player.defence = gp.player.getDefence();
            gp.player.exp = 0;
            gp.player.maxLife+=2;
            gp.ui.currentDialog = "Level Up ! \n You are now level " + level ;
            gp.play = gp.diaglogState;
        }
    }
    public void checkGameOver(){
        if(gp.player.CurrentLife < 0){
            gp.playShort(11);
            gp.play = gp.GameOverState;
        }
    }
    public void reTryGame(){
        gp.player.worldX = 12*gp.tileSize;
        gp.player.worldY = 11* gp.tileSize;
        gp.player.setAttributes();
        maxLife = gp.player.maxLife;
        CurrentLife = gp.player.maxLife;
        level = gp.player.level;
        exp = gp.player.exp;
        strength = gp.player.strength;
        dexterity = gp.player.dexterity;
        coin = gp.player.coin;
        nextLevel = gp.player.nextLevel;

        for(int i = 0 ; i < gp.maxMap ; i++){
            for(int j = 0 ; j < gp.monsterSetup[i].length;j ++){
                if(gp.monsterSetup[i][j]!=null){
                    gp.monsterSetup[i][j].die = false;
                    gp.monsterSetup[i][j].alive = true;
                    gp.monsterSetup[i][j].onPath = false;
                    gp.monsterSetup[i][j].HpBarOn = false;
                    gp.monsterSetup[i][j].setDefaultRectCollision(0,0,48,48);
                    gp.monsterSetup[i][j].worldX = gp.monsterSetup[i][j].defaultWorX;
                    gp.monsterSetup[i][j].worldY = gp.monsterSetup[i][j].defaultWorY;
                    gp.monsterSetup[i][j].setDefault("Monster" , 12,12,7,0,2,1);
                    gp.monster[i][j].setAttributes();
                }
            }
        }
    }
    public void draw(Graphics2D g2){
        int x1 = 15,y1=gp.tileSize * 10;
        g2.setColor(Color.WHITE);
       //System.out.println(CollisionOn);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15f));
        g2.drawString("WorldX: " + gp.player.worldX + "  " + worldX , x1,y1);
        g2.drawString("WorldY: " + gp.player.worldY + "  " + worldY, x1,y1+15);
        g2.drawString("Col : " +  (gp.player.worldX+rectCollision.x)/gp.tileSize , x1,y1+30);
        g2.drawString("Row: " + (gp.player.worldY+rectCollision.y)/gp.tileSize, x1,y1+45);
        g2.drawString("Inventory : " + gp.player.inventory.size() + "  " + worldX, x1,y1+60);


        int tmpX = screenX;
        int tmpY = screenY;
        switch (direction) {
            case "up":
                if(attacking){
                    tmpY -= gp.tileSize;
                    if(foot == 1) img = attackUp1;
                    else img = attackUp2;
                }
                else {
                    if (foot == 1) img = up1;
                    else img = up2;
                }
                break;
            case "down":
                if(attacking){
                    if(foot == 1) img = attackDown1;
                    else img = attackDown2;
                }
                else {
                    if (foot == 1) img = down1;
                    else img = down2;
                }
                break;
            case "left":
                if(attacking){
                    tmpX -= gp.tileSize;
                    if(foot == 1) img = attackLeft1;
                    else img = attackLeft2;
                }
                else {
                    if (foot == 1) img = left1;
                    else img = left2;
                }
                break;
            case "right":
                if(attacking){
                    if(foot == 1) img = attackRight1;
                    else img = attackRight2;
                }
                else {
                    if (foot == 1) img = right1;
                    else img = right2;
                }
                break;
        }

        // move to the edge of the frame
        int x = tmpX;
        int y = tmpY;
        if( screenX > gp.player.worldX){
            if(attacking && direction == "left") {
                x = gp.player.worldX - gp.tileSize;
            }
            else x=gp.player.worldX;
        }
        if( screenY > gp.player.worldY){
            if(attacking && direction == "up") y = gp.player.worldY - gp.tileSize;
            else y = gp.player.worldY;
        }
        if(gp.WorldWidth - gp.player.worldX < (gp.screenWidth - screenX)){
            if(attacking && direction =="left") x = gp.player.worldX - (gp.WorldWidth - gp.screenWidth) - gp.tileSize;
            else x = gp.player.worldX - (gp.WorldWidth - gp.screenWidth);
        }
        if(gp.WorldHeight - gp.player.worldY < (gp.screenHeight - screenY)){
            if(attacking && direction == "up")y = gp.player.worldY - (gp.WorldHeight - gp.screenHeight) - gp.tileSize;
            else y = gp.player.worldY - (gp.WorldHeight - gp.screenHeight);
        }

        if(invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(img, x, y, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
    public void chooseWeapon(){
        if(gp.player.currentWeapon.typeObject == 1){
            if(gp.player.currentWeapon.name == "axe"){
                gp.entityGraphics.setAttackImageAxe();
            }
            if(gp.player.currentWeapon.name == "wood Sword"){
                gp.entityGraphics.setAttackImageSword();
            }
            gp.player.setAttack();
        }
    }
}
