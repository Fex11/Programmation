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
import model.Metier;
import model.MetierPoketra;
import model.Mp;
import model.Poketra;
import model.StockMp;

/**
 *
 * @author fex
 */
public class InitDetailFabrication extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String date=request.getParameter("daty");
         int idPoketra;
        if(request.getParameter("idPoketra")!=null){
            idPoketra=Integer.parseInt(request.getParameter("idPoketra"));
        }else{
            idPoketra=(int) request.getAttribute("idPoketra");
        }
        try {
            Connection c=Connexion.getConnection();
            Metier [] m=Metier.getAll(c);
            Poketra p=Poketra.getById(c, idPoketra);
            MetierPoketra[] mp=MetierPoketra.getMetierByIdPoketra(c, idPoketra);
            p.setMetier(mp);
            c.close();
            request.setAttribute("metier", m);
            request.setAttribute("poketra", p);
            request.getRequestDispatcher("detailFabrication.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
