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
import model.Look;
import model.Mp;
import model.StockMp;

/**
 *
 * @author fex
 */
public class InsertMp extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nom=request.getParameter("nom");
        int idUnite=Integer.parseInt(request.getParameter("unite"));
        double pu=Double.parseDouble(request.getParameter("pu"));
        try {
            Connection c=Connexion.getConnection();
            int id=Mp.insert(c, nom, idUnite,pu);
            StockMp.init(c, id);
            c.close();
            request.getRequestDispatcher("InitMp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
