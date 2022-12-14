package Main;

import javax.swing.*;

public class MyFrame {
    public static JFrame jFrame;

    public static void main(String[] a) {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(false);
        jFrame.setResizable(false);
        GamePanel gamePanel = new GamePanel();
        gamePanel.cg.loadConfig();
        if(gamePanel.ui.fullScreenOn == true){
           jFrame.setUndecorated(true);
        }
        jFrame.add(gamePanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        gamePanel.SetUpGame();
        gamePanel.startGame();
    }
}
