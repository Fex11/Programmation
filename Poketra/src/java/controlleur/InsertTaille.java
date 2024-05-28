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
import model.Poketra;

/**
 *
 * @author fex
 */
public class InsertTaille extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] qte=request.getParameterValues("qte");
        int idp=Integer.parseInt(request.getParameter("poketra"));
        int taille=Integer.parseInt(request.getParameter("taille"));
        double[] qtes=new double[qte.length];
        for(int i=0;i<qte.length;i++){
            qtes[i]=Double.parseDouble(qte[i]);
        }
        try {
            Connection c=Connexion.getConnection();
            c.setAutoCommit(false);
            Mp[] mps=Poketra.getMpPoketra(c, idp);
            //Poketra.insertMp(c,3,5);
            for(int i=0;i<qtes.length;i++){
                Poketra.insertQte(c,idp,mps[i].getIdMp(),taille,qtes[i]);
            }
            Poketra.init(c, idp, taille);
            c.commit();
            c.close();
            request.getRequestDispatcher("InitTaille?idPoketra="+idp).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
