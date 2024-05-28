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
public class Unite {
    int idUnite;
    String unite;

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Unite(int idUnite, String unite) {
        this.idUnite = idUnite;
        this.unite = unite;
    }
    
    public static Unite[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Unite> v=new Vector<Unite>();
        ResultSet res = sm.executeQuery("select*from unite");
        while(res.next()){
            v.add(new Unite(res.getInt("idUnite"),res.getString("unite")));
        }
        return v.toArray(new Unite[v.size()]);
    }
    
}
