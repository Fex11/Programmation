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
public class Luminosite {
    int idLuminosite;
    //String date;
    String heure;
    double luminosite;

    public Luminosite(int idLuminosite, /*String date,*/ String heure, double luminosite) {
        this.idLuminosite = idLuminosite;
        //this.date = date;
        this.heure = heure;
        this.luminosite = luminosite;
    }

    /*public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/
    
    
    public int getIdLuminosite() {
        return idLuminosite;
    }

    public void setIdLuminosite(int idLuminosite) {
        this.idLuminosite = idLuminosite;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public double getLuminosite() {
        return luminosite;
    }

    public void setLuminosite(double luminosite) {
        this.luminosite = luminosite;
    }
    
    
   public static Luminosite[] getLuminositeByDate(Connection c,String date)throws Exception{
       Statement sm=c.createStatement();
       Vector<Luminosite> v=new Vector<Luminosite>();
       ResultSet res = sm.executeQuery("select * from luminosite where daty='"+date+"' order by heure");
       //ResultSet res = sm.executeQuery("select * from luminosite where daty='2000-01-01' order by heure");
       //ResultSet res = sm.executeQuery("select * from luminosite where idLuminosite!=2 order by heure");
       while(res.next()){
          v.add(new Luminosite(res.getInt("idluminosite"),res.getString("heure"),res.getDouble("luminosite")));
       }
       return v.toArray(new Luminosite[v.size()]);
   }
   
   public static Luminosite[] datehafa(Connection c,String date)throws Exception{
       Statement sm=c.createStatement();
       Vector<Luminosite> v=new Vector<Luminosite>();
       ResultSet res = sm.executeQuery("select * from luminosite where daty='"+date+"' order by heure");
       //ResultSet res = sm.executeQuery("select * from luminosite order by heure");
       while(res.next()){
          v.add(new Luminosite(res.getInt("idluminosite"),res.getString("heure"),res.getDouble("luminosite")));
       }
       return v.toArray(new Luminosite[v.size()]);
   }
}
