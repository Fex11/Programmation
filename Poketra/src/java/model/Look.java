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
public class Look {
    int idLook;
    String look;
    Mp[] mps;

    public Look(int idLook, String look) {
        this.idLook = idLook;
        this.look = look;
    }

    public Mp[] getMps() {
        return mps;
    }

    public void setMps(Mp[] mps) {
        this.mps = mps;
    }
    
    

    public int getIdLook() {
        return idLook;
    }

    public void setIdLook(int idLook) {
        this.idLook = idLook;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }
    
    public static Look[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Look> v=new Vector<Look>();
        ResultSet res = sm.executeQuery("select*from look");
        while(res.next()){
            v.add(new Look(res.getInt("idLook"),res.getString("look")));
        }
        return v.toArray(new Look[v.size()]);
    
    }
    
    public static Look getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Look look=null;
        ResultSet res = sm.executeQuery("select*from look where idLook="+id);
        while(res.next()){
            look=new Look(res.getInt("idLook"),res.getString("look"));
        }
        return look;
    }
    
    public void insert(Connection c)throws Exception { 
        String query = "INSERT INTO look(look) VALUES (?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1,this.look);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertMpLook(Connection c,int idLook,int idMp)throws Exception { 
        String query = "INSERT INTO mpLook VALUES (?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idLook);
            preparedStatement.setInt(2,idMp);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Look getInfoLookById(Connection c,int id)throws Exception{
        Look look=getById(c, id);
        Statement sm=c.createStatement();
        Vector<Mp> v=new Vector<Mp>();
        ResultSet res = sm.executeQuery("select*from getMpLook where idLook="+id);
        while(res.next()){
            v.add(new Mp(res.getInt("idMp"),res.getString("nom"),res.getDouble("pu"),res.getString("unite")));
        }
        Mp[] mps=v.toArray(new Mp[v.size()]);
        look.setMps(mps);
        return look;
    }
    
    
    
    
}
