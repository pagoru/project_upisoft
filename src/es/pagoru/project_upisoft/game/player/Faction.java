package es.pagoru.project_upisoft.game.player;

/**
 * Created by pablo on 28/2/17.
 */
public enum Faction {

    ALLIANCE("Alliance"),
    HORDE("Horde");

    private String name;

    Faction(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}