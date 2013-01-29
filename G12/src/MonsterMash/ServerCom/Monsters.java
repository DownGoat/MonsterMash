/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.Monster;
import database.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author sis13
 */
public class Monsters extends HttpServlet {

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
            out.println("<title>Servlet Monsters</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Monsters at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String userID = request.getParameter("userID");
        String monsterID = request.getParameter("monsterID");
        PrintWriter out = response.getWriter();

        if (userID != null) {
            out.write(usersMonsters(Integer.parseInt(userID), response));
        } 
        else if (monsterID != null) {
            out.write(singleMonster(monsterID, response));
        }
        
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Speak Java please.");
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

    public String usersMonsters(int userID, HttpServletResponse response) throws IOException {
        PersistenceManager pm = new PersistenceManager();
        ArrayList<Monster> monsters = pm.getMonsterList(userID);

        if (monsters == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
        }

        return JSONManager.jsonMonsterList(monsters).toString();
    }

    public String singleMonster(String monsterID, HttpServletResponse response) throws IOException {
        PersistenceManager pm = new PersistenceManager();
        Monster monster = pm.getMonster(Integer.parseInt(monsterID));

        if (monster == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Monster not found");
        }

        return JSONManager.jsonMonster(monster).toString();
    }
}
