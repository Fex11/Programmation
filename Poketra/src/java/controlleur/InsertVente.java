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
import model.Poketra;

/**
 *
 * @author fex
 */
public class InsertVente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idPoketra=Integer.parseInt(request.getParameter("idPoketra"));
        int idTaille=Integer.parseInt(request.getParameter("taille"));
        double prix=Double.parseDouble(request.getParameter("prix"));
        try {
            Connection c=Connexion.getConnection();
            Poketra.insertPrix(c, idPoketra, idTaille, prix);
            c.close();
            request.setAttribute("idPoketra",idPoketra);
            request.getRequestDispatcher("InitVente").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
