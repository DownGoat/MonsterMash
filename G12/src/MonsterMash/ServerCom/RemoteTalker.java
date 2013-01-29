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
import data.Friend;
import data.Monster;
import data.Player;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sis13
 */
public class RemoteTalker {
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
        
        String body = resource.path("users").queryParam("userID", userID).get(String.class);
	JSONObject json = new JSONObject(body);
        
        player = new Player();
        player.setEmail(json.getString("name"));
        player.setMoney(json.getInt("money"));
        player.setId(json.getString("userID"));
        
        return player;
    }
    
    public Monster getRemoteMonster(String monsterID, String remoteAddress) throws JSONException {
        Monster monster = null;
        resource = client.resource(remoteAddress);
        
        String body = resource.path("monsters").queryParam("monsterID", monsterID).get(String.class);
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
        
        String body = resource.path("monsters").queryParam("userID", userID).get(String.class);
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
    
    public void remoteFriendRequest(Player localUser, String remoteUserID, int serverNumber) {
        resource = client.resource(getRemoteAddress(serverNumber));
        Friend friend = new Friend(String.valueOf((new Date()).getTime()), remoteUserID, localUser.getId(), serverNumber, "N");
        
         MultivaluedMap<String, String> params = new MultivaluedMapImpl();
         params.add("friendID", friend.getFriendshipID());
         params.add("localUserID", localUser.getId());
         params.add("remoteUserID", remoteUserID);
         params.add("remoteServerNumber", String.valueOf(serverNumber));
        
        String body = resource.path("friends/request").queryParams(params).get(String.class);
        
        // TODO add Friend object to db when db method is made.
    
    }
}
