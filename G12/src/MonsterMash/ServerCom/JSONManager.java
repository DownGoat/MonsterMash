/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sis13
 */
public class JSONManager {

    /**
     *
     * @param player
     * @return
     */
    public static JSONObject jsonPlayer(Player player) {
        JSONObject jObj = new JSONObject();
          
        try {
            jObj.put("money", player.getMoney());
            jObj.put("userID", String.valueOf(player.getId()));
            jObj.put("name", player.getEmail());
        } catch (JSONException ex) {
            Logger.getLogger(JSONManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jObj;
    }
}
