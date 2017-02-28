package es.pagoru.project_upisoft.game.player.races;

import es.pagoru.project_upisoft.util.StringBuilder;
import es.pagoru.project_upisoft.game.player.Faction;
import es.pagoru.project_upisoft.game.player.Player;

/**
 * Created by pablo on 28/2/17.
 */
public class Elf extends Player {

    private float magic;

    public Elf(){ }

    public Elf(
            String name,
            float attackPower,
            Faction faction,
            float magic
    ) {
        super(name, attackPower, faction);
        setMagic(magic);
    }

    public void setMagic(float magic) {
        if(magic >= 0.0f
                && magic <= 0.99f)
        this.magic = magic;
    }

    public float getMagic() {
        return magic;
    }

    @Override
    public float getAverageAttackPower() {
        return
                getAttackPower()
                        + (getAverageWeaponsAttackPower() * (1 + getMagic()))
                / getWeaponListCount()
                ;
    }

    @Override
    public String getInfo(int position) {
        return new StringBuilder()
                .ln("Player " + position + ":")
                .ln(getName() + "is an Elf in " + getFaction().getName() + " faction")
                .ln("Magic: " + getMagic())
                .ln("Attack Power without weapons: " + getAttackPower())
                .ln(getWeaponListButBeautifulForTheExam())
                .ln("Average Attack Power: " + getAverageAttackPower())
                .build();
    }
}
