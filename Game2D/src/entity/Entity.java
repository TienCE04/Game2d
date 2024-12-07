package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {

    GamePanel gp;
    public BufferedImage up1 , up2 ,up3, up4, down1 , down2 ,down3 ,down4;
    public BufferedImage left1 , left2, left3, left4, right1 , right2, right3, right4;

    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5,
    attackDown1, attackDown2, attackDown3, attackDown4, attackDown5,
    attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5,
    attackRight1, attackRight2, attackRight3, attackRight4, attackRight5;

    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0 , 0 , 48 , 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues [] = new String[20];

    // STATE
    public int worldX , worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;

    // CHARACTER ATTIBUTES
    public int type; // 0: player , 1: npc , 2: monster
    public String name;
    public int speed;
    public int maxLife;
    public int life;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction() {}
    public void speak() {}
    public void update(){

        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                // We can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }

        }
        if(collisionOn == false){
            switch (direction) {
                case "up": worldY -= speed ; break;
                case "down": worldY += speed; break;
                case "right": worldX += speed; break;
                case "left": worldX -= speed; break;
                default: break;
            }
        }

        spriteCounter ++;
        if(spriteCounter > 10){
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2) spriteNum =1;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        // Stop moving the camere at the edge
        if(gp.player.screenX > gp.player.worldX){
            screenX = worldX;
        }
        if(gp.player.screenY > gp.player.worldY){
            screenY = worldY;
        }
        int rightOffset = gp.screenWidth - gp.player.screenX;
        if(rightOffset > gp.worldWidth - gp.player.worldX){
            screenX = gp.screenWidth - (gp.worldWidth - worldX);
        }

        int bottomOffset = gp.screenHeight - gp.player.screenY;
        if(bottomOffset > gp.worldHeight - gp.player.worldY){
            screenY = gp.screenHeight - (gp.worldHeight - worldY);
        }

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch(direction){
                case "up":
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                    break;
                case "down":
                    if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                    break;    
                case "right":
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                    break;
                case "left":
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                    break;
            }
            g2.drawImage(image, screenX , screenY , gp.tileSize , gp.tileSize , null);
        }
        else if(gp.player.screenX > gp.player.worldX || 
                gp.player.screenY > gp.player.worldY ||
                rightOffset > gp.worldWidth - gp.player.worldX ||
                bottomOffset > gp.worldHeight - gp.player.worldY) {
                g2.drawImage(image, screenX , screenY , gp.tileSize , gp.tileSize , null);  
        }
    }
    public BufferedImage setup(String imagePath, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
