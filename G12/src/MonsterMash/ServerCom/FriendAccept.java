/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.Friend;
import data.Notification;
import data.Player;
import database.OtherPersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sis13
 */
public class FriendAccept extends HttpServlet {

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
        String friendID = request.getParameter("friendID");
        
        if(friendID != null) {
            OtherPersistenceManager pm = new OtherPersistenceManager();
            Friend friend = pm.getFriend(friendID);
            
            if(friend != null) {
                pm.acceptFriendRequest(friend);
                response.setStatus(200);
                
                Player sender = pm.getPlayer(friend.getLocalUserID());
                sender.addNotification(new Notification("Accepted friend request from "+friend.getRemoteUserID(), "You have accepted friend request from "+friend.getRemoteUserID(), sender));
                pm.storeNotifications(sender);
                
                response.sendRedirect("/MonsterMash/main");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid friendID");
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
