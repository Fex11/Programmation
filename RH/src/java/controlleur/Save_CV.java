/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import connexion.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import model.Annonce;
import model.Cv;
import model.Note;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Save_CV extends HttpServlet {

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
        int ida = Integer.parseInt(request.getParameter("ida"));
                int idClient = Integer.parseInt(request.getParameter("idClient"));

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        int num = Integer.parseInt(request.getParameter("num"));
        String adresse = request.getParameter("adresse");
        String[] diplome=request.getParameterValues("diplome");
        String[] degre=request.getParameterValues("degre");
        String sexe = request.getParameter("sexe");
        String dtn = request.getParameter("dtn");
        int nationalite = Integer.parseInt(request.getParameter("nat"));
        int experience = Integer.parseInt(request.getParameter("exp"));
        String sm=request.getParameter("sm");
        String[] langue=request.getParameterValues("langue");
        int[] di = new int[diplome.length];
        int[] de = new int[diplome.length];
        for(int i=0;i<diplome.length;i++) {
            di[i] = Integer.parseInt(diplome[i]);
            de[i] = Integer.parseInt(degre[i]);
        }
        //
        try{
        Connection c=Connexion.getConnection();
        Cv.insertCv(c, ida);
        int idcv = Cv.getLastCv(c);
        Cv.langue(c, langue,idcv);
        for(int i=0;i<di.length;i++){
            Cv.insertDiplomeCv(c, idcv, di[i], de[i]);
        }
        Cv.insertDetailCv(c, idcv,idClient, ida, nationalite, nom, prenom, sexe ,dtn,experience,sm,email,num,adresse);
        Cv.initTest(c, idcv, ida);
        Note.noteCv(c, idcv);
        c.close();
        request.getRequestDispatcher("Inint_listeAnnonce").forward(request, response);
        }catch(Exception ex){
            StringWriter sw=new StringWriter();
            PrintWriter pw=new PrintWriter(sw);
            ex.printStackTrace(pw);
            String s=sw.toString();
            request.setAttribute("message", s);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        
        
    }
}
