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


public class AchatMp {
    int idAchat;
    String date;
    Mp mp;
    double qte;
    double pu;

    public int getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(int idAchat) {
        this.idAchat = idAchat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Mp getMp() {
        return mp;
    }

    public void setMp(Mp mp) {
        this.mp = mp;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public AchatMp(int idAchat, String date, Mp mp, double qte, double pu) {
        this.idAchat = idAchat;
        this.date = date;
        this.mp = mp;
        this.qte = qte;
        this.pu = pu;
    }
    
    public static AchatMp getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        AchatMp achat=null;
        ResultSet res = sm.executeQuery("select*from achat_mp where idMp="+id);
        while(res.next()){
            Mp mp=Mp.getById(c,res.getInt("idMp"));
            achat=new AchatMp(res.getInt("idAchat"),res.getString("daty"),mp,res.getDouble("qte"),res.getDouble("pu"));
        }
        return achat;
    }
    
    public static void insert(Connection c,String date,int idmp,double qte,double pu)throws Exception { 
        String query = "INSERT INTO achat_mp(daty,idmp,qte,pu) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1,date);
            preparedStatement.setInt(2,idmp);
            preparedStatement.setDouble(3,qte);
            preparedStatement.setDouble(4,pu);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /*public static AchatMp[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<AchatMp> v=new Vector<AchatMp>();
        ResultSet res = sm.executeQuery("select*from getMp");
        while(res.next()){
            v.add(new Mp(res.getInt("idMp"),res.getString("nom"),res.getDouble("pu"),res.getString("unite")));
        }
        return v.toArray(new Mp[v.size()]);
    
    }*/
}
