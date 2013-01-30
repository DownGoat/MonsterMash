/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.FightRequest;
import data.Monster;
import data.Notification;
import data.Player;
import database.OtherPersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author sis13
 */
public class FightLost extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fightID = request.getParameter("fightID");
        
        if(fightID != null) {
            OtherPersistenceManager pm = new OtherPersistenceManager();
            FightRequest fr = pm.getFightRequest(fightID);
            
            if(fr != null) {
               Player player = pm.getPlayer(fr.getSenderID());
               RemoteTalker rt = new RemoteTalker();
               Monster senderMonster = pm.getMonster(fr.getSenderMonsterID());
               Monster receiverMonster = null;
                try {
                    receiverMonster = rt.getRemoteMonster(fr.getReceiverMonsterID(), rt.getRemoteAddress(fr.getRecieverServerID()));
                } catch (JSONException ex) {
                    Logger.getLogger(FightLost.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cannot contact remote server!");
                }
               if(player != null && senderMonster != null && receiverMonster != null) {
                   
                   player.addNotification( new Notification (
                           "The battle is lost!",
                           "Your enemy fights with no honor and has won the battle. You lost your pet monster "+senderMonster.getName()+
                                "against the demonic and unjust "+receiverMonster.getId()+". "+fr.getRecieverID()+" is now enjoing the spoils of war.",
                           player)
                           );
                   
                   pm.storeNotifications(player);
                   pm.removeMonster(fr.getSenderMonsterID());
                   pm.removeFightRequest(fr);
               } else {
                   response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unkown player id in fight request.");
               }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad fight id.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid parameters for fight lost.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
