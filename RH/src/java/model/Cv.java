/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Cv {
    int idCv;
    int annonce;
    Nationalite nationalite;
    String nomCdt;
    String prenomCdt;
    String sexe;
    Date dtn;
    int expCdt;
    String situationCdt;
    String emailCdt;
    int numeroCdt;
    String adresseCdt;
    int idClient;
    int etat;
    int note;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    
    public Cv(int idCv,int idClient,int annonce, Nationalite nationalite, String nomCdt, String prenomCdt, String sexe, Date dtn, int expCdt, String situationCdt,String emailCdt, int numeroCdt,String adresseCdt,int note) {
        this.idCv = idCv;
        this.idClient = idClient;
        this.annonce = annonce;
        this.nationalite = nationalite;
        this.nomCdt = nomCdt;
        this.prenomCdt = prenomCdt;
        this.sexe = sexe;
        this.dtn = dtn;
        this.expCdt = expCdt;
        this.situationCdt = situationCdt;
        this.emailCdt = emailCdt;
        this.numeroCdt = numeroCdt;
        this.adresseCdt = adresseCdt;
        this.note=note;
    }

    public int getIdCv() {
        return idCv;
    }

    public void setIdCv(int idCv) {
        this.idCv = idCv;
    }

    public int getAnnonce() {
        return annonce;
    }

    public void setAnnonce(int annonce) {
        this.annonce = annonce;
    }

    public Nationalite getNationalite() {
        return nationalite;
    }

    public void setNationalite(Nationalite nationalite) {
        this.nationalite = nationalite;
    }

    public String getNomCdt() {
        return nomCdt;
    }

    public void setNomCdt(String nomCdt) {
        this.nomCdt = nomCdt;
    }

    public String getPrenomCdt() {
        return prenomCdt;
    }

    public void setPrenomCdt(String prenomCdt) {
        this.prenomCdt = prenomCdt;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getAgeCdt() {
        return dtn;
    }

    public void setAgeCdt(Date dtn) {
        this.dtn = dtn;
    }

    public int getExpCdt() {
        return expCdt;
    }

    public void setExpCdt(int expCdt) {
        this.expCdt = expCdt;
    }

    public String getSituationCdt() {
        return situationCdt;
    }

    public void setSituationCdt(String situationCdt) {
        this.situationCdt = situationCdt;
    }

    public String getEmailCdt() {
        return emailCdt;
    }

    public void setEmailCdt(String emailCdt) {
        this.emailCdt = emailCdt;
    }

    public int getNumeroCdt() {
        return numeroCdt;
    }

    public void setNumeroCdt(int numeroCdt) {
        this.numeroCdt = numeroCdt;
    }
    
    public String getAdresseCdt() {
        return adresseCdt;
    }

    public void setAdresseCdt(String adresseCdt) {
        this.adresseCdt = adresseCdt;
    }
    
    public static void insertCv(Connection c,int ida)throws Exception{
        Statement s=c.createStatement();
        LocalDate now=LocalDate.now();
        s.executeUpdate("insert into cv (idannonce,dateenvoie,note) values("+ida+",'"+now+"',0)");
    }
    
    public static void insertDiplomeCv(Connection c,int idCv,int idDip,int idDeg)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into diplomecv values("+idCv+","+idDip+","+idDeg+")");
    }
    
    public static void insertLangueCv(Connection c,int idCv,int idLangue,boolean p,boolean e) throws Exception{
         Statement s=c.createStatement();
         s.executeUpdate("insert into languecv values("+idCv+","+idLangue+","+p+","+e+")");
    }
    
    public static void insertDetailCv(Connection c,int idCv,int idClient,int ida,int idn,String nomCdt,String prenomCdt,String sexe,String dtn,int exp,String situation,String email,int num,String adresse) throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into detail_cv values("+idCv+","+idClient+","+ida+","+idn+",'"+nomCdt+"','"+prenomCdt+"','"+sexe+"','"+dtn+"','"+exp+"','"+situation+"','"+email+"',"+num+",'"+adresse+"')");
    }
    
     public static int getLastCv(Connection c)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select max(idcv) from cv");
        while(res.next()){
            return res.getInt(1);
        }
        return 0;
    }
    
    public static void langue(Connection c,String[] s,int idCv)throws Exception{
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
                  Cv.insertLangueCv(c, idCv, id[i], true, true);
                  i++;
                  j++;
             }else{
                 if(ep[i].hashCode()=="p".hashCode()){
                    Cv.insertLangueCv(c, idCv, id[i], true, false);
                 }else{
                    Cv.insertLangueCv(c, idCv, id[i], false, true);
                 }
             }
             j++;
         }
         if(j==s.length-1){
             if(ep[j].hashCode()=="p".hashCode()){
                    Cv.insertLangueCv(c, idCv, id[j], true, false);
                 }else{
                    Cv.insertLangueCv(c, idCv, id[j], false, true);
                 }
         }
    }
    
    public static Vector<Cv> getAllCV(Connection c)throws Exception{
         Statement sm=c.createStatement();
         Vector<Cv> v=new Vector<Cv>();
         ResultSet res = sm.executeQuery("select * from getCV");
          while(res.next()){
               Nationalite n=new Nationalite(res.getInt("idNationalite"),res.getString("nationalite"));
               v.add(new Cv(res.getInt("idCv"),res.getInt("idClient"),res.getInt("idAnnonce"),n,res.getString("nomCdt"),res.getString("prenomCdt"),res.getString("sexe"),res.getDate("dtn"),res.getInt("experience"),res.getString("situaton"),res.getString("email"),res.getInt("numero"),res.getString("adresse"),res.getInt("note")));
            }
           return v;
    }
    
    public static Cv getCvbyId(Connection c,int idcv)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from getCV where idcv="+idcv);
        while(res.next()){
               Nationalite n=new Nationalite(res.getInt("idNationalite"),res.getString("nationalite"));
               return new Cv(res.getInt("idcv"),res.getInt("idClient"),res.getInt("idAnnonce"),n,res.getString("nomCdt"),res.getString("prenomCdt"),res.getString("sexe"),res.getDate("dtn"),res.getInt("experience"),res.getString("situation"),res.getString("email"),res.getInt("numero"),res.getString("adresse"),res.getInt("note"));
        }
        return null;
    }
    
    public static Vector<Cv> getCvByIdAnnonce(Connection c,int ida)throws Exception{
         Statement sm=c.createStatement();
         Vector<Cv> v=new Vector<Cv>();
         ResultSet res = sm.executeQuery("select * from getCv where idannonce="+ida+" order by note desc");
          while(res.next()){
               Nationalite n=new Nationalite(res.getInt("idNationalite"),res.getString("nationalite"));
               v.add(new Cv(res.getInt("idCv"),res.getInt("idClient"),res.getInt("idAnnonce"),n,res.getString("nomCdt"),res.getString("prenomCdt"),res.getString("sexe"),res.getDate("dtn"),res.getInt("experience"),res.getString("situation"),res.getString("email"),res.getInt("numero"),res.getString("adresse"),res.getInt("note")));
            }
           return v;
    }
    
    public static Vector<Cv> getCvByIdClient(Connection c,int idc)throws Exception{
         Statement sm=c.createStatement();
         Vector<Cv> v=new Vector<Cv>();
         ResultSet res = sm.executeQuery("select * from getCv where idclient="+idc);
          while(res.next()){
               Nationalite n=new Nationalite(res.getInt("idNationalite"),res.getString("nationalite"));
               v.add(new Cv(res.getInt("idCv"),res.getInt("idClient"),res.getInt("idAnnonce"),n,res.getString("nomCdt"),res.getString("prenomCdt"),res.getString("sexe"),res.getDate("dtn"),res.getInt("experience"),res.getString("situation"),res.getString("email"),res.getInt("numero"),res.getString("adresse"),res.getInt("note")));
            }
           return v;
    }
    
    public static void initTest(Connection c,int idcv,int ida)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into test values("+ida+","+idcv+","+0+","+0+")");
    }
    
    public static void passerTest(Connection c,int idcv,int ida)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update test set etat=1 where idannonce="+ida+" and idcv="+idcv);
    }
    
    public static void passerEntretien(Connection c,int idcv,int ida)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update test set etat=3 where idannonce="+ida+" and idcv="+idcv);
    }
    
    public static void finTest(Connection c,int idcv,int ida,int note)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update test set etat=2,note="+note+" where idannonce="+ida+" and idcv="+idcv);
    }
    
    public static int checkEtat(Connection c,int idcv,int ida)throws Exception{
    Statement sm=c.createStatement();
         ResultSet res = sm.executeQuery("select * from test where idannonce="+ida+" and idcv="+idcv);
         while(res.next()){
             return res.getInt("etat");
         }
         return -1;
    }
    public int checkNotifCv(Connection c)throws Exception{
         Statement sm=c.createStatement();
         ResultSet res = sm.executeQuery("select * from test where etat=1 and idannonce="+this.getAnnonce()+" and idcv="+this.idCv);
         while(res.next()){
             return res.getInt("idcv");
         }
         return 0;
    }
    
    public int checkNotifEntretien(Connection c)throws Exception{
         Statement sm=c.createStatement();
         ResultSet res = sm.executeQuery("select * from test where etat=3 and idannonce="+this.getAnnonce()+" and idcv="+this.idCv);
         while(res.next()){
             return res.getInt("idcv");
         }
         return 0;
    }
    
    public static Vector<Cv> getNotif(Connection c,int idc)throws Exception{
        Vector<Cv> cvs=getCvByIdClient(c, idc);
        Vector<Cv> rep=new Vector<Cv>();
        for(int i=0;i<cvs.size();i++){
            int idcv=cvs.get(i).checkNotifCv(c);
            if(idcv!=0){
                rep.add(cvs.get(i));
            }
        }
        return rep;
    }
    public static Vector<Cv> getNotifEntretien(Connection c,int idc)throws Exception{
        Vector<Cv> cvs=getCvByIdClient(c, idc);
        Vector<Cv> rep=new Vector<Cv>();
        for(int i=0;i<cvs.size();i++){
            int idcv=cvs.get(i).checkNotifEntretien(c);
            if(idcv!=0){
                rep.add(cvs.get(i));
            }
        }
        return rep;
    }
    
    public int getNote(Connection c) throws Exception{
         Statement sm=c.createStatement();
         ResultSet res = sm.executeQuery("select * from notecv where idcv="+this.idCv);
         while(res.next()){
             return res.getInt("note");
         }
         return 0;
    }
    
    
    public void noterCv(Connection c,double note)throws Exception{
         Statement s=c.createStatement();
         s.executeUpdate("update cv set note="+note+" where idcv="+this.idCv);
    }
}
