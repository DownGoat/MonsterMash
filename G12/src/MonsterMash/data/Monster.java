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
    
    private final int MAX_CHILDREN = 10;
    
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
    /**
     * Breeding class to breed new monsters
     * 
     * @param other Monster that is being bred with
     * @return Monster[] and array of the children
     */
    
    public Monster[] breeding(Monster other) {
        Random r = new Random(); 
        int numberofchildren = (int) Math.sqrt(fertility * other.fertility) * MAX_CHILDREN;
    	Monster[] children = new Monster[numberofchildren + 1]; 
        
        for (int i = 0; i<= numberofchildren; i++){
            children[i]=new Monster();
            children[i].dob=new Date();
            //this is assuming that the children go to the owner of the monster that calls the method
            children[i].userID=userID; 
            
            //generating inherited defense
            if(r.nextInt(100)<5){
                 children[i].baseDefence=r.nextDouble();
            }
            else if(r.nextInt(100)<50){
                 children[i].baseDefence=baseDefence;
            }
            else { children[i].baseDefence=other.baseDefence;
            }
            //generating inherited strength
            if(r.nextInt(100)<5){
                 children[i].baseStrength=r.nextDouble();
            }
            else if(r.nextInt(100)<50){
                 children[i].baseStrength=baseStrength;
            }
            else { children[i].baseStrength=other.baseStrength;
            }
            //generating inherited health
            if(r.nextInt(100)<5){
                 children[i].baseHealth=r.nextDouble();
            }
            else if(r.nextInt(100)<50){
                 children[i].baseHealth=baseHealth;
            }
            else { children[i].baseHealth=other.baseHealth;
            }
            //generating inherited fertility
            if(r.nextInt(100)<5){
                 children[i].fertility=r.nextFloat();
            }
            else if(r.nextInt(100)<50){
                 children[i].fertility=fertility;
            }
            else { children[i].fertility=other.fertility;
            }
             
            
        }
            
        return children;
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

    public void updateStats(Double strength, Double defence, Double health) {
        this.currentStrength = strength;
        this.currentDefence = defence;
        this.currentHealth = health;
    }
    
}