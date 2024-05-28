/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Etat {
    String debut;
    String fin;
    Vector<DetailEtat> stocks;
    double montantTotal;

    public Etat(String debut, String fin, Vector<DetailEtat> stocks) throws Exception{
        this.debut = debut;
        this.fin = fin;
        this.stocks = stocks;
        double montant=0;
        for(int i=0;i<stocks.size();i++){
            montant=montant+stocks.get(i).getMontantFinal();
        }
        this.setMontantTotal(montantTotal);
    }
    
    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Vector<DetailEtat> getStocks() {
        return stocks;
    }

    public void setStocks(Vector<DetailEtat> stocks) {
        this.stocks = stocks;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal)throws Exception{
        if(montantTotal<0){
            throw new Exception("Montant negetif");
        }
        this.montantTotal = montantTotal;
    }
    
    public static Etat getEtat(Connection c,String debut,String fin,String m,String code)throws Exception{
        Article[] articles=Article.getArticleByCode(c, code);
        Magasin[] magasins=Magasin.getMagasin(c, m);
        Vector<DetailEtat> v=new Vector<DetailEtat>(); 
        for(int i=0;i<articles.length;i++){
            for(int j=0;j<magasins.length;j++){
                DetailEtat de=DetailEtat.getDetail(c, debut, fin,magasins[j], articles[i]);
                v.add(de);
            }
        }
        return new Etat(debut,fin,v);
    }
}
