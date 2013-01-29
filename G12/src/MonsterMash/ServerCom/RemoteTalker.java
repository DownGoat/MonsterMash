/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import data.Player;
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
        
        player.setEmail(json.getString("name"));
        player.setMoney(json.getInt("money"));
        player.setId(json.getString("userID"));
        
        return player;
    }
    
    
    
    public String getRemoteAddress(int serverNumber) {
        resource = client.resource("http://monstermash.digitdex.com/directory");
        return resource.path(String.valueOf(serverNumber)).path("service").get(String.class);
    }
}
