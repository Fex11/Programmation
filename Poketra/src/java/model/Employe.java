/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Employe {
    int idEmploye;
    String nom;
    String daty;
    int experience;
    double salaire;
    Grade grade;
    Metier metier;

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDaty() {
        return daty;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }

    public Metier getMetier() {
        return metier;
    }

    public void setMetier(Metier metier) {
        this.metier = metier;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    
    

    public Employe(int idEmploye, String nom, String daty, int experience, Metier metier) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.daty = daty;
        this.experience = experience;
        this.metier = metier;
    }

   
    
    public static Employe[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Employe> v=new Vector<Employe>();
        ResultSet res = sm.executeQuery("select*from v_employe");
        while(res.next()){
            Metier m=new Metier(res.getInt("idmetier"),res.getString("nommetier"),res.getDouble("th"));
            v.add(new Employe(res.getInt("idemploye"),res.getString("nom"),res.getString("date_embauche"),res.getInt("experience"),m));
        }
        return v.toArray(new Employe[v.size()]);
    
    }
    
    public static Employe getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Employe employe=null;
        ResultSet res = sm.executeQuery("select*from taille where idTaille="+id);
        while(res.next()){
            Metier met=new Metier(res.getInt("idmetier"),res.getString("nommetier"),res.getDouble("th"));
            employe=new Employe(res.getInt("idemploye"),res.getString("nom"),res.getString("daty"),res.getInt("expercience"),met);
        }   
        return employe;
    }
    
     public static int insert(Connection c,String nom,String date,int idm)throws Exception { 
        Statement s=c.createStatement();
        ResultSet res = s.executeQuery("INSERT INTO employe(nom,date_embauche,idMetier)  VALUES ('"+nom+"' ,'"+date+"' ,"+idm+")returning idemploye");
        while(res.next()){
            return res.getInt("idemploye");
        }
        return 0;
    }
     
     public void getSalaire(Connection c) throws Exception{
        Grade[] grade=Grade.getAll(c);
        for(int i=0;i<grade.length-1;i++){
            if(this.getExperience()>=grade[i].getExperience() && this.getExperience()<grade[i+1].getExperience()){
                this.setGrade(grade[i]);
                double sal=this.getMetier().getTh()*grade[i].getAugmentation();
                this.setSalaire(sal);
            }
        }
        if(this.getExperience()>=grade[grade.length-1].getExperience()){
            this.setGrade(grade[grade.length-1]);
                double sal=this.getMetier().getTh()*grade[grade.length-1].getAugmentation();
                this.setSalaire(sal);
        }
     }
}
