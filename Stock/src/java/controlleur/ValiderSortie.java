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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;
import model.Entree;
import model.Magasin;
import model.Sortie;

/**
 *
 * @author fex
 */
public class ValiderSortie extends HttpServlet {

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
        String date = request.getParameter("date");
        int id=Integer.parseInt(request.getParameter("id"));
        //
        try{
            Connection c=Connexion.getConnection();
            Entree e=Entree.getById(c, id);
            e.valider(c, date);
            c.close();
            request.setAttribute("message","Validation avec succes");
            request.getRequestDispatcher("Init_entreeNv").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("message",ex.getMessage());
            request.setAttribute("id",id);
            request.getRequestDispatcher("Init_validerEntree").forward(request, response);
        }
        
    }
}
