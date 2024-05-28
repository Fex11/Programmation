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
import model.Look;
import model.Mp;
import model.StockMp;

/**
 *
 * @author fex
 */
public class InsertAchat extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String date=request.getParameter("daty");
        int idMp=Integer.parseInt(request.getParameter("idmp"));
        double qte=Double.parseDouble(request.getParameter("qte"));
        double pu=Double.parseDouble(request.getParameter("pu"));
        try {
            Connection c=Connexion.getConnection();
            c.setAutoCommit(false);
            AchatMp.insert(c, date, idMp, qte, pu);
            double nvqte=StockMp.addStock(c, idMp, qte);
            Mp.historique(c, idMp, nvqte, date);
            c.commit();
            c.close();
            request.getRequestDispatcher("InitAchat").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
