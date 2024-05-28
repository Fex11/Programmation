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
public class Detail {
    String heure;
    double capaciteBatterieRestant;
    double puissancePanneau;
    double consoParheure;
    double luminosite;

    public double getConsoParheure() {
        return consoParheure;
    }

    public void setConsoParheure(double consoParheure) {
        this.consoParheure =Source.arrondir(consoParheure, 2);
    }

    public double getLuminosite() {
        return luminosite;
    }

    public void setLuminosite(double luminosite) {
        this.luminosite = luminosite;
    }
    
    
    

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public double getCapaciteBatterieRestant() {
        return capaciteBatterieRestant;
    }

    public void setCapaciteBatterieRestant(double capaciteBatterieRestant) {
        this.capaciteBatterieRestant = Source.arrondir(capaciteBatterieRestant, 2);
    }

    public double getPuissancePanneau() {
        return puissancePanneau;
    }

    public void setPuissancePanneau(double puissancePanneau) {
        this.puissancePanneau = puissancePanneau;
    }

    public Detail(String heure, double capaciteBatterieRestant, double puissancePanneau,double puissance,double lumi) {
        this.heure = heure;
        this.setCapaciteBatterieRestant(capaciteBatterieRestant);
        this.puissancePanneau = puissancePanneau;
        this.luminosite=lumi;
        this.setConsoParheure(puissance);
    }
    
    
}
