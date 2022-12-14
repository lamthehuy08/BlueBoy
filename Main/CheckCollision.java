package Main;

import EntitySetup.EntitySetup;
public class CheckCollision {
    GamePanel gp;
    public CheckCollision(GamePanel gp){
        this.gp = gp;
    }    // check Wall, water, tree,....
    public void CollisionCheckerTile(EntitySetup entity){
        int entityTopWorldY = entity.worldY + entity.rectCollision.y;
        int entityLeftWorldX= entity.worldX + entity.rectCollision.x;
        int entityRightWorldX = entity.worldX + entity.rectCollision.x + entity.rectCollision.width;
        int entityBottomWorldY = entity.worldY + entity.rectCollision.y + entity.rectCollision.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tile1,tile2;

        switch (entity.direction){
            case "up" :
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tile1 = gp.scenery.indexMap[gp.currentMap][entityTopRow][entityLeftCol];
            tile2 = gp.scenery.indexMap[gp.currentMap][entityTopRow][entityRightCol];
            if(gp.scenery.Tile[tile1].collision || gp.scenery.Tile[tile2].collision){
                entity.CollisionOn=true;
            }
            break;
            case "down" :
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tile1 = gp.scenery.indexMap[gp.currentMap][entityBottomRow][entityLeftCol];
                tile2 = gp.scenery.indexMap[gp.currentMap][entityBottomRow][entityRightCol];
                if(gp.scenery.Tile[tile1].collision || gp.scenery.Tile[tile2].collision){
                    entity.CollisionOn=true;
                }
                break;
            case "left" :
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tile1 = gp.scenery.indexMap[gp.currentMap][entityTopRow][entityLeftCol];
                tile2 = gp.scenery.indexMap[gp.currentMap][entityBottomRow][entityLeftCol];
                if(gp.scenery.Tile[tile1].collision || gp.scenery.Tile[tile2].collision){
                    entity.CollisionOn=true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tile1 = gp.scenery.indexMap[gp.currentMap][entityTopRow][entityRightCol];
                tile2 = gp.scenery.indexMap[gp.currentMap][entityBottomRow][entityRightCol];
                if(gp.scenery.Tile[tile1].collision || gp.scenery.Tile[tile2].collision){
                    entity.CollisionOn=true;
                }
                break;
        }


    }
    public int checkObject(EntitySetup entity, boolean player){
        int index = -2;
        for(int i =0 ;i<gp.obj[gp.currentMap].length ; i++){
            if(gp.obj[gp.currentMap][i] != null) {
                entity.rectCollision.x += entity.worldX;
                entity.rectCollision.y += entity.worldY;

                gp.obj[gp.currentMap][i].rectCollision.x += gp.obj[gp.currentMap][i].worldX;
                gp.obj[gp.currentMap][i].rectCollision.y += gp.obj[gp.currentMap][i].worldY;

                switch (entity.direction){
                    case "up" :
                        entity.rectCollision.y -= entity.speed;
                        break;
                    case "down" :
                        entity.rectCollision.y += entity.speed;
                        break;
                    case "left" :
                        entity.rectCollision.x -= entity.speed;
                        break;
                    case "right" :
                        entity.rectCollision.x += entity.speed;
                        break;
                }

                if(entity.rectCollision.intersects(gp.obj[gp.currentMap][i].rectCollision)){
                    if(gp.obj[gp.currentMap][i].collision){
                        entity.CollisionOn=true;
                    }
                    if(player){
                        index=i;
                    }
                }
                entity.rectCollision.x= entity.rectDefaultX;
                entity.rectCollision.y= entity.rectDefaultY;

                gp.obj[gp.currentMap][i].rectCollision.x = gp.obj[gp.currentMap][i].rectDefaultX;
                gp.obj[gp.currentMap][i].rectCollision.y = gp.obj[gp.currentMap][i].rectDefaultY;
            }
        }
        return index;
    }
    public int CheckEntity(EntitySetup entity , EntitySetup[] target ){
        int index = -1;
        for(int i =0 ;i<target.length ; i++){
            if(target[i] != null && target[i]!=entity) {
                entity.rectCollision.x += entity.worldX;
                entity.rectCollision.y += entity.worldY;

                target[i].rectCollision.x += target[i].worldX;
                target[i].rectCollision.y += target[i].worldY;

                switch (entity.direction){
                    case "up" :
                        entity.rectCollision.y -= entity.speed;
                        break;
                    case "down" :
                        entity.rectCollision.y += entity.speed;
                        break;
                    case "left" :
                        entity.rectCollision.x -= entity.speed;
                        break;
                    case "right" :
                        entity.rectCollision.x += entity.speed;
                        break;
                }

                if(entity.rectCollision.intersects(target[i].rectCollision)){
                    if(target[i].collision){
                        entity.CollisionOn=true;
                    }
                    index=i;
                }
                entity.rectCollision.x= entity.rectDefaultX;
                entity.rectCollision.y= entity.rectDefaultY;

                target[i].rectCollision.x = target[i].rectDefaultX;
                target[i].rectCollision.y = target[i].rectDefaultY;
            }
        }
        return index;
    }
    public int CheckSingleEntity(EntitySetup entity , EntitySetup target ){
        int index = -1;
            if(target != null && target!=entity) {
                entity.rectCollision.x += entity.worldX;
                entity.rectCollision.y += entity.worldY;

                target.rectCollision.x += target.worldX;
                target.rectCollision.y += target.worldY;

                switch (entity.direction){
                    case "up" :
                        entity.rectCollision.y -= entity.speed;
                        break;
                    case "down" :
                        entity.rectCollision.y += entity.speed;
                        break;
                    case "left" :
                        entity.rectCollision.x -= entity.speed;
                        break;
                    case "right" :
                        entity.rectCollision.x += entity.speed;
                        break;
                }

                if(entity.rectCollision.intersects(target.rectCollision)){
                    if(target.collision){
                        entity.CollisionOn=true;
                    }
                    index=1;
                }
                entity.rectCollision.x= entity.rectDefaultX;
                entity.rectCollision.y= entity.rectDefaultY;

                target.rectCollision.x = target.rectDefaultX;
                target.rectCollision.y = target.rectDefaultY;
            }
        return index;
    }
    public boolean checkPlayer(EntitySetup entity){
        boolean hit = false;
        entity.rectCollision.x += entity.worldX;
        entity.rectCollision.y += entity.worldY;

        gp.playerSetup.rectCollision.x +=gp.player.worldX;
        gp.playerSetup.rectCollision.y +=gp.player.worldY;

        switch (entity.direction){
            case"up":
                entity.rectCollision.y -= entity.speed;
                break;
            case"down":
                entity.rectCollision.y+= entity.speed;
                break;
            case"left":
                entity.rectCollision.x -= entity.speed;
                break;
            case"right":
                entity.rectCollision.x += entity.speed;
                break;
        }
        if(entity.rectCollision.intersects(gp.playerSetup.rectCollision)){
            entity.CollisionOn=true;
            hit = true;
        }

        entity.rectCollision.x = entity.rectDefaultX;
        entity.rectCollision.y = entity.rectDefaultY;

        gp.playerSetup.rectCollision.x=gp.playerSetup.rectDefaultX;
        gp.playerSetup.rectCollision.y = gp.playerSetup.rectDefaultY;
        return hit;
    }
}
