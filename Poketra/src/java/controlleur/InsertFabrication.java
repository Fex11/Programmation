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
public class InsertFabrication extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idPoketra=Integer.parseInt(request.getParameter("idPoketra"));
        int idTaille=Integer.parseInt(request.getParameter("taille"));
        int nombre=Integer.parseInt(request.getParameter("nombre"));
        String date=request.getParameter("date");
        try {
            Connection c=Connexion.getConnection();
            c.setAutoCommit(false);
            Poketra.fabricationPoketra(c, idPoketra, idTaille, nombre,date);
            c.commit();
            c.close();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("message",ex.getMessage());
            request.getRequestDispatcher("InitFabrication").forward(request, response);
        }   
    }
}
