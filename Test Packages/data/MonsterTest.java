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
        Monster instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Monster.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Monster instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDob method, of class Monster.
     */
    @Test
    public void testGetDob() {
        System.out.println("getDob");
        Monster instance = null;
        Date expResult = null;
        Date result = instance.getDob();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDob method, of class Monster.
     */
    @Test
    public void testSetDob() {
        System.out.println("setDob");
        Date dob = null;
        Monster instance = null;
        instance.setDob(dob);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenetic_strength method, of class Monster.
     */
    @Test
    public void testGetGenetic_strength() {
        System.out.println("getGenetic_strength");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getGenetic_strength();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenetic_strength method, of class Monster.
     */
    @Test
    public void testSetGenetic_strength() {
        System.out.println("setGenetic_strength");
        float genetic_strength = 0.0F;
        Monster instance = null;
        instance.setGenetic_strength(genetic_strength);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpeed method, of class Monster.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpeed method, of class Monster.
     */
    @Test
    public void testSetSpeed() {
        System.out.println("setSpeed");
        float speed = 0.0F;
        Monster instance = null;
        instance.setSpeed(speed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccuracy method, of class Monster.
     */
    @Test
    public void testGetAccuracy() {
        System.out.println("getAccuracy");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getAccuracy();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccuracy method, of class Monster.
     */
    @Test
    public void testSetAccuracy() {
        System.out.println("setAccuracy");
        float accuracy = 0.0F;
        Monster instance = null;
        instance.setAccuracy(accuracy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndurance method, of class Monster.
     */
    @Test
    public void testGetEndurance() {
        System.out.println("getEndurance");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getEndurance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndurance method, of class Monster.
     */
    @Test
    public void testSetEndurance() {
        System.out.println("setEndurance");
        float endurance = 0.0F;
        Monster instance = null;
        instance.setEndurance(endurance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArmor method, of class Monster.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getArmor();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArmor method, of class Monster.
     */
    @Test
    public void testSetArmor() {
        System.out.println("setArmor");
        float armor = 0.0F;
        Monster instance = null;
        instance.setArmor(armor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDodge method, of class Monster.
     */
    @Test
    public void testGetDodge() {
        System.out.println("getDodge");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getDodge();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDodge method, of class Monster.
     */
    @Test
    public void testSetDodge() {
        System.out.println("setDodge");
        float dodge = 0.0F;
        Monster instance = null;
        instance.setDodge(dodge);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAge_rate method, of class Monster.
     */
    @Test
    public void testGetAge_rate() {
        System.out.println("getAge_rate");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getAge_rate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAge_rate method, of class Monster.
     */
    @Test
    public void testSetAge_rate() {
        System.out.println("setAge_rate");
        float age_rate = 0.0F;
        Monster instance = null;
        instance.setAge_rate(age_rate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFertility method, of class Monster.
     */
    @Test
    public void testGetFertility() {
        System.out.println("getFertility");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getFertility();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFertility method, of class Monster.
     */
    @Test
    public void testSetFertility() {
        System.out.println("setFertility");
        float fertility = 0.0F;
        Monster instance = null;
        instance.setFertility(fertility);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHealth method, of class Monster.
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getHealth();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHealth method, of class Monster.
     */
    @Test
    public void testSetHealth() {
        System.out.println("setHealth");
        float health = 0.0F;
        Monster instance = null;
        instance.setHealth(health);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStrength method, of class Monster.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        Monster instance = null;
        float expResult = 0.0F;
        float result = instance.getStrength();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStrength method, of class Monster.
     */
    @Test
    public void testSetStrength() {
        System.out.println("setStrength");
        float strength = 0.0F;
        Monster instance = null;
        instance.setStrength(strength);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
