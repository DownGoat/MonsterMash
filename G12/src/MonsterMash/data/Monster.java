package data;

import java.util.Date;
import java.util.Random;

public class Monster {
    private final int START_HEALTH = 100;
    
    /** Attributes **/    
    private String id;
    private String name;
    private Date dob;
    private Date dod;
    private Double baseStrength;
    private Double currentStrength;
    private Double baseDefence;
    private Double currentDefence;
    private Double baseHealth;
    private Double currentHealth;
    private float fertility;
    private String userID;
    private int saleOffer;
    private int breedOffer;
    
    public Monster(String name, String userID){
        this.id = "0";
	this.name = name;
	this.dob = new Date();
        this.dod = new Date();
        Random random = new Random();
	this.fertility = random.nextFloat();
        this.userID = userID;
        this.saleOffer = 0;
        this.breedOffer = 0;
    }

    public Monster() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public float getFertility() {
        return fertility;
    }

    public void setFertility(float fertility) {
        this.fertility = fertility;
    }
    
    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDod() {
        return this.dod;
    }

    public Double getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(Double baseStrength) {
        this.baseStrength = baseStrength;
    }

    public Double getCurrentStrength() {
        return currentStrength;
    }

    public void setCurrentStrength(Double currentStrength) {
        this.currentStrength = currentStrength;
    }

    public Double getBaseDefence() {
        return baseDefence;
    }

    public void setBaseDefence(Double baseDefence) {
        this.baseDefence = baseDefence;
    }

    public Double getCurrentDefence() {
        return currentDefence;
    }

    public void setCurrentDefence(Double currentDefence) {
        this.currentDefence = currentDefence;
    }

    public Double getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(Double baseHealth) {
        this.baseHealth = baseHealth;
    }

    public Double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getSaleOffer() {
        return saleOffer;
    }

    public void setSaleOffer(int saleOffer) {
        this.saleOffer = saleOffer;
    }

    public int getBreedOffer() {
        return breedOffer;
    }

    public void setBreedOffer(int breedOffer) {
        this.breedOffer = breedOffer;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setDod(Date dod) {
        this.dod = dod;
    }
    
}