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
import model.Cv;
import model.Reponse;

/**
 *
 * @author fex
 */
public class Save_test extends HttpServlet {

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
//          String username =(String)session.getAttribute("username"); 
         if(session!=null){
            Connection c=Connexion.getConnection();      
            int idcv = (Integer.parseInt(request.getParameter("idcv")));
            int idc=(int)session.getAttribute("id");
            String[] reponse=request.getParameterValues("reponse");
            Cv cv=Cv.getCvbyId(c, idcv) ;
            Reponse.insertReponse(c, reponse, idc);
            Reponse.noteClient(c, cv);
            Vector<Cv> cvs=Cv.getNotif(c, idc);
            Vector<Annonce> v=new Vector<Annonce>();
            Vector<Annonce> a=new Vector<Annonce>();
            for(int i=0;i<cvs.size();i++){
                a.add(Annonce.getAnnonceById(c, cvs.get(i).getAnnonce()));
            }
            Annonce annonce=Annonce.getAnnonceById(c,cv.getAnnonce());
            annonce.checkEntretien(c);
            v=Annonce.getAllAnnonce(c);
            //
            Vector<Cv> e=Cv.getNotifEntretien(c, idc);
            Vector<Annonce> b=new Vector<Annonce>();
            for(int i=0;i<e.size();i++){
                b.add(Annonce.getAnnonceById(c, e.get(i).getAnnonce()));
            }
            c.close();
            request.setAttribute("annonces",v);
            request.setAttribute("test",a);
            request.setAttribute("cvs",cvs);
            request.setAttribute("entretien",b);
            request.setAttribute("cve",e);
            request.getRequestDispatcher("liste_annonce.jsp").forward(request, response);
         }
        }catch(Exception ex){
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
