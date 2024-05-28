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
public class Init_etat extends HttpServlet {

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
        //
        try{
            Connection c=Connexion.getConnection();
            Article[] a=Article.getAllArticle(c);
            Magasin[] m=Magasin.getAllMagasin(c);
            c.close();
            if(request.getAttribute("message")!=null){
                request.setAttribute("message",request.getAttribute("message"));
            }else{
                request.setAttribute("message","");
            }
            request.setAttribute("a",a);
            request.setAttribute("m",m);
            request.getRequestDispatcher("etat.jsp").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("message",ex.getMessage());
            request.getRequestDispatcher("etat.jsp").forward(request, response);
        }
        
    }
}
