package EntitySetup;

import Main.GamePanel;

public class Shield_blue extends EntitySetup {
    public Shield_blue(GamePanel gp) {
        super(gp);
        typeObject = 2;
        name = "";

        down1 = setup("shield_blue" , gp.tileSize, gp.tileSize);

    }
}
