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
import model.Degre;
import model.Diplome;
import model.Langue;
import model.Nationalite;
import model.Poste;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class FormCV extends HttpServlet {

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
            String sida=request.getParameter("ida");
            String sidp=request.getParameter("idp");
            int ida=Integer.parseInt(sida);
            int idp=Integer.parseInt(sidp);
            HttpSession session = request.getSession();

            try{
            Connection c=Connexion.getConnection();
            Annonce annonce=Annonce.getAnnonceById(c,ida) ; 
            Vector<Diplome> diplome=Diplome.getAllDiplome(c);
            Vector<Degre> degre=Degre.getAllDegre(c);
            Vector<Nationalite> nationalite=Nationalite.getAllNationalite(c);
            Vector<Langue> langue=Langue.getAllLangue(c);
            c.close();
            request.setAttribute("annonce",annonce);
            request.setAttribute("diplome",diplome);
            request.setAttribute("degre",degre);
            request.setAttribute("nationalite",nationalite);
            request.setAttribute("langue",langue);
            request.getRequestDispatcher("formulaireCV.jsp").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        
    }
}
