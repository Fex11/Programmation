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
import model.MetierPoketra;
import model.Mp;

/**
 *
 * @author fex
 */
public class InsertDetailFabrication extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idPoketra=Integer.parseInt(request.getParameter("idPoketra"));
        int idMetier=Integer.parseInt(request.getParameter("metier"));
        int nb=Integer.parseInt(request.getParameter("nb"));
        double temp=Double.parseDouble(request.getParameter("temp"));
        try {
            Connection c=Connexion.getConnection();
            MetierPoketra.insert(c, idPoketra, idMetier, temp, nb);
            c.close();
            request.setAttribute("idPoketra",idPoketra);
            request.getRequestDispatcher("InitDetailFabrication").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
