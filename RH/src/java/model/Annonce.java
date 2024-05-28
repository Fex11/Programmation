/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Annonce {
    int idAnnonce;
    Service service;
    Nationalite nationalite;
    Poste poste;
    int nombre;
    String sexe;
    int experience;
    int agemin;
    int agemax;
    String situation;
    LocalDate datefin;
    int etat;
    
    public Annonce(){}
    
     public Annonce(int idAnnonce, Service service, Poste poste,Nationalite nationalite, int vh, int hj, String sexe, int experience, int agemin, int agemax, String situation,Date df,int etat) {
        this.idAnnonce = idAnnonce;
        this.service = service;
        this.poste = poste;
        this.nationalite = nationalite;
        this.sexe = sexe;
        this.experience = experience;
        this.agemin = agemin;
        this.agemax = agemax;
        this.situation = situation;
        getPlace(vh, hj);
        this.etat=etat;
        this.datefin=df.toLocalDate();
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    
     public Nationalite getNationalite() {
        return nationalite;
    }

    public void setNationalite(Nationalite nationalite) {
        this.nationalite = nationalite;
    }


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAgemin() {
        return agemin;
    }

    public void setAgemin(int agemin) {
        this.agemin = agemin;
    }

    public int getAgemax() {
        return agemax;
    }

    public void setAgemax(int agemax) {
        this.agemax = agemax;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
    
    public static int getPersAnnonce(int vh, int hj){
       int hjs = hj*5;
       int nb = vh/hjs;
        return nb;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
    public void checkDate(Connection c)throws Exception {
        if(this.getEtat()==0){
            LocalDate now=LocalDate.now();
            Vector<Cv> cvs=Cv.getCvByIdAnnonce(c, this.idAnnonce);
            if(this.getDatefin().isBefore(now)){
                int check=checkNbCv(this.nombre,cvs.size());
                if(check==1){
                    changeEtat(c, 1, this.idAnnonce);
                }else{
                    changeEtat(c, 2, this.idAnnonce);
                }
            }       
        } 
    }
    
    public static int checkNbCv(int place,int nbcv){
        if(nbcv<place*3){
            return 0;
        }
        return 1;
    }
    
    public static void insertAnnonce(Connection c,String dd,String df)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into Annonce (datePublication,dateFin,etat) values ('"+dd+"','"+df+"',"+0+")");
    }
    
    public static void insertDiplomeRequisAnnonce(Connection c,int ida,int iddip,int idde)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into DiplomeRequisAnnonce values ("+ida+","+iddip+","+idde+")");
    }
    
    public static void insertDetailAnnonce(Connection c,int ida,int ids,int idp,int idn,int vh,int hj,String sexe,int min,int max,int exp,String sit)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into detailAnnonce values ("+ida+","+ids+","+idp+","+idn+","+vh+","+hj+",'"+sexe+"',"+min+","+max+","+exp+",'"+sit+"')");
    }
    
    public static void insertLangueRequisAnnonce(Connection c,int ida,int idl,boolean p,boolean e)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into LangueRequisAnnonce values ("+ida+","+idl+","+p+","+e+")");
    }
    public static int getLastAnnonce(Connection c)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("SELECT MAX(idAnnonce) FROM Annonce");
        while(res.next()){
            return res.getInt(1);
        }
        return 0;
    }
    
    
    
    public void getPlace(int vh,int hj){
        int sem=0;
        if(hj<=6){
            sem=hj*6;
        }else{
            sem=hj*5;
        }
        this.nombre= vh/sem;
    }
    public static void langue(Connection c,String[] s,int ida)throws Exception{
        String[][] str=new String[s.length][2];
        String[] ep=new String[s.length];
        int[] id=new int[s.length];
        for(int i=0;i<s.length;i++){
            str[i]=s[i].split("y");
            ep[i]=str[i][1];
            id[i]=Integer.parseInt(str[i][0]);
        }
        int j=0;
         for(int i=0;i<s.length-1;i++){
             if(id[i]==id[i+1]){
                  Annonce.insertLangueRequisAnnonce(c, ida, id[i], true, true);
                  i++;
                  j++;
             }else{
                 if(ep[i].hashCode()=="p".hashCode()){
                    Annonce.insertLangueRequisAnnonce(c, ida, id[i], true, false);
                 }else{
                    Annonce.insertLangueRequisAnnonce(c, ida, id[i], false, true);
                 }
             }
             j++;
         }
         if(j==s.length-1){
             if(ep[j].hashCode()=="p".hashCode()){
                    Annonce.insertLangueRequisAnnonce(c, ida, id[j], true, false);
                 }else{
                    Annonce.insertLangueRequisAnnonce(c, ida, id[j], false, true);
                 }
         }
    }
    
    public static Vector<Annonce> getAllAnnonce(Connection c)throws Exception{
        Statement sm=c.createStatement();
         Vector<Annonce> v=new Vector<Annonce>();
            ResultSet res = sm.executeQuery("select*from getAnnonce");
             while(res.next()){
               Service s=new Service(res.getInt("idService"),res.getString("nomService"));
               Poste p=new Poste(res.getInt("idPoste"),res.getInt("idService"),res.getString("nomPoste"));
               Nationalite n=new Nationalite(res.getInt("idNationalite"),res.getString("nationalite"));
               v.add(new Annonce(res.getInt("idAnnonce"),s,p,n,res.getInt("vh"),res.getInt("hj"),res.getString("sexe"),res.getInt("experience"),res.getInt("agemin"),res.getInt("agemax"),res.getString("situation"),res.getDate("datefin"),res.getInt("etat")));
            }
           return v;
    }
    
    public static Vector<Annonce> getNotifAnnonce(Connection c)throws Exception{
        Statement sm=c.createStatement();
         Vector<Annonce> v=new Vector<Annonce>();
            ResultSet res = sm.executeQuery("select*from getAnnonce where etat=1 or etat=2");
             while(res.next()){
               Service s=new Service(res.getInt("idService"),res.getString("nomService"));
               Poste p=new Poste(res.getInt("idPoste"),res.getInt("idService"),res.getString("nomPoste"));
               Nationalite n=new Nationalite(res.getInt("idNationalite"),res.getString("nationalite"));
               v.add(new Annonce(res.getInt("idAnnonce"),s,p,n,res.getInt("vh"),res.getInt("hj"),res.getString("sexe"),res.getInt("experience"),res.getInt("agemin"),res.getInt("agemax"),res.getString("situation"),res.getDate("datefin"),res.getInt("etat")));
            }
           return v;
    }
    
     public static Annonce getAnnonceById(Connection c,int id)throws Exception{
        Statement sm=c.createStatement();
        Annonce a = new Annonce();
            ResultSet res = sm.executeQuery("Select * from getAnnonce where idannonce ='"+id+"'");
             while(res.next()){
               Service s=new Service(res.getInt("idService"),res.getString("nomService"));
               Poste p=new Poste(res.getInt("idPoste"),res.getInt("idService"),res.getString("nomPoste"));
               Nationalite n=new Nationalite(res.getInt("idNationalite"),res.getString("nationalite"));
               a.setIdAnnonce( res.getInt("idAnnonce")); 
               a.setService(s);
               a.setNationalite(n);
               a.setPoste(p);
               a.getPlace(res.getInt("vh"),res.getInt("hj"));     
               a.setSexe(res.getString("sexe"));
               a.setExperience(res.getInt("experience"));
               a.setAgemin(res.getInt("agemin"));
               a.setAgemax(   res.getInt("agemax"));
               a.setSituation(res.getString("situation"));
               a.setDatefin(res.getDate("datefin").toLocalDate());
               a.setEtat(res.getInt("etat"));
            }
           return a;
    } 
     
    public static void changeEtat(Connection c,int etat,int ida) throws Exception{
         Statement s=c.createStatement();
        s.executeUpdate("update annonce set etat="+etat+" where idannonce="+ida);
    }
    
    public static void Ralonger(Connection c,LocalDate df,int ida,int nb)throws Exception{
        LocalDate date=df.plusDays(nb);
        Statement s=c.createStatement();
        s.executeUpdate("update annonce set etat=0,datefin='"+date.toString()+"' where idannonce="+ida);
    }
    
    public static boolean checkCv(Connection c,int ida,int idc)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from getCv where idclient="+idc+" and idannonce="+ida);
            while(res.next()){
              return true;
            }
           return false;
    }
    
    public static Vector<Integer> getCvTest(Connection c,int ida)throws Exception{
        Statement sm=c.createStatement();
        Vector<Integer> v=new Vector<Integer>();
        ResultSet res = sm.executeQuery("select*from test where etat=2 and idannonce="+ida+" order by note desc");
         while(res.next()){
            v.add(res.getInt("idcv"));
         }
           return v;
    }
    
    public void checkEntretien(Connection c)throws Exception{
        Vector<Integer> idcvs = getCvTest(c,this.idAnnonce);
        if(this.getNombre()*2<=idcvs.size()){
            changeEtat(c, 4,this.idAnnonce);
            for(int i=0;i<this.getNombre();i++){
                Cv.passerEntretien(c,idcvs.get(i),this.idAnnonce);
            }
        }
    }
    
}
