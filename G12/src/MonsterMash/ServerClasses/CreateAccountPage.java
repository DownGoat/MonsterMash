/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import database.PersistenceManager;
import java.io.IOException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sjk4
 */
public class CreateAccountPage extends HttpServlet {
    
    private final int MONEY_AMOUNT = 10;
    
    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
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
        request.getRequestDispatcher("/WEB-INF/create_account_page.jsp").forward(request, response);
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
        String cpassword = request.getParameter("cpassword");
        String message = null;
        String errorMessage = null;
        PersistenceManager pm = new PersistenceManager();
        if(email.length() < 1 || password.length() < 1 || cpassword.length() < 1){
            errorMessage = "Please fill in all text fields.";
        }else if(!isValidEmailAddress(email)){
            errorMessage = "Please enter correct email address.";
        }else if(!password.equals(cpassword)){
            errorMessage = "Passwords are not the same.";
        }else if(pm.count("SELECT \"id\" FROM \"Player\" WHERE \"email\" = '"+email+"'") > 0){
            errorMessage = "There is already account with this email address.";
        }else{
            password = MD5(password);
            if(pm.insert("INSERT INTO \"Player\" (\"email\", \"password\", \"money\") VALUES ('"+email+"', '"+password+"', "+MONEY_AMOUNT+")")){
                message = "Account created successfully. You can sign in now.";
            }else{
                message = pm.getErrorMessage();
            }
        }
        
        if(message != null){
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/login_page.jsp").forward(request, response);
        }
        if(errorMessage != null){
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/create_account_page.jsp").forward(request, response);
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
    }
}
