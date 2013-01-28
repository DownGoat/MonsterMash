/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.Player;
import database.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sis13
 */
@WebServlet(name = "user", urlPatterns = {"/user"})
public class User extends HttpServlet {

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

        PersistenceManager pm = new PersistenceManager();
        String userEmail = request.getParameter("userID");

        if (userEmail != null) {
            int userId = pm.getPlayerID(userEmail);
            Player player = null;
            System.out.println(userId);

            if (userId != 0) {
                player = pm.getPlayer(userId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
            }

            if (player != null) {
                PrintWriter out = response.getWriter();
                out.write(JSONManager.jsonPlayer(player).toString());
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
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
