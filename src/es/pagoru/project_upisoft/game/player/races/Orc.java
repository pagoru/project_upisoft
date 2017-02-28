package es.pagoru.project_upisoft.game.player.races;

import es.pagoru.project_upisoft.util.StringBuilder;
import es.pagoru.project_upisoft.game.player.Faction;
import es.pagoru.project_upisoft.game.player.Player;

/**
 * Created by pablo on 28/2/17.
 */
public class Orc extends Player {

    private int tenacity;

    public Orc(){ }

    public Orc(
            String name,
            float attackPower,
            Faction faction,
            int tenacity
    ) {
        super(name, attackPower, faction);
        setTenacity(tenacity);
    }

    public void setTenacity(int tenacity) {
        if(tenacity >= 1
                && tenacity <= 3)
        this.tenacity = tenacity;
    }

    public int getTenacity() {
        return tenacity;
    }

    @Override
    public float getAverageAttackPower() {
        return
                getAttackPower()
                        + ((getAverageWeaponsAttackPower() / getWeaponListCount())
                        * getTenacity()/2 //Literal del enunciat
                )
                ;
    }

    @Override
    public String getInfo(int position) {
        return new StringBuilder()
                .ln("Player " + position + ":")
                .ln(getName() + "is an Orc in " + getFaction().getName() + " faction")
                .ln("Tenacity: " + getTenacity())
                .ln("Attack Power without weapons: " + getAttackPower())
                .ln(getWeaponListButBeautifulForTheExam())
                .ln("Average Attack Power: " + getAverageAttackPower())
                .build();
    }
}
