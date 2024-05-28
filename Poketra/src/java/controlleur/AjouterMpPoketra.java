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
public class AjouterMpPoketra extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] mp=request.getParameterValues("mp");
        int idp=Integer.parseInt(request.getParameter("poketra"));
        int[] id=new int[mp.length];
        for(int i=0;i<mp.length;i++){
            id[i]=Integer.parseInt(mp[i]);
        }
        try {
            Connection c=Connexion.getConnection();
            //Poketra.insertMp(c,3,5);
            for(int i=0;i<mp.length;i++){
                Poketra.insertMp(c,idp,id[i]);
            }
            Poketra.updateEtat(c, idp);
            c.close();
            request.getRequestDispatcher("InitPoketra").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
