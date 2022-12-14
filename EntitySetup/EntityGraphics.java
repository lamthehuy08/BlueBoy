package EntitySetup;

import Main.GamePanel;

import java.awt.image.BufferedImage;

public class EntityGraphics extends EntitySetup {
    GamePanel gp;
    public BufferedImage topbar;
    public EntityGraphics(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }
    public void setGraphics(){
        setImagePlayer();
        setImageMonster();
        setImageNPC();
        setImageObj();
        setEffect();
    }
    public void setImagePlayer(){
        gp.playerSetup.up1 = setup("boy_up_1",gp.tileSize,gp.tileSize);
        gp.playerSetup.up2 = setup("boy_up_2",gp.tileSize,gp.tileSize);
        gp.playerSetup.down1 = setup("boy_down_1",gp.tileSize,gp.tileSize);
        gp.playerSetup.down2 = setup("boy_down_2",gp.tileSize,gp.tileSize);
        gp.playerSetup.left1 = setup("boy_left_1",gp.tileSize,gp.tileSize);
        gp.playerSetup.left2 = setup("boy_left_2",gp.tileSize,gp.tileSize);
        gp.playerSetup.right1 = setup("boy_right_1",gp.tileSize,gp.tileSize);
        gp.playerSetup.right2 = setup("boy_right_2",gp.tileSize,gp.tileSize);

        setAttackImageSword();
    }
    public void setImageMonster(){
        int lengMonster = gp.monsterSetup[1].length;
        for(int i = 0 ; i<lengMonster ; i++ ) {
            if (gp.monsterSetup[1][i] != null) {
                gp.monsterSetup[1][i].up1 = setup("greenslime_down_1", gp.tileSize, gp.tileSize);
                gp.monsterSetup[1][i].up2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[1][i].down1 = setup("greenslime_down_1", gp.tileSize, gp.tileSize);
                gp.monsterSetup[1][i].down2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[1][i].right1 = setup("greenslime_down_1", gp.tileSize, gp.tileSize);
                gp.monsterSetup[1][i].right2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[1][i].left1 = setup("greenslime_down_1", gp.tileSize, gp.tileSize);
                gp.monsterSetup[1][i].left2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
            }
            if (gp.monsterSetup[2][i] != null) {
                gp.monsterSetup[2][i].up1 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[2][i].up2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[2][i].down1 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[2][i].down2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[2][i].right1 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[2][i].right2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[2][i].left1 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
                gp.monsterSetup[2][i].left2 = setup("greenslime_down_2", gp.tileSize, gp.tileSize);
            }
        }
    }
    public void setImageNPC(){
        int lengNpc = gp.npcSetup[1].length;
        for(int i = 0 ; i<lengNpc ; i++ ) {
            if (gp.npcSetup[1][i] != null) {
                gp.npcSetup[1][i].up1 = setup("oldman_up_1",gp.tileSize,gp.tileSize);
                gp.npcSetup[1][i].up2 = setup("oldman_up_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[1][i].down1 = setup("oldman_down_1",gp.tileSize,gp.tileSize);
                gp.npcSetup[1][i].down2 = setup("oldman_down_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[1][i].left1 = setup("oldman_left_1",gp.tileSize,gp.tileSize);
                gp.npcSetup[1][i].left2 = setup("oldman_left_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[1][i].right1 = setup("oldman_right_1",gp.tileSize,gp.tileSize);
                gp.npcSetup[1][i].right2 = setup("oldman_right_2",gp.tileSize,gp.tileSize);
            }
            if (gp.npcSetup[2][i] != null) {
                gp.npcSetup[2][i].up1 = setup("oldman_up_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[2][i].up2 = setup("oldman_up_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[2][i].down1 = setup("oldman_down_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[2][i].down2 = setup("oldman_down_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[2][i].left1 = setup("oldman_left_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[2][i].left2 = setup("oldman_left_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[2][i].right1 = setup("oldman_right_2",gp.tileSize,gp.tileSize);
                gp.npcSetup[2][i].right2 = setup("oldman_right_2",gp.tileSize,gp.tileSize);
            }
        }
    }
    public void setImageObj(){
        for(int i = 0 ;i < gp.maxMap ;i++){
            for(int j = 0 ;j < gp.obj[1].length;j++){
                if(gp.obj[i][j] != null){
                    String name = gp.obj[i][j].name;
                    switch (name){
                        case "Door" :
                            gp.obj[i][j].down1 = setup("Door" , gp.tileSize,gp.tileSize);
                            break;
                        case "Boots":
                            gp.obj[i][j].down1 = setup("boots" , gp.tileSize,gp.tileSize);
                            break;
                        case "axe":
                            gp.obj[i][j].down1 = setup("axe" , gp.tileSize,gp.tileSize);
                            break;
                        case "Chest":
                            gp.obj[i][j].down1 = setup("chest" , gp.tileSize,gp.tileSize);
                            break;
                        case "coin bronze":
                            gp.obj[i][j].down1 = setup("coin_bronze" , gp.tileSize,gp.tileSize);
                            break;
                        case "mana":
                            gp.obj[i][j].down1 = setup("mpp" , gp.tileSize,gp.tileSize);
                            break;
                        case "red potion":
                            gp.obj[i][j].down1 = setup("potion_red" , gp.tileSize,gp.tileSize);
                            break;
                        case "hut":
                            gp.obj[i][j].down1 = setup("hut" , gp.tileSize,gp.tileSize);
                            break;
                        case "Black_hole":
                            gp.obj[i][j].down1 = setup("right" , gp.tileSize,gp.tileSize);
                            break;
                        case "Key":
                            gp.obj[i][j].down1 = setup("key" , gp.tileSize,gp.tileSize);
                            break;
                    }
                }
            }
        }
    }
    public void setAttackImageSword(){
        gp.playerSetup.attackUp1 = setup("boy_attack_up_1" , gp.tileSize , gp.tileSize*2);
        gp.playerSetup.attackUp2 = setup("boy_attack_up_2" , gp.tileSize , gp.tileSize * 2);
        gp.playerSetup.attackDown1 = setup("boy_attack_down_1" , gp.tileSize , gp.tileSize*2);
        gp.playerSetup.attackDown2 = setup("boy_attack_down_2" , gp.tileSize , gp.tileSize*2);
        gp.playerSetup.attackLeft1= setup("boy_attack_left_1" , gp.tileSize*2 , gp.tileSize);
        gp.playerSetup.attackLeft2 = setup("boy_attack_left_2" , gp.tileSize*2 , gp.tileSize);
        gp.playerSetup.attackRight1 = setup("boy_attack_right_1" , gp.tileSize*2 , gp.tileSize);
        gp.playerSetup.attackRight2 = setup("boy_attack_right_2" , gp.tileSize*2 , gp.tileSize);
    }
    public void setAttackImageAxe(){
        gp.playerSetup.attackUp1 = setup("boy_axe_up_1" , gp.tileSize , gp.tileSize*2);
        gp.playerSetup.attackUp2 = setup("boy_axe_up_2" , gp.tileSize , gp.tileSize*2);
        gp.playerSetup.attackDown1 = setup("boy_axe_down_1" , gp.tileSize , gp.tileSize*2);
        gp.playerSetup.attackDown2 = setup("boy_axe_down_2" , gp.tileSize , gp.tileSize*2);
        gp.playerSetup.attackLeft1 = setup("boy_axe_left_1" , gp.tileSize*2 , gp.tileSize);
        gp.playerSetup.attackLeft2 = setup("boy_axe_left_2" , gp.tileSize*2 , gp.tileSize);
        gp.playerSetup.attackRight1 = setup("boy_axe_right_1" , gp.tileSize*2 , gp.tileSize);
        gp.playerSetup.attackRight2 = setup("boy_axe_right_2" , gp.tileSize*2 , gp.tileSize);
    }
    public void setEffect() {
        topbar = setup("topbar", 250, 80);
    }
}
