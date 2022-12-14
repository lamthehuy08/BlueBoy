package Main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp){
        this.gp = gp;
    }
    public void saveConfig(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            if(gp.ui.fullScreenOn == true){
                bw.write("On");
            }
            if(gp.ui.fullScreenOn == false ){
                bw.write("Off");
            }
            bw.newLine();
            bw.write(String.valueOf(gp.sound.volumeScale));
            bw.newLine();
            bw.write(String.valueOf(gp.SE.volumeScale));
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();
            if(s.equals("Off")){
                gp.ui.fullScreenOn = false;
            }
            if(s.equals("On")){
                gp.ui.fullScreenOn = true;
            }
            s = br.readLine();
            gp.sound.volumeScale = Integer.parseInt(s);
            s = br.readLine();
            gp.SE.volumeScale = Integer.parseInt(s);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
