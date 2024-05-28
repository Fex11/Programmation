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
public class Nationalite {
    int idNationalite;
    String nationalite;

    public Nationalite(int idNationalite, String nationalite) {
        this.idNationalite = idNationalite;
        this.nationalite = nationalite;
    }

    public int getIdNationalite() {
        return idNationalite;
    }

    public void setIdNationalite(int idNationalite) {
        this.idNationalite = idNationalite;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
    
    public static Vector<Nationalite> getAllNationalite(Connection c)throws Exception{
         Statement sm=c.createStatement();
         Vector<Nationalite> v=new Vector<Nationalite>();
            ResultSet res = sm.executeQuery("select*from Nationalite");
             while(res.next()){
               v.add(new Nationalite(res.getInt("idNationalite"),res.getString("nationalite")));
            }
           return v;
    }
    
}
