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
public class StockMp extends Mp{
    double qte;
    public StockMp(int idMp, String nom, double pu, String unite) {
        super(idMp, nom, pu, unite);
    }

    public StockMp(double qte, int idMp, String nom, double pu, String unite) {
        super(idMp, nom, pu, unite);
        this.qte = qte;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }
    
    public static StockMp getById(Connection c,int id)throws Exception{
        Statement sm=c.createStatement();
        StockMp mp=null;
        ResultSet res = sm.executeQuery("select*from getStock where idMp="+id);
        while(res.next()){
            mp=new StockMp(res.getDouble("reste"),res.getInt("idMp"),res.getString("nom"),res.getDouble("pu"),res.getString("unite"));
        }
        return mp;
    }
    
    public static StockMp[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<StockMp> v=new Vector<StockMp>();
        ResultSet res = sm.executeQuery("select*from getStock");
        while(res.next()){
            v.add(new StockMp(res.getDouble("reste"),res.getInt("idMp"),res.getString("nom"),res.getDouble("pu"),res.getString("unite")));
        }
        return v.toArray(new StockMp[v.size()]);
    
    }
    public static void init(Connection c,int id)throws Exception { 
        String query = "INSERT INTO stock VALUES (?,0)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
          preparedStatement.setInt(1,id);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static double addStock(Connection c,int idmp,double qte)throws Exception{
        StockMp s=StockMp.getById(c, idmp);
        double nvqte=s.getQte()+qte;
        String query = "update stock set reste=? where idmp=?";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setDouble(1,nvqte);
            preparedStatement.setInt(2,idmp);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nvqte;
    }
    public static double manalaStock(Connection c,int idmp,double qte)throws Exception{
        StockMp s=StockMp.getById(c, idmp);
        double nvqte=s.getQte()-qte;
        String query = "update stock set reste=? where idmp=?";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setDouble(1,nvqte);
            preparedStatement.setInt(2,idmp);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nvqte;
    }
    
    public static void sortieMp(Connection c,String date,int idmp,double qte,int source)throws Exception { 
        String query = "INSERT INTO sortie_mp(daty,idmp,qte,source) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1,date);
            preparedStatement.setInt(2,idmp);
            preparedStatement.setDouble(3,qte);
            preparedStatement.setInt(4,source);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
