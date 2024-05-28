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
 * @author fex
 */
public class Diplome {
    int idDiplome;
    int idPoste;
    String diplome;
    int  idAnnonce ;
    int idDegree;
    String degree;

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public int getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(int idDegree) {
        this.idDegree = idDegree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    public Diplome(int idDiplome, String diplome) {
        this.idDiplome = idDiplome;
        this.diplome = diplome;
    }

    public int getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(int idDiplome) {
        this.idDiplome = idDiplome;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public Diplome(int idDiplome, int idPoste, String diplome) {
        this.idDiplome = idDiplome;
        this.idPoste = idPoste;
        this.diplome = diplome;
    }
    
    public Diplome(){}
    
    public Diplome(int  idAnnonce ,int idDiplome,String diplome,int idDegree,String degree){
         this.idDiplome = idDiplome;
        this.diplome = diplome;
        this.idAnnonce = idAnnonce;
        this.idDegree = idDegree;
        this.degree = degree;
    }
    
    public static Vector<Diplome> getAllDiplome(Connection c)throws Exception{
         Statement sm=c.createStatement();
         Vector<Diplome> v=new Vector<Diplome>();
            ResultSet res = sm.executeQuery("select*from Diplome");
             while(res.next()){
               v.add(new Diplome(res.getInt("idDiplome"),res.getString("nomDiplome")));
            }
           return v;
    }
    
    public static Diplome getDiplomeByAnnonce(Connection c,int id)throws Exception{
         Statement sm=c.createStatement();
         Diplome v=new Diplome();
            ResultSet res = sm.executeQuery("Select * from getDiplome where idannonce ='"+id+"'");
             while(res.next()){
             v.setIdAnnonce( res.getInt("idannonce"));  
             v.setIdDiplome(res.getInt("idDiplome"));
             v.setDiplome(res.getString("nomDiplome"));  
             v.setIdDegree(res.getInt("iddegre")); 
             v.setDegree(res.getString("degre"));
            }
           return v;
    }
     
    public static Vector<Diplome> getDiplomeByIdP(Connection c,int idP)throws Exception{
         Statement sm=c.createStatement();
         Vector<Diplome> v=new Vector<Diplome>();
          ResultSet res = sm.executeQuery("select * from diplome where idPoste="+idP+"");
             while(res.next()){
               v.add(new Diplome(res.getInt("idDiplome"),res.getInt("idPoste"),res.getString("nomDiplome")));
            }
           return v;
    }
    public static Vector<Diplome> getDiplomeByCv(Connection c,int id)throws Exception{
         Statement sm=c.createStatement();
            ResultSet res = sm.executeQuery("Select * from getDiplomecv where idcv ='"+id+"'");
            Vector<Diplome> rep=new Vector<Diplome>();
             while(res.next()){
             Diplome v=new Diplome();
             v.setIdDiplome(res.getInt("idDiplome"));
             v.setDiplome(res.getString("nomDiplome"));  
             v.setIdDegree(res.getInt("iddegre")); 
             v.setDegree(res.getString("degre"));
             rep.add(v);
            }
           return rep;
    }
      
}
