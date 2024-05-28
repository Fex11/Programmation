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
import model.Bc;
import model.Payement;
import model.Service;

/**
 *
 * @author fex
 */
public class Init_finance extends HttpServlet {

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
            out.println("<title>Servlet Init_finance</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Init_finance at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        try{
          HttpSession session = request.getSession();
// Vérifier si l'utilisateur est connecté en vérifiant la présence d'un attribut "username" dans la session
       
        if ( session!= null) {
            Connection c = Connexion.getConnection();
            Vector<Bc> bc =Bc.getAllByEtat(c, 0);
            Vector<Bc> notif=Bc.getAllByEtat(c, 2);
            Vector<Bc> acc=Bc.getAllByEtat(c, 1);
            double solde=Payement.getSolde(c);
            for(int i=0;i<acc.size();i++){
                solde=solde-acc.get(i).getTtc();
            }
            c.close();
            request.setAttribute("bc", bc);
            request.setAttribute("notif", notif);
            request.setAttribute("dispo", solde);
            request.getRequestDispatcher("finance.jsp").forward(request, response);
        }
       
    }catch(Exception ex){
        request.setAttribute("message", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
