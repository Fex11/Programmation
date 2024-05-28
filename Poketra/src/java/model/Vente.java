/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author fex
 */
public class Vente {
    int idVentePoketra;
    int idPoketra;
    int idClient;
    int nb;

    public Vente(int idVentePoketra, int idPoketra, int idClient, int nb) {
        this.idVentePoketra = idVentePoketra;
        this.idPoketra = idPoketra;
        this.idClient = idClient;
        this.nb = nb;
    }

    public int getIdVentePoketra() {
        return idVentePoketra;
    }

    public void setIdVentePoketra(int idVentePoketra) {
        this.idVentePoketra = idVentePoketra;
    }

    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    
    public static void insert(Connection c,int idp,int idc,int nb)throws Exception { 
        String query = "INSERT INTO ventePoketra(idPoketra,idClient,nb) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idp);
            preparedStatement.setInt(2,idc);
            preparedStatement.setInt(3,nb);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
