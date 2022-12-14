package EntitySetup;

import Main.GamePanel;
public class Shield_wood extends EntitySetup {
    public Shield_wood(GamePanel gp) {
        super(gp);
        typeObject = 2;
        down1 = setup("shield_wood" , gp.tileSize,gp.tileSize);
    }
}
