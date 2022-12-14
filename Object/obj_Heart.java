package Object;

import EntitySetup.EntitySetup;
import Main.GamePanel;
import entity.Entity;

public class obj_Heart extends EntitySetup {
    public obj_Heart(GamePanel gp){
        super(gp);
        typeObject = 4;
        name = "Heart";
    }
}
