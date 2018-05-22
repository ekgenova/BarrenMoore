
import java.util.Scanner;

public class BarrenMoorGame {

    private String name;
    private String command;
    private int turns = 0;
    private int drinksConsumed = 0;
    private Scanner scan = new Scanner(System.in);
    private Boolean blueBottle = false;
    private Boolean isPlaying;
    private Boolean caveExplored = false;

    private Item tablet = new Item("Tablet", "Tablet device");
    private Item strengthBottle = new Item("red", "Shiny bottle of red liquid");
    private Item magicBottle = new Item("blue", "Shiny empty bottle");
    private Item agilityBottle = new Item("green", "Shiny bottle of green liquid");
    private Item luckBottle = new Item("yellow", "Shiny bottle of yellow liquid");

    private Player player = new Player("", 10, 2, 0, 2, 1, 0, 0, 0, 0);

    private PointOfInterest cave = new PointOfInterest("cave", 2,0,0,0);
    private PointOfInterest lake = new PointOfInterest("lake", 0, 1, 0, 0);
    private PointOfInterest house = new PointOfInterest("house", 0,0,2,0);

    private Wolf wolf = new Wolf(5,2,3);
    private Goblin goblin = new Goblin(10, 4, 1);

    private Bag bag = new Bag();


    public void play() {

        isPlaying = true;
        bag.addItem(tablet);
        bag.addItem(strengthBottle);
        bag.addItem(magicBottle);
        bag.addItem(agilityBottle);
        bag.addItem(luckBottle);

        //Checks for empty name
        do {
            System.out.println("To play please type in your name:");
            name = scan.nextLine();
        } while (name.equals(""));

        player.setName(name);
        System.out.println("Welcome " + name + ". Have fun!");
        System.out.println("You wake up from the rain pouring down on you. You are laying in a large puddle of water " +
                "" +
                "and are completely soaked.\nYou sit up and have a look around. As far as your eyes can see you are surrounded by a grim looking moor.");
        System.out.println("As you stand up you notice a bag next to you that has your name engraved in it. Inside there are a few items: \n" +
                "1. A device that reminds you of a tablet with one button.\n2. Four tiny bottles of coloured liquid - three full, one empty.\n" +
                "3. A piece of paper folded neatly in half. \n4. A device that looks like a merge between a watch and a compass that has 4 hands each one pointing to a different direction.\n" +
                "Each hand is a different length and points towards a direction. Your gut feeling says this is important so you put it on your wrist.");

        do {

            System.out.println("What will you do now?");
            do {
                System.out.println("Commands available: Go [north] / Go [south] / Go [west] / Go [east] / [stay] where you are / [inspect] items closely / [look] at watch");
                command = scan.nextLine();
            }
            while (!(command.equalsIgnoreCase("north") || command.equalsIgnoreCase("south") || command.equalsIgnoreCase("west") || command.equalsIgnoreCase("east") ||
                    command.equalsIgnoreCase("stay") || command.equalsIgnoreCase("inspect") || command.equalsIgnoreCase("look")));

            if (command.equalsIgnoreCase("inspect")) {
                inspectBag();
            }

            if (command.equalsIgnoreCase("look")) {
                lookAtWatch();
            }

            if (command.equalsIgnoreCase("north")) {
                goNorth();
            }

            if (command.equalsIgnoreCase("south")) {
                goSouth();
            }

            if (command.equalsIgnoreCase("east")) {
                goEast();
            }

            if (command.equalsIgnoreCase("west")) {
                goWest();
            }

            if (command.equalsIgnoreCase("stay")){
                System.out.println("You decide to stay in the same place and do nothing. A storm rages on above you and after some time a lightning strikes you and you die on the spot. GAME OVER.");
                isPlaying = false;
            }

            if (player.getDistFromOriginNorth()==cave.getDistFromOriginNorth()){
                System.out.println("You see a cave covered by a rock.");
                do{
                    System.out.println("Commands available: [push] rock / [leave] and go back to origin point");
                    command = scan.nextLine();

                }while(!(command.equalsIgnoreCase("push") || command.equalsIgnoreCase("leave")));

                if (command.equalsIgnoreCase("push")) {
                    if (caveExplored = false) {
                        if (player.getStrength() > 3) {
                            System.out.println("It takes a great deal of effort but you push the rock away from the entrance. You can now explore inside the cave.");
                            System.out.println("You cautiously go inside. The cave is very small and not that dark. You can see drawings on the walls. Someone lives here.");
                            System.out.println("As you are looking around you hear a screech and turn around. A goblin is at the entrance.");

                            do {
                                System.out.println("The goblin charges for you. You brace yourself.");

                                int goblinAttack = goblin.attack();
                                int playerDefense = player.defend();

                                if (goblinAttack > playerDefense) {
                                    System.out.println("Your defense is weaker than his attack. You are hurt by his attack.");
                                    if (goblinAttack > player.getHealth()) {
                                        System.out.println("The goblin's attack finishes you off. You die. GAME OVER.");
                                        player.setHealth(0);
                                        isPlaying = false;
                                    } else {
                                        System.out.println("You lose " + goblinAttack + " health. Now it's your turn to attack!");
                                        player.setHealth(player.getHealth()-goblinAttack);

                                    }
                                } else if (goblinAttack <= playerDefense) {
                                    System.out.println("You dodge his attack and come out unscathed. Now it's your turn to attack!");
                                }

                                int goblinDefense = goblin.defend();
                                int playerAttack = player.attack();

                                if (playerAttack > goblinDefense) {
                                    System.out.println("your attack is stronger than the goblin's defense. You hurt the goblin.");
                                    if (goblin.getHealth() < playerAttack) {
                                        System.out.println("Your attack is strong and you kill the goblin.");
                                        System.out.println("You feel your strength increasing.");
                                        goblin.setHealth(0);
                                        player.setStrength(player.getStrength()+1);
                                    } else {
                                        System.out.println("Your attack damages the goblin and takes " + playerAttack + " of his health.");
                                        goblin.setHealth(goblin.getHealth()-playerAttack);
                                    }
                                }
                            }while(player.getHealth()>0 || goblin.getHealth()>0);

                            System.out.println("After an exhausting battle you return to the origin point.");
                            player.setDistFromOriginNorth(0);
                            caveExplored = true;

                        } else {
                            System.out.println("You don't have enough strength to push the rock away right now.");
                        }
                    }
                }
                else if (command.equalsIgnoreCase("leave")){
                    System.out.println("You leave the cave alone and go back to the origin point.");
                    player.setDistFromOriginNorth(0);
                }
            }

            if (player.getDistFromOriginSouth()==lake.getDistFromOriginSouth()){
                System.out.println("You see a medium sized lake. You can go for a swim!");
                do{
                    System.out.println("Commands available: [swim] in lake / [leave] and go back to origin point");
                    command = scan.nextLine();

                }while(!(command.equalsIgnoreCase("swim") || command.equalsIgnoreCase("leave")));

                if (command.equalsIgnoreCase("swim")){
                    System.out.println("You go for a swim. The swimming further trains your body and improves your agility.");
                    player.setAgility(player.getAgility()+1);
                    System.out.println("You return to the origin point.");
                    player.setDistFromOriginSouth(0);
                } else if (command.equalsIgnoreCase("leave")){
                    System.out.println("You leave the lake alone and go back to the origin point.");
                    player.setDistFromOriginSouth(0);
                }
            }

            if (player.getDistFromOriginEast()==house.getDistFromOriginEast()){
                System.out.println("You see a house in the distance. From inside you hear screaming. You run inside immediately.");
                System.out.println("A woman is being attacked by a wolf. This is who you are supposed to save.");
                System.out.println("You attack the wolf!");

                do {
                    System.out.println("The goblin charges for you. You brace yourself.");

                    int wolfDefense = wolf.defend();
                    int playerAttack = player.attack();

                    if (playerAttack > wolfDefense) {
                        System.out.println("Your attack is stronger than the wolf's defense. You damage it.");
                        if (playerAttack > wolf.getHealth()) {
                            System.out.println("You kill the wolf and save the woman. She turns out to be a wizard who desperately needed your help. She transports you back to your world. YOU WIN.");
                            wolf.setHealth(0);
                            isPlaying = false;
                        } else {
                            System.out.println("You damage the wolf for  " + playerAttack + " health. Now it's his turn to attack!");
                            wolf.setHealth(wolf.getHealth()-playerAttack);

                        }
                    } else if (playerAttack <= wolfDefense) {
                        System.out.println("The wolf dodges your attack and comes out unscathed. Now it's his turn to attack!");
                    }

                    int playerDefense = player.defend();
                    int wolfAttack = wolf.attack();

                    if (wolfAttack > playerDefense) {
                        System.out.println("The wolf's attack is stronger than your defense. You are hurt.");
                        if (player.getHealth() < wolfAttack) {
                            System.out.println("The wolf kills you and the woman. GAME OVER.");
                            player.setHealth(0);
                            isPlaying = false;

                        } else {
                            System.out.println("The wolf damages you for " + wolfAttack + " of your health.");
                            player.setHealth(player.getHealth()-wolfAttack);
                        }
                    }
                }while(player.getHealth()>0 || wolf.getHealth()>0);
            }


            } while (isPlaying);
    }

