/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SABI
 */
public class ClientLogin {
    public int id;
    public String nom;
//    public ClientLogin(int id,String nom, String mdp) {
//        this.id  =id ;
//        this.nom = nom;
//        this.mdp = mdp;
//    }
    public ClientLogin(){}
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String mdp;
    public ClientLogin getClient(Connection c, String nom, String mdp) throws Exception {
    ClientLogin cli = new ClientLogin();
    try {
        Statement sm = c.createStatement();
        ResultSet res = sm.executeQuery("Select * from client where nom ='" + nom + "' and mdp ='" + mdp + "'");
        while (res.next()) {
            cli.setId(res.getInt("id"));
            cli.setNom(res.getString("nom"));
            cli.setMdp(res.getString("mdp"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return cli;
}
public void InsertClient( Connection c , String nom, String mdp)  throws Exception {
            Statement sm = c.createStatement();
            sm.executeUpdate("insert into client(nom,mdp) values ('"+nom+"','"+mdp+"')");

}

}
