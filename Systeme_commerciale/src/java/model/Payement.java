/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author fex
 */
public class Payement {
    int idPayement;
    String date;
    int idBc;
    String mode;

    public int getIdPayement() {
        return idPayement;
    }

    public void setIdPayement(int idPayement) {
        this.idPayement = idPayement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdBc() {
        return idBc;
    }

    public void setIdBc(int idBc) {
        this.idBc = idBc;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Payement(int idPayement, String date, int idBc, String mode) {
        this.idPayement = idPayement;
        this.date = date;
        this.idBc = idBc;
        this.mode = mode;
    }
    
    public static void insert(Connection c,String date,int idbc,String mode)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into payement (daty,idbc,mode) values('"+date+"',"+idbc+",'"+mode+"')");
    }
    
    public static double getSolde(Connection c)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from solde");
        while(res.next()){
           return res.getDouble("montant");
        }
        return 0;
    }
    
    public static void updateSolde(Connection c,double nv)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update solde set montant="+nv);
    }
    
    public static void payer(Connection c,String date,int idbc,String mode)throws Exception{
        insert(c, date, idbc, mode);
        Bc.updateEtat(c, idbc,3);
        double solde=getSolde(c);
        Bc bc=Bc.getBcById(c, idbc);
        double reste=solde-bc.getTtc();
        updateSolde(c, reste);
    }
}
