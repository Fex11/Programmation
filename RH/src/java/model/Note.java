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
import java.time.Period;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Note {
    int idcv;
    double sexe;
    double age;
    double situation;
    double diplome;
    double langue;
    double nationalite;
    double experience;

    public int getIdcv() {
        return idcv;
    }

    public void setIdcv(int idcv) {
        this.idcv = idcv;
    }

    public double getSexe() {
        return sexe;
    }

    public void setSexe(double sexe) {
        this.sexe = sexe;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getSituation() {
        return situation;
    }

    public void setSituation(double situation) {
        this.situation = situation;
    }

    public double getDiplome() {
        return diplome;
    }

    public void setDiplome(double diplome) {
        this.diplome = diplome;
    }

    public double getLangue() {
        return langue;
    }

    public void setLangue(double langue) {
        this.langue = langue;
    }

    public double getNationalite() {
        return nationalite;
    }

    public void setNationalite(double nationalite) {
        this.nationalite = nationalite;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }
    
    public Note(double sexe, double age, double situation, double diplome, double langue, double nationalite, double experience) {
        this.sexe = sexe;
        this.age = age;
        this.situation = situation;
        this.diplome = diplome;
        this.langue = langue;
        this.nationalite = nationalite;
        this.experience = experience;
    }
    public Note() {
        
    }
    
    public void note(Connection c,int idcv)throws Exception{
        this.setIdcv(idcv);
        Cv cv=Cv.getCvbyId(c, idcv);
        Annonce annonce=Annonce.getAnnonceById(c, cv.getAnnonce());
        //Age
        LocalDate dtn=cv.getAgeCdt().toLocalDate();
        LocalDate dateActuelle = LocalDate.now();
        int age=Period.between(dtn, dateActuelle).getYears();
        if(age<=annonce.getAgemax() && age>=annonce.getAgemin()){
            this.setAge(1);
        }
        //Experience
        if(cv.getExpCdt()>=annonce.getExperience()){
            if(cv.getExpCdt()==annonce.getExperience()){
                this.setExperience(1);
            }else{
                int not=cv.getExpCdt()-annonce.getExperience();
                this.setExperience(not+1);
            }
        }
        //sexe
        if(annonce.getSexe().hashCode()=="Tous".hashCode()){
            this.setSexe(1);
        }else{
            if(cv.getSexe().hashCode()==annonce.getSexe().hashCode()){
                this.setSexe(1);
            }
        }
        //Situation
        if(annonce.getSituation().hashCode()=="Tous".hashCode()){
            this.setSituation(1);
        }else{
            if(cv.getSituationCdt().hashCode()==annonce.getSituation().hashCode()){
                this.setSituation(1);
            }
        }
        //Nationalite
        if(annonce.getNationalite().getNationalite().hashCode()=="Tous".hashCode()){
            this.setNationalite(1);
        }else{
            if(cv.getNationalite().getIdNationalite()==annonce.getNationalite().getIdNationalite()){
                this.setNationalite(1);
            }
        }
        //Diplome
        Diplome diplome=Diplome.getDiplomeByAnnonce(c,cv.getAnnonce());
        Vector<Diplome> dip=Diplome.getDiplomeByCv(c, idcv);
        if(dip.size()==1){
            if(diplome.getIdDiplome()==dip.get(0).getIdDiplome()){
                if(diplome.getIdDegree()==dip.get(0).getIdDegree()){
                    this.setDiplome(1);
                }else if(diplome.getIdDegree()<dip.get(0).getIdDegree()){
                    int diff=dip.get(0).getIdDegree()-diplome.getIdDegree();
                    this.setDiplome(1+diff);
                }
            }
        }else{
            int marque=0;
            for(int i=0;i<dip.size();i++){
                if(diplome.getIdDiplome()==dip.get(i).getIdDiplome()){
                    if(diplome.getIdDegree()==dip.get(i).getIdDegree()){
                        this.setDiplome(this.getDiplome()+1);
                        marque=1;
                    }else if(diplome.getIdDegree()<dip.get(i).getIdDegree()){
                        int diff=dip.get(i).getIdDegree()-diplome.getIdDegree();
                        this.setDiplome(this.getDiplome()+diff);
                        marque=1;
                    }
                }else{
                    this.setDiplome(this.getDiplome()+1);
                }
            }
            if(marque==0){ this.setDiplome(0); };
        }
        //Langue
        Vector<Langue> langue=Langue.getLangueByAnnonce(c,cv.getAnnonce());
        Vector<Langue> languecv=Langue.getLangueByCv(c, idcv);
        int nb=0;
        for(int i=0;i<langue.size();i++){
            for(int j=0;j<languecv.size();j++){
                if(langue.get(i).getIdLangue()==languecv.get(j).getIdLangue()){
                    int nl=noteLangue(langue.get(i),languecv.get(j));
                    if(nl==1){
                        nb++;
                    }
                    break;
                }
            }
        }
        if(nb==langue.size()){
            this.setLangue(1);
        }
    }
    
    public int noteLangue(Langue la,Langue lcv){
        if(la.getEcrit().hashCode()== lcv.getEcrit().hashCode() && la.getParle().hashCode()== lcv.getParle().hashCode()){
            return 1;
        }else if(la.getEcrit().hashCode()== "".hashCode()){
            if(la.getParle().hashCode()== lcv.getParle().hashCode()){
                return 1;
            }else{
                return 0;
            }
        }else if(la.getParle().hashCode()== "".hashCode()){
            if(la.getEcrit().hashCode()== lcv.getEcrit().hashCode()){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;
    }
    
    public static Note getCoeff(Connection c,int idp)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from coefficient where idposte="+idp);
        while(res.next()){
            return new Note(res.getInt("sexe"),res.getInt("age"),res.getInt("situation"),res.getInt("diplome"),res.getInt("langue"),res.getInt("nationalite"),res.getInt("experience"));
        }
        return new Note();
    } 
    
    public static void insertNoteCv(Connection c,int idCv,double note) throws Exception{
         Statement s=c.createStatement();
         s.executeUpdate("insert into noteCv values("+idCv+","+note+")");
    }
    
    public double calculNote(Connection c) throws Exception{
        Cv cv=Cv.getCvbyId(c,this.idcv);
        Annonce annonce=Annonce.getAnnonceById(c,cv.getAnnonce());
        Note coeff=getCoeff(c, annonce.getPoste().getIdPoste());
        double sexe=this.getSexe()*coeff.getSexe();
        double age=this.getAge()*coeff.getAge();
        double sit=this.getSituation()*coeff.getSituation();
        double dip=this.getDiplome()*coeff.getDiplome();
        double nat=this.getNationalite()*coeff.getNationalite();
        double langue=this.getLangue()*coeff.getLangue();
        double exp=this.getExperience()*coeff.getExperience();
        double note = sexe+age+sit+dip+nat+langue+exp;
        return note;
        //insertNoteCv(c, this.idcv, note);
        //cv.noterCv(c, note);
    } 
    
    public static void noteCv(Connection c,int idcv)throws Exception{
        Note note=new Note();
        note.note(c, idcv);
        note.calculNote(c);
    }
    
}
