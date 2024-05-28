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
public class Magasin {
    int idMagasin;
    String nom;
    String localisation;

    public Magasin(int idMagasin, String nom, String localisation) {
        this.idMagasin = idMagasin;
        this.nom = nom;
        this.localisation = localisation;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    
    public static Magasin[] getAllMagasin(Connection c) throws Exception{
        Statement sm=c.createStatement();
        Vector<Magasin> v=new Vector<Magasin>();
            ResultSet res = sm.executeQuery("select*from magasin");
             while(res.next()){
               v.add(new Magasin(res.getInt("idmagasin"),res.getString("nom"),res.getString("localisation")));
            }
        return v.toArray(new Magasin[v.size()]);
    }
    
    public static Magasin[] getMagasin(Connection c,String magasin) throws Exception{
        Statement sm=c.createStatement();
        Vector<Magasin> v=new Vector<Magasin>();
            ResultSet res = sm.executeQuery("select*from magasin where nom like '"+magasin+"'");
             while(res.next()){
               v.add(new Magasin(res.getInt("idmagasin"),res.getString("nom"),res.getString("localisation")));
            }
        return v.toArray(new Magasin[v.size()]);
    }
    
    public static Magasin getMagasinById(Connection c,int id) throws Exception{
        Statement sm=c.createStatement();
            ResultSet res = sm.executeQuery("select*from magasin where idmagasin="+id);
             while(res.next()){
               return new Magasin(res.getInt("idmagasin"),res.getString("nom"),res.getString("localisation"));
            }
           return null;
    }
    
}
