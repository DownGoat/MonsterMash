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
        Monster monster = new Monster("Bob","1");
        assertEquals("Expected id = 0", "0",monster.getId());
    }

    /**
     * Test of setId method, of class Monster.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Monster monster = new Monster("Bob","1");
        monster.setId("23");
        assertEquals("Expected id = 23", "23",monster.getId());
    }

    /**
     * Test of getName method, of class Monster.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
         Monster monster = new Monster("Bob","1");
         assertEquals("Expected name 'Bob'","Bob",monster.getName());
         
    
    }

    /**
     * Test of setName method, of class Monster.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Monster monster = new Monster("Bob","1");
        monster.setName("Bill");
        assertEquals("Expected name 'Bill'","Bill", monster.getName());
        
    }

    /**
     * Test of getDob method, of class Monster.
     */
    @Test
    public void testGetDob() {
        System.out.println("getDob");
        Monster monster = new Monster("Bob","1");
        Date now = new Date();
        assertEquals("That now = the monsters dob",now,monster.getDob());
    }


    /**
     * Test of getBaseStrength method, of class Monster.
     */
    @Test
    public void testGetBaseStrength() {
        System.out.println("getBaseStrength");
        Monster monster = new Monster("Bob","1");
        assertTrue(0<monster.getBaseStrength() && monster.getBaseStrength()<1);
    }

       /**
     * Test of getCurrentStrength method, of class Monster.
     */
    @Test
    public void testCurrentStrength() {
        System.out.println("getCurrentStrength");
        Monster monster = new Monster("Bob","1");
        assertTrue(0<monster.getCurrentStrength() && monster.getCurrentStrength()<1);
    }



    /**
     * Test of getBaseDefence method, of class Monster.
     */
    @Test
    public void testGetBaseDefence() {
        System.out.println("BaseDefence");
        Monster monster = new Monster("Bob","1");
        assertTrue(0<monster.getBaseDefence() && monster.getBaseDefence()<1);
    }


    /**
     * Test of getCurrentDefence method, of class Monster.
     */
    @Test
    public void testCurrentDefence() {
        System.out.println("CurrentDefence");
        Monster monster = new Monster("Bob","1");
        assertTrue(0<monster.getCurrentDefence() && monster.getCurrentDefence()<1);
    }

    /**
     * Test of getBaseHealth method, of class Monster.
     */
    @Test
    public void testGetBaseHealth() {
        System.out.println("getBaseHealth");
        Monster monster = new Monster("Bob","1");
        assertTrue(0<monster.getBaseHealth() && monster.getBaseHealth()<1);
    }

    /**
     * Test of getCurrentHealth method, of class Monster.
     */
    @Test
    public void testGetCurrentHealth() {
        System.out.println("getCurrentHealth");
        Monster monster = new Monster("Bob","1");
        assertTrue(0<monster.getCurrentHealth() && monster.getCurrentHealth()<1);
    }

    /**
     * Test of getAge_rate method, of class Monster.
    
    @Test
    public void testGetAge_rate() {
        System.out.println("getAge_rate");
        Monster monster = new Monster("Bob");
        assertTrue(0<monster.getAge_rate() && monster.getAge_rate()<1);
    }
 */
    /**
     * Test of getFertility method, of class Monster.
     */
    @Test
    public void testGetFertility() {
        System.out.println("getFertility");
        Monster monster = new Monster("Bob",1);
        assertTrue(0<monster.getFertility() && monster.getFertility()<1);
    }



}
