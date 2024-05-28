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
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Article {
    int idArticle;
    String code;
    String nom;
    String unite;
    String sortie;

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSortie() {
        return sortie;
    }

    public void setSortie(String sortie) {
        this.sortie = sortie;
    }

    public Article(int idArticle, String code, String nom, String unite, String sortie) {
        this.idArticle = idArticle;
        this.code = code;
        this.nom = nom;
        this.unite = unite;
        this.sortie = sortie;
    }

    
    public static Article[] getAllArticle(Connection c) throws Exception{
        Statement sm=c.createStatement();
         Vector<Article> v=new Vector<Article>();
            ResultSet res = sm.executeQuery("select*from article");
             while(res.next()){
               v.add(new Article(res.getInt("idarticle"),res.getString("code"),res.getString("nom"),res.getString("unite"),res.getString("sortie")));
            }
           return v.toArray(new Article[v.size()]);
    }
    
    public static Article[] getArticleByCode(Connection c,String code) throws Exception{
        Statement sm=c.createStatement();
        Vector<Article> v=new Vector<Article>();
            ResultSet res = sm.executeQuery("select*from article where code like '"+code+"'");
             while(res.next()){
               v.add(new Article(res.getInt("idarticle"),res.getString("code"),res.getString("nom"),res.getString("unite"),res.getString("sortie")));
            }
        return v.toArray(new Article[v.size()]);
    }
    
    public static Article getArticleById(Connection c,int ida) throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from article where idarticle="+ida);
         while(res.next()){
           return new Article(res.getInt("idarticle"),res.getString("code"),res.getString("nom"),res.getString("unite"),res.getString("sortie"));
        }
       return null;
    }
}
