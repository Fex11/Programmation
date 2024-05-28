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
public class Categorie {
    int idCategorie;
    String categorie;
    
    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idType) {
        this.idCategorie = idType;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Categorie(int idCategorie, String categorie) {
        this.idCategorie = idCategorie;
        this.categorie = categorie;
    }
    
    public static Categorie[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Categorie> v=new Vector<Categorie>();
        ResultSet res = sm.executeQuery("select*from categorie");
        while(res.next()){
            v.add(new Categorie(res.getInt("idCategorie"),res.getString("categorie")));
        }
        return v.toArray(new Categorie[v.size()]);
    
    }
    
    public static Categorie getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Categorie categorie=null;
        ResultSet res = sm.executeQuery("select*from categorie where idCategorie="+id);
        while(res.next()){
            categorie=new Categorie(res.getInt("idCategorie"),res.getString("categorie"));
        }
        return categorie;
    }
    
    public void insert(Connection c)throws Exception { 
        String query = "INSERT INTO categorie(categorie) VALUES (?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1,this.categorie);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
}
