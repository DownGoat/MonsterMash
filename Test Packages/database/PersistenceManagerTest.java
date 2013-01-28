/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import data.Player;
import java.util.ArrayList;
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
public class PersistenceManagerTest {
    
    public PersistenceManagerTest() {
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
     * Test of accountExists method, of class PersistenceManager.
     */
    @Test
    public void testAccountExists() {
        System.out.println("accountExists");
     PersistenceManager pm = new PersistenceManager();
     Player p = new Player("jau1@aber.ac.uk", "password", 5, "Bill");
     pm.addNewPlayer(p);
     assertTrue(pm.accountExists("jau1@aber.ac.uk"));
    }

    /**
     * Test of addNewPlayer method, of class PersistenceManager.
     */
    @Test
    public void testAddNewPlayer() {
        System.out.println("addNewPlayer");
        Player p = null;
        PersistenceManager instance = new PersistenceManager();
        instance.addNewPlayer(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewNotifications method, of class PersistenceManager.
     */
    @Test
    public void testAddNewNotifications() {
        System.out.println("addNewNotifications");
        Player p = null;
        PersistenceManager instance = new PersistenceManager();
        instance.addNewNotifications(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewMonsters method, of class PersistenceManager.
     */
    @Test
    public void testAddNewMonsters() {
        System.out.println("addNewMonsters");
        Player p = null;
        PersistenceManager instance = new PersistenceManager();
        instance.addNewMonsters(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doLogin method, of class PersistenceManager.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        String email = "";
        String password = "";
        PersistenceManager instance = new PersistenceManager();
        Player expResult = null;
        Player result = instance.doLogin(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFriendList method, of class PersistenceManager.
     */
    @Test
    public void testGetFriendList() {
        System.out.println("getFriendList");
        int playerID = 0;
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getFriendList(playerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotificationList method, of class PersistenceManager.
     */
    @Test
    public void testGetNotificationList() {
        System.out.println("getNotificationList");
        int playerID = 0;
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getNotificationList(playerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonsterList method, of class PersistenceManager.
     */
    @Test
    public void testGetMonsterList() {
        System.out.println("getMonsterList");
        int playerID = 0;
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getMonsterList(playerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class PersistenceManager.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        String query = "";
        PersistenceManager instance = new PersistenceManager();
        boolean expResult = false;
        boolean result = instance.insert(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class PersistenceManager.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        String query = "";
        PersistenceManager instance = new PersistenceManager();
        int expResult = 0;
        int result = instance.count(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerID method, of class PersistenceManager.
     */
    @Test
    public void testGetPlayerID() {
        System.out.println("getPlayerID");
        String email = "";
        PersistenceManager instance = new PersistenceManager();
        long expResult = 0L;
        long result = instance.getPlayerID(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFriendRequestList method, of class PersistenceManager.
     */
    @Test
    public void testGetFriendRequestList() {
        System.out.println("getFriendRequestList");
        long id = 0L;
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getFriendRequestList(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testSendFriendRequest() {
        System.out.println("sendFriendRequest");
        long fromID = 0L;
        String emailTo = "";
        PersistenceManager instance = new PersistenceManager();
        instance.sendFriendRequest(fromID, emailTo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acceptFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testAcceptFriendRequest() {
        System.out.println("acceptFriendRequest");
        long fromID = 0L;
        String toEmail = "";
        PersistenceManager instance = new PersistenceManager();
        instance.acceptFriendRequest(fromID, toEmail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testCancelFriendRequest() {
        System.out.println("cancelFriendRequest");
        long fromID = 0L;
        String toEmail = "";
        PersistenceManager instance = new PersistenceManager();
        instance.cancelFriendRequest(fromID, toEmail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getErrorMessage method, of class PersistenceManager.
     */
    @Test
    public void testGetErrorMessage() {
        System.out.println("getErrorMessage");
        PersistenceManager instance = new PersistenceManager();
        String expResult = "";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
