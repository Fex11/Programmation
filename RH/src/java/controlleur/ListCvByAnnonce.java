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
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Annonce;
import model.Cv;

/**
 *
 * @author fex
 */
public class ListCvByAnnonce extends HttpServlet {

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
            int ida = (Integer.parseInt(request.getParameter("ida")));
            Annonce a = Annonce.getAnnonceById(c, ida);
            Vector<Cv> cvs=Cv.getCvByIdAnnonce(c, ida) ;
            for(int i=0;i<cvs.size();i++){
               cvs.get(i).setEtat(Cv.checkEtat(c,cvs.get(i).getIdCv(),ida));
               //cvs.get(i).setNote(cvs.get(i).getNote(c));
            }
            c.close();
            request.setAttribute("cvs",cvs);
             request.setAttribute("a",a);
            request.getRequestDispatcher("listecv.jsp").forward(request, response);
         }
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
