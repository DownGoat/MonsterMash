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
/**
 *
 * @author Toshiba
 */
public class MainPage extends HttpServlet {

    
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
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            // Redirect when user is not logged in
            response.sendRedirect("");
        }else{
            Player selected = (Player)session.getAttribute("user");
            // Get notifications
            request.setAttribute("notificationList", selected.getNotifications());
            // Get friends and friend request
            request.setAttribute("friendshipList", selected.getFriends());
            request.getRequestDispatcher("/WEB-INF/main_page.jsp").forward(request, response);
        }

        
       // ArrayList<String> friendList = pm.getFriendList((Long)session.getAttribute("user"));
        //request.setAttribute("friendList", friendList);
        //ArrayList<String> friendRequestList = pm.getFriendRequestList((Long)session.getAttribute("user"));
        //request.setAttribute("friendRequestList", friendRequestList);
        
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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            if(session == null || session.getAttribute("user") == null){
                // Redirect when user is not logged in
                out.println("Please, sign in first.");
            }else{
                String email = request.getParameter("sendFriendRequest");
                out.println("Friend request to <b>"+email+"</b> sent successfully.");
            }
        } finally {            
            out.close();
        }
        
        //doGet(request,response);
    }
}
