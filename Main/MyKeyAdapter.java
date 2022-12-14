package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class MyKeyAdapter extends KeyAdapter {
    public boolean up= false,down=false,right=false,left=false , spacePressed = false, enterPressed =false , shotPressed = false;
    GamePanel gp;
    public MyKeyAdapter(GamePanel gp){this.gp=gp;}
    public void keyPressed(KeyEvent e){
        if(gp.play == gp.tileScreen){
            if(gp.ui.tileScreen == 0) {
                if (e.getKeyChar() == 'w') {
                    gp.ui.choiceNumber--;
                    if (gp.ui.choiceNumber < 0) gp.ui.choiceNumber = 2;
                } else if (e.getKeyChar() == 's') {
                    gp.ui.choiceNumber++;
                    if (gp.ui.choiceNumber > 2) gp.ui.choiceNumber = 0;
                }
                else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(gp.ui.choiceNumber==0)gp.ui.tileScreen=1;
                    else if(gp.ui.choiceNumber==2)System.exit(0);
                }
            }
            else if(gp.ui.tileScreen == 1){
                if (e.getKeyChar() == 'w') {
                    gp.ui.choiceNumber--;
                    if (gp.ui.choiceNumber < 0) gp.ui.choiceNumber = 1;
                } else if (e.getKeyChar() == 's') {
                    gp.ui.choiceNumber++;
                    if (gp.ui.choiceNumber > 1) gp.ui.choiceNumber = 0;
                }
                else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(gp.ui.choiceNumber == 0){
                        gp.play = gp.playState;
                    }
                    else if(gp.ui.choiceNumber== 1){
                        gp.ui.tileScreen = 0;
                        gp.ui.choiceNumber=0;
                    }
                }
            }
        }
        else if(gp.play == gp.playState ) {
            if (e.getKeyChar() == 'w') {
                up = true;
            } else if (e.getKeyChar() == 's') {
                down = true;
            } else if (e.getKeyChar() == 'a') {
                left = true;
            } else if (e.getKeyChar() == 'd') {
                right = true;
            } else if (e.getKeyChar() == 'p') {
                    gp.play = gp.pauseState;
            }
            else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_C){
                gp.play = gp.CharacteristicState;
            }
            else if(e.getKeyCode() == KeyEvent.VK_J){
                shotPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                gp.play = gp.OptionState;
            }
            else if(e.getKeyCode() == KeyEvent.VK_M){
                gp.play = gp.GameOverState;
            }
            else if(e.getKeyCode() == KeyEvent.VK_T){
                gp.scenery.loadMap("/Map/map1.txt",1);
            }
        }
        else if(gp.play == gp.OptionState){
            if (e.getKeyChar() == 'w') {
                gp.playShort(8);
                gp.ui.choiceNumber--;
                if (gp.ui.choiceNumber < 0) gp.ui.choiceNumber = 5;
            } else if (e.getKeyChar() == 's') {
                gp.playShort(8);
                gp.ui.choiceNumber++;
                if (gp.ui.choiceNumber > 5) gp.ui.choiceNumber = 0;
            }
            else if(e.getKeyCode() == KeyEvent.VK_A){
                if(gp.ui.choiceNumber == 1 && gp.sound.volumeScale > 0) {
                    gp.sound.volumeScale--;
                    gp.sound.checkVolume();
                }
                else if(gp.ui.choiceNumber == 2 && gp.SE.volumeScale > 0){
                    gp.SE.volumeScale--;
                    gp.SE.checkVolume();
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_D){
                if(gp.ui.choiceNumber == 1 && gp.sound.volumeScale < 5) {
                    gp.sound.volumeScale++;
                    gp.sound.checkVolume();
                }
                else if(gp.ui.choiceNumber == 2 && gp.SE.volumeScale < 5) {
                    gp.SE.volumeScale++;
                    gp.SE.checkVolume();
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                gp.play = gp.playState;
            }
        }
        else if(gp.play == gp.pauseState){
            if(e.getKeyChar() == 'p'){
                gp.play = gp.playState;
            }
        }
        else if(gp.play == gp.diaglogState){
            if(e.getKeyChar() == KeyEvent.VK_ENTER){
                gp.play = gp.playState;
            }
        }
        else if(gp.play == gp.CharacteristicState){
            if(e.getKeyCode() == KeyEvent.VK_C){
                gp.play = gp.playState;
            }
            else if(e.getKeyCode() == KeyEvent.VK_W){
                gp.ui.slotRow--;
                gp.playShort(8);
            }
            else if(e.getKeyCode() == KeyEvent.VK_S){
                gp.ui.slotRow++;
                gp.playShort(8);
            }
            else if(e.getKeyCode() == KeyEvent.VK_A){
                gp.ui.slotCol--;
                gp.playShort(8);
            }
            else if(e.getKeyCode() == KeyEvent.VK_D){
                gp.ui.slotCol++;
                gp.playShort(8);

            }
            else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                int index = gp.ui.slotRow * 5 + gp.ui.slotCol;
                if(index < gp.player.inventory.size()){
                    if(gp.player.inventory.get(index).typeObject == 1){
                        gp.player.currentWeapon = gp.player.inventory.get(index);
                        gp.playerSetup.chooseWeapon();
                    }
                    if(gp.player.inventory.get(index).typeObject == 2){
                        gp.player.currentShield = gp.player.inventory.get(index);
                        gp.player.setDefence();
                    }
                    if(gp.player.inventory.get(index).typeObject == 4){
                        gp.player.inventory.get(index).use();
                        gp.player.inventory.remove(index);
                    }
                }
            }
        }
        else if(gp.play == gp.GameOverState){
            if (e.getKeyChar() == 'w') {
                gp.playShort(8);
                gp.ui.choiceNumber--;
                if (gp.ui.choiceNumber < 0) gp.ui.choiceNumber = 1;
            } else if (e.getKeyChar() == 's') {
                gp.playShort(8);
                gp.ui.choiceNumber++;
                if (gp.ui.choiceNumber > 1) gp.ui.choiceNumber = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(gp.ui.choiceNumber == 0){
                    gp.playerSetup.reTryGame();
                    gp.play = gp.playState;
                }
                else if(gp.ui.choiceNumber == 1){
                    System.exit(0);
                }
            }
        }
    }
    public void keyReleased(KeyEvent e ){
        if(e.getKeyChar()=='w'){
            up=false;
        }
        else if(e.getKeyChar()=='s'){
            down=false;
        }else if(e.getKeyChar()=='a'){
            left=false;
        }else if(e.getKeyChar()=='d'){
            right=false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_J){
            shotPressed = false;
        }
    }
}
