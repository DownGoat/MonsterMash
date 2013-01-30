/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Monster;
import data.Notification;
import data.Player;
import database.PersistenceManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sjk4
 */
public class MatingPage extends HttpServlet {

    /**
     * Gets all data from DB, which will be displayed on main screen (list of
     * friends, monsters and notifications).
     *
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
            // Saves all friends and friend requests to attribute
            request.setAttribute("friendList", current.getFriends());
            // Saves all friend requests to attribute
            request.setAttribute("requestList", pm.getFriendRequestList(current.getUserID()));
            // Saves all monsters to attribute
            request.setAttribute("monsterList", pm.getMonsterList(current.getUserID()));
            request.getRequestDispatcher("/WEB-INF/mating_page.jsp").forward(request, response);
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
            PersistenceManager pm = new PersistenceManager();
            Player current = (Player) session.getAttribute("user");
            // Check if user wants to cancel offer
            this.cancelOffer(request, response, pm, current);
            // Check if user wants to buy monster
            this.breedMonster(request, response, pm, current);
            ArrayList<Monster> monsters = pm.getMonstersForBreeding(current.getUserID());
            // Prepare strings:
            ArrayList<String> monstersForBreed = new ArrayList<String>();
            for(Monster m: monsters){
                monstersForBreed.add("<li><a href=\"javascript:{}\" onclick=\"document.getElementById('monsterID').value = '"+m.getId()+"'; document.getElementById('serverID').value = '"+m.getServerID()+"'; form.submit(); return false;\"><b>Name:</b> "+m.getName()+" | <b>Owner:</b> "+pm.getPlayerUsername(m.getUserID(), m.getServerID())+" | <b>Price:</b> "+m.getSaleOffer()+"$ | <b>Stats:</b> DEF: "+(int)(m.getBaseDefence()*100)+" /  HP: "+(int)(m.getBaseHealth()*100)+" / STR: "+(int)(m.getBaseStrength()*100)+" </a></li>");
            }
            request.setAttribute("monstersForBreed", monstersForBreed);
            this.getDataFromDB(request, response);
        }
    }
    
    private void cancelOffer(HttpServletRequest request, HttpServletResponse response, PersistenceManager pm, Player current) throws ServletException, IOException {
        String monsterID = request.getParameter("cancelOffer");
        if(monsterID != null){
            if(pm.cancelBreedingOffer(current.getUserID(), monsterID)){
                current.addNotification(new Notification("You have canceled your breeding offer of <b>"+pm.getMonsterName(monsterID)+"</b>.", "<b>"+pm.getMonsterName(monsterID)+"</b> breeding offer has been canceled by you. Now breeding offer will not appear on the market.", current));
                pm.storeNotifications(current);
            }
        }
    }
    
    private void breedMonster(HttpServletRequest request, HttpServletResponse response, PersistenceManager pm, Player current) throws ServletException, IOException {
        String monsterID = request.getParameter("monster");
        String server = request.getParameter("server");
        String myMonsterID = request.getParameter("myMonster");
        if(monsterID != null && server != null && myMonsterID != null){
            try{
                String message = null;
                int serverID = Integer.parseInt(server);
                if(!pm.canUserBreedMonster(current.getMoney(), monsterID, serverID)){
                    message = "You don not have enough money.";
                }else{
                    Monster myMonster = pm.getMonster(myMonsterID, 12);
                    Monster monster = pm.getMonster(monsterID, serverID);
                    if(myMonster == null || monster == null){
                        message = "Cannot find monster.";
                    }else{
                        //ArrayList<Monster> children = monster.breedWith(myMonster);
                        //request.setAttribute("monsterChildren", children);
                    }
                }
                request.setAttribute("alertMessage", message);
            }catch(Exception e){
                
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Redirects when user is not logged in
            response.sendRedirect("");
        } else {
            // Make new offer:
            Player current = (Player) session.getAttribute("user");
            String monsterID = request.getParameter("monsterID");
            String offerAmount = request.getParameter("offerAmount");
            String error = null;
            PersistenceManager pm = new PersistenceManager();
            if(monsterID == null || offerAmount == null){
                error = "Please fill both fields.";
            }else if(monsterID.length() < 1){
                error = "Please select monster name.";
            }else if(offerAmount.length() < 1){
                error = "Please specify your offer amount.";
            }else{
                int amount = 0;
                try{
                    amount = Integer.parseInt(offerAmount);
                    if(!pm.makeNewMarketOffer(current.getUserID(), monsterID, amount)){
                        error = "Incorrect monster name.";
                    }
                }catch(Exception e){
                    error = "Incorrect amount.";
                }
            }
            if(error != null){
                request.setAttribute("alertMessage", error);
            }else{
                current.addNotification(new Notification("You offered <b>"+pm.getMonsterName(monsterID)+"</b> for sale for <b>"+offerAmount+"$</b>.", "<b>"+pm.getMonsterName(monsterID)+"</b> is now available for sale for <b>"+offerAmount+"$</b>. You cannot use this monster until you cancel your offer.", current));
                pm.storeNotifications(current);
                request.setAttribute("alertMessage", "You offered "+pm.getMonsterName(monsterID)+" for sale for <b>"+offerAmount+"$</b>.");
            }
            doGet(request, response);
        }
    }
}
