/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import data.CONFIG;
import data.FightRequest;
import data.Friend;
import data.Monster;
import data.Notification;
import data.Player;
import database.OtherPersistenceManager;
import database.PersistenceManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sis13
 */
public class RemoteTalker {

    private final int OUR_SERVER = CONFIG.OUR_SERVER;
    private Client client;
    private WebResource resource;

    public RemoteTalker() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        client.setConnectTimeout(5000);
        client.setReadTimeout(10000);

    }

    public Player getRemotePlayer(String userID, String remoteAddress) throws JSONException {
        Player player = null;
        resource = client.resource(remoteAddress);
        String body = null;
        try {
            body = resource.path("users").queryParam("userID", userID).get(String.class);
        } catch (Exception err) {
            return null;
        }
        JSONObject json = new JSONObject(body);

        player = new Player();
        player.setUsername(json.getString("name"));
        player.setMoney(json.getInt("money"));
        player.setUserID(json.getString("userID"));

        return player;
    }

    public Monster getRemoteMonster(String monsterID, String remoteAddress) throws JSONException {
        Monster monster = null;
        resource = client.resource(remoteAddress);
        String body = null;

        try {
            body = resource.path("monsters").queryParam("monsterID", monsterID).get(String.class);
        } catch (Exception err) {
            return null;
        }
        JSONObject json = new JSONObject(body);

        monster = new Monster();
        monster.setBaseStrength(json.getDouble("baseStrength"));
        monster.setCurrentStrength(json.getDouble("currentStrength"));
        monster.setBaseDefence(json.getDouble("baseDefence"));
        monster.setCurrentDefence(json.getDouble("currentDefence"));
        monster.setBaseHealth(json.getDouble("baseHealth"));
        monster.setCurrentHealth(json.getDouble("currentHealth"));
        monster.setUserID(json.getString("userID"));
        monster.setId(json.getString("monsterID"));
        monster.setDob(new Date(json.getInt("birthDate")));
        monster.setDod(new Date(json.getInt("lifespan")));
        monster.setBreedOffer(json.getInt("breedOffer"));
        monster.setSaleOffer(json.getInt("saleOffer"));

        return monster;
    }

    public ArrayList<Monster> getRemoteUsersMonsters(String userID, String remoteAddress) throws JSONException {
        ArrayList<Monster> monsters = null;
        resource = client.resource(remoteAddress);
        String body = null;

        try {
            body = resource.path("monsters").queryParam("userID", userID).get(String.class);
        } catch (Exception err) {
            return null;
        }
        JSONArray jsonArray = new JSONArray(body);

        monsters = new ArrayList<Monster>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            Monster monster = new Monster();
            monster.setBaseStrength(json.getDouble("baseStrength"));
            monster.setCurrentStrength(json.getDouble("currentStrength"));
            monster.setBaseDefence(json.getDouble("baseDefence"));
            monster.setCurrentDefence(json.getDouble("currentDefence"));
            monster.setBaseHealth(json.getDouble("baseHealth"));
            monster.setCurrentHealth(json.getDouble("currentHealth"));
            monster.setUserID(json.getString("userID"));
            monster.setId(json.getString("monsterID"));
            monster.setDob(new Date(json.getInt("birthDate")));
            monster.setDod(new Date(json.getInt("lifespan")));
            monster.setBreedOffer(json.getInt("breedOffer"));
            monster.setSaleOffer(json.getInt("saleOffer"));

            monsters.add(monster);
        }
        return monsters;
    }

    public String getRemoteAddress(int serverNumber) {
        resource = client.resource("http://monstermash.digitdex.com/directory");
        return resource.path(String.valueOf(serverNumber)).path("service").get(String.class);
    }

    public Boolean remoteFriendRequest(Player localUser, String remoteUserID, int serverNumber) {
        resource = client.resource(getRemoteAddress(serverNumber));

        Friend friend = new Friend(String.valueOf(new Date().getTime()), remoteUserID, localUser.getUserID(), CONFIG.OUR_SERVER, serverNumber, "N");

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("friendID", friend.getFriendshipID());
        params.add("localUserID", remoteUserID);
        params.add("remoteUserID", localUser.getUserID());
        params.add("remoteServerNumber", CONFIG.OUR_SERVER+"");
        String body = null;

        try {
            body = resource.path("friends/request").queryParams(params).get(String.class);
        } catch (Exception err) {
            return false;
        }

        OtherPersistenceManager pm = new OtherPersistenceManager();
        pm.addFriend(friend);
        String message = "Friend request to <b>" + remoteUserID + "</b> sent successfully.";
//        localUser.addNotification(new Notification(message, "You have sent friend request to <b>"+remoteUserID+"</b>.", localUser));
//        pm.storeNotifications(localUser);

        return true;
    }

    public Boolean acceptRemoteFriendRequest(Friend friend) {
        resource = client.resource(getRemoteAddress(friend.getRemoteServerID()));

        String body = null;
        try {
            body = resource.path("friends/accept").queryParam("friendID", friend.getFriendshipID()).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Boolean rejectRemoteFriendRequest(Friend friend) {
        resource = client.resource(getRemoteAddress(friend.getRemoteServerID()));

        String body = null;
        try {
            body = resource.path("friends/reject").queryParam("friendID", friend.getFriendshipID()).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Boolean remoteFightRequest(FightRequest fightRequest, int serverNumber) {
        resource = client.resource(getRemoteAddress(serverNumber));

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("fightID", fightRequest.getFightID());
        params.add("localMonsterID", fightRequest.getReceiverMonsterID());
        params.add("remoteMonsterID", fightRequest.getSenderMonsterID());
        params.add("remoteServerNumber", String.valueOf(fightRequest.getSenderServerID()));

        String body = null;
        try {
            body = resource.path("fight/request").queryParams(params).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
        // TODO save FR to DB.
    }

    public Boolean wonRemoteFight(FightRequest fightRequest, int serverNumber, Monster monster) {
        resource = client.resource(getRemoteAddress(serverNumber));

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("fightID", fightRequest.getFightID());
        params.add("strength", String.valueOf(monster.getCurrentStrength()));
        params.add("defence", String.valueOf(monster.getCurrentDefence()));
        params.add("health", String.valueOf(monster.getCurrentHealth()));

        String body = null;
        try {
            body = resource.path("fight/won").queryParams(params).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Boolean lostRemoteFight(FightRequest fightRequest, int serverNumber) {
        resource = client.resource(getRemoteAddress(serverNumber));

        String body = null;
        try {
            body = resource.path("fight/lost").queryParam("fightID", fightRequest.getFightID()).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Boolean rejectRemoteFight(FightRequest fightRequest, int serverNumber) {
        resource = client.resource(getRemoteAddress(serverNumber));

        String body = null;
        try {
            body = resource.path("fight/reject").queryParam("fightID", fightRequest.getFightID()).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Boolean sendBreedRequest(String monsterID, int serverNumber) {
        resource = client.resource(getRemoteAddress(serverNumber));

        String body = null;
        try {
            body = resource.path("breed").queryParam("monsterID", monsterID).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Boolean sendBuyRequest(String monsterID, int serverNumber) {
        resource = client.resource(getRemoteAddress(serverNumber));

        String body = null;
        try {
            body = resource.path("buy").queryParam("monsterID", monsterID).get(String.class);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Player findUser(String userID) {
        Player player = null;

        OtherPersistenceManager pm = new OtherPersistenceManager();
        player = pm.getPlayer(userID);
        if (player != null) {
            player.setServerID(CONFIG.OUR_SERVER);
        }

        for (int i = 1; i < 20 && player == null; i++) {
            String addr = null;
            try {
                addr = getRemoteAddress(i);
                if (addr.length() == 0) {
                    continue;
                }

                System.out.printf("ServerID: %d, server address: %s\n", i, addr);
                player = getRemotePlayer(userID, addr);
                if (player != null) {
                    player.setServerID(i);
                }

            } catch (JSONException ex) {
                Logger.getLogger(RemoteTalker.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (player != null) {
                try {
                    player.setMonsters(this.getRemoteUsersMonsters(userID, addr));
                } catch (JSONException ex) {
                    Logger.getLogger(RemoteTalker.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
        }

        return player;
    }

    public ArrayList<String> getHighScores(Player player) {
        ArrayList<String> highscores = new ArrayList<String>();
        ArrayList<Player> players = new ArrayList<Player>();
        RemoteTalker rt = new RemoteTalker();
        OtherPersistenceManager pm = new OtherPersistenceManager();
        ArrayList<Friend> friends = pm.getFriends(player);

        for (Friend friend : friends) {
            int friendServerID = 0;
            String friendUserID = null;

            if (friend.getLocalUserID().equals(player.getUserID())) {
                friendServerID = friend.getRemoteServerID();
                friendUserID = friend.getRemoteUserID();
            } else {
                friendServerID = friend.getLocalServerID();
                friendUserID = friend.getLocalUserID();
            }

            Player theFriend = null;

            if (friendServerID != CONFIG.OUR_SERVER) {
                try {
                    theFriend = rt.getRemotePlayer(friendUserID, rt.getRemoteAddress(friendServerID));
                } catch (JSONException ex) {
                    System.err.println("Can not get remote friend data, server down?");
                    Logger.getLogger(RemoteTalker.class.getName()).log(Level.SEVERE, null, ex);
                }

                continue;
            } else {
                theFriend = pm.getPlayer(friendUserID);
            }

            if (theFriend == null) {
                System.err.println("Can not get remote friend data, server down? Or player does not exist.");
                continue;
            }

            players.add(theFriend);
        }
        
        players = player.sortByMoney(players);
        
        int i = 1;
        for(Player theFriend : players) {
            highscores.add("<tr><td>"+i+".</td><td><b>"+theFriend.getUserID()+"</b></td><td>"+theFriend.getMoney()+"$</td></tr>");
            i++;
        }

        return highscores;
    }
}