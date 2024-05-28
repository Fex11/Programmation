/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fex
 */
public class MpPoketra extends Mp{
    double qte;

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }
    
    public MpPoketra(int idMp, String nom,double pu, String unite) {
        super(idMp, nom,pu,unite);
    }

    public MpPoketra(double qte, int idMp, String nom, double pu, String unite) {
        super(idMp, nom, pu, unite);
        this.qte = qte;
    }
}
