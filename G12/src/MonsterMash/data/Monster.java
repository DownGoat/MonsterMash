package data;

import java.util.Date;
import java.util.Random;

public class Monster {
    private final int START_HEALTH = 100;
    
    /** Attributes **/    
    private int id;
    private String name;
    private Date dob;
    private Date dod;
    private float genetic_strength;
    private float age_rate;
    private float fertility;
    private float health;
    private float strength;
    private int userID;
    private int forSale;
    private int forBreed;
    
    public Monster(String name, int userID){
        this.id = 0;
	this.name = name;
	this.dob = new Date();
        this.dod = new Date();
        Random random = new Random();
        this.genetic_strength = random.nextFloat();
	this.strength = random.nextFloat();
	this.fertility = random.nextFloat();
	this.health = START_HEALTH;
        this.userID = userID;
        this.forBreed = 0;
        this.forSale = 0;
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

    public float getGenetic_strength() {
        return genetic_strength;
    }

    public void setGenetic_strength(float genetic_strength) {
        this.genetic_strength = genetic_strength;
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
    
    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDod() {
        return this.dod;
    }

    public int getForSale() {
        return forSale;
    }

    public void setForSale(int forSale) {
        this.forSale = forSale;
    }

    public int getForBreed() {
        return forBreed;
    }

    public void setForBreed(int forBreed) {
        this.forBreed = forBreed;
    }
}