/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ServerCom.RemoteTalker;
import data.CONFIG;
import data.Monster;
import data.Player;
import database.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;

/**
 *
 * @author FZajac
 */
@WebServlet(name = "FightingPage", urlPatterns = {"/fight"})
public class FightingPage extends HttpServlet {

    private void getDataFromDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            // Redirects when user is not logged in
            response.sendRedirect("");
        }else{
            int serverID = 0;
            try{
                 serverID = Integer.parseInt(request.getParameter("server"));
            }catch(Exception e){
                response.sendRedirect("");
                return;
            }
            Player current = (Player)session.getAttribute("user");
            PersistenceManager pm = new PersistenceManager();
            // Check if any monster dies:
            pm.checkIfAnyMonsterDies();
            // Updates player informations
            current = pm.getPlayer(current.getUserID());
            session.setAttribute("user", current);
            // Saves all friends to attribute
            request.setAttribute("friendList", current.getFriends());
            // Saves monsters of selected friend to attribute
            if(serverID == CONFIG.OUR_SERVER){
                request.setAttribute("friendMonsterList", pm.getMonsterList(request.getParameter("user")));
            }else{
                RemoteTalker rt = new RemoteTalker();
                String address = rt.getRemoteAddress(serverID);
                ArrayList<Monster> monsters;
                try {
                    monsters = rt.getRemoteUsersMonsters(request.getParameter("user"), address);
                } catch (JSONException ex) {
                    response.sendRedirect("");
                    return;
                }
                request.setAttribute("friendMonsterList", monsters);
            }
            
            // Saves server id of selected friend to attribute
            request.setAttribute("remoteServer", request.getParameter("server"));
            // Saves all friend requests to attribute
            request.setAttribute("requestList", pm.getFriendRequestList(current.getUserID()));
            // Saves all monsters to attribute
            request.setAttribute("monsterList", pm.getMonsterList(current.getUserID()));
            request.getRequestDispatcher("/WEB-INF/fight_page.jsp").forward(request, response);
        }
    }
    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            // Redirects when user is not logged in
            response.sendRedirect("");
        }else{
            this.getDataFromDB(request, response);
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
        doGet(request, response);
    }
}
