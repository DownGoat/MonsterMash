// ## Implementation preserve start class opening. 
// ## Implementation preserve end class opening. 
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/*import Player;
import Monster;
import FriendRequest;
import FightRequest;
import MarketOffer;
import BreedingOffer;*/
// ## Implementation preserve start class import. 
// ## Implementation preserve end class import. 

public class Player
// ## Implementation preserve start class extends. 
// ## Implementation preserve end class extends. 

// ## Implementation preserve start class inheritence. 
// ## Implementation preserve end class inheritence. 

{
    /** Attributes */
    private int id;
    private String password;
    private String email;
    private ArrayList<Player> friends;
    private int money;
    private ArrayList<Notification> notifications; 
    // ## Implementation preserve start class attributes. 
    // ## Implementation preserve end class attributes. 
    /** Associations */
    private ArrayList<Monster> monsters;
    // ## Implementation preserve start class associations. 
    // ## Implementation preserve end class associations. 
    /**
     * Operation
     *
     * @return 
     */
    
    public Player(int id, String email, String password, int money)
    {
    	this.id = id; 
    	this.email = email;
    	this.password = password;
    	this.money = money;
    	
    	createInitialMonster();
    }
    
    public void createInitialMonster()
    {
    	Random random = new Random();
    	Monster monster;
    	boolean isMale = random.nextBoolean();
		if(isMale)
		{
			monster = new Male();
		}
		else
		{
			monster = new Female(); 
		}
		
		monster.name = "New monster ";
		monster.dob = new Date();
		
		monster.strength = random.nextFloat();
		monster.speed = random.nextFloat();
		monster.accuracy = random.nextFloat();
		monster.endurance = random.nextFloat();
		monster.armor = random.nextFloat();
		monster.dodge = random.nextFloat();
		monster.age_rate = random.nextFloat();
		monster.fertility = random.nextFloat();
		
		monster.health = 100; //?
		
		monsters.add(monster);
    }
    /**
     * Operation
     *
     * @param friend
     * @return 
     */
    public void addFriend ( Player friend )
    {
        friends.add(friend);
    }
    /**
     * Operation
     *
     * @param friend
     * @return 
     */
    public void removeFriend ( Player friend )
    {
        // ## Implementation preserve start class method.removeFriend@@@@Player 
        // ## Implementation preserve end class method.removeFriend@@@@Player 
    	friends.remove(friend);
    }
    /**
     * Operation
     *
     * @param monster
     * @return 
     */
    public void addMonster ( Monster monster )
    {
        // ## Implementation preserve start class method.addMonster@@@@Monster 
        // ## Implementation preserve end class method.addMonster@@@@Monster 
    	monsters.add(monster);
    }
    /**
     * Operation
     *
     * @param monster
     * @return 
     */
    public void removeMonster ( Monster monster )
    {
        // ## Implementation preserve start class method.removeMonster@@@@Monster 
        // ## Implementation preserve end class method.removeMonster@@@@Monster 
    	monsters.remove(monster);
    }
    /**
     * Operation
     *
     * @param notefication
     * @return 
     */
    public void addNotification ( Notification notification )
    {
        // ## Implementation preserve start class method.addNotification@@@@Notefication 
        // ## Implementation preserve end class method.addNotification@@@@Notefication 
    	notifications.add(notification);
    }
    /**
     * Operation
     *
     * @return 
     */
    public void updateMonsters (  )
    {
        // ## Implementation preserve start class method.updateMonsters@@@ 
        // ## Implementation preserve end class method.updateMonsters@@@ 
    	for(int i = 0; i < monsters.size(); i++)
    	{
    		if(monsters.get(i).health <= 0)
    			monsters.remove(i);
    	}
    }
    // ## Implementation preserve start class other.operations. 
    // ## Implementation preserve end class other.operations. 
}

// ## Implementation preserve start class closing. 
// ## Implementation preserve end class closing. 
