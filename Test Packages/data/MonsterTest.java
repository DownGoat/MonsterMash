/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Llionv
 */
public class MonsterTest {
    
    public MonsterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Monster.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Monster monster = new Monster("Bob");
        assertEquals("Expected id = 0", 0,monster.getId());
    }

    /**
     * Test of setId method, of class Monster.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Monster monster = new Monster("Bob");
        monster.setId(23);
        assertEquals("Expected id = 23", 23,monster.getId());
    }

    /**
     * Test of getName method, of class Monster.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
         Monster monster = new Monster("Bob");
         assertEquals("Expected name 'Bob'","Bob",monster.getName());
         
    
    }

    /**
     * Test of setName method, of class Monster.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Monster monster = new Monster("Bob");
        monster.setName("Bill");
        assertEquals("Expected name 'Bill'","Bill", monster.getName());
        
    }

    /**
     * Test of getDob method, of class Monster.
     */
    @Test
    public void testGetDob() {
        System.out.println("getDob");
        Monster monster = new Monster("Bob");
        Date now = new Date();
        assertEquals("That now = the monsters dob",now,monster.getDob());
    }


    /**
     * Test of getGenetic_strength method, of class Monster.
     */
    @Test
    public void testGetGenetic_strength() {
        System.out.println("getGenetic_strength");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getStrength() && monster.getStrength()<1);
    }

    /**
     * Test of getSpeed method, of class Monster.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getSpeed() && monster.getSpeed()<1);
    }

    /**
     * Test of getAccuracy method, of class Monster.
     */
    @Test
    public void testGetAccuracy() {
        System.out.println("getAccuracy");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getAccuracy() && monster.getAccuracy()<1);
    }


    /**
     * Test of getEndurance method, of class Monster.
     */
    @Test
    public void testGetEndurance() {
        System.out.println("getEndurance");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getEndurance() && monster.getEndurance()<1);
    }

    /**
     * Test of getArmor method, of class Monster.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getArmor() && monster.getArmor()<1);
    }

    /**
     * Test of getDodge method, of class Monster.
     */
    @Test
    public void testGetDodge() {
        System.out.println("getDodge");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getDodge() && monster.getDodge()<1);
    }

    /**
     * Test of getAge_rate method, of class Monster.
     */
    @Test
    public void testGetAge_rate() {
        System.out.println("getAge_rate");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getAge_rate() && monster.getAge_rate()<1);
    }

    /**
     * Test of getFertility method, of class Monster.
     */
    @Test
    public void testGetFertility() {
        System.out.println("getFertility");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getFertility() && monster.getFertility()<1);
    }


    /**
     * Test of getHealth method, of class Monster.
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        Monster monster = new Monster("Bob");
        assertEquals("Health should = 100",100, monster.getHealth(),0);
    }



    /**
     * Test of getStrength method, of class Monster.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getStrength() && monster.getStrength()<1);
    }


}
