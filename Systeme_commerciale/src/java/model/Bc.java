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
public class Bc {
    int idBc;
    String date;
    double ttc;
    int etat;

    public int getIdBc() {
        return idBc;
    }

    public void setIdBc(int idBc) {
        this.idBc = idBc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTtc() {
        return ttc;
    }

    public void setTtc(double ttc) {
        this.ttc = ttc;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Bc(int idBc, String date, double ttc, int etat) {
        this.idBc = idBc;
        this.date = date;
        this.ttc = ttc;
        this.etat = etat;
    }
    
    public static Vector<Bc> getAllByEtat(Connection c,int etat)throws Exception{
        Statement sm=c.createStatement();
        Vector<Bc> v=new Vector<Bc>();
        ResultSet res = sm.executeQuery("select * from bc where etat="+etat);
        while(res.next()){
           v.add(new Bc(res.getInt("idbc"),res.getString("daty"),res.getDouble("ttc"),res.getInt("etat")));
        }
        return v;
    }
    
    public static Bc getBcById(Connection c,int id)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from bc where idbc="+id);
        while(res.next()){
           return new Bc(res.getInt("idbc"),res.getString("daty"),res.getDouble("ttc"),res.getInt("etat"));
        }
        return null;
    }
    
    public static void updateEtat(Connection c,int idbc,int etat)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update bc set etat="+etat+" where idbc="+idbc);
    }
}
