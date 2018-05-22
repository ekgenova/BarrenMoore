import java.util.Random;

public class Player {


    /*Variables that define a Player.
    Name = name of the player
    Health = health of the player. Starts at 10, goes down if injured. When at 0, player dies and game over.
    Strength = physical strength of the player. Starts at value and can be increased by doing physical labour or fighting.
    Magic = magic ability of the player. Starts at 0 and can be increased if the right point of interest is found.
    Agility = agility of the player. Increased while running or fighting enemies.
    Luck = luck of the player. Increased when random events work out in players favour.
     */
    private String name;
    private int health;
    private int strength;
    private int magic;
    private int agility;
    private int luck;
    private int distFromOriginNorth;
    private int distFromOriginSouth;
    private int distFromOriginEast;
    private int distFromOriginWest;


    //Constructor of Player object

    public Player(String name,int health, int strength, int magic, int agility, int luck, int distFromOriginNorth, int distFromOriginSouth, int distFromOriginEast, int distFromOriginWest){
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.magic = magic;
        this.agility = agility;
        this.luck = luck;
        this.distFromOriginNorth = distFromOriginNorth;
        this.distFromOriginSouth = distFromOriginSouth;
        this.distFromOriginEast = distFromOriginEast;
        this.distFromOriginWest = distFromOriginWest;
    }

    //Getters for player object

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getMagic() {
        return magic;
    }

    public int getAgility() {
        return agility;
    }

    public int getLuck() {
        return luck;
    }

    public int getDistFromOriginNorth() {
        return distFromOriginNorth;
    }

    public int getDistFromOriginSouth() {
        return distFromOriginSouth;
    }

    public int getDistFromOriginWest() {
        return distFromOriginWest;
    }

    public int getDistFromOriginEast() {
        return distFromOriginEast;
    }

    //Setters for player object


    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setDistFromOriginNorth(int distFromOriginNorth) {
        this.distFromOriginNorth = distFromOriginNorth;
    }

    public void setDistFromOriginSouth(int distFromOriginSouth) {
        this.distFromOriginSouth = distFromOriginSouth;
    }

    public void setDistFromOriginWest(int distFromOriginWest) {
        this.distFromOriginWest = distFromOriginWest;
    }

    public void setDistFromOriginEast(int distFromOriginEast) {
        this.distFromOriginEast = distFromOriginEast;
    }


            /*Methods player can use
    toString override to print player's current stats
     */

    @Override
    public String toString() {
        String playerStats = "" + this.getName().toUpperCase() + "'S STATS: \nHealth: " + this.getHealth() + "\nStrength: " + this.getStrength() + "\nMagic: " +
                this.getMagic() + "\nAgility: " + this.getAgility() + "\nLuck: " + this.getLuck() + "\nPosition from origin N: " + this.getDistFromOriginNorth() +
                "\nPosition from origin S:" + this.getDistFromOriginSouth() + "\nPosition from origin E:" + this.getDistFromOriginEast() + "\nPosition from origin W:" + this.getDistFromOriginWest();
        return playerStats;
    }

    public int attack(){
        Random r = new Random();
        System.out.println("You attack.");
        int randomModifier = r.nextInt(4);
        int damage = (this.getStrength()*randomModifier);
        return damage;
    }

    public int defend(){
        Random r = new Random();
        System.out.println("You defend.");
        int randomModifier = r.nextInt(4);
        int defense = (this.getAgility()*randomModifier);
        return defense;
    }

}
