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
public class Poste {
    int idPoste;
    int idService;
    String nomPoste;

    public Poste(int idPoste, int idService, String nomPoste) {
        this.idPoste = idPoste;
        this.idService = idService;
        this.nomPoste = nomPoste;
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }
    
    public static Vector<Poste> getAllPosteByIdService(Connection c,int ids)throws Exception{
         Statement sm=c.createStatement();
         Vector<Poste> v=new Vector<Poste>();
            ResultSet res = sm.executeQuery("select*from getposte where idService="+ids);
             while(res.next()){
               v.add(new Poste(res.getInt("idPoste"),res.getInt("idService"),res.getString("nomPoste")));
            }
           return v;
    }
    
}
