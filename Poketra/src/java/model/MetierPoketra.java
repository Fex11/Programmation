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
public class MetierPoketra extends Metier {
    double temp;
    int nb;
    public MetierPoketra(int idMetier, String nomMetier, double th) {
        super(idMetier, nomMetier, th);
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public MetierPoketra(double temp, int nb, int idMetier, String nomMetier, double th) {
        super(idMetier, nomMetier, th);
        this.temp = temp;
        this.nb = nb;
    }
    
    
    
    public static void insert(Connection c,int idPoketra,int idMetier,double temp,int nb)throws Exception { 
        String query = "INSERT INTO metierPoketra(idPoketra,idMetier,temp,nb) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idPoketra);
            preparedStatement.setInt(2,idMetier);
            preparedStatement.setDouble(3,temp);
            preparedStatement.setInt(4,nb);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static MetierPoketra[] getMetierByIdPoketra(Connection c,int idPoketra) throws Exception{
        Statement sm=c.createStatement();
        Vector<MetierPoketra> v=new Vector<MetierPoketra>();
        ResultSet res = sm.executeQuery("select*from getmetierpoketra where idPoketra="+idPoketra);
        while(res.next()){
            MetierPoketra m=new MetierPoketra(res.getDouble("temp"),res.getInt("nb"),res.getInt("idMetier"),res.getString("nomMetier"),res.getDouble("th"));
            v.add(m);
        }
        return v.toArray(new MetierPoketra[v.size()]);
    }
    
}
