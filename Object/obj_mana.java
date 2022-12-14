package Object;

import EntitySetup.EntitySetup;
import Main.GamePanel;

import java.awt.image.BufferedImage;

public class obj_mana extends EntitySetup {
    public obj_mana(GamePanel gp) {
        super(gp);
        typeObject = 4;
        name = "mana";
    }
}
