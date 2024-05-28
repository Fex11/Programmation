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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Look;
import model.Mp;
import model.Poketra;
import model.Statistiqu;
import model.StockMp;

/**
 *
 * @author fex
 */
public class Statistique extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idp=Integer.parseInt(request.getParameter("idp"));
        int chart=0;
        if(request.getParameter("chart")!=null){
            chart=1;
        }
        Vector<Statistiqu> v=new Vector<Statistiqu>();
        try {
            Connection c=Connexion.getConnection();
            if(idp==0){
                Poketra[] p=Poketra.getAll(c);
                for(int i=0;i<p.length;i++){
                    Statistiqu s=Statistiqu.statistique(c, p[i].getIdPoketra());
                    v.add(s);
                }
            }else{
                Statistiqu s=Statistiqu.statistique(c,idp);
                v.add(s);
            }
            c.close();
            request.setAttribute("s", v);
            if(chart==1){
                request.getRequestDispatcher("chart.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("statistique.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