    private void goNorth() {
        if (player.getDistFromOriginSouth() == 0) {
            player.setDistFromOriginNorth(player.getDistFromOriginNorth() + 1);
        } else {
            player.setDistFromOriginSouth(player.getDistFromOriginSouth() - 1);
        }
        System.out.println("You walked towards the North.");

    }

    private void goSouth() {
        if (player.getDistFromOriginNorth() == 0) {
            player.setDistFromOriginSouth(player.getDistFromOriginSouth() + 1);
        } else {
            player.setDistFromOriginNorth(player.getDistFromOriginNorth() - 1);
        }
        System.out.println("You walked towards the South.");

    }

    private void goEast() {
        if (player.getDistFromOriginWest() == 0) {
            player.setDistFromOriginEast(player.getDistFromOriginEast() + 1);
        } else {
            player.setDistFromOriginWest(player.getDistFromOriginWest() - 1);
        }
        System.out.println("You walked towards the East.");

    }

    private void goWest() {
        if (player.getDistFromOriginEast() == 0) {
            player.setDistFromOriginWest(player.getDistFromOriginWest() + 1);
        } else {
            player.setDistFromOriginEast(player.getDistFromOriginEast() - 1);
        }
        System.out.println("You walked towards the West.");

    }

    private void lookAtWatch(){
        System.out.println("You look at the watch on your wrist. Each hand is pointing precisely in a direction and doesn't move. \n" +
        "However, it appears the more you walk in a direction the shorter that hand becomes.");
    }


