package controlleur;

import connexion.Connexion;
import controlleur.InsertLook;
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
import model.Taille;

/**
 *
 * @author fex
 */
public class InitTaille extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id=Integer.parseInt(request.getParameter("idPoketra"));
        try {
            Connection c=Connexion.getConnection();
            Poketra p=Poketra.getById(c, id);
            p.getTaille(c);
            p.getAllMpTaillePoketra(c);
            Mp[] mp=Poketra.getMpPoketra(c, id);
            p.setMp(mp);
            Taille[] taille=Taille.getAll(c);
            c.close();
            request.setAttribute("taille",taille);
            request.setAttribute("poketra",p);
            request.getRequestDispatcher("insertTaille.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}


