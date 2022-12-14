package Main;
import AI_Find_Path.AI_Search;
import EntitySetup.*;
import Scenery.Scenery;
import entity.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    public final int tileSize = 48;
    public final int maxScreenCol = 30;
    public final int maxScreenRow = 18;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight= tileSize * maxScreenRow;
    public int screenWidth2 = screenWidth ;
    public int screenHeight2 = screenHeight;
    public final int WorldWidth = 50 * tileSize;
    public final int WorldHeight = 50 * tileSize;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int diaglogState = 3;
    public final int tileScreen = 4;
    public final int CharacteristicState = 5;
    public final int OptionState = 6;
    public final int GameOverState = 7;
    public int play = tileScreen;
    public int currentMap = 1;
    public int maxMap = 4;
    Thread threadGame ;
    public MyKeyAdapter KeyH = new MyKeyAdapter(this);
    public Player player  = new Player();
    public PlayerSetup playerSetup = new PlayerSetup(this,KeyH);
    public UI ui = new UI(this);    
    public EntityGraphics entityGraphics = new EntityGraphics(this);
    public Scenery scenery = new Scenery(this);
    public CheckCollision cChecker = new CheckCollision(this);
    public EntitySetup obj[][] = new EntitySetup[4][30];
    public Sound sound = new Sound();
    public Sound SE = new Sound();
    public Config cg = new Config(this);
    public Entity npc[][] = new NPC[4][10];
    public EntitySetup npcSetup[][] = new npcSetup[4][10];
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler cCheckEventHandler = new EventHandler(this);
    public Entity monster[][] = new Monster_slim[4][20];
    public EntitySetup monsterSetup[][] = new EntitySetup[4][20];
    public ArrayList<Particle> particles = new ArrayList<>();
    private ArrayList<EntitySetup> entityList = new ArrayList<>();
    public AI_Search AI = new AI_Search(this);
    BufferedImage tempImage;
    public Graphics2D g2;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.addKeyListener(KeyH);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);      // should be added
        this.setFocusable(true);           //must not be forgotten
    }
    public void SetUpGame(){

        aSetter.setObject();
        aSetter.setNpc();
        aSetter.setMonster();

        tempImage = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempImage.getGraphics();

        if(ui.fullScreenOn == true) {
            setFullScreen();
        }
        play(0);
        entityGraphics.setGraphics();
    }
    public void startGame(){
        threadGame = new Thread(this);
        threadGame.start();
    }

    @Override
    public void run() {
        //double interval = 1000000000.0/60.0;
       // double nextDraw = interval + System.nanoTime();
        /*while(threadGame!=null){
            update();
            repaint();
           double remaining = nextDraw - System.nanoTime();
            remaining/=1000000;
            if(remaining<0)remaining=0;
            nextDraw+=interval;
            try {
                Thread.sleep((long)remaining);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
*/
            // delta solution (in while)
               double intervalPerSecond = 1000000000.0/60.0;
               double lastTime = System.nanoTime();
               double delta = 0;
               long currentTime;
               double timer=0;
               int fa=0;
               while(threadGame!=null){
                    currentTime = System.nanoTime();
                    delta += (currentTime - lastTime )/intervalPerSecond;  // n FPS per x seconds
                    timer += (currentTime - lastTime)/1000000;
                    lastTime = currentTime;
                    if(delta>=1){
                        update();
                        drawToTempBuffer();
                        drawToScreen();
                        //repaint();
                        delta--;
                      //  fa++;
                    }
                  //  if(timer>=1000){
                     //   System.out.println("FPS: " + fa);
                       // fa=0;
                       // timer = 0;
                  //  }
               }
    }
    public void update(){
        if(play==playState) {
            playerSetup.update();
            for(int i =0;i<npcSetup[1].length;i++){
                if(npcSetup[currentMap][i]!=null){
                    npcSetup[currentMap][i].update();
                }
            }
            for(int i =0;i<monsterSetup[1].length;i++){
                if(monsterSetup[currentMap][i]!=null && monsterSetup[currentMap][i].alive == true){
                     monsterSetup[currentMap][i].update();
                }
            }
            for(int i = 0; i < monsterSetup[1].length ;i ++ ){
                if(monsterSetup[currentMap][i]!=null) {
                    if(monsterSetup[currentMap][i].projectile.alive == true){
                        monsterSetup[currentMap][i].projectile.update();
                    }
                }
            }
            for(int i = 0; i < particles.size() ;i ++ ){
                if(particles.get(i).alive == true){
                    particles.get(i).update(i);
                }
            }
            if(player.projectile.alive == true){
                player.projectile.update();
            }
        }
    }
    public void play(int i){
        sound.setClipToObject(i);
        sound.clip.start();
        sound.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopClip(){
        sound.clip.stop();
    }
    public void playShort(int i){
        SE.setClipToObject(i);
        SE.clip.start();
    }
    public void drawToTempBuffer() {
        if (play == tileScreen) {
            ui.draw();
        } else {
            // SCENERY
            scenery.draw(g2);


            // Add entity


            entityList.add(playerSetup);

            for (int i = 0; i < npcSetup[1].length; i++) {
                if (npcSetup[currentMap][i] != null) {
                    entityList.add(npcSetup[currentMap][i]);
                }
            }

            for (int i = 0; i < monsterSetup[1].length; i++) {
                if (monsterSetup[currentMap][i] != null && monsterSetup[currentMap][i].alive == true) {
                    entityList.add(monsterSetup[currentMap][i]);
                }
            }
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            Collections.sort(entityList, new Comparator<EntitySetup>() {
                @Override
                public int compare(EntitySetup e1, EntitySetup e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITY
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            for (int i = 0; i < monsterSetup[1].length; i++) {
                if (monsterSetup[currentMap][i] != null && monsterSetup[currentMap][i].projectile.alive == true) {
                    monsterSetup[currentMap][i].projectile.draw(g2);
                }
            }
            for (int i = 0; i < particles.size(); i++) {
                if (particles.get(i).alive == true) {
                    particles.get(i).draw(g2);
                }
            }
            if (player.projectile.alive == true) {
                player.projectile.draw(g2);
            }
            entityList.clear();

            // USER INTERFACE
            ui.draw();
        }
    }    // tham khảo

    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempImage,0,0,screenWidth2,screenHeight2,null);
        g.dispose();
    }


    public void setFullScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        MyFrame.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenWidth2 = (int) width;
        screenHeight2 = (int) height;
        //offset factor to be used by mouse listener or mouse motion listener if you are using cursor in your game. Multiply your e.getX()e.getY() by this.
    }
}