    /*Drink bottle method
    increases increment of drinksConsumed to make sure no more than 2 drinks are consumed. If yes, you die.
    prints out what kind of benefit you gain, increases player's stats
    removes that bottle from your items
     */

    private void drinkBottle(Item item) {
        drinksConsumed++;

        if (drinksConsumed > 2) {
            System.out.println("The power from the bottles becomes too much for your human body. You fall to the ground and writhe in pain.\n" +
                    "The power corrupts you and you die.\n GAME OVER.");
            isPlaying = false;
        }

        if (isPlaying) {
            switch (item.getID()) {
                case "red":
                    System.out.println("You drank the red liquid and threw the bottle away. You feel power surging through you and your body getting stronger.");
                    player.setStrength(player.getStrength() + 3);
                    break;
                case "blue":
                    System.out.println("You drank the blue liquid and threw the bottle away. You feel magical power surging through you. Sparks begin arcing from your fingertips. You've gained the power of electricity.");
                    player.setMagic(player.getMagic() + 3);
                    break;
                case "green":
                    System.out.println("You drank the green liquid and threw the bottle away. You feel power surging through you and your reactions getting quicker.");
                    player.setAgility(player.getAgility() + 3);
                    break;
                case "yellow":
                    System.out.println("You drank the yellow liquid and threw the bottle away. You feel like the world is on your side. You feel luckier.");
                    player.setLuck(player.getLuck() + 3);
                    break;
            }
        }

        bag.removeItem(item);
    }

