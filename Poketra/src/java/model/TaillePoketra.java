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
public class TaillePoketra extends Taille{
    int stockk;
    double prix;
    double prixVente;
    double mainOeuvre;
    double benefice;
    MpPoketra[] mpPoketra;

    public int getStockk() {
        return stockk;
    }

    public void setStockk(int stockk) {
        this.stockk = stockk;
    }
    
    

    public TaillePoketra(double prix, int idTaille, String taille,int niveau) {
        super(idTaille, taille,niveau);
        this.prix = prix;
    }

    public double getMainOeuvre() {
        return mainOeuvre;
    }

    public void setMainOeuvre(double mainOeuvre) {
        this.mainOeuvre = mainOeuvre;
    }
    

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }
    
    
    
    

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
    public MpPoketra[] getMpPoketra() {
        return mpPoketra;
    }

    public void setMpPoketra(MpPoketra[] mpPoketra) {
        this.mpPoketra = mpPoketra;
    }
    
    public static double getPrix(Connection c,int idp,int idt)throws Exception {
        Statement sm=c.createStatement();
        double prix=0;
        ResultSet res = sm.executeQuery("select*from prixDeVente where idPoketra="+idp+" and idTaille="+idt);
        while(res.next()){
            prix=res.getDouble("prix");
        }
        return prix;
    }
}
