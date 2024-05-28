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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * @author MOREL BEN Taboaly
 */
public class RalongerAnnonce extends HttpServlet {

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
        int nb = Integer.parseInt(request.getParameter("nbrj"));
        //int idClient = Integer.parseInt(request.getParameter("idClient"));
        String sdf = request.getParameter("df");
        DateTimeFormatter f=DateTimeFormatter.ISO_DATE;
        LocalDate df=LocalDate.parse(sdf,f);
        HttpSession session = request.getSession();
// Vérifier si l'utilisateur est connecté en vérifiant la présence d'un attribut "username" dans la session
       
        if ( session!= null) {
        
        //
            try{
                Connection c=Connexion.getConnection();
                Annonce.Ralonger(c, df, ida,nb);
                c.close();
                request.getRequestDispatcher("Init_choixService").forward(request, response);
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
}
