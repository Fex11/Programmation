/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Question {
    int idQuestion;
    int idAnnonce;
    String question;
    Vector<Reponse> reponse;

    public Question() {
       
    }

    public Vector<Reponse> getReponse() {
        return reponse;
    }

    public void setReponse(Vector<Reponse> reponse) {
        this.reponse = reponse;
    }
    

    public Question(int idQuestion, int idAnnonce,String question) {
        this.idQuestion = idQuestion;
        this.idAnnonce = idAnnonce;
        this.question = question;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public static void insertQuestion(Connection c,int ida,String question)throws Exception {
        Statement sm=c.createStatement();
        sm.executeUpdate("insert into question (idannonce,question) values("+ida+",'"+question+"')");
    }
    
    public static Vector<Question> getAllQuestionByAnnonce(Connection c,int ida)throws Exception {
        Statement sm=c.createStatement();
        Vector<Question> v=new Vector<Question>();
        ResultSet res = sm.executeQuery("select * from question where idannonce="+ida);
        while(res.next()){
            v.add(new Question(res.getInt("idQ"),res.getInt("idannonce"),res.getString("question")));
        }
        return v;
    }
    public static Question getQuestionById(Connection c,int idq)throws Exception {
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from question where idq="+idq);
        while(res.next()){
            return new Question(res.getInt("idQ"),res.getInt("idannonce"),res.getString("question"));
        }
        return new Question();
    }
    
    public void getReponseByQuestion(Connection c)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from reponse where idq="+this.idQuestion);
        Vector<Reponse> v=new Vector<Reponse>();
         while(res.next()){
            v.add(new Reponse(res.getInt("idr"),res.getString("reponse"),res.getBoolean("typeR")));
         }
         this.setReponse(v);
    }
     public static int getLastQuestion(Connection c)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select max(idq) from question");
        while(res.next()){
            return res.getInt(1);
        }
        return 0;
    }
}
