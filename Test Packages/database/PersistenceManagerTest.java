/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import data.Monster;
import data.Notification;
import data.Player;
import database.PersistenceManager;
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
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        pm.storePlayer(player);
        assertTrue(pm.accountExists("Pavel"));
    }

    /**
     * Test of storePlayer method, of class PersistenceManager.
     */
    @Test
    public void testStorePlayer() {
        System.out.println("storePlayer");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        pm.storePlayer(player);
        assertTrue(pm.accountExists("Pavel"));
        
        Player playerTwo = new Player("Pavels","pal1@aber.ac.uk", "password", 100, "Bill");
        pm.storePlayer(playerTwo);
        assertTrue(pm.accountExists("Pavel"));
        
    }

    /**
     * Test of storeNotifications method, of class PersistenceManager.
     */
    @Test
    public void testStoreNotifications() {
        System.out.println("storeNotifications");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        pm.storeNotifications(player);
        assertNotNull(pm.getNotificationList("pal@aber.ac.uk"));
        
    }

    /**
     * Test of storeMonsters method, of class PersistenceManager.
     */
    @Test
    public void testStoreMonsters() {
        System.out.println("storeMonsters");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        PersistenceManager pm = new PersistenceManager();
        pm.storeMonsters(player);
        assertNotNull(pm.getMonsterList("Bill"));

    }

    /**
     * Test of doLogin method, of class PersistenceManager.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");        
        PersistenceManager pm = new PersistenceManager();
        assertNotNull(pm.doLogin("Pavel", "password"));
        //TODO
    }

    /**
     * Test of getFriendList method, of class PersistenceManager.
     */
    @Test
    public void testGetFriendList() {
        System.out.println("getFriendList");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("Pavels","tab@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Pavel", "Table", 12);
        pm.confirmFriendship("Pavel", 12, "Table", 12);
        assertNotNull(pm.getFriendList("Pavel").get(0));
    }

    /**
     * Test of getNotificationList method, of class PersistenceManager.
     */
    @Test
    public void testGetNotificationList() {
        System.out.println("getNotificationList");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        PersistenceManager pm = new PersistenceManager();
        pm.storePlayer(player);
        pm.storeNotifications(player);
        assertNotNull(pm.getNotificationList("Pavel").get(pm.getNotificationList("Pavel").size()-1).getShortText());
        //should get a message about account creation
    }

    /**
     * Test of getMonsterList method, of class PersistenceManager.
     */
    @Test
    public void testGetMonsterList() {
        System.out.println("getMonsterList");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        PersistenceManager pm = new PersistenceManager();
        pm.storePlayer(player);
        assertNotNull(pm.getMonsterList("Pavel").get(0));
    }

    /**
     * Test of getPlayer method, of class PersistenceManager.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");                
        PersistenceManager pm = new PersistenceManager();
        pm.storePlayer(player);
        assertEquals(pm.getPlayer("Pavel").getUserID(), player.getUserID());
    }

    /**
     * Test of getPlayerIdAndServer method, of class PersistenceManager.
     */
    @Test
    public void testGetPlayerIdAndServer() {
        System.out.println("getPlayerIdAndServer");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        PersistenceManager pm = new PersistenceManager();
        pm.storePlayer(player);
        String[] example = new String[2];
        example[0] = "Pavel";
        example[1] = "12";
        assertArrayEquals(pm.getPlayerIdAndServer("Pavel"), example);
        
    }

    /**
     * Test of isFriendRequestSent method, of class PersistenceManager.
     */
    @Test
    public void testIsFriendRequestSent() {
        System.out.println("isFriendRequestSent");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("Table","tab@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Pavel", "Table", 12);
        assertTrue(pm.isFriendRequestSent("Pavel", "Table"));
    }

    /**
     * Test of sendFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testSendFriendRequest() {
        System.out.println("sendFriendRequest");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("Table","tab@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Pavel", "Table", 12);
        assertTrue(pm.isFriendRequestSent("Pavel", "Table"));
    }

    /**
     * Test of confirmFriendship method, of class PersistenceManager.
     */
    @Test
    public void testConfirmFriendship() {
        System.out.println("confirmFriendship");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("Table","tab@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Pavel", "Table", 12);
        pm.confirmFriendship("Pavel", 12, "Table", 12);
        assertEquals(pm.getFriendList("Pavel").get(pm.getFriendList("Pavel").size()-1).getUserID(), "Table");
    }

    /**
     * Test of rejectFriendship method, of class PersistenceManager.
     */
    @Test
    public void testRejectFriendship() {
        System.out.println("rejectFriendship");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Timmy","tim@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("Floor","flr@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Timmy", "Floor", 12);
        pm.confirmFriendship("Timmy", 12, "Floor", 12);
        int size = pm.getFriendList("Timmy").size()-1; 
        pm.rejectFriendship("Timmy", 12, "Floor", 12);
        assertEquals(pm.getFriendList("Timmy").size(), size );
    }

    /**
     * Test of getHighscores method, of class PersistenceManager.
     */
    @Test
    public void testGetHighscores() {
        System.out.println("getHighscores");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill"); 
        PersistenceManager pm = new PersistenceManager();
        pm.storePlayer(player);
        assertNotNull(pm.getHighscores("pal@aber.ac.uk"));
        fail();
    }

    /**
     * Test of getPlayerUsername method, of class PersistenceManager.
     */
    @Test
    public void testGetPlayerUsername() {
        System.out.println("getPlayerUsername");
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill");
        PersistenceManager pm = new PersistenceManager();
        pm.storePlayer(player);
        
        assertEquals( "pal@aber.ac.uk", pm.getPlayerUsername("Pavel", 12));
    }

    /**
     * Test of getFriendRequestList method, of class PersistenceManager.
     */
    @Test
    public void testGetFriendRequestList() {
        System.out.println("getFriendRequestList");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("Table","tab@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Pavel", "Table", 12);
        assertNotNull(pm.getFriendRequestList("Table").get(0));
    }

    /**
     * Test of acceptFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testAcceptFriendRequest() {
        System.out.println("acceptFriendRequest");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Pavel","pal@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("Table","tab@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Pavel", "Table", 12);
        pm.acceptFriendRequest("Pavel", "Table");
        assertEquals(pm.getFriendList("Pavel").get(0).getUserID(), playerTwo.getUserID());
        
    }

    /**
     * Test of cancelFriendRequest method, of class PersistenceManager.
     */
    @Test
    public void testCancelFriendRequest() {
        System.out.println("cancelFriendRequest");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Shovel","shl@aber.ac.uk", "password", 100, "Bill"); 
        pm.storePlayer(player);
        Player playerTwo = new Player("SomeonesFace","sof@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.sendFriendRequest("Shovel", "SomeonesFace", 12);
        int requestListSize = pm.getFriendRequestList("SomeonesFace").size()-1; //wha?
        pm.cancelFriendRequest("Shovel", "SomeonesFace");
        assertEquals(pm.getFriendRequestList("SomeonesFace").size(), requestListSize);
    }

    /**
     * Test of getMonstersForSale method, of class PersistenceManager.
     */
    @Test
    public void testGetMonstersForSale() {
        System.out.println("getMonstersForSale");
        PersistenceManager pm = new PersistenceManager();
        Player player = new Player("Shovel","shl@aber.ac.uk", "password", 100, "Bill");
        pm.storePlayer(player);
        Player playerTwo = new Player("SomeonesFace","sof@aber.ac.uk", "password", 100, "Billy");
        pm.storePlayer(playerTwo);
        pm.makeNewMarketOffer("Shovel", "Bill", 100);
        assertNotNull(pm.getMonstersForSale("SomeonesFace").get(0));
        
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
        PersistenceManager pm = new PersistenceManager();
        assertNotNull(pm.getErrorMessage());
        //Need to cause an error
        
    }
      
     
}
