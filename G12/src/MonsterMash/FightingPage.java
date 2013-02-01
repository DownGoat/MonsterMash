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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 *
 * @author FZajac
 */
@WebServlet(name = "FightingPage", urlPatterns = {"/fight"})
public class FightingPage extends HttpServlet {

    Encoder encoder = new DefaultEncoder();

    private void getDataFromDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Redirects when user is not logged in
            response.sendRedirect("");
        } else {
            String remoteRequest = request.getParameter("remoteFight");

            if ("Yes".equals(remoteRequest)) {
                doRemoteRequest(request, response);
            }


            int serverID = 0;
            try {
                serverID = Integer.parseInt(request.getParameter("server"));
            } catch (Exception e) {
                response.sendRedirect("");
                return;
            }
            Player current = (Player) session.getAttribute("user");
            PersistenceManager pm = new PersistenceManager();
            // Check if any monster dies:
            pm.checkIfAnyMonsterDies();
            // Updates player informations
            current = pm.getPlayer(current.getUserID());
            session.setAttribute("user", current);
            // Saves all friends to attribute
            request.setAttribute("friendList", current.getFriends());
            // Saves monsters of selected friend to attribute
            if (serverID == CONFIG.OUR_SERVER) {
                request.setAttribute("friendMonsterList", pm.getMonsterList(encoder.encodeForSQL(new OracleCodec(), request.getParameter("user"))));
            } else {
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
        if (session == null || session.getAttribute("user") == null) {
            // Redirects when user is not logged in
            response.sendRedirect("");
        } else {
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

    public void doRemoteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Redirects when user is not logged in
            response.sendRedirect("");
        } else {
            RemoteTalker rt = new RemoteTalker();
            String user = encoder.encodeForSQL(new OracleCodec(), request.getParameter("user"));
            String localMonsterID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("localMonsterID"));
            String remoteMonsterID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("remoteMonsterID"));
            int server = Integer.parseInt(encoder.encodeForSQL(new OracleCodec(), request.getParameter("remoteServerNumber")));
            Player player = (Player) session.getAttribute("user");
            
            
//            String senderID, String recieverID
//            , String fightID, String senderMonsterID
//            , String receiverMonsterID, int senderServerID, int recieverServerID

            FightRequest ft = new FightRequest(
                    user,
                    player.getUserID(),
                    String.valueOf(new Date().getTime()),
                    remoteMonsterID,
                    localMonsterID,
                    server,
                    CONFIG.OUR_SERVER
                    );
            
            OtherPersistenceManager pm = new OtherPersistenceManager();
            pm.storeFightRequest(ft);
            rt.remoteFightRequest(ft, server);
        }

    }
}
