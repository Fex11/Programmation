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
public class Unite {
    int idUnite;
    int ida;
    String nomUnite;
    double valeur;

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Unite(int idUnite, int ida, String nomUnite, double valeur) {
        this.idUnite = idUnite;
        this.ida = ida;
        this.nomUnite = nomUnite;
        this.valeur = valeur;
    }
    
    public static Unite[] getAllUnite(Connection c)throws Exception{
        Statement sm=c.createStatement();
        Vector<Unite> v=new Vector<Unite>();
        ResultSet res = sm.executeQuery("select*from unite");
         while(res.next()){
             v.add(new Unite(res.getInt("idunite"),res.getInt("idarticle"),res.getString("nomUnite"),res.getDouble("valeur")));
        }
        return v.toArray(new Unite[v.size()]);
    }
    
    public static Unite[] getUniteByIdArticle(Connection c,int ida)throws Exception{
        Statement sm=c.createStatement();
        Vector<Unite> v=new Vector<Unite>();
        ResultSet res = sm.executeQuery("select*from unite where idarticle="+ida);
         while(res.next()){
             v.add(new Unite(res.getInt("idunite"),res.getInt("idarticle"),res.getString("nomUnite"),res.getDouble("valeur")));
        }
        return v.toArray(new Unite[v.size()]);
    }
    
    public static Unite verifyUnite(Connection c,int ida,int idu)throws Exception{
        Unite[] article=getUniteByIdArticle(c, ida);
        int m=0;
        for(int i=0;i<article.length;i++){
            if(article[i].getIdUnite()==idu){
                m=1;
                return article[i];
            }
        }
        if(m==0){
            throw new Exception("Tsisy anio unite ao amle article");
        }
        return null;
    }
    
}
