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
import model.TaillePoketra;

/**
 *
 * @author fex
 */
public class InitVente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idPoketra;
        if(request.getParameter("idPoketra")!=null){
            idPoketra=Integer.parseInt(request.getParameter("idPoketra"));
        }else{
            idPoketra=(int) request.getAttribute("idPoketra");
        }
        try {
            Connection c=Connexion.getConnection();
            Poketra p=Poketra.getAllInfoById(c, idPoketra);
            for(int i=0;i<p.getTaille().length;i++){
                double prixV=TaillePoketra.getPrix(c,p.getIdPoketra(),p.getTaille()[i].getIdTaille());
                p.getTaille()[i].setPrixVente(prixV);
            }
            c.close();
            request.setAttribute("poketra", p);
            request.getRequestDispatcher("prixVente.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
