/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Metier {
    int idMetier;
    String nomMetier;
    double th;

    public Metier(int idMetier, String nomMetier, double th) {
        this.idMetier = idMetier;
        this.nomMetier = nomMetier;
        this.th = th;
    }

    public int getIdMetier() {
        return idMetier;
    }

    public void setIdMetier(int idMetier) {
        this.idMetier = idMetier;
    }

    public String getNomMetier() {
        return nomMetier;
    }

    public void setNomMetier(String nomMetier) {
        this.nomMetier = nomMetier;
    }

    public double getTh() {
        return th;
    }

    public void setTh(double th) {
        this.th = th;
    }
    
    public static Metier[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Metier> v=new Vector<Metier>();
        ResultSet res = sm.executeQuery("select*from metier");
        while(res.next()){
            v.add(new Metier(res.getInt("idMetier"),res.getString("nomMetier"),res.getDouble("th")));
        }
        return v.toArray(new Metier[v.size()]);
    
    }
    
    public static Metier getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Metier metier=null;
        ResultSet res = sm.executeQuery("select*from metier where idMetier="+id);
        while(res.next()){
            metier=new Metier(res.getInt("idMetier"),res.getString("nomMetier"),res.getDouble("th"));
        }
        return metier;
    }
    
    public void insert(Connection c)throws Exception { 
        String query = "INSERT INTO metier(nomMetier,th) VALUES (?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1,this.nomMetier);
            preparedStatement.setDouble(2,this.getTh());
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
