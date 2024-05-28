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
import model.*;

/**
 *
 * @author fex
 */
public class InitMp extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Connection c=Connexion.getConnection();
            Mp[] mps=Mp.getAll(c);
            Unite[] unites=Unite.getAll(c);
            c.close();
            request.setAttribute("mps",mps);
            request.setAttribute("unites",unites);
            request.getRequestDispatcher("insertMp.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InitLook.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
