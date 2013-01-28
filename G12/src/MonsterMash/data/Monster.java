package data;

import java.util.Date;
import java.util.Random;

public class Monster {
    private final int START_HEALTH = 100;
    
    /** Attributes **/    
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
    
    public Monster(String name){
        this.id = 0;
	this.name = name;
	this.dob = new Date();
        Random random = new Random();
        this.genetic_strength = random.nextFloat();
	this.strength = random.nextFloat();
	this.speed = random.nextFloat();
	this.accuracy = random.nextFloat();
	this.endurance = random.nextFloat();
	this.armor = random.nextFloat();
	this.dodge = random.nextFloat();
	this.age_rate = random.nextFloat();
	this.fertility = random.nextFloat();
	this.health = START_HEALTH;
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