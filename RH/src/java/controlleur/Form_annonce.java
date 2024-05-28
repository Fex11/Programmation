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
import model.Degre;
import model.Diplome;
import model.Langue;
import model.Nationalite;
import model.Poste;
import model.Service;

/**
 *
 * @author fex
 */
public class Form_annonce extends HttpServlet {

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
        String sids=request.getParameter("service");
        //
        int ids=Integer.parseInt(sids);
        //
        try{
        Connection c=Connexion.getConnection();
        Vector<Poste> poste=Poste.getAllPosteByIdService(c,ids);
        Vector<Diplome> diplome=Diplome.getAllDiplome(c);
        Vector<Degre> degre=Degre.getAllDegre(c);
        Vector<Nationalite> nationalite=Nationalite.getAllNationalite(c);
        Vector<Langue> langue=Langue.getAllLangue(c);
        c.close();
        request.setAttribute("poste",poste);
        request.setAttribute("diplome",diplome);
        request.setAttribute("degre",degre);
        request.setAttribute("nationalite",nationalite);
        request.setAttribute("langue",langue);
        request.setAttribute("idService",ids);
        request.getRequestDispatcher("formulaire_annonce.jsp").forward(request, response);
    }catch(Exception ex){
        request.setAttribute("message", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
        
        
    }
}
