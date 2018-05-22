import java.util.Random;

public class Wolf extends Enemy{

    private int health;
    private int strength;
    private int agility;

    public Wolf(int health, int strength, int agility){
        this.health = health;
        this.strength = strength;
        this.agility = agility;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public int attack() {
        Random r = new Random();
        int randomModifier = r.nextInt(4);
        int damage = (this.getStrength()*randomModifier);
        return damage;
    }

    @Override
    public int defend() {
        Random r = new Random();
        int randomModifier = r.nextInt(4);
        int defense = (this.getAgility()*randomModifier);
        return defense;
    }
}
