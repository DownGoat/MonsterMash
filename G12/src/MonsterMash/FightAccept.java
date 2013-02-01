/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ServerCom.RemoteTalker;
import data.CONFIG;
import data.FightRequest;
import data.Monster;
import data.Player;
import database.OtherPersistenceManager;
import database.PersistenceManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author FZajac
 */
public class FightAccept extends HttpServlet {

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
                Player sender = pm.getPlayer(fr.getSenderID());
                
                if(sender != null) {
                    Monster opponent = pm.getMonster(fr.getSenderMonsterID(), fr.getSenderServerID());
                    double opponentHealth = pm.getMonster(fr.getReceiverMonsterID(), CONFIG.OUR_SERVER).fight(opponent);
                    if(opponentHealth > 0)
                    {
                        response.sendRedirect("/MonsterMash/fight/won?fightID=" + fightID + "&strength=" + opponent.getCurrentStrength() + "&defence=" + opponent.getCurrentDefence() + "&health=" + opponentHealth);
                    }
                    else
                    {
                        response.sendRedirect("/MonsterMash/fight/lost?fightID=" + fightID);
                    }
                    
                    
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown player in fight request.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown fightID.");
            }
            
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid parameters for fight reject.");
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
