/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

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
public class PlayerTest {

    public PlayerTest() {
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
     * Test of getId method, of class Player.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("Id should be 0", 0, player.getId());

    }

    /**
     * Test of setId method, of class Player.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Player player = new Player("jau1@aber.ac.uk", "password1", 100, "Bill");
        player.setId(69);
        assertEquals("Id should be 69", 69, player.getId());

    }

    /**
     * Test of getPassword method, of class Player.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Player player = new Player("jau1@aber.ac.uk", "password1", 100, "Bill");
        assertEquals("password should be password1", "password1", player.getPassword());

    }

    /**
     * Test of setPassword method, of class Player.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        player.setPassword("mittens");
        assertEquals("password should be mittens", "mittens", player.getPassword());


    }

    /**
     * Test of getEmail method, of class Player.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("the email should be jau1@aber.ac.uk", "jau1@aber.ac.uk", player.getEmail());

    }

    /**
     * Test of setEmail method, of class Player.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        player.setEmail("lwv@aber.ac.uk");
        assertEquals("the email should be lwv@aber.ac.uk", "lwv@aber.ac.uk", player.getEmail());

    }

    /**
     * Test of getMoney method, of class Player.
     */
    @Test
    public void testGetMoney() {
        System.out.println("getMoney");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("the money should be 100", 100, player.getMoney());

    }

    /**
     * Test of setMoney method, of class Player.
     */
    @Test
    public void testSetMoney() {
        System.out.println("setMoney");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        player.setMoney(200);
        assertEquals("money should be 200", 200, player.getMoney());

    }

    /**
     * Test of getFriends method, of class Player.
     */
    @Test
    public void testGetFriendY() {
        System.out.println("getFriends");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        Friend friend = new Friend(23, "lwv@aber.ac.uk", "N");
        player.addFriend(friend);
        assertEquals("friend ID should be 23", 23, friend.getId(player.getFriend(0)));
    }

    /**
     * Test of getFriends method, of class Player.
     */
    @Test
    public void testGetFriendN() {
        System.out.println("getFriends");
        Player player = new Player("jau1@aber.ac.uk", "password", 100, "Bill");
        Friend friend = new Friend(23, "lwv@aber.ac.uk", "N");
        player.addFriend(friend);
        assertEquals("Confirmed should be no", false,
                friend.isFriendshipConfirmed(player.getFriend(0)));
        friend.setFriendConfirmed("Y");
        assertEquals("Confirmed should be yes", true,
                friend.isFriendshipConfirmed(player.getFriend(0)));
    }

    /**
     * Test of getNotifications method, of class Player.
     */
    @Test
    public void testGetNotifications() {
        System.out.println("getNotifications");
        Player instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getNotifications();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNotifications method, of class Player.
     */
    @Test
    public void testSetNotifications() {
        System.out.println("setNotifications");
        ArrayList<Notification> notifications = null;
        Player instance = null;
        instance.setNotifications(notifications);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonsters method, of class Player.
     */
    @Test
    public void testGetMonsters() {
        System.out.println("getMonsters");
        Player instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getMonsters();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMonsters method, of class Player.
     */
    @Test
    public void testSetMonsters() {
        System.out.println("setMonsters");
        ArrayList<Monster> monsters = null;
        Player instance = null;
        instance.setMonsters(monsters);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFriend method, of class Player.
     */
    @Test
    public void testAddFriend() {
        System.out.println("addFriend");
        Friend friend = null;
        Player instance = null;
        instance.addFriend(friend);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFriend method, of class Player.
     */
    @Test
    public void testRemoveFriend() {
        System.out.println("removeFriend");
        Friend friend = null;
        Player instance = null;
        instance.removeFriend(friend);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMonster method, of class Player.
     */
    @Test
    public void testAddMonster() {
        System.out.println("addMonster");
        Monster monster = null;
        Player instance = null;
        instance.addMonster(monster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMonster method, of class Player.
     */
    @Test
    public void testRemoveMonster() {
        System.out.println("removeMonster");
        Monster monster = null;
        Player instance = null;
        instance.removeMonster(monster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNotification method, of class Player.
     */
    @Test
    public void testAddNotification() {
        System.out.println("addNotification");
        Notification notification = null;
        Player instance = null;
        instance.addNotification(notification);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMonsters method, of class Player.
     */
    @Test
    public void testUpdateMonsters() {
        System.out.println("updateMonsters");
        Player instance = null;
        instance.updateMonsters();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
