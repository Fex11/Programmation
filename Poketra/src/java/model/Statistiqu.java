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
public class Statistiqu {
    Poketra poketra;
    int nbHomme;
    int nbFemme;
    int total;
    double pourcHomme;
    double pourcFemme;

    public double getPourcHomme() {
        return pourcHomme;
    }

    public void setPourcHomme(double pourcHomme) {
        this.pourcHomme = pourcHomme;
    }

    public double getPourcFemme() {
        return pourcFemme;
    }

    public void setPourcFemme(double pourcFemme) {
        this.pourcFemme = pourcFemme;
    }
    

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Statistiqu(Poketra poketra, int nbHomme, int nbFemme) {
        this.poketra = poketra;
        this.nbHomme = nbHomme;
        this.nbFemme = nbFemme;
    }
    public Statistiqu() {
      
    }

    public Poketra getPoketra() {
        return poketra;
    }

    public void setPoketra(Poketra poketra) {
        this.poketra = poketra;
    }

    public int getNbHomme() {
        return nbHomme;
    }

    public void setNbHomme(int nbHomme) {
        this.nbHomme = nbHomme;
    }

    public int getNbFemme() {
        return nbFemme;
    }

    public void setNbFemme(int nbFemme) {
        this.nbFemme = nbFemme;
    }
    
    public void pourc(){
        if(this.getTotal()!=0){
            this.setPourcHomme((this.getNbHomme()*100)/this.getTotal());
            this.setPourcFemme(100-this.getPourcHomme());
        }
    }
    
    public static Statistiqu statistique(Connection c,int idp)throws Exception{
        Statement sm=c.createStatement();
        Statistiqu statistique=new Statistiqu();
        Poketra p=Poketra.getById(c, idp);
        statistique.setPoketra(p);
        ResultSet res = sm.executeQuery("select*from getnb where idpoketra="+idp);
        while(res.next()){
            if(res.getInt("genre")==0){
                statistique.setNbHomme(res.getInt("sum"));
            }else{
                 statistique.setNbFemme(res.getInt("sum"));
            }
        }
        statistique.setTotal(statistique.getNbFemme()+statistique.getNbHomme());
        statistique.pourc();
        return statistique;
    }
}
