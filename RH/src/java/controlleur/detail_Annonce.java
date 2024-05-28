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
import model.Diplome;
import model.Langue;

/**
 *
 * @author SABI
 */
public class detail_Annonce extends HttpServlet {

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
            Connection c=Connexion.getConnection();
            Vector<Langue> v=new Vector<Langue>();         
            HttpSession session = request.getSession();
            int id = (Integer.parseInt(request.getParameter("id")));
            v = Langue.getLangueByAnnonce(c,id);
            Annonce a=Annonce.getAnnonceById(c,id) ; 
            Diplome  d = Diplome.getDiplomeByAnnonce(c,id) ;
            boolean exist=Annonce.checkCv(c, id,(int)session.getAttribute("id"));
            c.close();
            request.setAttribute("annonces",a);
            request.setAttribute("exist",exist);
            request.setAttribute("langue",v);
            request.setAttribute("diplome",d);
            request.getRequestDispatcher("detail_annonce.jsp").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


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
