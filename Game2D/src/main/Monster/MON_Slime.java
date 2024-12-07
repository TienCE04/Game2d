package main.Monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Slime extends Entity{

    GamePanel gp;
    public MON_Slime(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        name = "Smile";
        speed = 2;
        maxLife = 4;
        life = maxLife;
    }

    public void getImage(){

        up1 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
        up3 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
        up4 = setup("/monster/slime1", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
        down4 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
        
        right1 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
        right4 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
    
        left1 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
        left4 = setup("/monster/slime2", gp.tileSize, gp.tileSize);
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
