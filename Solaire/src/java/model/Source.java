/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Source {
    int idSource;
    String nomSource;
    double puissanceMaxPanneau;
    double capaciteBatterie;
    double nbEtudiantAM;
    double nbEtudiantPM;
    String heureCoupure;
    double conso;
    Detail[] details;

    public Detail[] getDetails() {
        return details;
    }

    public void setDetails(Detail[] details) {
        this.details = details;
    }
    
    

    public double getConso() {
        return conso;
    }

    public void setConso(double conso) {
        this.conso =arrondir(conso,5);
        //this.setConso(conso);
    }
    
    

    public int getIdSource() {
        return idSource;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public String getNomSource() {
        return nomSource;
    }

    public void setNomSource(String nomSource) {
        this.nomSource = nomSource;
    }

    public double getPuissanceMaxPanneau() {
        return puissanceMaxPanneau;
    }

    public void setPuissanceMaxPanneau(double puissanceMaxPanneau) {
        this.puissanceMaxPanneau = puissanceMaxPanneau;
    }

    public double getCapaciteBatterie() {
        return capaciteBatterie;
    }

    public void setCapaciteBatterie(double capaciteBatterie) {
        this.capaciteBatterie = arrondir(capaciteBatterie,2);
    }

    public double getNbEtudiantAM() {
        return nbEtudiantAM;
    }

    public void setNbEtudiantAM(double nbEtudiantAM) {
        this.nbEtudiantAM = arrondir(nbEtudiantAM,2);
    }

    public double getNbEtudiantPM() {
        return nbEtudiantPM;
    }

    public void setNbEtudiantPM(double nbEtudiantPM) {
       
        this.nbEtudiantPM =arrondir(nbEtudiantPM,2);
    }

    public String getHeureCoupure() {
        return heureCoupure;
    }

    public void setHeureCoupure(String heureCoupure) {
        this.heureCoupure = heureCoupure;
    }

    public Source(int idSource, String nomSource, double puissanceMaxPanneau, double capaciteBatterie, double nbEtudiantAM, double nbEtudiantPM, String heureCoupure) {
        this.idSource = idSource;
        this.nomSource = nomSource;
        this.puissanceMaxPanneau = puissanceMaxPanneau;
        this.setCapaciteBatterie(capaciteBatterie);
        this.setNbEtudiantAM(nbEtudiantAM);
        this.setNbEtudiantPM(nbEtudiantPM);
        this.heureCoupure = heureCoupure;
    }

    public Source(int idSource, String nomSource) {
        this.idSource = idSource;
        this.nomSource = nomSource;
    }
    
    public static Source[] getSourceByDate(Connection c,String date)throws Exception{
        Statement sm=c.createStatement();
        Vector<Source> v=new Vector<Source>();
        ResultSet res = sm.executeQuery("select * from journee where daty='"+date+"'");
        while(res.next()){
           v.add(new Source(res.getInt("idsource"),res.getString("nomSource"),res.getDouble("puissance_max"),res.getDouble("capacite"),res.getDouble("am"),res.getDouble("pm"),res.getString("heure")));
        }
        return v.toArray(new Source[v.size()]);
    }
    
    public LocalTime getHeureCoupure(double conso,Luminosite [] luminosites)throws Exception{
        Vector<Detail> v=new Vector<Detail>();
        double resteBatterie=this.getCapaciteBatterie(); // reste capacite batterie
        int minute=0;
        int i=0;
        int tapaka=0;
        for(i=0;i<luminosites.length;i++){
            double puissance=(this.getPuissanceMaxPanneau()*luminosites[i].getLuminosite())/10; // puissance panneau
            double consommation=this.getNbEtudiantAM()*conso; // conso total des etudaiants par heure(AM)
            if(i>=4){
                consommation=this.getNbEtudiantPM()*conso;  // conso total des etudaiants par heure(PM)
            }
            double reste=consommation-puissance;  // 
            v.add(new Detail(luminosites[i].getHeure(),resteBatterie,puissance,consommation,luminosites[i].getLuminosite())); // detail pour l'heure
            if(reste>0){ // verification si on a besoin du batterie
                resteBatterie=resteBatterie-reste; // reste capacite batterie
                if(resteBatterie<=(this.getCapaciteBatterie()/2)){ // verification si la capacite restant de la betterie suffit pour 1h
                    tapaka=1;
                    resteBatterie=resteBatterie+reste;
                    double puissanceRestant=puissance+resteBatterie-(this.getCapaciteBatterie()/2); // puissance restant utilisable(panneau+batterie)
                    minute = (int)((puissanceRestant*60)/consommation); // minute ou on a encore le courant apres l'heure
                    break;
                }
            }else if(reste<0){
                resteBatterie=resteBatterie-reste;
                if(resteBatterie>this.capaciteBatterie){
                    resteBatterie=this.capaciteBatterie;
                }
            }
        }
        this.setDetails(v.toArray(new Detail[v.size()]));
        LocalTime h;
        LocalTime heure;
        if(tapaka==0){
            heure=LocalTime.of(18,0);
        }else{  // si l'heure de coupure est avant 17h 
            h=LocalTime.parse(luminosites[i].getHeure());
            heure=h.plusMinutes(minute);
        }
        return heure;
    } 
    
    public void consoMoyenneByDate(Luminosite[] luminosites)throws Exception{ //mameno ny conso moyenne ana source amna date ray
        double conso=1;
        double iteration=0.1;
        LocalTime heureCoupure=this.getHeureCoupure(conso, luminosites); // heure de coupure pour un consommation donnee
        LocalTime dernier=null; // la deniere heure de coupure
        LocalTime realite=LocalTime.parse(this.getHeureCoupure()); // heure de coupure 
        while(heureCoupure.isAfter(realite)){
            conso=conso+iteration; // hiteration de la conso 
            dernier=heureCoupure;
            heureCoupure=this.getHeureCoupure(conso, luminosites);
        }
        long difference1 = Math.abs(realite.until(dernier, java.time.temporal.ChronoUnit.MINUTES));
        long difference2 = Math.abs(realite.until(heureCoupure, java.time.temporal.ChronoUnit.MINUTES));
        if (difference1 <= difference2) { //verification de l'heure qui est plus proche de la realite
            this.setConso(conso-iteration);
            System.out.println(conso-iteration);
        } else {
            this.setConso(conso);
            System.out.println(conso);
        }
    }
    
    public void dichotomie (Luminosite[] luminosites)throws Exception{
        double debut=0;
        double fin=100;
        double pas=fin;
        LocalTime realite=LocalTime.parse(this.getHeureCoupure()); // heure de coupure
        while(fin-debut>0.00001){
            LocalTime heureDebut=this.getHeureCoupure(debut, luminosites); // heure de coupure pour un consommation donnee
            LocalTime heureFin=this.getHeureCoupure(fin, luminosites); 
            System.out.println("debut :"+debut+" , fin : "+fin);
            System.out.println("heureDebut :"+heureDebut+" , heureFin : "+heureFin);
            System.out.println("----------------------------------------");
            if(heureDebut.isAfter(realite) && heureFin.isAfter(realite)){
                debut=fin;
                fin=fin+pas;
            }else if(heureDebut.isAfter(realite) && heureFin.isBefore(realite)){
                pas=(fin-debut)/2;
                fin=debut+pas;
            }else if(heureFin.toString().hashCode()==realite.toString().hashCode()){
                this.setConso(fin);
                System.out.println(fin);
                debut=fin;
            }else if(heureDebut.toString().hashCode()==realite.toString().hashCode()){
                this.setConso(debut);
                System.out.println(debut);
                fin=debut;
            }
            this.setConso(fin);
        }
        
    }
    
    public static Source[] getConsoMoyenneParSource(Journee[] j)throws Exception{ // source rehetra miaraka am conso moyenne 
        Vector<Source> v=new Vector<Source>();
        for(int i=0;i<j[0].getSources().length;i++){
            Source s=new Source(j[0].getSources()[i].getIdSource(),j[0].getSources()[i].getNomSource());
            double totalConso=0;
            for(int k=0;k<j.length;k++){ //somme de tout les conso pour une source
                totalConso=totalConso+j[k].getSources()[i].getConso();
            }
            double conso=totalConso/j.length; // consommation moyen par etudiant par source
            s.setConso(conso);
            v.add(s); // ajouter la source avec consommmation moyen
        }
        return v.toArray(new Source[v.size()]);
    }
    
    public static Source[] getNbEtudiantByJour(Connection c,int jour)throws Exception{
        Statement sm=c.createStatement();
        Vector<Source> v=new Vector<Source>();
        ResultSet res = sm.executeQuery("SELECT idsource,nomSource,puissance_max,capacite,avg(am) as am,avg(pm) as pm FROM journee WHERE EXTRACT(ISODOW FROM daty) = "+jour+" group by idsource,nomSource,puissance_max,capacite");
        while(res.next()){
           v.add(new Source(res.getInt("idsource"),res.getString("nomSource"),res.getDouble("puissance_max"),res.getDouble("capacite"),res.getDouble("am"),res.getDouble("pm"),""));
        }
        return v.toArray(new Source[v.size()]);
    }
    
    public static double arrondir(double nombre, int nombreDeChiffresApresVirgule) {
        double puissance = Math.pow(10, nombreDeChiffresApresVirgule);
        return Math.round(nombre * puissance) / puissance;
    }
}
