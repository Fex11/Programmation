/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import connexion.Connexion;
import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fex
 */
public class SortieStock extends HttpServlet {

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
        String article = request.getParameter("article");
        String magasin = request.getParameter("magasin");
        String sidu = request.getParameter("unite");
        String sqte=request.getParameter("qte");
        double qte=Double.parseDouble(sqte);
        int ida=Integer.parseInt(article);
        int idm=Integer.parseInt(magasin);
        int idu=Integer.parseInt(sidu);

        //
        try{
            Connection c=Connexion.getConnection();
            Magasin m=Magasin.getMagasinById(c, idm);
            Article a=Article.getArticleById(c, ida);
            Unite unite=Unite.verifyUnite(c, ida, idu);
            double qtee=qte*unite.getValeur();
            Sortie ss=new Sortie(date,m,a,qtee);
            ss.setUnite(unite.getNomUnite());
            ss.setValeur(qte);
            ss.insert(c);
            c.close();
            request.setAttribute("message","Sortie effectué avec succes");
            request.getRequestDispatcher("Init_sortie").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("message",ex.getMessage());
            request.getRequestDispatcher("Init_sortie").forward(request, response);
        }
        
    }
}
