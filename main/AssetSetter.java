package main;

import entity.MON_BlueSlime;
import entity.NPC_Bone;
import entity.NPC_OldMan;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        int mapNum = 0;
        gp.obj[mapNum][0] = new OBJ_Heart();
        gp.obj[mapNum][0].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 46 * gp.tileSize;
        
        gp.obj[mapNum][1] = new OBJ_Heart();
        gp.obj[mapNum][1].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 20 * gp.tileSize;
        
        gp.obj[mapNum][2] = new OBJ_Chest();
        gp.obj[mapNum][2].worldX = 42 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 42 * gp.tileSize;
        
        gp.obj[mapNum][3] = new OBJ_Bones();
        gp.obj[mapNum][3].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 37 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Bones();
        gp.obj[mapNum][4].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 32 * gp.tileSize;

        gp.obj[mapNum][5] = new OBJ_Door();
        gp.obj[mapNum][5].worldX = 40 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 51 * gp.tileSize;


        mapNum = 1;
        gp.obj[mapNum][1] = new OBJ_Door();
        gp.obj[mapNum][1].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 55 * gp.tileSize;

    }

    public void setNpc(){
        int mapNum = 0;
        gp.npc[mapNum][0] = new NPC_OldMan(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 29;
        gp.npc[mapNum][0].worldY = gp.tileSize * 9;

        mapNum = 1;
        gp.npc[mapNum][1] = new NPC_Bone(gp);
        gp.npc[mapNum][1].worldX = gp.tileSize * 31;
        gp.npc[mapNum][1].worldY = gp.tileSize * 12;
    }
    
    public void setMonster(){
        int mapNum = 0;
        gp.monster[mapNum][0] = new MON_BlueSlime(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 30;
        gp.monster[mapNum][0].worldY = gp.tileSize * 13;

        gp.monster[mapNum][1] = new MON_BlueSlime(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize * 45;
        gp.monster[mapNum][1].worldY = gp.tileSize * 18;

        gp.monster[mapNum][2]= new MON_BlueSlime(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize * 43;
        gp.monster[mapNum][2].worldY = gp.tileSize * 19;

        gp.monster[mapNum][3] = new MON_BlueSlime(gp);
        gp.monster[mapNum][3].worldX = gp.tileSize * 21;
        gp.monster[mapNum][3].worldY = gp.tileSize * 9;

        gp.monster[mapNum][4] = new MON_BlueSlime(gp);
        gp.monster[mapNum][4].worldX = gp.tileSize * 23;
        gp.monster[mapNum][4].worldY = gp.tileSize * 10;

        gp.monster[mapNum][5] = new MON_BlueSlime(gp);
        gp.monster[mapNum][5].worldX = gp.tileSize * 29;
        gp.monster[mapNum][5].worldY = gp.tileSize * 12;

        gp.monster[mapNum][6] = new MON_BlueSlime(gp);
        gp.monster[mapNum][6].worldX = gp.tileSize * 9;
        gp.monster[mapNum][6].worldY = gp.tileSize * 22;

        gp.monster[mapNum][7] = new MON_BlueSlime(gp);
        gp.monster[mapNum][7].worldX = gp.tileSize * 10;
        gp.monster[mapNum][7].worldY = gp.tileSize * 23;

        gp.monster[mapNum][8] = new MON_BlueSlime(gp);
        gp.monster[mapNum][8].worldX = gp.tileSize * 23;
        gp.monster[mapNum][8].worldY = gp.tileSize * 23;

        gp.monster[mapNum][9] = new MON_BlueSlime(gp);
        gp.monster[mapNum][9].worldX = gp.tileSize * 12;
        gp.monster[mapNum][9].worldY = gp.tileSize * 33;

        gp.monster[mapNum][10] = new MON_BlueSlime(gp);
        gp.monster[mapNum][10].worldX = gp.tileSize * 13;
        gp.monster[mapNum][10].worldY = gp.tileSize * 33;

        gp.monster[mapNum][11] = new MON_BlueSlime(gp);
        gp.monster[mapNum][11].worldX = gp.tileSize * 12;
        gp.monster[mapNum][11].worldY = gp.tileSize * 46;

        gp.monster[mapNum][12] = new MON_BlueSlime(gp);
        gp.monster[mapNum][12].worldX = gp.tileSize * 14;
        gp.monster[mapNum][12].worldY = gp.tileSize * 48;

        gp.monster[mapNum][13] = new MON_BlueSlime(gp);
        gp.monster[mapNum][13].worldX = gp.tileSize * 13;
        gp.monster[mapNum][13].worldY = gp.tileSize * 47;

        gp.monster[mapNum][14] = new MON_BlueSlime(gp);
        gp.monster[mapNum][14].worldX = gp.tileSize * 23;
        gp.monster[mapNum][14].worldY = gp.tileSize * 46;

        gp.monster[mapNum][15] = new MON_BlueSlime(gp);
        gp.monster[mapNum][15].worldX = gp.tileSize * 30;
        gp.monster[mapNum][15].worldY = gp.tileSize * 53;

        gp.monster[mapNum][16] = new MON_BlueSlime(gp);
        gp.monster[mapNum][16].worldX = gp.tileSize * 33;
        gp.monster[mapNum][16].worldY = gp.tileSize * 53;

        gp.monster[mapNum][17] = new MON_BlueSlime(gp);
        gp.monster[mapNum][17].worldX = gp.tileSize * 42;
        gp.monster[mapNum][17].worldY = gp.tileSize * 43;

        gp.monster[mapNum][18] = new MON_BlueSlime(gp);
        gp.monster[mapNum][18].worldX = gp.tileSize * 45;
        gp.monster[mapNum][18].worldY = gp.tileSize * 42;

    }

}
