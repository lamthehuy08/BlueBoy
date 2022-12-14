package Object;

import EntitySetup.EntitySetup;
import Main.GamePanel;
import entity.Entity;

public class obj_Door extends EntitySetup {
    public obj_Door(GamePanel gp){
        super(gp);
        typeObject = 3;
        name = "Door";
    }
}
