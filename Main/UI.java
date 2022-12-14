package Main;

import EntitySetup.EntitySetup;
import  Object.obj_Key;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    private BufferedImage img;
    private Font MV_Boli;
    private Font Arial = new Font("Arial" , Font.PLAIN , 11);
    private GamePanel gp;
    private Graphics2D g2;
    public boolean gameFinished = false;
    public String currentDialog = "";
    public int choiceNumber = 0;  // các sự kiện chọn
    public int tileScreen = 0;    // chuyển màn hình action hoạt động
    public int slotCol = 0;
    public int slotRow = 0;
    public boolean fullScreenOn = false;
    public ArrayList<String> message = new ArrayList<>();
    public ArrayList<Integer> messageCounter = new ArrayList<>();
    public UI(GamePanel gp){
        this.gp=gp;
        EntitySetup obj = new obj_Key(gp);
        createFont();
        img = obj.image;
    }
    public void draw(){
         if(gameFinished){
            drawFinishedState();
            drawPlayerLifeAndMana();
             drawPlayerLifeAndMana();
        }
        if(gp.play == gp.playState){
            drawPlayerLifeAndMana();
            drawPlayerLifeAndMana();
            drawMessageDamage();
        }
        else if(gp.play == gp.pauseState){
            drawPauseState();
            drawPlayerLifeAndMana();
        }
        else if(gp.play == gp.diaglogState){
            drawPlayerLifeAndMana();
            drawPlayerLifeAndMana();
            drawDialogShow();
        }
        else if(gp.play == gp.tileScreen){
            drawTitleScreen();
        }
        else if(gp.play == gp.CharacteristicState){
            drawCharacteristicScreen();
            drawSubItem();
            drawSubDescriptionItem();
        }
        else if(gp.play == gp.OptionState){
            drawOptionState();
        }
        else if(gp.play == gp.GameOverState){
            drawGameOver();
        }
    }
    public void drawMessageDamage(){
         int x = gp.tileSize;
         int y = gp.tileSize * 4;
         g2.setFont(MV_Boli);
         g2.setFont(g2.getFont().deriveFont(Font.BOLD,28f));

         for(int i = 0 ;i < message.size() ;i ++){
             if(message.get(i) != null) {
                 g2.setColor(Color.black);
                 g2.drawString(message.get(i), x+1, y+1);
                 g2.setColor(Color.WHITE);
                 g2.drawString(message.get(i), x, y);
                 y += 30;
                 messageCounter.set(i,messageCounter.get(i)+1);
                 if (messageCounter.get(i) > 120) {
                     message.remove(i);
                     messageCounter.remove(i);
                 }
             }
         }
    }
    public void drawGameOver(){
        g2.setColor(new Color(0,0,0,210));
        g2.setFont(MV_Boli);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString("Game Over" , gp.tileSize *13 , gp.tileSize *4);
        g2.setFont(g2.getFont().deriveFont(34f));
        g2.drawString("Retry", gp.tileSize *14, gp.tileSize * 7);
        g2.drawString("Quit", gp.tileSize *14, gp.tileSize *8);
        if(choiceNumber == 0)g2.drawString(">" , gp.tileSize *14 - 15 , gp.tileSize *7);
        if(choiceNumber == 1)g2.drawString(">" , gp.tileSize *14 - 15 , gp.tileSize *8);
    }
    public void drawOptionState(){
        final int FrameX = gp.tileSize * 7;
        final int FrameY = gp.tileSize * 1;
        drawSubFrameWindow(FrameX,FrameY,gp.tileSize * 7 , gp.tileSize * 9);
        g2.setFont(MV_Boli);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 28f));

        int x = FrameX + gp.tileSize;
        int y = FrameY;
        String text = "Full Screen";
        y+=gp.tileSize;
        g2.drawString("Options" , FrameX + gp.tileSize * 3, y);

        y+=gp.tileSize;
        g2.drawString(text , x , y);
        if(choiceNumber == 0)g2.drawString(">", x - 25 , y);
        g2.drawRect(x+gp.tileSize*5,y-20,28,25);
        if(gp.KeyH.enterPressed == true && choiceNumber == 0){
            if(fullScreenOn == true)fullScreenOn = false;
            else fullScreenOn = true;
            gp.KeyH.enterPressed = false;
        }
        if(fullScreenOn == true)g2.fillRect(x+gp.tileSize*5 , y-20 ,28,25 );


        text = "Music";
        y+=gp.tileSize;
        g2.drawString(text , x , y);
        if(choiceNumber == 1)g2.drawString(">", x - 25 , y);
        g2.drawRect(x+gp.tileSize*3+5,y-20,120,25);
        g2.fillRect(x+gp.tileSize*3+5 , y - 20 , 24 * gp.sound.volumeScale , 25);


        text = "SE";
        y+=gp.tileSize;
        g2.drawString(text , x , y);
        if(choiceNumber == 2)g2.drawString(">", x - 25 , y);
        g2.drawRect(x+gp.tileSize*3+5,y-20,120,25);
        g2.fillRect(x+gp.tileSize*3+5 , y - 20 , 24 * gp.SE.volumeScale , 25);

        text = "Control";
        y+=gp.tileSize;
        g2.drawString(text , x , y);
        if(choiceNumber == 3)g2.drawString(">", x - 25 , y);

        text = "End Game";
        y+=gp.tileSize;
        g2.drawString(text , x , y);
        if(choiceNumber == 4)g2.drawString(">", x - 25 , y);
        if(gp.KeyH.enterPressed == true && choiceNumber == 4){
            System.exit(0);
        }

        text = "Back";
        y+=gp.tileSize * 2;
        g2.drawString(text,x,y);
        if(choiceNumber == 5)g2.drawString(">", x - 25 , y);
        gp.cg.saveConfig();
        if(gp.KeyH.enterPressed == true && choiceNumber == 5){
            gp.play = gp.playState;
        }
    }
    public void drawCharacteristicScreen(){
        drawSubFrameWindow(gp.tileSize,gp.tileSize,gp.tileSize*5,gp.tileSize*10);
        int x =gp.tileSize+18;
        int y = gp.tileSize*2;
        int rightCorner = x + gp.tileSize * 5 - 30;
        g2.setFont(MV_Boli);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32f));

        String text = "Level";
        g2.drawString(text ,  x, y);
        String value = String.valueOf(gp.player.level);
        g2.drawString(value, rightCorner -getLengTextX(value) , y);

        text = "Life";
        y+=40;
        g2.drawString(text ,  x, y);
        value = gp.player.CurrentLife + "/" + gp.player.maxLife;
        g2.drawString(value, rightCorner -getLengTextX(value) , y);

        text = "EXP";
        y+=40;
        g2.drawString(text ,  x, y);
        value = String.valueOf(gp.player.exp);
        g2.drawString(value, rightCorner -getLengTextX(value) , y);

        text = "Attack";
        y+=40;
        g2.drawString(text ,  x, y);
        value = String.valueOf(gp.player.attack);
        g2.drawString(value, rightCorner-getLengTextX(value) , y);

        text = "Defence";
        y+=40;
        g2.drawString(text ,  x, y);
        value = String.valueOf(gp.player.defence);
        g2.drawString(value, rightCorner -getLengTextX(value) , y);

        text = "Coin";
        y+=40;
        g2.drawString(text ,  x, y);
        value = String.valueOf(gp.player.coin);
        g2.drawString(value, rightCorner -getLengTextX(value) , y);

        text = "Next Level";
        y+=40;
        g2.drawString(text ,  x, y);
        value = String.valueOf(gp.player.nextLevel);
        g2.drawString(value, rightCorner -getLengTextX(value) , y);

        text = "Weapon";
        y+=55;
        g2.drawString(text ,  x, y);
        g2.drawImage(gp.player.currentWeapon.down1 ,rightCorner - gp.tileSize ,y -36,null);

        text = "Shield";
        y+=55;
        g2.drawString(text ,  x, y);
        g2.drawImage(gp.player.currentShield.down1 ,rightCorner - gp.tileSize ,y -36,null);

    }
    public void drawSubItem(){
        final int frameX = gp.tileSize * 12;
        final int frameY = gp.tileSize;
        drawSubFrameWindow(frameX , frameY , gp.tileSize *6 + 18 , gp.tileSize *5 + 18);

        if(slotRow < 0)slotRow = 3;
        if(slotRow > 3)slotRow = 0;
        if(slotCol < 0)slotCol = 4;
        if(slotCol > 4)slotCol = 0;

        int x = frameX + 22 + (gp.tileSize + 4 ) * slotCol;
        int y = frameY + 22 + (gp.tileSize + 4 )* slotRow;


        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x,y,gp.tileSize,gp.tileSize,10,10);

        int x1 = frameX + 22;
        int y1 = frameY + 22;
        int col = 0;

        for(int i =0 ;i < gp.player.inventory.size() ; i++ ){
            x1 = frameX + 22 + col * (gp.tileSize + 4 );
            if(col == 5){
                x1 = frameX + 22;
                col = 0;
                y1 += (gp.tileSize+4);
            }
            if(gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield ){
                g2.setColor(Color.orange);
                g2.fillRoundRect(x1,y1,gp.tileSize,gp.tileSize,10,10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1 , x1 , y1 , gp.tileSize, gp.tileSize,null);
            col++;
        }
    }
    public void drawSubDescriptionItem(){
        final int frameX = gp.tileSize * 12;
        final int frameY = gp.tileSize * 6 + 20;
        String tm = "";

        g2.setFont(MV_Boli);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));

        int x = frameX + 16;
        int y = frameY + 30;
        if(slotRow < 0)slotRow = 3;
        int indexItem = slotRow * 5 + slotCol;
        if(indexItem < gp.player.inventory.size()){
            drawSubFrameWindow(frameX , frameY , gp.tileSize * 6 , gp.tileSize *3);
            tm = gp.player.inventory.get(indexItem).description;
            String line[] = tm.split("\n");
            for(String t : line){
                g2.drawString(t , x,y);
                y+=gp.tileSize;
            }
        }
    }
    public void drawSubFrameWindow(int x,int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+3,y+3,width-7,height-3,25,25);
    }
    public void drawPlayerLifeAndMana(){

        g2.setFont(Arial);
        int x = 50 - (gp.player.maxLife - gp.player.CurrentLife);
        g2.setColor(Color.red);
        g2.fillRect(x,12,190,20);
        g2.setColor(Color.blue);
        int mana = gp.player.currentMana;
        g2.fillRect(50,40,45+ mana,15);
        g2.setColor(Color.WHITE);
        g2.drawString(String.valueOf(gp.player.CurrentLife),110,25);
        g2.drawString(String.valueOf(gp.player.currentMana) , 110 , 51);
        g2.setColor(Color.yellow);

        g2.drawImage(gp.entityGraphics.topbar , 0,0,null);
        g2.setFont(g2.getFont().deriveFont(20f));
        String t = String.valueOf(gp.player.level);
        int lengLevel = getLengTextX(t);
        g2.drawString(String.valueOf(gp.player.level) , (86 - lengLevel) / 2,40);

        if(mana <= 0 && gp.KeyH.shotPressed == true && gp.player.projectile.alive == false){
            gp.play = gp.diaglogState;
            gp.ui.currentDialog = "You are not enough mana to use.";
        }
    }
    public void drawTitleScreen(){
        this.g2=gp.g2;
        g2.setColor(new Color(0,0,0,210));
        g2.fillRect(0,0,gp.screenWidth2, gp.screenHeight2);
        if(tileScreen == 0) {
            g2.setFont(MV_Boli);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 91F));
            String text = "BLUE BOY ADVENTURE";
            int leng = getLengTextX(text);
            int x = gp.screenWidth / 2 - leng / 2;
            int y = gp.tileSize * 2;
            g2.setColor(Color.GRAY);
            g2.drawString(text, x + 5, y + 5);
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            x = gp.screenWidth / 2 - gp.tileSize;
            y += gp.tileSize * 2;
            g2.drawImage(gp.playerSetup.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD | Font.ITALIC, 46F));
            text = "NEW GAME";
            x = gp.screenWidth / 2 - getLengTextX(text) / 2;
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (choiceNumber == 0) {
                g2.drawString(">", x - 32, y);
            }

            text = "LOAD GAME";
            y += gp.tileSize + 16;
            g2.drawString(text, x, y);
            if (choiceNumber == 1) {
                g2.drawString(">", x - 32, y);
            }

            text = "QUIT";
            y += gp.tileSize + 16;
            g2.drawString(text, x, y);
            if (choiceNumber == 2) {
                g2.drawString(">", x - 32, y);
            }
        }
        else if(tileScreen == 1){
            g2.setColor(Color.WHITE);
            g2.setFont(MV_Boli);
            g2.setFont(g2.getFont().deriveFont(46F));
            String text = "Welcome to my Land!!!";

            int x = gp.screenWidth/2 - gp.tileSize * 4;
            int y = gp.tileSize*2;
            g2.drawString(text,x,y);

            y += gp.tileSize +16;
            text = "Let's adventure and challenge";
            g2.drawString(text , x,y);

            y+=gp.tileSize +50;
            text = "Play";
            g2.drawString(text,x,y);
            if(choiceNumber==0)g2.drawString(">" , x-32,y);

            y+=gp.tileSize +16;
            text = "Back";
            g2.drawString(text,x,y);
            if(choiceNumber==1)g2.drawString(">" , x-32,y);

        }
    }
    public void drawPauseState(){
        String text = "PAUSE";
        g2.setColor(Color.WHITE);
        g2.setFont(MV_Boli);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        int leng = getLengTextX(text);
        g2.drawString(text , gp.screenWidth/2-leng/2 , gp.tileSize*4 );
    }
    public void drawFinishedState(){
        g2.setFont(MV_Boli);
        String text1="You found the treasure";
        String text2= "Congratulation!!!";

        g2.setColor(Color.BLUE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        int leng1 = getLengTextX(text1);
        int x1 = gp.screenWidth/2 - leng1/2;
        g2.drawString(text1 , x1,gp.tileSize*3);

        g2.setColor(Color.red);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
        int leng2 = getLengTextX(text2);
        int x2 = gp.screenWidth/2 - leng2/2;
        g2.drawString(text2,x2,gp.tileSize*9);
        gp.threadGame = null;
    }
    public void drawDialogShow(){
         drawSubFrameWindow(gp.tileSize*7,gp.tileSize-40,gp.tileSize*12,gp.tileSize*4);
        int x = gp.tileSize*7;
        int y = gp.tileSize-40;
         g2.setFont(MV_Boli);
         g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
         //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         for(String line : currentDialog.split("\n") ){
             g2.drawString(line , x+17,y+40);
             y+=40;
         }
    }
    public void createFont(){
        // create a font
        try {
            InputStream is = getClass().getResourceAsStream("/Font/x12y16pxMaruMonica.ttf");
            MV_Boli = Font.createFont(Font.TRUETYPE_FONT,is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }  // tạo font cho chữ vẽ
    public int getLengTextX(String text){
        int leng = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return leng;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
}
