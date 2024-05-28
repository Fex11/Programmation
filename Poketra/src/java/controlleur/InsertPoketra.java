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
import model.Poketra;

/**
 *
 * @author fex
 */
public class InsertPoketra extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idLook=Integer.parseInt(request.getParameter("look"));
        int idCategorie=Integer.parseInt(request.getParameter("categorie"));
        String nom=request.getParameter("nom");
        try {
            Connection c=Connexion.getConnection();
            Poketra.insert(c, idLook, idCategorie,nom);
            c.close();
            request.getRequestDispatcher("InitPoketra").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
