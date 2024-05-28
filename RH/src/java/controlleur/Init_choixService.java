/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import connexion.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Annonce;
import model.Service;

/**
 *
 * @author fex
 */
public class Init_choixService extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
          HttpSession session = request.getSession();
// Vérifier si l'utilisateur est connecté en vérifiant la présence d'un attribut "username" dans la session
       
        if ( session!= null) {
            Connection c = Connexion.getConnection();
            Vector<Service> services = Service.getAllService(c);
            Vector<Annonce> annonce = Annonce.getAllAnnonce(c);
            for(int i=0;i<annonce.size();i++){
                annonce.get(i).checkDate(c);
            }
            Vector<Annonce> notif=Annonce.getNotifAnnonce(c);
            c.close();
            request.setAttribute("services", services);
            request.setAttribute("notif", notif);
            request.getRequestDispatcher("choixService.jsp").forward(request, response);
        }
       
    }catch(Exception ex){
        request.setAttribute("message", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
    }
}
