/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Player;
import database.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FZajac
 */
@WebServlet(name = "FightingPage", urlPatterns = {"/fight"})
public class FightingPage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FightingPage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FightingPage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            // Redirects when user is not logged in
            response.sendRedirect("");
        }else{
            this.getDataFromDB(request, response);
        }
    }
    
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
            // Saves monsters of selected friend to attribute
            request.setAttribute("friendMonsterList", pm.getMonsterList(request.getParameter("user")));
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
