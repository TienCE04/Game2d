package main.Monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Skeleton extends Entity{

    GamePanel gp;
    public MON_Skeleton(GamePanel gp){
        super(gp);

        this.gp = gp;
        type = 2;
        name = "Skeleton";
        speed = 2;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = setup("/monster/skeleton_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/skeleton_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/skeleton_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/skeleton_down2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/skeleton_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/skeleton_right2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/skeleton_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/skeleton_left2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        actionLockCounter ++;
        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1;// random a number from 1 to 100

            if(i <= 25 ){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}
