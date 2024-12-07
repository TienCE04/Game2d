package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed , downPressed , rightPressed, leftPressed, enterPressed;
    //DEBUG
    boolean checkDrawTime = false;
    public KeyHandler(GamePanel gp){

        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState){

            if(gp.ui.titleScreenState == 0){

                if(code == KeyEvent.VK_W){
                    gp.ui.commandNum --;
                    if(gp.ui.commandNum < 0){
                        // TODO: Khi thêm option thì cần thay đổi dòng này
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commandNum ++;
                    // TODO: Khi thêm option thì cần thay đổi dòng này
                    if(gp.ui.commandNum > 3){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        // ADD LOAD GAME
                    }
                    if(gp.ui.commandNum == 2){
                        gp.ui.titleScreenState = 2; // chuyển sang màn hình hướng dẫn điều khiển
                    }
                    if(gp.ui.commandNum == 3){
                        System.exit(0);
                    }
                }
            }
            else if(gp.ui.titleScreenState == 1){

                if(code == KeyEvent.VK_W){
                    gp.ui.commandNum --;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commandNum ++;
                    if(gp.ui.commandNum > 3){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        // FIGHTER
                        // TODO: Làm gì đó sau khi chọn xong nhân vật
                        System.out.println("Do some fighter specific stuff!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1){
                        // THIEF
                        System.out.println("Do some thief specific stuff!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 2){
                        // SORCERER
                        System.out.println("Do some sorcerer specific stuff!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 3){
                        // BACK
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
            // SCREEN GUIDE CONTROL
            else if(gp.ui.titleScreenState == 2){
                // TODO: Viết code cho xử lí phím tắt với CONTROL GUIDE
                if(code == KeyEvent.VK_ENTER){
                    gp.ui.titleScreenState = 0;
                    gp.ui.commandNum = 0;
                }
            }
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
    
            //DEBUG
            if(code == KeyEvent.VK_T){
                if(checkDrawTime == false) checkDrawTime = true;
                else if(checkDrawTime == true) checkDrawTime = false;
            }
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum --;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum ++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    // CONTINUE
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    // BACK
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 1;
                    gp.stopMusic();
                    gp.ui.commandNum = 0;
                }
                if(gp.ui.commandNum == 2){
                    // EXIT
                    System.exit(0);
                }
            }
        }
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

}
