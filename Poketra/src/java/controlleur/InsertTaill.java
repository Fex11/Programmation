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
import model.AchatMp;
import model.Grade;
import model.Look;
import model.Mp;
import model.StockMp;
import model.Taille;

/**
 *
 * @author fex
 */
public class InsertTaill extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String taille=request.getParameter("taille");
        try {
            Connection c=Connexion.getConnection();
            c.setAutoCommit(false);
            Taille.inserer(c, taille);
            c.commit();
            c.close();
            request.getRequestDispatcher("InitTaill").forward(request, response);
        } catch (Exception ex) {
           request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
    }
}
