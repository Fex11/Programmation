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
import model.Annonce;
import model.Question;
import model.RepAdmin;


/**
 *
 * @author MOREL BEN Taboaly
 */
public class Save_Questionnaire extends HttpServlet {

       /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int ida = Integer.parseInt(request.getParameter("ida"));
        String question = request.getParameter("quest");
        String[] reponse=request.getParameterValues("rep");
        String[] typeRep=request.getParameterValues("type");
        //
        try{
        Connection c=Connexion.getConnection();
        Question.insertQuestion(c, ida, question);
        int idQ = Question.getLastQuestion(c);
        for(int i=0;i<reponse.length;i++){
            RepAdmin.Reponse(c, idQ,reponse[i],typeRep[i]);
        }
        c.close();
        request.setAttribute("ida",ida);
        request.getRequestDispatcher("Init_Questionnaire").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        
        
    }

}
