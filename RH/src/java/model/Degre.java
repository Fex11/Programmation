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
public class Degre {
    int idDegre;
    String degre;

    public Degre(int idDegre, String degre) {
        this.idDegre = idDegre;
        this.degre = degre;
    }

    public int getIdDegre() {
        return idDegre;
    }

    public void setIdDegre(int idDegre) {
        this.idDegre = idDegre;
    }

    public String getDegre() {
        return degre;
    }

    public void setDegre(String degre) {
        this.degre = degre;
    }
    
    public static Vector<Degre> getAllDegre(Connection c)throws Exception{
         Statement sm=c.createStatement();
         Vector<Degre> v=new Vector<Degre>();
            ResultSet res = sm.executeQuery("select*from Degre");
             while(res.next()){
               v.add(new Degre(res.getInt("idDegre"),res.getString("degre")));
            }
           return v;
    }
}
