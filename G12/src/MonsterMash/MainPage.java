/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import database.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Toshiba
 */
public class MainPage extends HttpServlet {

    /**
     * Gets all data from DB, which will be displayed on main screen.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void getDataFromDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            // Redirect when user is not logged in
            response.sendRedirect("");
        }else{
            Player selected = (Player)session.getAttribute("user");
            // Get notifications and reverse order
            ArrayList<Notification> notifications = selected.getNotifications();
            Collections.reverse(notifications);
            request.setAttribute("notificationList", notifications);
            // Get friends and friend request
            request.setAttribute("friendshipList", selected.getFriends());
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
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            String email = request.getParameter("email");
            PersistenceManager pm = new PersistenceManager();
            // Check if user with that email address exists
            int playerID = pm.getPlayerID(email);
            if(playerID > 0){
                Player selected = (Player)session.getAttribute("user");
                String message = "Friend request to <b>"+email+"</b> sent successfully.";
                // Add notification
                selected.addNotification(new Notification(message, message, selected));
                // Store notification in DB
                pm.addNewNotifications(selected);
                // Add friend request
                selected.addFriend(new Friend(playerID, 0, email, "N")); // SERVER ID = 0 -> only for now
                // Store friend request in DB
                pm.addNewFriends(selected);
                session.setAttribute("user", selected);
                request.setAttribute("alertMessage", message);
            }else{
                request.setAttribute("alertMessage", "Cannot find user with this email address.");
            }
            
        }
        this.getDataFromDB(request, response);
    }
}
