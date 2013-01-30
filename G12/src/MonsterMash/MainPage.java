/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import database.PersistenceManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.*;
import java.util.Collections;
/**
 *
 * @author sjk4
 */
public class MainPage extends HttpServlet {

    /**
     * Gets all data from DB, which will be displayed on main screen (list of friends, monsters and notifications).
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void getDataFromDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            // Redirects when user is not logged in
            response.sendRedirect("");
        }else{
            Player current = (Player)session.getAttribute("user");
            PersistenceManager pm = new PersistenceManager();
            // Updates player informations
            current = pm.getPlayer(current.getUserID());
            session.setAttribute("user", current);
            // Saves all notifications to attribute
            request.setAttribute("notificationList", current.getNotifications());
            // Saves all friends to attribute
            request.setAttribute("friendList", current.getFriends());
            // Saves all friend requests to attribute
            request.setAttribute("requestList", pm.getFriendRequestList(current.getUserID()));
            request.getRequestDispatcher("/WEB-INF/main_page.jsp").forward(request, response);
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
        this.respondToFriendRequest(request, response);
        this.getDataFromDB(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.sendFriendRequest(request, response);
        this.doGet(request, response);
    }
    
    /**
     * Check if user sent form with new friend request if so process it and send friend request with proper notifications.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void sendFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Checks if user is logged in
        if(session != null && session.getAttribute("user") != null){
            // Gets email from POST
            String email = request.getParameter("email");
            PersistenceManager pm = new PersistenceManager();
            Player sender = (Player)session.getAttribute("user");
            // Checks if user with this email address exists
            String[] receiver = pm.getPlayerIdAndServer(email);
            if(receiver[0].equals("0")){
                request.setAttribute("alertMessage", "Cannot find user with this email address.");
            }else if(pm.isFriendRequestSent(sender.getUserID(), receiver[0])){
                request.setAttribute("alertMessage", "Cannot send friend request to this player.");
            }else if(sender.getUserID().equals(receiver[0])){
                request.setAttribute("alertMessage", "Cannot send friend request to yourself.");
            }else{
                String message = "Friend request to <b>"+email+"</b> sent successfully.";
                sender.addNotification(new Notification(message, "You have sent friend request to <b>"+email+"</b>.", sender));
                pm.storeNotifications(sender);
                int receiverServerID = Integer.parseInt(receiver[1]);
                pm.sendFriendRequest(sender.getUserID(), receiver[0], receiverServerID);
                if(receiver[1].equals("12")){
                    //Receiver is on our server
                    Player receiverObject = pm.getPlayer(receiver[0]);
                    receiverObject.addNotification(new Notification("Received friend request from <b>"+sender.getUsername()+"</b>", "You have received friend request from <b>"+sender.getUsername()+"</b>.", receiverObject)); 
                    pm.storeNotifications(receiverObject);
                    // Save updated player's object in session
                    session.setAttribute("user", sender);
                    request.setAttribute("alertMessage", message);
                }else{
                    // TODO: Receiver is on different server
                }
            }
        }
    }
    
    /**
     * Accept or cancel friend request.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void respondToFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check if user is logged in
        if(session != null && session.getAttribute("user") != null){
            Player logged = (Player)session.getAttribute("user");
            PersistenceManager pm = new PersistenceManager();
            // Check if acceptFriendRequest occured:
            if(request.getParameter("acceptFriendRequest") != null){
                String friendshipID = request.getParameter("acceptFriendRequest");
                pm.acceptFriendRequest(friendshipID, logged.getUserID());
            }
            // Check if cancelFriendRequest occured:
            if(request.getParameter("cancelFriendRequest") != null){
                String friendshipID = request.getParameter("cancelFriendRequest");
                pm.cancelFriendRequest(friendshipID, logged.getUserID());
            }
        }
    }
}
