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
public class Taille {
    int idTaille;
    String taille;
    int niveau;

    public Taille(int idTaille, String taille, int niveau) {
        this.idTaille = idTaille;
        this.taille = taille;
        this.niveau = niveau;
    }
    
    
    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }
    
    public static Taille[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Taille> v=new Vector<Taille>();
        ResultSet res = sm.executeQuery("select*from taille");
        while(res.next()){
            v.add(new Taille(res.getInt("idTaille"),res.getString("taille"),res.getInt("niveau")));
        }
        return v.toArray(new Taille[v.size()]);
    
    }
    
    public static Taille getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Taille taille=null;
        ResultSet res = sm.executeQuery("select*from taille where idTaille="+id);
        while(res.next()){
            taille=new Taille(res.getInt("idTaille"),res.getString("taille"),res.getInt("niveau"));
        }
        return taille;
    }
    
    public void insert(Connection c)throws Exception { 
        String query = "INSERT INTO taille(taille,niveau) VALUES (?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1,this.taille);
            preparedStatement.setInt(2,this.niveau);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int getLast(Connection c)throws Exception {
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from taille order by niveau desc");
        while(res.next()){
           return res.getInt("niveau");
        }
        return 0;
    }
    
    public static void inserer(Connection c,String taille)throws Exception{
        int last=getLast(c);
        Taille t =new Taille(0,taille,last+1);
        t.insert(c);
    }
    
    
}
