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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Look;
import model.Mp;
import model.Poketra;
import model.Vente;

/**
 *
 * @author fex
 */
public class InsertVentePoketra extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idc=Integer.parseInt(request.getParameter("client"));
        int idp=Integer.parseInt(request.getParameter("poketra"));
        int idt=Integer.parseInt(request.getParameter("taille"));
        int nb=Integer.parseInt(request.getParameter("nb"));
        String date=request.getParameter("date");
        try {
            Connection c=Connexion.getConnection();
            c.setAutoCommit(false);
            Poketra.checkSortie(c, idp,idt,date, nb);
            Vente.insert(c, idp, idc, nb);
            c.commit();
            c.close();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("message",ex.getMessage());
            request.getRequestDispatcher("InitVentePoketra").forward(request, response);
        }
        
    }

}
