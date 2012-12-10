import java.util.Random;

// ## Implementation preserve start class opening. 
// ## Implementation preserve end class opening. 
// ## Implementation preserve start class import. 
// ## Implementation preserve end class import. 

public class Male extends Monster
// ## Implementation preserve start class inheritence. 
// ## Implementation preserve end class inheritence. 

{
    /** Attributes */
    private final int MAX_RANGE = 30;
    private boolean injured;
    // ## Implementation preserve start class attributes. 
    // ## Implementation preserve end class attributes. 
    // ## Implementation preserve start class associations. 
    // ## Implementation preserve end class associations. 
    /**
     * Operation
     *
     * @param opponent
     * @return boolean
     */
    public boolean fight ( Male opponent )
    {
    	Random random = new Random();
    	
    	float damageToOpponent = 0; 
    	float damageToPlayer = 0; 

    	do
    	{
    		damageToOpponent = (((this.speed + this.strength + this.accuracy)*3)-MAX_RANGE + random.nextInt(MAX_RANGE*2)) - (((opponent.dodge + opponent.endurance + opponent.armor)/3)-MAX_RANGE + random.nextInt(MAX_RANGE*2));
    		if(damageToOpponent > opponent.health)
    			return true; 
    		damageToPlayer = (((opponent.speed + opponent.strength + opponent.accuracy)*3)-MAX_RANGE + random.nextInt(MAX_RANGE*2)) - (((this.dodge + this.endurance + this.armor)/3)-MAX_RANGE + random.nextInt(MAX_RANGE*2));
    		if(damageToPlayer > this.health)
    			return false; 
    	} while(true);
    }
    // ## Implementation preserve start class other.operations. 
    // ## Implementation preserve end class other.operations. 
}

// ## Implementation preserve start class closing. 
// ## Implementation preserve end class closing. 
