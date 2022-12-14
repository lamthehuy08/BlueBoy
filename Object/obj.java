package Object;

import EntitySetup.EntitySetup;
import Main.GamePanel;

public class obj extends EntitySetup {

    public obj(GamePanel gp) {
        super(gp);
    }
    public void setObj(String name, int typeObject){
        this.name = name;
        this.typeObject = typeObject;
        if(name == "axe"){
            description = "[" + name + "]\nCut the tree, sometimes\ncan attack monster";
            attackValueOfObject = 1;
            rectAttack.x = 8;
            rectAttack.y = 8;
            rectAttack.width = 32;
            rectAttack.height = 32;
        }
        else if(name == "wood Sword"){
            attackValueOfObject = 2;
            description = "[" + name + "] \nThe old weapon from\nthe past";
            rectAttack.x = 6;
            rectAttack.y = 6;
            rectAttack.width = 36;
            rectAttack.height = 36;
        }
        else if(name == "wood Shield"){
            defenceValueOfObject = 1;
            description = "[" + name + "] \nThe wood shield";
        }
        else if(name == "blue Shield"){
            defenceValueOfObject = 3;
            description = "[" + name + "]\nModern shield";
            setDefaultRectCollision(0,0,48,48);
        }

    }
}
