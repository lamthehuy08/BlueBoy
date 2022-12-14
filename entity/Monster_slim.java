package entity;



public class Monster_slim extends Entity {
    public Monster_slim() {
       setAttributes();
    }
    @Override
    public void setAttributes() {
        name = "Monster";
        attack = 7;
        defence = 0;
        maxLife = 12;
        CurrentLife = maxLife;
        exp = 2;
        speed = 1;
    }
}
