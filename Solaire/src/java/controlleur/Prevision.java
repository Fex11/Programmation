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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Journee;

/**
 *
 * @author fex
 */
public class Prevision extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String date = request.getParameter("daty");
            //
            try{
                Connection c=Connexion.getConnection();
                Journee journee=Journee.coupure(c, date);
                c.close();
                request.setAttribute("journee",journee);
                request.getRequestDispatcher("prevision.jsp").forward(request, response);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
}
