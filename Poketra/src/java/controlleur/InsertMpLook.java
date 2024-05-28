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

/**
 *
 * @author fex
 */
public class InsertMpLook extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idLook=Integer.parseInt(request.getParameter("idLook"));
        int idMp=Integer.parseInt(request.getParameter("idMp"));
        try {
            Connection c=Connexion.getConnection();
            Look.insertMpLook(c, idLook, idMp);
            c.close();
            request.setAttribute("idLook",idLook);
            request.getRequestDispatcher("InitMpLook").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertLook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
