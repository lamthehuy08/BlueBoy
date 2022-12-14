package EntitySetup;

import Main.GamePanel;

public class Sword_wood extends EntitySetup {
    public Sword_wood(GamePanel gp) {
        super(gp);
        typeObject = 1;
        name = "wood Sword";
        down1 = setup("sword_normal" , gp.tileSize,gp.tileSize);

    }
}
