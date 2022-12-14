package Main;
import javax.sound.sampled.*;
import java.net.URL;
public class Sound {
    public Clip clip;
    private URL urlSound[] = new URL[12];
    private FloatControl fc;
    public int volumeScale = 3;
    private float volume;
    public Sound(){
        urlSound[0] = getClass().getResource("/Sound/BlueBoyAdventure.wav");
        urlSound[1] = getClass().getResource("/Sound/coin.wav");
        urlSound[2] = getClass().getResource("/Sound/powerup.wav");
        urlSound[3] = getClass().getResource("/Sound/unlock.wav");
        urlSound[4] = getClass().getResource("/Sound/fanfare.wav");
        urlSound[5] = getClass().getResource("/Sound/hitmonster.wav");
        urlSound[6] = getClass().getResource("/Sound/receivedamage.wav");
        urlSound[7] = getClass().getResource("/Sound/216675__hitrison__stick-swoosh-whoosh.wav");
        urlSound[8] = getClass().getResource("/Sound/cursor.wav");
        urlSound[9] =  getClass().getResource(("/Sound/levelup.wav"));
        urlSound[10] = getClass().getResource("/Sound/burning.wav");
        urlSound[11] = getClass().getResource("/Sound/gameover.wav");
    }

    public void setClipToObject(int i){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(urlSound[i]);
            clip = AudioSystem.getClip();
            clip.open(audio);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkVolume(){
        switch (volumeScale){
            case 0: volume = -80f;break;
            case 1 : volume = -12f;break;
            case 2 : volume = -8f;break;
            case 3: volume = -1f;break;
            case 4: volume = 3f;break;
            case 5 : volume = 6f;break;
        }
        fc.setValue(volume);
    }

}
