package Object;

import EntitySetup.EntitySetup;
import Main.GamePanel;


public class obj_Key extends EntitySetup {
    public obj_Key(GamePanel gp){
        super(gp);
        typeObject = 4;
        name = "Key";
        description = "[" + name + "]\nUsed to unlock the door";
    }
    public void use(){
        gp.play = gp.diaglogState;
        int id = gp.playerSetup.getDetected( "Door");
        if(id != -1 ){
            gp.playShort(3);
            gp.obj[gp.currentMap][id] = null;
            gp.ui.currentDialog = "You use the Key and open the door";
        }
        else{
            gp.ui.currentDialog = "What a y d?";
        }
    }
}
