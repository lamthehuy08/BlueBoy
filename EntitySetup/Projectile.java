package EntitySetup;

import Main.GamePanel;

import java.awt.*;


public class Projectile extends EntitySetup {
    public GamePanel gp;
    public int costMana;
    public Projectile(GamePanel gp) {
         super(gp);
         this.gp = gp;
    }
    public void set(int worldX , int worldY , String direction){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.CurrentLife = this.maxLife;
    }
    public void update(){
         switch(direction){
             case "up" : worldY -= speed;break;
             case "down" : worldY += speed;break;
             case "left" : worldX -= speed;break;
             case "right" : worldX += speed;break;
         }

         setAttackProjectile();

         CurrentLife--;
         if(CurrentLife <= 0){
             alive = false;
         }
         keyCount++;
         if(keyCount > 15){
             if(foot == 1)foot = 2;
             else foot = 1;
             keyCount = 0;
         }
    }
    public void GeneratorParticle(EntitySetup entity , Color color){
        int worldX = entity.worldX +20;
        int worldY = entity.worldY + 20;
        Particle p1 = new Particle(gp,worldX,worldY,1,1,color);
        Particle p2 = new Particle(gp,worldX,worldY,1,-1,color);
        Particle p3 = new Particle(gp,worldX,worldY,-1,1,color);
        Particle p4 = new Particle(gp,worldX,worldY,-1,-1,color);
        gp.particles.add(p1);
        gp.particles.add(p2);
        gp.particles.add(p3);
        gp.particles.add(p4);
    }
    public void setAttackProjectile(){
        if(name == "fireball") {
            int monster = gp.cChecker.CheckEntity(this, gp.monsterSetup[gp.currentMap]);
            gp.playerSetup.DamageMonster(monster, attack);
            if (monster != -1) {
                GeneratorParticle(gp.monsterSetup[gp.currentMap][monster] , this.getColor());
                alive = false;
            }
            for(int i = 0 ; i < gp.monsterSetup[1].length ; i++){
                if(gp.monsterSetup[gp.currentMap][i]!=null && gp.monsterSetup[gp.currentMap][i].projectile.alive == true){
                    int hit = gp.cChecker.CheckSingleEntity(this,gp.monsterSetup[gp.currentMap][i].projectile);
                    if(hit != -1){
                        GeneratorParticle(this,this.getColor());
                        GeneratorParticle(gp.monsterSetup[gp.currentMap][i].projectile,gp.monsterSetup[gp.currentMap][i].projectile.getColor());
                        gp.monsterSetup[gp.currentMap][i].projectile.alive=false;
                        this.alive = false;
                    }
                }
            }
        }
        if(name == "rock"){
            boolean hit = gp.cChecker.checkPlayer(this);
                if(hit == true && gp.playerSetup.invincible == false){
                    alive = false;
                    GeneratorParticle(this,this.getColor());
                    int damage = attack - gp.player.defence;
                    gp.playShort(6);
                    if(damage<0)damage = 0;
                    gp.player.CurrentLife -= damage;
                    gp.playerSetup.checkGameOver();
                    gp.playerSetup.invincible = true;
                }
            }
        }
}

