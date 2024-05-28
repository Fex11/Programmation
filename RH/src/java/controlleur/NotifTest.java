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
import model.Note;

/**
 *
 * @author fex
 */
public class NotifTest extends HttpServlet {

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
            Annonce a=Annonce.getAnnonceById(c, ida);
            Vector<Cv> cvs=Cv.getCvByIdAnnonce(c, ida) ;
            Annonce.changeEtat(c, 3, ida);
            for(int i=0;i<a.getNombre()*2;i++){
               cvs.get(i).setEtat(Cv.checkEtat(c,cvs.get(i).getIdCv(),ida));
               Cv.passerTest(c, cvs.get(i).getIdCv(), ida);
            }
            c.close();
            //request.setAttribute("cvs",cvs);
            request.getRequestDispatcher("Init_choixService").forward(request, response);
         }
        }catch(Exception ex){
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Deconnexion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + ex + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        }
    }

}
