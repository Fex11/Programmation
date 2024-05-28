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
public class Client {
    int idClient;
    String nom;
    int genre;

    public Client(int idClient, String nom, int genre) {
        this.idClient = idClient;
        this.nom = nom;
        this.genre = genre;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
    
    public static Client[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Client> v=new Vector<Client>();
        ResultSet res = sm.executeQuery("select*from client");
        while(res.next()){
            v.add(new Client(res.getInt("idClient"),res.getString("nom"),res.getInt("genre")));
        }
        return v.toArray(new Client[v.size()]);
    
    }
    
    public static Client getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Client client=null;
        ResultSet res = sm.executeQuery("select*from look where idLook="+id);
        while(res.next()){
            client=new Client(res.getInt("idClient"),res.getString("nom"),res.getInt("genre"));
        }
        return client;
    }
    
    public static void insert(Connection c,String nom,int genre)throws Exception { 
        String query = "INSERT INTO client(nom,genre) VALUES (?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1,nom);
            preparedStatement.setInt(2,genre);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getSexe(){
        if(this.getGenre()==1){
            return "Femme";
        }else{
            return "Homme";
        }
    }
}
