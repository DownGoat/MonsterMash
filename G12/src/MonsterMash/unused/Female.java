package unused;

import data.Monster;
import java.util.Date;
import java.util.Random;

// ## Implementation preserve start class opening. 
// ## Implementation preserve end class opening. 
// ## Implementation preserve start class import. 
// ## Implementation preserve end class import. 

public class Female extends Monster
// ## Implementation preserve start class inheritence. 
// ## Implementation preserve end class inheritence. 

{
    /** Attributes */
    private final int MAX_CHILDREN = 10;
    // ## Implementation preserve start class attributes. 
    // ## Implementation preserve end class attributes. 
    // ## Implementation preserve start class associations. 
    // ## Implementation preserve end class associations. 
    /**
     * Operation
     *
     * @param monster
     * @return Monster[]
     */
    public Monster[] breeding ( Male monster )
    {
    	Random random = new Random(); 
    	
    	int numberOfChildren = (int) Math.sqrt(fertility * monster.fertility) * MAX_CHILDREN; 
    	Monster[] children = new Monster[numberOfChildren + 1]; 
    	
    	for(int i = 0; i < numberOfChildren; i++)
    	{
    		boolean isMale = random.nextBoolean();
    		if(isMale)
    		{
    			children[i] = new Male();
    		}
    		else
    		{
    			children[i] = new Female(); 
    		}
    		
    		children[i].name = "New monster " + i; 
    		children[i].dob = new Date();
    		
    		if(random.nextInt(100)<5)
    			children[i].strength = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].strength = strength; 
    		else
    			children[i].strength = monster.strength; 
    			
    		if(random.nextInt(100)<5)
    			children[i].speed = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].speed = speed; 
    		else
    			children[i].speed = monster.speed; 
    			
    		if(random.nextInt(100)<5)
    			children[i].accuracy = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].accuracy = accuracy; 
    		else
    			children[i].accuracy = monster.accuracy; 
    			
    		if(random.nextInt(100)<5)
    			children[i].endurance = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].endurance = endurance; 
    		else
    			children[i].endurance = monster.endurance; 
    			
    		if(random.nextInt(100)<5)
    			children[i].armor = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].armor = armor; 
    		else
    			children[i].armor = monster.armor; 
    			
    		if(random.nextInt(100)<5)
    			children[i].dodge = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].dodge = dodge; 
    		else
    			children[i].dodge = monster.dodge; 
    			
    		if(random.nextInt(100)<5)
    			children[i].age_rate = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].age_rate = age_rate; 
    		else
    			children[i].age_rate = monster.age_rate; 
    		
    		if(random.nextInt(100)<5)
    			children[i].fertility = random.nextFloat();
    		else if(random.nextInt(100)<50)
    			children[i].fertility = fertility; 
    		else
    			children[i].fertility = monster.fertility; 
    			
    		children[i].health = 100; //?
    	}
    	return children; 
    }
    // ## Implementation preserve start class other.operations. 
    // ## Implementation preserve end class other.operations. 
}

// ## Implementation preserve start class closing. 
// ## Implementation preserve end class closing. 
