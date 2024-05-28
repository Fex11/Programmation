/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Journee {
    String date;
    Source[] sources;
    Luminosite[] luminosites;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Source[] getSources() {
        return sources;
    }

    public void setSources(Source[] sources) {
        this.sources = sources;
    }

    public Luminosite[] getLuminosites() {
        return luminosites;
    }

    public void setLuminosites(Luminosite[] luminosites) {
        this.luminosites = luminosites;
    }

    public Journee(String date, Source[] sources, Luminosite[] luminosites) {
        this.date = date;
        this.sources = sources;
        this.luminosites = luminosites;
    }
    
    public static Journee getJournee(Connection c,String date)throws Exception{
        Source[] s=Source.getSourceByDate(c, date);
        Luminosite[] l=Luminosite.getLuminositeByDate(c, date);
        return new Journee(date,s,l);
    }
    
    public void getConsoAllSourceByJournee()throws Exception{ // mameno ny consommation par source ana date ray
        for(int i=0;i<this.getSources().length;i++){
            //this.getSources()[i].consoMoyenneByDate(this.getLuminosites());
            this.getSources()[i].dichotomie(this.getLuminosites());
        }
    }
    
    public static String[] getAllDate(Connection c)throws Exception{ 
        Statement sm=c.createStatement();
        Vector<String> v=new Vector<String>();
        ResultSet res = sm.executeQuery("select distinct daty from presence");
        while(res.next()){
           v.add(res.getString("daty"));
        }
        return v.toArray(new String[v.size()]);
    }
    
    public static Journee[] getAllJournee(Connection c)throws Exception{ // maka ny journee rehetra ao anaty base
        String[] dates=getAllDate(c);
        Vector<Journee> v=new Vector<Journee>();
        for(int i=0;i<dates.length;i++){
            Journee j=getJournee(c,dates[i]);
            j.getConsoAllSourceByJournee();
            v.add(j);
        }
        return v.toArray(new Journee[v.size()]);
    }
    
    public static Journee coupure(Connection c,String date)throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate daty = LocalDate.parse(date, formatter); // date anaovana prediction 
        int jour=daty.getDayOfWeek().getValue(); // mamadika anle date ou lasa nb ana semaine
        Luminosite[] luminosites=Luminosite.getLuminositeByDate(c, date);
        //Luminosite[] luminosites=Luminosite.datehafa(c, date);
        Journee[] j=Journee.getAllJournee(c); // ny journee rehetra
        Source[] sources=Source.getNbEtudiantByJour(c, jour); // source rehetra misy ny nb_moyenne_eleve_am,nb_moyenne_eleve_pm,puissance_max,capacite
        Source[] s=Source.getConsoMoyenneParSource(j); // source rehetra efa misy ny conso rehetra par source
        for(int i=0;i<sources.length;i++){ // mampiditra anle conso moyenne par source anatinle sources
            for(int k=0;k<s.length;k++){
                if(sources[i].getIdSource()==s[k].getIdSource()){
                    sources[i].setConso(s[k].getConso());
                    break;
                }
            }
            LocalTime heureCoupure=sources[i].getHeureCoupure(sources[i].getConso(), luminosites); // estimation heure de coupure nle source amle date
            sources[i].setHeureCoupure(heureCoupure.toString());
        }
        return new Journee(date,sources,luminosites);
    }
}
