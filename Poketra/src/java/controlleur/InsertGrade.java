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
import model.Grade;
import model.Look;
import model.Mp;
import model.StockMp;

/**
 *
 * @author fex
 */
public class InsertGrade extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String desi=request.getParameter("designation");
        int aug=Integer.parseInt(request.getParameter("augmentation"));
        int exp=Integer.parseInt(request.getParameter("experience"));
        try {
            Connection c=Connexion.getConnection();
            c.setAutoCommit(false);
            Grade.inserer(c, desi, exp, aug);
            c.commit();
            c.close();
            request.getRequestDispatcher("InitGrade").forward(request, response);
        } catch (Exception ex) {
            String message=ex.getMessage();
            request.setAttribute("message",message);
            request.getRequestDispatcher("InitGrade").forward(request, response);
        }
        
    }
}
