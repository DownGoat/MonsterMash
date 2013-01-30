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
public class FightRequestServlet extends HttpServlet {

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
            throws ServletException, IOException, JSONException {
        String fightID = request.getParameter("fightID");
        String recieverMonsterID = request.getParameter("localMonsterID");
        String senderMonsterID = request.getParameter("remoteMonsterID");
        int senderServerID = Integer.parseInt(request.getParameter("remoteServerNumber"));
        
        if(fightID != null && recieverMonsterID != null && senderMonsterID != null) {
            OtherPersistenceManager pm = new OtherPersistenceManager();
            Monster reciverMonster = pm.getMonster(recieverMonsterID);
            RemoteTalker rt = new RemoteTalker();
            Monster senderMonster = rt.getRemoteMonster(senderMonsterID, rt.getRemoteAddress(senderServerID));
            
            if(reciverMonster != null && senderMonster != null) {            
                Player p = pm.getPlayer(reciverMonster.getUserID());
                if(p != null) {
                    FightRequest fr = new FightRequest(senderMonster.getUserID(), reciverMonster.getUserID(), fightID, senderMonsterID, recieverMonsterID, senderServerID, 12);
                    pm.storeFightRequest(fr);
                    p.addNotification(
                            new Notification(
                                "You got a new figth request from "+senderMonster.getUserID(),
                                senderMonster.getUserID()+" has challenged you to a epic battle! His monster "+senderMonster.getName()+" versus your monster "+reciverMonster.getName(),
                                p));
                    pm.storeNotifications(p);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, Player ID not found.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid monster IDS.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid parameters for fight request.");
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(FightRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(FightRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
