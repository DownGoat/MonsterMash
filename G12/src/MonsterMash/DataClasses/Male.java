package MonsterMash.DataClasses;

import MonsterMash.DataClasses.Male;
import MonsterMash.DataClasses.Monster;
import MonsterMash.DataClasses.BreedingOffer;
import MonsterMash.DataClasses.FightRequest;
import java.util.Random;

public class Male extends Monster {

    /**
     * Attributes
     */
    private final int MAX_RANGE = 30;
    private boolean injured;

    /**
     * Operation
     *
     * @param opponent
     * @return boolean
     */
    public boolean fight(Male opponent) {
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
}
