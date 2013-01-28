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
public class NotificationTest {
    
    public NotificationTest() {
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
     * Test of getId method, of class Notification.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Notification instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Notification.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Notification instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShortText method, of class Notification.
     */
    @Test
    public void testGetShortText() {
        System.out.println("getShortText");
        Notification instance = null;
        String expResult = "";
        String result = instance.getShortText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShortText method, of class Notification.
     */
    @Test
    public void testSetShortText() {
        System.out.println("setShortText");
        String shortText = "";
        Notification instance = null;
        instance.setShortText(shortText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLongText method, of class Notification.
     */
    @Test
    public void testGetLongText() {
        System.out.println("getLongText");
        Notification instance = null;
        String expResult = "";
        String result = instance.getLongText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLongText method, of class Notification.
     */
    @Test
    public void testSetLongText() {
        System.out.println("setLongText");
        String longText = "";
        Notification instance = null;
        instance.setLongText(longText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayer method, of class Notification.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        Notification instance = null;
        Player expResult = null;
        Player result = instance.getPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayer method, of class Notification.
     */
    @Test
    public void testSetPlayer() {
        System.out.println("setPlayer");
        Player player = null;
        Notification instance = null;
        instance.setPlayer(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSent method, of class Notification.
     */
    @Test
    public void testGetTimeSent() {
        System.out.println("getTimeSent");
        Notification instance = null;
        Date expResult = null;
        Date result = instance.getTimeSent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeSent method, of class Notification.
     */
    @Test
    public void testSetTimeSent() {
        System.out.println("setTimeSent");
        Date timeSent = null;
        Notification instance = null;
        instance.setTimeSent(timeSent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
