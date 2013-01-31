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
import java.util.Date;

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
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("Id should be James", "James", player.getUserID());

    }

    /**
     * Test of setId method, of class Player.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Player player = new Player("James","jau1@aber.ac.uk", "password1", 100, "Bill");
        player.setUserID("69");
        assertEquals("Id should be 69", "69", player.getUserID());

    }

    /**
     * Test of getPassword method, of class Player.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Player player = new Player("James","jau1@aber.ac.uk", "password1", 100, "Bill");
        assertEquals("password should be password1", "password1", player.getPassword());

    }

    /**
     * Test of setPassword method, of class Player.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        player.setPassword("mittens");
        assertEquals("password should be mittens", "mittens", player.getPassword());


    }

    /**
     * Test of getEmail method, of class Player.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("the email should be jau1@aber.ac.uk", "jau1@aber.ac.uk", player.getUsername());

    }

    /**
     * Test of setEmail method, of class Player.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        player.setUsername("lwv@aber.ac.uk");
        assertEquals("the email should be lwv@aber.ac.uk", "lwv@aber.ac.uk", player.getUsername());

    }

    /**
     * Test of getMoney method, of class Player.
     */
    @Test
    public void testGetMoney() {
        System.out.println("getMoney");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("the money should be 100", 100, player.getMoney());

    }

    /**
     * Test of setMoney method, of class Player.
     */
    @Test
    public void testSetMoney() {
        System.out.println("setMoney");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        player.setMoney(200);
        assertEquals("money should be 200", 200, player.getMoney());

    }

    /**
     * Test of getFriends method, of class Player.
     */
    @Test
    public void testGetFriendY() {
        System.out.println("getFriends");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        Player friend = new Player("Llion","lwv@aber.ac.uk", 12);
        player.addFriend(friend);
        assertEquals("friend ID should be Llion", "Llion", player.getFriend(0).getUserID());
    }

    /**
     * Test of getFriends method, of class Player.
     */
    @Test
    public void testGetFriendN() {
        System.out.println("getFriends");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        Player playerv2 = new Player("Dan","dah27@aber.ac.uk", "password", 100,"Rodger");
        Friend friend = new Friend("jau1@aber.ac.uk","dah27@aber.ac.uk","11",23,34,"N");
      
        player.addFriend(playerv2);
       
        assertEquals("friend should be plaerv2",
                playerv2, player.getFriend(0));
        
    }

    /**
     * Test of getNotifications method, of class Player.
     */
    @Test
    public void testGetNotifications() {
        System.out.println("getNotifications");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
     
        Notification noti = new Notification("short text", "long text",player);
        ArrayList notices = new ArrayList();
        notices.add(noti);
        player.setNotifications(notices);
        assertEquals("notification should be noti",noti,player.getNotifications().get(0));
        
        

    }

    /**
     * Test of setNotifications method, of class Player.
     */
    @Test
    public void testSetNotifications() {
        System.out.println("setNotifications");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Notification notice = new Notification("short", "long", player);
        ArrayList notices = new ArrayList();
        notices.add(notice);
        player.setNotifications(notices);
        assertEquals("notification should be notice",notice, player.getNotifications().get(0));
    }

    /**
     * Test of getMonsters method, of class Player.
     */
    @Test
    public void testGetMonsters() {
        System.out.println("getMonsters");
        Player player = new Player("Dan","dah27@aber.ac.uk", "password", 100, "Rodger");
       Monster monster = new Monster("Name", "dah27@aber.ac.uk" );
        ArrayList monsterarr = new ArrayList();
        monsterarr.add(monster);
        player.setMonsters(monsterarr);
        assertEquals("Monster should be monster",monster,player.getMonsters().get(0));
    }

    /**
     * Test of setMonsters method, of class Player.
     */
    @Test
    public void testSetMonsters() {
        System.out.println("setMonsters");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Monster monster = new Monster("Name", "dah27@aber.ac.uk");
        ArrayList monsterarr = new ArrayList();
        monsterarr.add(monster);
        player.setMonsters(monsterarr);
        assertEquals("Monster should be monster",monster, player.getMonsters().get(0));
    }

    /**
     * Test of addFriend method, of class Player.
     */
    @Test
    public void testAddFriend() {
        System.out.println("addFriend");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Player playerv2 = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        //Friend friend = new Friend("dah27@aber.ac.uk","jau1@aber.ac.uk","11",23,34,"N");
        Player friend = player;
        Player instance = playerv2;
      friend.addFriend(instance);  
        assertEquals("Friend should be friend",friend, instance.getFriend(0));
        
        // TODO review the generated test code and remove the default call to fail.
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
