package es.pagoru.project_upisoft.game.player.races;

import es.pagoru.project_upisoft.util.StringBuilder;
import es.pagoru.project_upisoft.game.player.Faction;
import es.pagoru.project_upisoft.game.player.Player;

/**
 * Created by pablo on 28/2/17.
 */
public class Human extends Player {

    private boolean intelligence;

    public Human(){ }

    public Human(
            String name,
            float attackPower,
            Faction faction,
            boolean intelligence
    ) {
        super(name, attackPower, faction);
        this.intelligence = intelligence;
    }

    public void setIntelligence(boolean intelligence) {
        this.intelligence = intelligence;
    }

    public boolean hasIntelligence() {
        return intelligence;
    }

    @Override
    public float getAverageAttackPower() {
        return
                (getAttackPower() * (hasIntelligence() ? 1.33f : 1))
                + (getAverageWeaponsAttackPower() / getWeaponListCount())
                ;
    }

    @Override
    public String getInfo(int position) {
        return new StringBuilder()
                .ln("Player " + position + ":") //Humand After All
                .ln(getName() + "is an Human in " + getFaction().getName() + " faction")
                .ln("Intelligence " + (hasIntelligence() ? " Yeah!" : " Nah!"))
                .ln("Attack Power without weapons: " + getAttackPower())
                .ln(getWeaponListButBeautifulForTheExam())
                .ln("Average Attack Power: " + getAverageAttackPower())
                .build();
    }
}
