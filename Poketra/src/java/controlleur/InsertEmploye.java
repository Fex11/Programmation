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
import model.Employe;
import model.Look;
import model.Mp;
import model.StockMp;

/**
 *
 * @author fex
 */
public class InsertEmploye extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nom=request.getParameter("nom");
        String date=request.getParameter("dateEmbauche");
        int idMetier=Integer.parseInt(request.getParameter("idMetier"));
        try {
            Connection c=Connexion.getConnection();
            int id=Employe.insert (c,nom,date,idMetier);
            c.close();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
