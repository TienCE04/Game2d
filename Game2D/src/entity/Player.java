package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    // public int hasKey = 0;
    public int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 30;
        solidArea.width = 30;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;// 1 life = 1/2 heart
    }
    public void getPlayerImage(){
        up1 = setup("/player/runsau1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/runsau2", gp.tileSize, gp.tileSize);
        up3 = setup("/player/runsau3", gp.tileSize, gp.tileSize);
        up4 = setup("/player/runsau4", gp.tileSize, gp.tileSize);

        down1 = setup("/player/runtruoc1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/runtruoc2", gp.tileSize, gp.tileSize);
        down3 = setup("/player/runtruoc3", gp.tileSize, gp.tileSize);
        down4 = setup("/player/runtruoc4", gp.tileSize, gp.tileSize);

        right1 = setup("/player/runright1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/runright2", gp.tileSize, gp.tileSize);
        right3 = setup("/player/runright3", gp.tileSize, gp.tileSize);
        right4 = setup("/player/runright4", gp.tileSize, gp.tileSize);

        left1 = setup("/player/runleft1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/runleft2", gp.tileSize, gp.tileSize);
        left3 = setup("/player/runleft3", gp.tileSize, gp.tileSize);
        left4 = setup("/player/runleft4", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/attack_up1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/attack_up2", gp.tileSize, gp.tileSize*2);
        attackUp3 = setup("/player/attack_up3", gp.tileSize, gp.tileSize*2);
        attackUp4 = setup("/player/attack_up4", gp.tileSize, gp.tileSize*2);
        attackUp5 = setup("/player/attack_up5", gp.tileSize, gp.tileSize*2);

        attackDown1 = setup("/player/attack_down1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/attack_down2", gp.tileSize, gp.tileSize*2);
        attackDown3 = setup("/player/attack_down3", gp.tileSize, gp.tileSize*2);
        attackDown4 = setup("/player/attack_down4", gp.tileSize, gp.tileSize*2);
        attackDown5 = setup("/player/attack_down5", gp.tileSize, gp.tileSize*2);

        attackRight1 = setup("/player/attack_right1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/attack_right2", gp.tileSize*2, gp.tileSize);
        attackRight3 = setup("/player/attack_right3", gp.tileSize*2, gp.tileSize);
        attackRight4 = setup("/player/attack_right4", gp.tileSize*2, gp.tileSize);
        attackRight5 = setup("/player/attack_right5", gp.tileSize*2, gp.tileSize);

        attackLeft1 = setup("/player/attack_left1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/attack_left2", gp.tileSize*2, gp.tileSize);
        attackLeft3 = setup("/player/attack_left3", gp.tileSize*2, gp.tileSize);
        attackLeft4 = setup("/player/attack_left4", gp.tileSize*2, gp.tileSize);
        attackLeft5 = setup("/player/attack_left5", gp.tileSize*2, gp.tileSize);
    }
    public void update(){

        if(attacking == true){
            attacking();
        }

        else if(keyH.upPressed == true || keyH.downPressed == true 
            || keyH.rightPressed == true || keyH.leftPressed == true
            || keyH.enterPressed == true){
            
                if(keyH.upPressed == true && keyH.rightPressed == true){
                direction = "up_right";
            }
            else if(keyH.upPressed == true && keyH.leftPressed == true){
                direction = "up_left";
            }
            else if(keyH.downPressed == true && keyH.rightPressed == true){
                direction = "down_right";
            }
            else if(keyH.downPressed == true && keyH.leftPressed == true){
                direction = "down_left";
            }

            else if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            if(collisionOn == false && keyH.enterPressed == false){
                switch (direction) {
                    case "up": worldY -= speed ; break;
                    case "down": worldY += speed; break;
                    case "right": worldX += speed; break;
                    case "left": worldX -= speed; break;
                    case "up_right":
                        worldY -= (int) (speed / Math.sqrt(2));
                        worldX += (int) (speed / Math.sqrt(2));
                        break;
                    case "up_left":
                        worldY -= (int) (speed / Math.sqrt(2));
                        worldX -= (int) (speed / Math.sqrt(2));
                        break;
                    case "down_right":
                        worldY += (int) (speed / Math.sqrt(2));
                        worldX += (int) (speed / Math.sqrt(2));
                        break;
                    case "down_left":
                        worldY += (int) (speed / Math.sqrt(2));
                        worldX -= (int) (speed / Math.sqrt(2));
                        break;
                }
            }

            gp.keyH.enterPressed = false;
            
            spriteCounter ++;
            if(spriteCounter > 10){
                if(spriteNum == 1) spriteNum = 2;
                else if(spriteNum == 2) spriteNum = 3;
                else if(spriteNum == 3) spriteNum = 4;
                else if(spriteNum == 4) spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else{
            standCounter ++;
            if(standCounter == 20){
                spriteNum = 4;
                standCounter = 0;
            }
        }

        // INVINCIBLE COUNTER
        if(invincible == true){
            invincibleCounter ++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 10 && spriteCounter <= 15){
            spriteNum = 2;
        }
        if(spriteCounter > 15 && spriteCounter <= 20){
            spriteNum = 3;
        }
        if(spriteCounter > 20 && spriteCounter <= 25){
            spriteNum = 4;
        }
        if(spriteCounter > 25 && spriteCounter <= 30){
            spriteNum = 5;
        }
        if(spriteCounter > 30){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
           // TODO: Xử lí va chạm với vật thể
        }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else attacking = true;
        }
    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        // STOP CAMERA: Dừng camera khi người chơi ra rìa map
        int x = screenX;
        int y = screenY;

        if(screenX > worldX){
            x = worldX;
        }
        if(screenY > worldY){
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX){
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }

        int bottomOffset = gp.screenHeight - screenX;
        if(bottomOffset > gp.worldHeight - worldY){
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }
        
        switch(direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                    if(spriteNum == 3) image = up3;
                    if(spriteNum == 4) image = up4;
                }
                if(attacking == true){
                    y = y - gp.tileSize; 
                    if(spriteNum == 1) image = attackUp1;
                    if(spriteNum == 2) image = attackUp2;
                    if(spriteNum == 3) image = attackUp3;
                    if(spriteNum == 4) image = attackUp4;
                    if(spriteNum == 5) image = attackUp5;
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                    if(spriteNum == 3) image = down3;
                    if(spriteNum == 4) image = down4;
                }
                if(attacking == true){
                    if(spriteNum == 1) image = attackDown1;
                    if(spriteNum == 2) image = attackDown2;
                    if(spriteNum == 3) image = attackDown3;
                    if(spriteNum == 4) image = attackDown4;
                    if(spriteNum == 5) image = attackDown5;
                }
                break;    
            case "right", "up_right", "down_right":
                if(attacking == false){
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                    if(spriteNum == 3) image = right3;
                    if(spriteNum == 4) image = right4;
                }
                if(attacking == true){
                    if(spriteNum == 1) image = attackRight1;
                    if(spriteNum == 2) image = attackRight2;
                    if(spriteNum == 3) image = attackRight3;
                    if(spriteNum == 4) image = attackRight4;
                    if(spriteNum == 5) image = attackRight5;
                }
                break;
            case "left", "up_left", "down_left":
                if(attacking == false){
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                    if(spriteNum == 3) image = left3;
                    if(spriteNum == 4) image = left4;
                }
                if(attacking == true){
                    x = x - gp.tileSize;
                    if(spriteNum == 1) image = attackLeft1;
                    if(spriteNum == 2) image = attackLeft2;
                    if(spriteNum == 3) image = attackLeft3;
                    if(spriteNum == 4) image = attackLeft4;
                    if(spriteNum == 5) image = attackLeft5;
                }
                break;
    
        }
        // Làm mờ khi nhân sát thương
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image , x, y, null );
        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
