/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import connexion.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ClientLogin;

/**
 *
 * @author SABI
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//       PrintWriter out = response.getWriter();
             String nom = request.getParameter("username");
             String mdp = request.getParameter("password");
             String ad = "admin";
             String mad = "admin";
    try{
            if(ad.equals(nom)&&mad.equals(mdp)){
               HttpSession session = request.getSession();
                session.setAttribute("admin",ad);
               request.getRequestDispatcher("Init_choixService").forward(request, response);
            }else{
             ClientLogin cl = new ClientLogin().getClient(Connexion.getConnection(),nom,mdp);
             if (nom.equals(cl.getNom())&&mdp.equals(cl.getMdp())) {
                HttpSession session = request.getSession();
                session.setAttribute("username", cl.getNom());               
                session.setAttribute("id", cl.getId());
                if(cl.getId()==2){
                    request.getRequestDispatcher("Init_dg").forward(request, response);
                }
            } else {
               request.getRequestDispatcher("index.jsp").forward(request, response);
               
            }

            }
    
           
        } catch(Exception e){
      e.printStackTrace();
      }

}
      

    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

//}
