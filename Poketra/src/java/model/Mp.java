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
public class Mp {
    int idMp;
    String nom;
    double pu;
    String unite;

    public Mp(int idMp, String nom, double pu, String unite) {
        this.idMp = idMp;
        this.nom = nom;
        this.pu = pu;
        this.unite = unite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }
    
    
    public int getIdMp() {
        return idMp;
    }

    public void setIdMp(int idMp) {
        this.idMp = idMp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    public static Mp[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Mp> v=new Vector<Mp>();
        ResultSet res = sm.executeQuery("select*from getMp");
        while(res.next()){
            v.add(new Mp(res.getInt("idMp"),res.getString("nom"),res.getDouble("pu"),res.getString("unite")));
        }
        return v.toArray(new Mp[v.size()]);
    
    }
    
    public static Mp getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Mp mp=null;
        ResultSet res = sm.executeQuery("select*from getMp where idMp="+id);
        while(res.next()){
            mp=new Mp(res.getInt("idMp"),res.getString("nom"),res.getDouble("pu"),res.getString("unite"));
        }
        return mp;
    }
    
    public static int insert(Connection c,String nom,int idu,double pu)throws Exception { 
        Statement s=c.createStatement();
        ResultSet res = s.executeQuery("INSERT INTO mp(nom,idUnite,pu) VALUES ('"+nom+"',"+idu+","+pu+")returning idmp");
        while(res.next()){
            return res.getInt("idmp");
        }
        return 0;
    }
    
    public static void historique(Connection c,int idmp,double qte,String date)throws Exception{
        String query = "INSERT INTO historique_mp(idmp,daty,stock) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idmp);
            preparedStatement.setString(2,date);
            preparedStatement.setDouble(3,qte);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
