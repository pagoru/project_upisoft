package es.pagoru.project_upisoft.game.player;

/**
 * Created by pablo on 28/2/17.
 */
public class Weapon {

    private String name;
    private float attackPower;

    public Weapon(){ }

    public Weapon(String name, float attackPower){
        this.name = name;
        this.attackPower = attackPower;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public String getName() {
        return name;
    }

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }

    public void setName(String name) {
        this.name = name;
    }
}
