package entity;
import EntitySetup.EntitySetup;
import EntitySetup.Projectile;
import java.util.ArrayList;

public class Player extends Entity {
    public int maxMana;
    public int currentMana;
    public Projectile projectile;
    public ArrayList<EntitySetup> inventory = new ArrayList<>();
    public EntitySetup currentWeapon;
    public EntitySetup currentShield;
    public Player(){
        setAttributes();
    }
    public void setAttributes(){
        maxMana = 100;
        currentMana = maxMana;
        this.speed = 4;
        maxLife = 100;
        CurrentLife = maxLife;
        level = 100;
        exp = 0;
        strength = 1;
        dexterity = 3;
        coin = 0;
        nextLevel = 5;
    }

    public int getAttack(){
        return attack;
    }
    public int getDefence(){
        return defence;
    }
    public void setAttack(){
        attack = strength + currentWeapon.attackValueOfObject;
    }
    public void setDefence(){
        defence = dexterity + currentShield.defenceValueOfObject;
    }
}
