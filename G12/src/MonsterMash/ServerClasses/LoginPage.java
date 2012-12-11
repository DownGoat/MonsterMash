/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import database.PersistenceManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sjk4
 */
public class LoginPage extends HttpServlet {

    public String MD5(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
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
        HttpSession session = request.getSession(true);
        if(session.getAttribute("user") != null){
            response.sendRedirect("main");
        }else{
            request.getRequestDispatcher("/WEB-INF/login_page.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMessage;
        boolean logged = false;
        if(email.length() < 1 || password.length() < 1){
            errorMessage = "Please fill in both fields.";
        }else{
            PersistenceManager pm = new PersistenceManager();
            password = MD5(password);
            if(pm.count("SELECT \"id\" FROM \"Player\" WHERE \"email\" = '"+email+"' AND \"password\" = '"+password+"'") > 0){
                logged = true;
                errorMessage = "";
            }else{
                errorMessage = "Password or email address is incorrect.";
            }
        }
        if(logged){
            HttpSession session = request.getSession(true);
            session.setAttribute("user", email);
            response.sendRedirect("main");
        }else{
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/login_page.jsp").forward(request, response);
        }
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
