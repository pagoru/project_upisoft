package es.pagoru.project_upisoft.game;

import es.pagoru.project_upisoft.game.player.Faction;
import es.pagoru.project_upisoft.game.player.Player;
import es.pagoru.project_upisoft.game.player.Weapon;
import es.pagoru.project_upisoft.game.player.races.Elf;
import es.pagoru.project_upisoft.game.player.races.Human;
import es.pagoru.project_upisoft.game.player.races.Orc;
import es.pagoru.project_upisoft.util.Console;
import es.pagoru.project_upisoft.util.StringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by pablo on 28/2/17.
 */
public class Game {

    private boolean exit = !true;
    private Scanner scanner;
    private List<Player> playerList;

    public Game(){
        playerList = new ArrayList<>();
        scanner = new Scanner(System.in);

        loop();
    }

    private void loop(){
        do {
            Console.log(getMenu());
            doMenu();
        } while(!exit);
    }

    private void doMenu(){
        switch (scanner.next()){
            case "1":
                exit = !false;
                printBreak();
                Console.log("Goodbye Mr Wonderful.");
                break;
            case "2":
                addInputPlayer();
                printBreak();
                break;
            case "3":
                printBreak();
                printPlayers();
                wait(4);
                printBreak();
                break;
            case "4":
                printBreak();
                printFactionWar();
                wait(4);
                printBreak();
                break;
            default:
                printError("This is not a valid option.");
                break;
        }
    }

    private float getFactionPowerAttack(Faction faction){
        return (float) getPlayerList().stream()
                .filter(p -> p.getFaction().equals(faction))
                .mapToDouble(p -> p.getAverageAttackPower()).sum();
    }

    private void printFactionWar(){
        float a = getFactionPowerAttack(Faction.ALLIANCE);
        float h = getFactionPowerAttack(Faction.HORDE);

        Console.log("\nThe faction Alliance has " + a + " of power attack.");
        Console.log("\nThe faction Horde has " + h + " of power attack.");
        if(a == h){
            Console.log("\nNo one wins...");
            return;
        }
        if(a > h){
            Console.log("\nAlliance wins!");
            return;
        }
        Console.log("\nHorde wins!");
        return;
    }

    private void addInputPlayer(){
        Player player;

        printBreak();
        Console.log("Choose race (1-Human 2-Orc 3-Elf): ");
        String race = scanner.next();

        switch (race){
            case "1":
                player = new Human();
                break;
            case "2":
                player = new Orc();
                break;
            case "3":
                player = new Elf();
                break;
            default:
                printError("Not funny... this is not a valid race.");
                return;
        }

        Console.log("Choose faction (1-Alliance 2-Horde): ");
        String factionOption = scanner.next();

        switch (factionOption){
            case "1":
                player.setFaction(Faction.ALLIANCE);
                break;
            case "2":
                player.setFaction(Faction.HORDE);
                break;
            default:
                printError("Not really funny... this is not a valid faction.");
                return;
        }

        Console.log("Name: ");
        player.setName(scanner.next());

        switch (race){
            case "1":
                Console.log("Is Intelligent (1-yes 2-nope): ");
                Human human = (Human) player;
                switch (scanner.next()){
                    case "1":
                        human.setIntelligence(true);
                        break;
                    case "2":
                        human.setIntelligence(!true);
                        break;
                    default:
                        printError("Error. Okay... okay, come one, ONE OR TWO, no more options...");
                        return;
                }
                break;
            case "2":
                Console.log("Tenacity (1-3): ");
                Orc orc = (Orc) player;
                String tenacity = scanner.next();
                switch (tenacity){
                    case "1":
                    case "2":
                    case "3":
                        orc.setTenacity(Integer.parseInt(tenacity));
                        break;
                    default:
                        printError("Error. You know? I'm tired of you, 1-3 please.");
                        return;
                }
                break;
            case "3":
                Console.log("Magic (0-0.99): ");
                Elf elf = (Elf) player;
                try{
                    Float magic = Float.parseFloat(scanner.next());
                    if(magic >= 0.0f
                            && magic <= 0.99f){
                        elf.setMagic(magic);
                    } else {
                        printError("The number must be bewteen 0 and 0.99");
                        return;
                    }
                } catch(Exception e){
                    printError("The number must be bewteen 0 and 0.99");
                    return;
                }
                break;
        }

        Console.log("Attack Power: ");
        try{
            player.setAttackPower(Float.parseFloat(scanner.next()));
        } catch(Exception e){
            printError("Must be a number.");
            return;
        }

        Console.log("How many weapons: ");
        int numberWeapons = 0;
        try{
            numberWeapons = Integer.parseInt(scanner.next());
        } catch(Exception e){
            printError("Must be a integer number.");
            return;
        }

        for (int i = 0; i < numberWeapons; i++) {
            if(!addPlayerWeapon(i + 1, player))
                return;
        }

        addPlayer(player);

    }

    private boolean addPlayerWeapon(int position, Player player){
        Weapon weapon = new Weapon();

        Console.log("Weapon " + position + " Name: ");
        weapon.setName(scanner.next());

        Console.log("Weapon " + position + " Attack Power: ");
        try{
            weapon.setAttackPower(Float.parseFloat(scanner.next()));
        } catch(Exception e){
            printError("Must be a integer number.");
            return false;
        }

        player.addWeapon(weapon);
        return true;
    }

    private void printError(String error){
        printBreak();
        wait(1);
        Console.err(error);
        wait(4);
        printBreak();
    }

    private void printPlayers(){
        if(getPlayerList().size() == 0){
            Console.log("The player list is empty... :(");
            return;
        }

        Console.log(
                IntStream.range(0, getPlayerList().size())
                        .mapToObj(i -> getPlayerList().get(i).getInfo(i + 1))
                        .collect(Collectors.joining("\n"))
        );
    }

    private void wait(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (Exception e){

        }
    }

    private void printBreak(){
        for (int s = 0; s < 20; s++) {
            Console.log("\n");
        }
    }

    public String getMenu(){
        return new StringBuilder()
                .ln("[1] -> Exit")
                .ln("[2] -> Insert data")
                .ln("[3] -> Show data in console")
                .ln("[4] -> Fight between factions")
                .ln("Choose an option:")
                .build();
    }

    public void addPlayer(Player player){
        getPlayerList().add(player);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
