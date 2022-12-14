package Object;

import EntitySetup.EntitySetup;
import Main.GamePanel;
import entity.Entity;

public class obj_potion_red extends EntitySetup {
    private int RecoveredValue = 5; // the recovered value
    public obj_potion_red(GamePanel gp) {
        super(gp);
        name = "red potion";
        typeObject = 4;
        description = "[" + name + "]\nhealing your life by " + RecoveredValue;
    }
    public void use(){
        gp.player.CurrentLife += RecoveredValue;
        if(gp.player.CurrentLife > gp.player.maxLife )gp.player.CurrentLife = gp.player.maxLife;
        gp.play = gp.diaglogState;
        gp.ui.currentDialog = "You drank!\nGot " + RecoveredValue + " life !";
        gp.playShort(2);
        gp.player.inventory.remove(this);
    }
}
