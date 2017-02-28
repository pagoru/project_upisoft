package es.pagoru.project_upisoft.game.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by pablo on 28/2/17.
 * a.k.a. Personatge
 */
public abstract class Player {

    protected String name;
    protected float attackPower;
    protected Faction faction;

    protected List<Weapon> weaponList;

    public Player() {
        this.weaponList = new ArrayList<>();
    }

    public Player(
            String name,
            float attackPower,
            Faction faction
    ){
        this.name = name;
        this.attackPower = attackPower;
        this.faction = faction;

        this.weaponList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public Weapon getWeapon(int position){
           return getWeaponList().get(position);
    }

    public String getWeaponListButBeautifulForTheExam(){
        return IntStream.range(0, getWeaponListCount())
                .mapToObj(i -> {
                    Weapon w = getWeapon(i);
                    return "Weapon " + i + ": " + w.getName() + " - " + w.getAttackPower();
                }).collect(Collectors.joining("\n"));
    }

    public int getWeaponListCount(){
        return getWeaponList().size();
    }

    public float getAverageWeaponsAttackPower(){
        return (float) weaponList.stream()
                .mapToDouble(w -> w.getAttackPower()).sum();
    }

    public abstract String getInfo(int position);

    public abstract float getAverageAttackPower();


    public void addWeapon(Weapon weapon){
        getWeaponList().add(weapon);
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }
}
