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
import model.*;

/**
 *
 * @author fex
 */
public class EtatStock extends HttpServlet {

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
        String debut = request.getParameter("date1");
        String article = request.getParameter("article");
        String magasin = request.getParameter("magasin");
        String fin=request.getParameter("date2");
        StringBuilder stringBuilder = new StringBuilder(debut);
        stringBuilder.setCharAt(10,' ');
        debut = stringBuilder.toString();
        StringBuilder stringBuilder1 = new StringBuilder(fin);
        stringBuilder1.setCharAt(10,' ');
        fin = stringBuilder1.toString();
        //
        try{
            Connection c=Connexion.getConnection();
            Etat e=Etat.getEtat(c, debut, fin, magasin, article);
            c.close();
            request.setAttribute("etat",e);
            request.getRequestDispatcher("etat_de_stock.jsp").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("message",ex.getMessage());
            request.getRequestDispatcher("etat.jsp").forward(request, response);
        }
    }
}