    /*Method to inspect items in your bag
    Provides options to do multiple things:
    1. read note
    2. check bottles (stored in checkBottles method
    3. use tablet
    4. stop inspecting
     */

    private void inspectBag() {
        System.out.println("You decide to inspect your items closer.");
        do {
            System.out.println("Commands available: [read] note / [check] bottles / [use] tablet / [stop] inspecting");
            command = scan.nextLine();
        }
        while (!(command.equalsIgnoreCase("read") || command.equalsIgnoreCase("check") || command.equalsIgnoreCase("use") || command.equalsIgnoreCase("stop")));

        if (command.equalsIgnoreCase("read")) {
            System.out.println("You take the note out of the bag and open it. It reads: ");
            System.out.println(name.toUpperCase() + ", PLEASE HELP. I AM LOST. IN DANGER. I LEFT MY SPECIAL DRINKS. USE THEM TO GET STRONGER. BUT BE CAREFUL.\n" +
                    "DRINK MORE THAN TWO AND YOU WILL DIE. RED STRENGTH. BLUE MAGIC. GREEN AGILITY. YELLOW LUCK. HELP ME.");
            System.out.println("It sounds like someone took you here to help them. Hopefully, you can find them and leave this place.");

            inspectAnother();
        }

        if (command.equalsIgnoreCase("check")) {
            checkBottles();
        }

        if (command.equalsIgnoreCase("use")) {
            System.out.println("You take out the tablet and press the button. It lights up and shows a picture of you and a table. The table reads: ");
            System.out.println(player.toString());
            inspectAnother();
        }

        if (command.equalsIgnoreCase("stop")) {
            System.out.println("You close the bag and stop inspecting.");
        }

    }


    private void inspectAnother(){
        do {
            System.out.println("Would you like to inspect another item? [yes/no]");
            command = scan.nextLine();
        } while (!(command.equalsIgnoreCase("no") || command.equalsIgnoreCase("yes")));

        if (command.equalsIgnoreCase("yes")) {
            inspectBag();
        }
    }

    /*Method to check the bottles in your bag
    Contains nested if statements that check for multiple things:
    1. whether the blue liquid has been acquired
    2. whether a bottle has already been drank and discarded
    3. Only 2 bottles may be missing from the bag at any time
    4. Provides options to drink bottle which is stored in drinkBottle method
     */

    private void checkBottles() {
        //If Blue bottle is full
        if (blueBottle) {

            //bag contains all 4 bottles. none have been drunk
            if (bag.bag.contains(strengthBottle) && bag.bag.contains(magicBottle) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                System.out.println("You check the bottles in your bag. There are 4 in total. The bottles are full with shiny colored liquid.");
                System.out.println("The first bottle is red. The second is blue. The third is green. The fourth is yellow. Each liquid shines with a magical glow.");
                do {
                    System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + magicBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [" +
                            luckBottle.getID() + "], drink [nothing]");
                    command = scan.nextLine();

                    switch (command) {
                        case "red":
                            drinkBottle(strengthBottle);
                            break;
                        case "blue":
                            drinkBottle(magicBottle);
                            break;
                        case "green":
                            drinkBottle(agilityBottle);
                            break;
                        case "yellow":
                            drinkBottle(luckBottle);
                            break;
                        case "nothing":
                            System.out.println("You don't drink any of the bottles.");
                            break;
                    }

                    if (isPlaying) {
                        inspectAnother();
                    }
                }
                while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("blue") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));

            }

