package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Heart;
import entity.Entity;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    FontMetrics metrics;
    Font arial_40, arial_80, arial_28;
    // BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen, 1: the second screen 

    // public double playTime;
    // DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        // arial_80B = new Font("Arial", Font.BOLD, 80);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        arial_28 = new Font("Arial", Font.PLAIN, 28);

        // OBJ_Key key = new OBJ_Key(gp);
        // keyImage = key.image;

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }
    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
    // public void drawCenterTextUI(Font font, Color color, String text, int y){
    //     g2.setFont(font);
    //     g2.setColor(color);
    //     int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    //     int xCenter = gp.screenWidth/2 - textLength/2;
    //     g2.drawString(text, xCenter , y);
    // }
    //TODO: Hàm in ra Congratulation khi chiến thắng
    /* public void draw(Graphics2D g2){
        if(gameFinished == true){

            String text;
            
            text = "You found the treasure!";
            drawTextUI(g2, arial_40, Color.WHITE, text, gp.screenHeight/2 - gp.tileSize *3);

            text = "Your Time is :" + dFormat.format(playTime) + "!";
            drawTextUI(g2, arial_40, Color.WHITE, text, gp.screenHeight/2 + gp.tileSize *3);

            text = "Congratulation!";
            drawTextUI(g2, arial_80B, Color.YELLOW, text, gp.screenHeight/2 + gp.tileSize *2);

            gp.gameThread = null;
        }
        else{
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize , gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 62);
            //TIME
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 62);

            //MESSAGE
            if(messageOn){

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter ++;
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.pauseState){

            drawPauseScreen();

        }
    }
    */
    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW MAX LIFE
        while(i < gp.player.maxLife/2){

            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        // DRAW CURRENT LIFE
        while(i < gp.player.life){
            
            g2.drawImage(heart_half,x , y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public int getXforCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public void drawTitleScreen(){

        if(titleScreenState == 0){

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
            String text = "Name Game Here";
            int x = getXforCenterText(text);
            int y = gp.tileSize*3;

            // SHADOW
            g2.setColor(Color.GRAY);
            g2.drawString(text, x+5, y+5);
            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // (MAIN CHARACTER) IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

            text = "NEW GAME";
            x = getXforCenterText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "CONTROL GUIDE";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1){
            
            // CLASS SELECTION SCREEN
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXforCenterText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenterText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Thief";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Socerer";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenterText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        // CONTROL GUIDE SCREEN
        else if(titleScreenState == 2){
            // TODO: Viết code cho các hướng dẫn phím tắt của game
            String text = "Control Guide";
            int x = getXforCenterText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Di chuyển: W A S D";
            x = gp.tileSize;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            
            text = "Tạm dừng: P";
            x = gp.tileSize;
            y += gp.tileSize;
            g2.drawString(text, x, y);

            text = "Kiểm tra tốc độ game: T";
            x = gp.tileSize;
            y += gp.tileSize;
            g2.drawString(text, x, y);

            text = "Back";
            x = getXforCenterText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    
    public void drawPauseScreen(){

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(80F));

        String text = "PAUSE";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(42F));
        text = "Continue";
        x = getXforCenterText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Back";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Exit";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawDialogueScreen(){

        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - gp.tileSize*4;
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(arial_28);
        x += gp.tileSize/2;
        y += gp.tileSize;

        metrics = g2.getFontMetrics();
        int lineWidth = 0;
        int maxlineWidth = width - gp.tileSize;
        int xfordrawString = x;
        for(String line : currentDialogue.split(" ")){
            lineWidth += metrics.stringWidth(line + " ");
            
            if(lineWidth <= maxlineWidth){
                g2.drawString(line + " " , xfordrawString, y);
                xfordrawString += metrics.stringWidth(line + " ");
            }
            else{
                y += 40;
                xfordrawString = x;
                lineWidth = 0;
                g2.drawString(line + " ", xfordrawString , y);

                xfordrawString += metrics.stringWidth(line + " ");
                lineWidth += metrics.stringWidth(line + " ");
            }
        }

    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0 , 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x + 3, y + 3, width - 6, height - 6, 27, 27);

    }
}
