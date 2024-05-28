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
import java.time.LocalDate;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Annonce;
import model.Degre;
import model.Diplome;
import model.Langue;
import model.Nationalite;
import model.Poste;

/**
 *
 * @author fex
 */
public class Save_annonce extends HttpServlet {

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
        String sservice=request.getParameter("ids");
        String sposte=request.getParameter("poste");
        String sdiplome=request.getParameter("diplome");
        String sdegre=request.getParameter("degre");
        String sexe=request.getParameter("sexe");
        String svh=request.getParameter("vh");
        String shj=request.getParameter("hj");
        String snationalite=request.getParameter("nationalite");
        String smin=request.getParameter("min");
        String smax=request.getParameter("max");
        String sexperience=request.getParameter("experience");
        String sm=request.getParameter("sm");
        String[] langue=request.getParameterValues("langue");
        String sdatefin = request.getParameter("datefin");
        //
        int service=Integer.parseInt(sservice);
        int poste=Integer.parseInt(sposte);
        int diplome=Integer.parseInt(sdiplome);
        int degre=Integer.parseInt(sdegre);
        int vh=Integer.parseInt(svh);
        int hj=Integer.parseInt(shj);
        int nationalite=Integer.parseInt(snationalite);
        int min=Integer.parseInt(smin);
        int max=Integer.parseInt(smax);
        int experience=Integer.parseInt(sexperience);
        //
        try{
        Connection c=Connexion.getConnection();
        LocalDate dateActuelle = LocalDate.now();
        String dd = dateActuelle.toString();
        Annonce.insertAnnonce(c,dd,sdatefin);
        int ida=Annonce.getLastAnnonce(c);
        Annonce.insertDiplomeRequisAnnonce(c, ida, diplome, degre);
        Annonce.insertDetailAnnonce(c, ida, service, poste,nationalite,vh,hj, sexe, min, max, experience, sm);
        Annonce.langue(c, langue, ida);
        request.getRequestDispatcher("Init_choixService").forward(request, response);
    }catch(Exception ex){
        request.setAttribute("message", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
        
        
        
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