            //Bag doesn't contain red anymore
            else if (!(bag.bag.contains(strengthBottle))) {
                //BAG CONTAINS BLUE, GREEN, YELLOW
                if (bag.bag.contains(magicBottle) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is blue. The second is green. The third is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + magicBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "blue":
                                drinkBottle(magicBottle);
                                break;
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("blue") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS GREEN, YELLOW, NOT BLUE
                else if (!(bag.bag.contains(magicBottle)) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is green. The second is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + agilityBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS BLUE, YELLOW, NOT GREEN
                else if (bag.bag.contains(magicBottle) && !(bag.bag.contains(agilityBottle)) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is blue. The second is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + magicBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "blue":
                                drinkBottle(magicBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("blue") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS BLUE, GREEN, NOT YELLOW
                if (bag.bag.contains(magicBottle) && bag.bag.contains(agilityBottle) && !(bag.bag.contains(luckBottle))) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is blue. The second is green. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + magicBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "blue":
                                drinkBottle(magicBottle);
                                break;
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("blue") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("nothing")));
                }
            }

            //Bag doesn't contain blue anymore
            else if ((bag.bag.contains(strengthBottle))) {

                //BAG CONTAINS RED, GREEN, YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is green. The third is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS RED, YELLOW, NOT GREEN
                if (bag.bag.contains(strengthBottle) && !(bag.bag.contains(agilityBottle)) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS RED, GREEN, NOT YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(agilityBottle) && !(bag.bag.contains(luckBottle))) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is green. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("nothing")));
                }
            }

            //Bag doesn't contain green anymore
            else if (!(bag.bag.contains(agilityBottle))) {

                //BAG CONTAINS RED, BLUE, YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(magicBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is blue. The third is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + magicBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "blue":
                                drinkBottle(magicBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("blue") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS RED, BLUE, NOT YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(magicBottle) && !(bag.bag.contains(luckBottle))) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is blue. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + magicBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "blue":
                                drinkBottle(magicBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("blue") || command.equalsIgnoreCase("nothing")));
                }
            }

            //bag doesn't contain yellow anymore
            else if (!(bag.bag.contains(luckBottle))) {
                System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                System.out.println("The first bottle is red. The second is blue. The third is green. Each liquid shines with a magical glow.");
                do {
                    System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + magicBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [nothing]");
                    command = scan.nextLine();

                    switch (command) {
                        case "red":
                            drinkBottle(strengthBottle);
                            break;
                        case "blue":
                            drinkBottle(magicBottle);
                            break;
                        case "green":
                            drinkBottle(agilityBottle);
                            break;
                        case "nothing":
                            System.out.println("You don't drink any of the bottles.");
                            break;
                    }
                    if (isPlaying) {
                        inspectAnother();
                    }

                }
                while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("blue") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("nothing")));
            }
        }

        //Blue bottle is empty
        else if (!blueBottle) {

            //bag contains all 4 bottles. none have been drunk
            if (bag.bag.contains(strengthBottle) && bag.bag.contains(magicBottle) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                System.out.println("You check the bottles in your bag. There are 4 in total. The bottles are full with shiny colored liquid.");
                System.out.println("The first bottle is red. The second is empty. The third is green. The fourth is yellow. Each liquid shines with a magical glow.");
                do {
                    System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [" +
                            luckBottle.getID() + "], drink [nothing]");
                    command = scan.nextLine();

                    switch (command) {
                        case "red":
                            drinkBottle(strengthBottle);
                            break;
                        case "green":
                            drinkBottle(agilityBottle);
                            break;
                        case "yellow":
                            drinkBottle(luckBottle);
                            break;
                        case "nothing":
                            System.out.println("You don't drink any of the bottles.");
                            break;
                    }
                    if (isPlaying) {
                        inspectAnother();
                    }

                }
                while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
            }

            //Bag doesn't contain red anymore
            else if (!(bag.bag.contains(strengthBottle))) {
                //BAG CONTAINS BLUE, GREEN, YELLOW
                if (bag.bag.contains(magicBottle) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is empty. The second is green. The third is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + agilityBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS GREEN, YELLOW, NOT BLUE
                else if (!(bag.bag.contains(magicBottle)) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is green. The second is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + agilityBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS BLUE, YELLOW, NOT GREEN
                else if (bag.bag.contains(magicBottle) && !(bag.bag.contains(agilityBottle)) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is empty. The second is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS BLUE, GREEN, NOT YELLOW
                if (bag.bag.contains(magicBottle) && bag.bag.contains(agilityBottle) && !(bag.bag.contains(luckBottle))) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is empty. The second is green. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + agilityBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("green") || command.equalsIgnoreCase("nothing")));
                }
            }

            //Bag doesn't contain blue anymore
            else if ((bag.bag.contains(strengthBottle))) {

                //BAG CONTAINS RED, GREEN, YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(agilityBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is green. The third is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS RED, YELLOW, NOT GREEN
                if (bag.bag.contains(strengthBottle) && !(bag.bag.contains(agilityBottle)) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS RED, GREEN, NOT YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(agilityBottle) && !(bag.bag.contains(luckBottle))) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is green. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "green":
                                drinkBottle(agilityBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("nothing")));
                }
            }

            //Bag doesn't contain green anymore
            else if (!(bag.bag.contains(agilityBottle))) {

                //BAG CONTAINS RED, BLUE, YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(magicBottle) && bag.bag.contains(luckBottle)) {
                    System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is empty. The third is yellow. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + luckBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "yellow":
                                drinkBottle(luckBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("yellow") || command.equalsIgnoreCase("nothing")));
                }

                //BAG CONTAINS RED, BLUE, NOT YELLOW
                if (bag.bag.contains(strengthBottle) && bag.bag.contains(magicBottle) && !(bag.bag.contains(luckBottle))) {
                    System.out.println("You check the bottles in your bag. There are 2 in total. The bottles are full with shiny colored liquid.");
                    System.out.println("The first bottle is red. The second is empty. Each liquid shines with a magical glow.");
                    do {
                        System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [nothing]");
                        command = scan.nextLine();

                        switch (command) {
                            case "red":
                                drinkBottle(strengthBottle);
                                break;
                            case "nothing":
                                System.out.println("You don't drink any of the bottles.");
                                break;
                        }
                        if (isPlaying) {
                            inspectAnother();
                        }

                    }
                    while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("nothing")));
                }
            }

            //bag doesn't contain yellow anymore
            else if (!(bag.bag.contains(luckBottle))) {
                System.out.println("You check the bottles in your bag. There are 3 in total. The bottles are full with shiny colored liquid.");
                System.out.println("The first bottle is red. The second is empty. The third is green. Each liquid shines with a magical glow.");
                do {
                    System.out.println("Commands available: drink [" + strengthBottle.getID() + "], drink [" + agilityBottle.getID() + "], drink [nothing]");
                    command = scan.nextLine();

                    switch (command) {
                        case "red":
                            drinkBottle(strengthBottle);
                            break;
                        case "green":
                            drinkBottle(agilityBottle);
                            break;
                        case "nothing":
                            System.out.println("You don't drink any of the bottles.");
                            break;
                    }
                    if (isPlaying) {
                        inspectAnother();
                    }
                }
                while (!(command.equalsIgnoreCase("red") || command.equalsIgnoreCase("green") || command.equalsIgnoreCase("nothing")));
            }
        }
    }


}

