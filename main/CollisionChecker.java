package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.collisionBounds.x;
        int entityRightWorldX = entity.worldX + entity.collisionBounds.x + entity.collisionBounds.width;
        int entityTopWorldY = entity.worldY + entity.collisionBounds.y;
        int entityBottomWorldY = entity.worldY + entity.collisionBounds.y + entity.collisionBounds.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean isPlayer){
        int index = 999;

        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] != null){
                //get enttiy's solid area
                entity.collisionBounds.x = entity.worldX + entity.collisionBounds.x;
                entity.collisionBounds.y = entity.worldY+ entity.collisionBounds.y;
                //get obj solid area
                gp.obj[gp.currentMap][i].collisionBounds.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].collisionBounds.x;
                gp.obj[gp.currentMap][i].collisionBounds.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].collisionBounds.y;

                switch(entity.direction){
                    case"up":
                        entity.collisionBounds.y -= entity.speed;
                        break;
                    case"down":
                        entity.collisionBounds.y += entity.speed;
                        break;
                    case"left":
                        entity.collisionBounds.x -= entity.speed;
                        break;
                    case"right":
                        entity.collisionBounds.x += entity.speed;
                        break;
                }
                
                if(entity.collisionBounds.intersects(gp.obj[gp.currentMap][i].collisionBounds)){
                            if(gp.obj[gp.currentMap][i].collision == true){
                                    entity.collisionOn = true;
                                }
                                if(isPlayer == true){
                                index = i;
                            }
                        }
                entity.collisionBounds.x = entity.collisionBoundsDefaultX;
                entity.collisionBounds.y = entity.collisionBoundsDefaultY;
                gp.obj[gp.currentMap][i].collisionBounds.x = gp.obj[gp.currentMap][i].collisionBoundsDefaultX;
                gp.obj[gp.currentMap][i].collisionBounds.y = gp.obj[gp.currentMap][i].collisionBoundsDefaultY;

            }
        }

        return index;
    }

    //NPC OR MONSTER COLLISION
    public int checkEntity(Entity entity, Entity[][] target){
        int index = 999;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                //get enttiy's solid area
                entity.collisionBounds.x = entity.worldX + entity.collisionBounds.x;
                entity.collisionBounds.y = entity.worldY+ entity.collisionBounds.y;
                //get obj solid area
                target[gp.currentMap][i].collisionBounds.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].collisionBounds.x;
                target[gp.currentMap][i].collisionBounds.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].collisionBounds.y;

                switch(entity.direction){
                    case"up":
                        entity.collisionBounds.y -= entity.speed;
                        break;
                    case"down":
                        entity.collisionBounds.y += entity.speed;
                        break;
                    case"left":
                        entity.collisionBounds.x -= entity.speed;
                        break;
                    case"right":
                        entity.collisionBounds.x += entity.speed;
                        break;
                }

                if(entity.collisionBounds.intersects(target[gp.currentMap][i].collisionBounds)){
                    if(target[gp.currentMap][i] != entity){
                        entity.collisionOn = true;
                        index=i;
                    }
                }

                entity.collisionBounds.x = entity.collisionBoundsDefaultX;
                entity.collisionBounds.y = entity.collisionBoundsDefaultY;
                target[gp.currentMap][i].collisionBounds.x = target[gp.currentMap][i].collisionBoundsDefaultX;
                target[gp.currentMap][i].collisionBounds.y = target[gp.currentMap][i].collisionBoundsDefaultY;

            }
        }

        return index; 
    }

    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;
       //get entity's solid area
       entity.collisionBounds.x = entity.worldX + entity.collisionBounds.x;
       entity.collisionBounds.y = entity.worldY+ entity.collisionBounds.y;
       //get obj solid area
       gp.player.collisionBounds.x = gp.player.worldX + gp.player.collisionBounds.x;
       gp.player.collisionBounds.y = gp.player.worldY + gp.player.collisionBounds.y;

       switch(entity.direction){
           case"up":
               entity.collisionBounds.y -= entity.speed;
               break;
           case"down":
               entity.collisionBounds.y += entity.speed;
               break;
           case"left":
               entity.collisionBounds.x -= entity.speed;
               break;
           case"right":
               entity.collisionBounds.x += entity.speed;
               break;
       }

        if(entity.collisionBounds.intersects(gp.player.collisionBounds)){
            entity.collisionOn = true;
            contactPlayer = true;
        }

       entity.collisionBounds.x = entity.collisionBoundsDefaultX;
       entity.collisionBounds.y = entity.collisionBoundsDefaultY;
       gp.player.collisionBounds.x = gp.player.collisionBoundsDefaultX;
       gp.player.collisionBounds.y = gp.player.collisionBoundsDefaultY; 
        return contactPlayer;
    }
}
