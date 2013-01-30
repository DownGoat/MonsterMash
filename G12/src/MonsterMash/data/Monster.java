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
    
    private int serverID;
    
    public Monster(String name, String userID){
        this.id = "0";
	this.name = name;
	this.dob = new Date();
        this.dod = new Date();
        Random random = new Random();
        this.baseStrength = random.nextDouble();
        this.currentStrength = random.nextDouble();
        this.baseDefence = random.nextDouble();
        this.currentDefence = random.nextDouble();
        this.baseHealth = random.nextDouble();
        this.currentHealth = random.nextDouble();
	this.fertility = random.nextFloat();
        this.userID = userID;
        this.saleOffer = 0;
        this.breedOffer = 0;
    }

    public Monster(String id, String name, Date dob, Date dod, Double baseStrength, Double currentStrength, Double baseDefence, Double currentDefence, Double baseHealth, Double currentHealth, float fertility, String userID, int saleOffer, int breedOffer) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.dod = dod;
        this.baseStrength = baseStrength;
        this.currentStrength = currentStrength;
        this.baseDefence = baseDefence;
        this.currentDefence = currentDefence;
        this.baseHealth = baseHealth;
        this.currentHealth = currentHealth;
        this.fertility = fertility;
        this.userID = userID;
        this.saleOffer = saleOffer;
        this.breedOffer = breedOffer;
    }

    public Monster() {
        
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
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