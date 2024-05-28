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
import model.TaillePoketra;

/**
 *
 * @author fex
 */
public class Benefice extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double pu=Double.parseDouble(request.getParameter("p1"));
        double pp=Double.parseDouble(request.getParameter("p2"));
        Vector<Poketra> v=new Vector<Poketra>();
        try {
            Connection c=Connexion.getConnection();
            Poketra[] ppp=Poketra.getAll(c);
            //Poketra[] ppp=new Poketra[p.length];
            for(int i=0;i<ppp.length;i++){
                ppp[i]=Poketra.hhh(c,ppp[i].getIdPoketra());
            }
            for(int i=0;i<ppp.length;i++){
                for(int j=0;j<ppp[i].getTaille().length;j++){
                    double benefice=ppp[i].getTaille()[j].getPrixVente()-(ppp[i].getTaille()[j].getPrix()+ppp[i].getTaille()[j].getMainOeuvre());
                    if(benefice>=pu && benefice<=pp){
                        Poketra p=Poketra.getById(c, ppp[i].getIdPoketra());
                        TaillePoketra[] taille=new TaillePoketra[1];
                        taille[0]=p.getTailleRay(c, ppp[i].getTaille()[j].getIdTaille());
                        taille[0].setBenefice(benefice);
                        p.setTaille(taille);
                        v.add(p);
                    }
                }
            }
            c.close();
            request.setAttribute("poketras",v);
            request.getRequestDispatcher("ben.jsp").forward(request, response);
        } catch (Exception ex) {
            PrintWriter out=response.getWriter();
            out.print(ex.getMessage());
        }
        
    }

}
