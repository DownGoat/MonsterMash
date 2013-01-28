/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.Monster;
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
            jObj.put("userID", player.getEmail());
            jObj.put("name", player.getEmail());
        } catch (JSONException ex) {
            Logger.getLogger(JSONManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jObj;
    }

    public static JSONObject jsonMonster(Monster monster) {
        JSONObject jObj = new JSONObject();

        try {
            jObj.put("monsterID", String.valueOf(monster.getId()));
            jObj.put("userID", String.valueOf(monster.getId()));
            jObj.put("baseStrength", monster.getGenetic_strength());
            jObj.put("defence", monster.getArmor());
            jObj.put("dob", monster.getDob().getTime());
            jObj.put("dod", monster.getDod().getTime());
            jObj.put("forSale", monster.getForSale());
            jObj.put("forBreed", monster.getForBreed());
        } catch (JSONException ex) {
            Logger.getLogger(JSONManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jObj;
    }
}
