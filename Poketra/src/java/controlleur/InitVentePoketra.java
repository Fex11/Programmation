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
import model.*;

/**
 *
 * @author fex
 */
public class InitVentePoketra extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String message="";
        if(request.getAttribute("message")!=null){
            message=(String)request.getAttribute("message");
        }
        try {
            Connection c=Connexion.getConnection();
            Client[] clients=Client.getAll(c);
            Poketra[] poketras=Poketra.getAll(c);
            Taille[] taille=Taille.getAll(c);
            c.close();
            request.setAttribute("c",clients);
            request.setAttribute("p",poketras);
            request.setAttribute("t",taille);
            request.setAttribute("m",message);
            request.getRequestDispatcher("ventepoketra.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InitLook.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
