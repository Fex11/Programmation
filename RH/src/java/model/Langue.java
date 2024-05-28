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
public class Langue {
    int idLangue;
    String langue;
    int  idAnnonce ;
    String Parle;

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getParle() {
        return Parle;
    }

    public void setParle(String Parle) {
        if(Parle.hashCode()=="t".hashCode()){
            this.Parle="Parler";
        }else{
            this.Parle="";
        }
    }

    public String getEcrit() {
        return Ecrit;
    }

    public void setEcrit(String Ecrit) {
        if(Ecrit.hashCode()=="t".hashCode()){
            this.Ecrit="Ecrit";
        }else{
            this.Ecrit="";
        }
        
    }
    String Ecrit;
    public Langue(int  idAnnonce,int idLangue, String langue,String Parle,String Ecrit){
        this.idAnnonce = idAnnonce;
         this.idLangue = idLangue;
        this.langue = langue;
        this.setParle(Parle);
        this.setEcrit(Ecrit);
    }
    public Langue(int idLangue, String langue) {
        this.idLangue = idLangue;
        this.langue = langue;
    }

    public int getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(int idLangue) {
        this.idLangue = idLangue;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
    
    public static Vector<Langue> getAllLangue(Connection c)throws Exception{
         Statement sm=c.createStatement();
         Vector<Langue> v=new Vector<Langue>();
            ResultSet res = sm.executeQuery("select*from Langue");
             while(res.next()){
               v.add(new Langue(res.getInt("idLangue"),res.getString("langue")));
            }
           return v;
    }
       public static Vector<Langue> getLangueByAnnonce(Connection c,int id)throws Exception{
         Statement sm=c.createStatement();
         Vector<Langue> v=new Vector<Langue>();
            ResultSet res = sm.executeQuery("Select * from getLangue where idannonce ='"+id+"'");
             while(res.next()){
               v.add(new Langue(res.getInt("idannonce"),res.getInt("idLangue"),res.getString("langue"),res.getString("parle"),res.getString("ecrit")));
            } 
          
           return v;
    }
    
    public static Vector<Langue> getLangueByCv(Connection c,int id)throws Exception{
         Statement sm=c.createStatement();
         Vector<Langue> v=new Vector<Langue>();
            ResultSet res = sm.executeQuery("Select * from getLanguecv where idcv ='"+id+"'");
             while(res.next()){
               v.add(new Langue(res.getInt("idcv"),res.getInt("idLangue"),res.getString("langue"),res.getString("parle"),res.getString("ecrit")));
            }
           return v;
        }
}
