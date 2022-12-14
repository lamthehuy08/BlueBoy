package Main;

import EntitySetup.MonsterSlimSetup;
import EntitySetup.npcSetup;
import Object.*;
import entity.Monster_slim;
import entity.NPC;

public class AssetSetter {
    GamePanel gp ;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){

        gp.obj[1][0] = new obj(gp);
        gp.obj[1][0].setObj("Key" , 4);
        gp.obj[1][0].worldX = 14 * gp.tileSize;
        gp.obj[1][0].worldY = 10 * gp.tileSize;
        gp.obj[1][0].setDefaultRectCollision(0,0,48,48);
/*
        int i = 0;

        gp.obj[1][i] = new obj_Key(gp);    // i = 0;
        gp.obj[1][i].worldX = 14 * gp.tileSize;
        gp.obj[1][i].worldY = 10 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_Key(gp);    // i = 2;
        gp.obj[1][i].worldX = 14 * gp.tileSize;
        gp.obj[1][i].worldY = 4 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;
        gp.obj[1][i] = new obj_Key(gp);    // i = 2;
        gp.obj[1][i].worldX = 10 * gp.tileSize;
        gp.obj[1][i].worldY = 1 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_Door(gp);   // i = 4
        gp.obj[1][i].worldX = 8 * gp.tileSize;
        gp.obj[1][i].worldY = 9 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,16,48,32);
        gp.obj[1][i].collision = true;
        i++;

        gp.obj[1][i] = new obj_Door(gp);   // i = 4
        gp.obj[1][i].worldX = 8 * gp.tileSize;
        gp.obj[1][i].worldY = 21 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,16,48,32);
        gp.obj[1][i].collision = true;
        i++;

        gp.obj[1][i] = new obj_Door(gp);   // i = 4
        gp.obj[1][i].worldX = 26 * gp.tileSize;
        gp.obj[1][i].worldY = 6 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,16,48,32);
        gp.obj[1][i].collision = true;
        i++;


        gp.obj[1][i] = new obj_Boots(gp);     // i = 5
        gp.obj[1][i].worldX = 1 * gp.tileSize;
        gp.obj[1][i].worldY = 4 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_axe(gp);         // i = 6
        gp.obj[1][i].worldX = 9 * gp.tileSize;
        gp.obj[1][i].worldY = 11 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_potion_red(gp);     // i = 7
        gp.obj[1][i].worldX = 9 * gp.tileSize;
        gp.obj[1][i].worldY = 13 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_potion_red(gp);     // i = 8
        gp.obj[1][i].worldX = 3 * gp.tileSize;
        gp.obj[1][i].worldY = 13 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_Heart(gp);         // i = 9
        gp.obj[1][i].worldX = 5 * gp.tileSize;
        gp.obj[1][i].worldY = 4 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_coin_bronze(gp);      // i = 10
        gp.obj[1][i].worldX = 13 * gp.tileSize;
        gp.obj[1][i].worldY = 17 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_coin_bronze(gp);      // i = 11
        gp.obj[1][i].worldX = 6 * gp.tileSize;
        gp.obj[1][i].worldY = 13 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new obj_mana(gp);      // i = 12
        gp.obj[1][i].worldX = 4 * gp.tileSize;
        gp.obj[1][i].worldY = 13 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[1][i] = new Shield_blue(gp);      // i = 13
        gp.obj[1][i].worldX = 4 * gp.tileSize;
        gp.obj[1][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[1][i] = new hut(gp);      // i = 14
        gp.obj[1][i].worldX = 29 * gp.tileSize;
        gp.obj[1][i].worldY = 2 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);

        gp.obj[2][i] = new hut(gp);
        gp.obj[2][i].worldX = 2 * gp.tileSize;
        gp.obj[2][i].worldY = 13 * gp.tileSize;
        gp.obj[2][i].setDefaultRectCollision(0,0,48,48);
        i++;

        gp.obj[2][i] = new hut(gp);
        gp.obj[2][i].worldX = 25 * gp.tileSize;
        gp.obj[2][i].worldY = 29 * gp.tileSize;
        gp.obj[2][i].setDefaultRectCollision(0,0,48,48);

        gp.obj[3][i] = new Black_hole(gp);
        gp.obj[3][i].worldX = 22 * gp.tileSize;
        gp.obj[3][i].worldY = 18 * gp.tileSize;
        gp.obj[3][i].setDefaultRectCollision(0,0,48,48);
        i++;
        gp.obj[3][i] = new obj_Chest(gp);
        gp.obj[3][i].worldX = 41 * gp.tileSize;
        gp.obj[3][i].worldY = 20 * gp.tileSize;
        gp.obj[3][i].setDefaultRectCollision(0,0,48,48);
        i++;
        gp.obj[1][i] = new Black_hole(gp);
        gp.obj[1][i].worldX = 8 * gp.tileSize;
        gp.obj[1][i].worldY = 1 * gp.tileSize;
        gp.obj[1][i].setDefaultRectCollision(0,0,48,48);
        i++;
*/
    }
    public void setNpc(){
        gp.npc[1][0] = new NPC();
        gp.npc[1][0].worldX = 7 * gp.tileSize;
        gp.npc[1][0].worldY = 3 * gp.tileSize;
        gp.npcSetup[1][0] = new npcSetup(gp , gp.npc[1][0]);
        gp.npcSetup[1][0].collision = true;

        gp.npc[1][1] = new NPC();
        gp.npc[1][1].worldX = 7 * gp.tileSize;
        gp.npc[1][1].worldY = 8 * gp.tileSize;
        gp.npcSetup[1][1] = new npcSetup(gp,gp.npc[1][1]);
        gp.npcSetup[1][1].collision = true;

        gp.npc[2][0] = new NPC();
        gp.npc[2][0].worldX = 9 * gp.tileSize;
        gp.npc[2][0].worldY = 6 * gp.tileSize;
        gp.npcSetup[2][0] = new npcSetup(gp , gp.npc[2][0]);
        gp.npcSetup[2][0].collision = true;
        
    }
    public void setMonster() {
        gp.monster[1][0] = new Monster_slim();
        gp.monster[1][0].worldX = 5 * gp.tileSize;
        gp.monster[1][0].worldY = 13 * gp.tileSize;
        gp.monsterSetup[1][0] = new MonsterSlimSetup(gp, gp.monster[1][0]);
        gp.monsterSetup[1][0].collision = true;

        gp.monster[1][1] = new Monster_slim();
        gp.monster[1][1].worldX = 5 * gp.tileSize;
        gp.monster[1][1].worldY = 14 * gp.tileSize;
        gp.monsterSetup[1][1] = new MonsterSlimSetup(gp, gp.monster[1][1]);
        gp.monsterSetup[1][1].collision = true;

        gp.monster[1][2] = new Monster_slim();
        gp.monster[1][2].worldX = 10 * gp.tileSize;
        gp.monster[1][2].worldY = 13 * gp.tileSize;
        gp.monsterSetup[1][2] = new MonsterSlimSetup(gp , gp.monster[1][2]);
        gp.monsterSetup[1][2].collision = true;

        gp.monster[1][3] = new Monster_slim();
        gp.monster[1][3].worldX = 10 * gp.tileSize;
        gp.monster[1][3].worldY = 14 * gp.tileSize;
        gp.monsterSetup[1][3] = new MonsterSlimSetup(gp , gp.monster[1][3]);
        gp.monsterSetup[1][3].collision = true;

        gp.monster[1][1] = new Monster_slim();
        gp.monster[1][1].worldX = 3 * gp.tileSize;
        gp.monster[1][1].worldY = 9 * gp.tileSize;
        gp.monsterSetup[1][1] = new MonsterSlimSetup(gp, gp.monster[1][1]);
        gp.monsterSetup[1][1].collision = true;

        gp.monster[2][2] = new Monster_slim();
        gp.monster[2][2].worldX = 6 * gp.tileSize;
        gp.monster[2][2].worldY = 8 * gp.tileSize;
        gp.monsterSetup[2][2] = new MonsterSlimSetup(gp , gp.monster[2][2]);
        gp.monsterSetup[2][2].collision = true;

        gp.monster[2][3] = new Monster_slim();
        gp.monster[2][3].worldX = 22 * gp.tileSize;
        gp.monster[2][3].worldY = 22 * gp.tileSize;
        gp.monsterSetup[2][3] = new MonsterSlimSetup(gp , gp.monster[2][3]);
        gp.monsterSetup[2][3].collision = true;
    }

}
