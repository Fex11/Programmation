/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class DetailEtat {
    String magasin;
    String code;
    String nom;
    String unite;
    double qteInitial;
    double qteFinal;
    double montantInitial;
    double montantFinal;
    double puInitial;
    double puFinal;

    public DetailEtat(String magasin, String code, String nom, String unite, double qteInitial, double qteFinal, double montantInitial, double montantFinal) {
        this.magasin = magasin;
        this.code = code;
        this.nom = nom;
        this.unite = unite;
        this.qteInitial = qteInitial;
        this.qteFinal = qteFinal;
        this.montantInitial = montantInitial;
        this.montantFinal = montantFinal;
        this.puInitial=montantInitial/qteInitial;
        this.puFinal=montantFinal/qteFinal;
    }

    public double getPuInitial() {
        return puInitial;
    }

    public void setPuInitial(double puInitial) {
        this.puInitial = puInitial;
    }

    public double getPuFinal() {
        return puFinal;
    }

    public void setPuFinal(double puFinal) {
        this.puFinal = puFinal;
    }
    
    

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
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

    public double getQteInitial() {
        return qteInitial;
    }

    public void setQteInitial(double qteInitial) {
        this.qteInitial = qteInitial;
    }

    public double getQteFinal() {
        return qteFinal;
    }

    public void setQteFinal(double qteFinal) {
        this.qteFinal = qteFinal;
    }

    public double getMontantInitial() {
        return montantInitial;
    }

    public void setMontantInitial(double montantInitial) {
        this.montantInitial = montantInitial;
    }

    public double getMontantFinal() {
        return montantFinal;
    }

    public void setMontantFinal(double montantFinal) {
        this.montantFinal = montantFinal;
    }
    
    public static DetailEtat getDetail(Connection c,String debut,String fin,Magasin m,Article a)throws Exception{
        double[] si=Sortie.getSortieByDate(c, m.getIdMagasin(),a.getIdArticle(), debut);
        double[] ei=Entree.getEntreeByDate(c, m.getIdMagasin(),a.getIdArticle(), debut);
        double[] sf=Sortie.getSortieByDate(c, m.getIdMagasin(),a.getIdArticle(),  fin);
        double[] ef=Entree.getEntreeByDate(c, m.getIdMagasin(),a.getIdArticle(), fin);
        String code=a.getCode();
        String nom=a.getNom();
        String unite=a.getUnite();
        double qteInitial=ei[0]-si[0];
        double qteFinal=ef[0]-sf[0];
        double montantInitial=ei[1]-si[1];
        double montantFinal=ef[1]-sf[1];
        return new DetailEtat(m.getNom(), code, nom, unite, qteInitial, qteFinal, montantInitial, montantFinal);
    }
    
}
