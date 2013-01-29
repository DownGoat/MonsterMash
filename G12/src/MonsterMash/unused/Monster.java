package unused;


import java.util.Date;
import java.util.Random;


public class Monster


{
    /** Attributes */
    private final int MAX_RANGE = 30;
    private boolean injured;
    private int id;
    private String name;
    private Date dob;
    private float genetic_strength;
    private float speed;
    private float accuracy;
    private float endurance;
    private float armor;
    private float dodge;
    private float age_rate;
    private float fertility;
    private float health;
    private float strength;
    
    /**
     * Operation
     *
     * @param opponent
     * @return boolean
     */
    public boolean fight(Monster opponent) {
        do {
            float damageToOpponent = 0;
            float damageToPlayer = 0;
            float attackProduct, attackBonus;
            float defenceProduct, defenceBonus;

            Random rndGen = new Random();

            // Calculating damage to opponent
            attackBonus = ((this.speed + this.strength + this.accuracy) * 3);
            attackProduct = attackBonus - MAX_RANGE + rndGen.nextFloat() + MAX_RANGE * 2;
            System.out.println("Damage to opponent:");
            System.out.printf("attackBonus: %f\n", attackBonus);
            System.out.printf("attackProduct: %f\n", attackProduct);

            defenceBonus = ((opponent.dodge + opponent.endurance + opponent.armor) / 3);
            defenceProduct = defenceBonus - MAX_RANGE + rndGen.nextFloat() + MAX_RANGE * 2;
            
            System.out.printf("defenceBonus: %f\n", defenceBonus);
            System.out.printf("defenceProduct: %f\n", defenceProduct);

            damageToOpponent = attackProduct - defenceProduct;
            if (damageToOpponent > opponent.health) {
                return true;
            }
            
            // Damage to player.
            attackBonus = ((opponent.speed + opponent.strength + opponent.accuracy) * 3);
            attackProduct = attackBonus - MAX_RANGE + rndGen.nextFloat() + MAX_RANGE * 2;
            System.out.println("Damage to Player:");
            System.out.printf("attackBonus: %f\n", attackBonus);
            System.out.printf("attackProduct: %f\n", attackProduct);

            defenceBonus = ((this.dodge + this.endurance + this.armor) / 3);
            defenceProduct = defenceBonus - MAX_RANGE + rndGen.nextFloat() + MAX_RANGE * 2;
            
            System.out.printf("defenceBonus: %f\n", defenceBonus);
            System.out.printf("defenceProduct: %f\n", defenceProduct);

            damageToPlayer = attackProduct - defenceProduct;
            if (damageToPlayer > this.health) {
                return false;
            }
        } while (true);
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public float getGenetic_strength() {
        return genetic_strength;
    }

    public void setGenetic_strength(float genetic_strength) {
        this.genetic_strength = genetic_strength;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public float getDodge() {
        return dodge;
    }

    public void setDodge(float dodge) {
        this.dodge = dodge;
    }

    public float getAge_rate() {
        return age_rate;
    }

    public void setAge_rate(float age_rate) {
        this.age_rate = age_rate;
    }

    public float getFertility() {
        return fertility;
    }

    public void setFertility(float fertility) {
        this.fertility = fertility;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    
}

