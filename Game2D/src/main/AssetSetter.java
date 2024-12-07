package main;

import entity.NPC_OldMan;
import main.Monster.MON_Skeleton;
import object.OBJ_Door;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
        
    }
    public void setObject(){
        
        // gp.obj[0] = new OBJ_Door(gp);
        // gp.obj[0].worldX =  gp.tileSize *3;
        // gp.obj[0].worldY =  gp.tileSize *13;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX =  gp.tileSize *21;
        gp.obj[1].worldY =  gp.tileSize *6;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

    }

    public void setMonster(){
        // TODO: Set vị trí cho quái
        gp.monster[0] = new MON_Skeleton(gp);
        gp.monster[0].worldX = gp.tileSize *3;
        gp.monster[0].worldY = gp.tileSize *22;

        gp.monster[1] = new MON_Skeleton(gp);
        gp.monster[1].worldX = gp.tileSize * 2;
        gp.monster[1].worldY = gp.tileSize *24;

        gp.monster[2] = new MON_Skeleton(gp);
        gp.monster[2].worldX = gp.tileSize * 9;
        gp.monster[2].worldY = gp.tileSize * 47;
        
        gp.monster[3] = new MON_Skeleton(gp);
        gp.monster[3].worldX = gp.tileSize * 3;
        gp.monster[3].worldY = gp.tileSize * 43;
        
        gp.monster[4] = new MON_Skeleton(gp);
        gp.monster[4].worldX = gp.tileSize * 23;
        gp.monster[4].worldY = gp.tileSize * 47;
        
        gp.monster[5] = new MON_Skeleton(gp);
        gp.monster[5].worldX = gp.tileSize * 28;
        gp.monster[5].worldY = gp.tileSize * 47;
        
        gp.monster[6] = new MON_Skeleton(gp);
        gp.monster[6].worldX = gp.tileSize * 35;
        gp.monster[6].worldY = gp.tileSize * 47;
                
        gp.monster[7] = new MON_Skeleton(gp);
        gp.monster[7].worldX = gp.tileSize * 51;
        gp.monster[7].worldY = gp.tileSize * 43;
                
        gp.monster[8] = new MON_Skeleton(gp);
        gp.monster[8].worldX = gp.tileSize * 66;
        gp.monster[8].worldY = gp.tileSize * 46;
                
        gp.monster[9] = new MON_Skeleton(gp);
        gp.monster[9].worldX = gp.tileSize * 20;
        gp.monster[9].worldY = gp.tileSize * 34;
                
        gp.monster[10] = new MON_Skeleton(gp);
        gp.monster[10].worldX = gp.tileSize * 27;
        gp.monster[10].worldY = gp.tileSize * 39;
                
        gp.monster[11] = new MON_Skeleton(gp);
        gp.monster[11].worldX = gp.tileSize * 37;
        gp.monster[11].worldY = gp.tileSize * 41;
                
        gp.monster[12] = new MON_Skeleton(gp);
        gp.monster[12].worldX = gp.tileSize * 45;
        gp.monster[12].worldY = gp.tileSize * 38;
                
        gp.monster[13] = new MON_Skeleton(gp);
        gp.monster[13].worldX = gp.tileSize * 55;
        gp.monster[13].worldY = gp.tileSize * 38;
                
        gp.monster[14] = new MON_Skeleton(gp);
        gp.monster[14].worldX = gp.tileSize * 67;
        gp.monster[14].worldY = gp.tileSize * 39;
        
    }
}
