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
public class Service {
    int idService;
    String nomService;
    String mdp;

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    Vector<Poste> postes;
    public Service(){}
    public Service(int idService, String nomService) {
        this.idService = idService;
        this.nomService = nomService;
    }

    public Vector<Poste> getPostes() {
        return postes;
    }

    public void setPostes(Vector<Poste> postes) {
        this.postes = postes;
    }
    
    
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }
    
    public static Vector<Service> getAllService(Connection c)throws Exception{
         Statement sm=c.createStatement();
         Vector<Service> v=new Vector<Service>();
            ResultSet res = sm.executeQuery("select*from services");
             while(res.next()){
               v.add(new Service(res.getInt("idService"),res.getString("nomService")));
            }
           return v;
    }
      public static Service getServiceById(Connection c,int id)throws Exception{
        Statement sm=c.createStatement();
        Service a = new Service();
            ResultSet res = sm.executeQuery("Select * from secureService where idS ='"+id+"'");
             while(res.next()){
                 a.setIdService(res.getInt("idS"));
                 a.setMdp(res.getString("mdp"));
             
            }
           return a;
    } 
}
