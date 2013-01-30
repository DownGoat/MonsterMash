/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import data.Monster;
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
        String userID = "";
        PersistenceManager instance = new PersistenceManager();
        boolean expResult = false;
        boolean result = instance.accountExists(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storePlayer method, of class PersistenceManager.
     */
    @Test
    public void testStorePlayer() {
        System.out.println("storePlayer");
        Player p = null;
        PersistenceManager instance = new PersistenceManager();
        instance.storePlayer(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeNotifications method, of class PersistenceManager.
     */
    @Test
    public void testStoreNotifications() {
        System.out.println("storeNotifications");
        Player p = null;
        PersistenceManager instance = new PersistenceManager();
        instance.storeNotifications(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeMonsters method, of class PersistenceManager.
     */
    @Test
    public void testStoreMonsters() {
        System.out.println("storeMonsters");
        Player p = null;
        PersistenceManager instance = new PersistenceManager();
        instance.storeMonsters(p);
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
        String playerID = "";
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
        String playerID = "";
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
        String playerID = "";
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getMonsterList(playerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayer method, of class PersistenceManager.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        String userID = "";
        PersistenceManager instance = new PersistenceManager();
        Player expResult = null;
        Player result = instance.getPlayer(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerIdAndServer method, of class PersistenceManager.
     */
    @Test
    public void testGetPlayerIdAndServer() {
        System.out.println("getPlayerIdAndServer");
        String email = "";
        PersistenceManager instance = new PersistenceManager();
        String[] expResult = null;
        String[] result = instance.getPlayerIdAndServer(email);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFriendRequestSent method, of class PersistenceManager.
     */
    @Test
    public void testIsFriendRequestSent() {
        System.out.println("isFriendRequestSent");
        String playerOne = "";
        String playerTwo = "";
        PersistenceManager instance = new PersistenceManager();
        boolean expResult = false;
        boolean result = instance.isFriendRequestSent(playerOne, playerTwo);
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
        String senderID = "";
        String receiverID = "";
        int receiverServerID = 0;
        PersistenceManager instance = new PersistenceManager();
        instance.sendFriendRequest(senderID, receiverID, receiverServerID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of confirmFriendship method, of class PersistenceManager.
     */
    @Test
    public void testConfirmFriendship() {
        System.out.println("confirmFriendship");
        String senderID = "";
        int senderServer = 0;
        String receiverID = "";
        int receiverServer = 0;
        PersistenceManager instance = new PersistenceManager();
        instance.confirmFriendship(senderID, senderServer, receiverID, receiverServer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rejectFriendship method, of class PersistenceManager.
     */
    @Test
    public void testRejectFriendship() {
        System.out.println("rejectFriendship");
        String senderID = "";
        int senderServer = 0;
        String receiverID = "";
        int receiverServer = 0;
        PersistenceManager instance = new PersistenceManager();
        instance.rejectFriendship(senderID, senderServer, receiverID, receiverServer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHighscores method, of class PersistenceManager.
     */
    @Test
    public void testGetHighscores() {
        System.out.println("getHighscores");
        String playerID = "";
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getHighscores(playerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerUsername method, of class PersistenceManager.
     */
    @Test
    public void testGetPlayerUsername() {
        System.out.println("getPlayerUsername");
        String playerID = "";
        int serverID = 0;
        PersistenceManager instance = new PersistenceManager();
        String expResult = "";
        String result = instance.getPlayerUsername(playerID, serverID);
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
        String userID = "";
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getFriendRequestList(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acceptFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testAcceptFriendRequest() {
        System.out.println("acceptFriendRequest");
        String requestID = "";
        String receiverID = "";
        PersistenceManager instance = new PersistenceManager();
        instance.acceptFriendRequest(requestID, receiverID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testCancelFriendRequest() {
        System.out.println("cancelFriendRequest");
        String requestID = "";
        String receiverID = "";
        PersistenceManager instance = new PersistenceManager();
        instance.cancelFriendRequest(requestID, receiverID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonstersForSale method, of class PersistenceManager.
     */
    @Test
    public void testGetMonstersForSale() {
        System.out.println("getMonstersForSale");
        String playerID = "";
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getMonstersForSale(playerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonstersForBreeding method, of class PersistenceManager.
     */
    @Test
    public void testGetMonstersForBreeding() {
        System.out.println("getMonstersForBreeding");
        String playerID = "";
        PersistenceManager instance = new PersistenceManager();
        ArrayList expResult = null;
        ArrayList result = instance.getMonstersForBreeding(playerID);
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

    /**
     * Test of getMonster method, of class PersistenceManager.
     */
    @Test
    public void testGetMonster() {
        System.out.println("getMonster");
        int monsterID = 0;
        PersistenceManager instance = new PersistenceManager();
        Monster expResult = null;
        Monster result = instance.getMonster(monsterID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
