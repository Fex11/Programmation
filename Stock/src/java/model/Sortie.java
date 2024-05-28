/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Sortie {
    int idSortie;
    String date;
    Magasin magasin;
    Article article;
    double qte;
    double montant;
    double etat;
    String dateValidation;
    String unite;
    double valeur;

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
    

    public Sortie(String date, Magasin magasin, Article article, double qte)throws Exception{
        this.date = date;
        this.magasin = magasin;
        this.article = article;
        this.setQte(qte);
    }

    public Sortie(int idSortie, String date, Magasin magasin, Article article, double qte, double montant, double etat,String dv,String unite,double valeur)throws Exception {
        this.idSortie = idSortie;
        this.date = date;
        this.magasin = magasin;
        this.article = article;
        this.setQte(qte);
        this.montant = montant;
        this.etat = etat;
        this.dateValidation=dv;
        this.setUnite(unite);
        this.setValeur(valeur);
    } 

    public String getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(String dateValidation) {
        this.dateValidation = dateValidation;
    }

    public int getIdSortie() {
        return idSortie;
    }

    public void setIdSortie(int idSortie) {
        this.idSortie = idSortie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte)throws Exception{
        if(qte<0){
            throw new Exception("Quantite negatif");
        }
        this.qte = qte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getEtat() {
        return etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }
    
    public void insert(Connection c)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into sortie (daty,idmagasin,idarticle,qte,montant,etat,unite,valeur)  values('"+this.date+"',"+this.magasin.getIdMagasin()+","+this.article.getIdArticle()+","+this.qte+","+0+","+0+",'"+this.unite+"',"+this.valeur+")");
    }
    
    public static Sortie getById(Connection c,int id) throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from getSortie where idsortie="+id);
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            return new Sortie(res.getInt("idsortie"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("montant"),res.getInt("etat"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur"));
        }
        return null;
    }
    
    public static Sortie[] getAllSortieNv(Connection c) throws Exception{
        Statement sm=c.createStatement();
        Vector<Sortie> v=new Vector<Sortie>();
        ResultSet res = sm.executeQuery("select * from getSortie where datevalidation is null");
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            v.add(new Sortie(res.getInt("idsortie"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("montant"),res.getInt("etat"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur")));
        }
        return v.toArray(new Sortie[v.size()]);
    }
    
    public static Sortie[] getAllSortieV(Connection c) throws Exception{
        Statement sm=c.createStatement();
        Vector<Sortie> v=new Vector<Sortie>();
        ResultSet res = sm.executeQuery("select * from getSortie where datevalidation is not null");
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            v.add(new Sortie(res.getInt("idsortie"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("montant"),res.getInt("etat"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur")));
        }
        return v.toArray(new Sortie[v.size()]);
    }
    
    
    public static void insertMouvement(Connection c,int ids,int ide,double qte,double pu)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into mouvement values("+ids+","+ide+","+qte+","+pu+")");
    }
    
    public void decomposer(Connection c)throws Exception {
        Entree[] reste=Entree.getEntreeRestant(c,this.getMagasin().getIdMagasin(),this.getArticle().getIdArticle());
        int i=0;
        double montant=0;
        double qte=this.getQte();
        while(qte>0){
            qte=qte-reste[i].getQte();
            if(qte>=0){
                reste[i].changeMarque(c);
                insertMouvement(c,this.getIdSortie(), reste[i].getIdEntree(),reste[i].getQte(),reste[i].getPu());
                montant=montant+(reste[i].getQte()*reste[i].getPu());
            }else{
                insertMouvement(c,this.getIdSortie(), reste[i].getIdEntree(),qte+reste[i].getQte(),reste[i].getPu());
                montant=montant+((qte+reste[i].getQte())*reste[i].getPu());
            }
            i++;
        }
        this.setMontant(montant);
        this.updateMontant(c);
    }
    
    public static Sortie getLastSortie(Connection c,int idm,int ida)throws Exception{
        Statement s=c.createStatement();
        ResultSet res = s.executeQuery("select * from getsortie where datevalidation=(select max(datevalidation) from getsortie) and idmagasin="+idm+" and idarticle="+ida);
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            return new Sortie(res.getInt("idsortie"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("montant"),res.getInt("etat"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur"));
        }
        return null;
    }
    
    public void valider(Connection c,String date)throws Exception{
        c.setAutoCommit(false);
        Sortie s=getLastSortie(c,this.getMagasin().getIdMagasin(),this.getArticle().getIdArticle());
        Entree e=Entree.getLastEntree(c,this.getMagasin().getIdMagasin(),this.getArticle().getIdArticle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss" );
        LocalDateTime ds;
        LocalDateTime de;
        if(s==null){
            ds=LocalDateTime.MIN;
        }else{
             ds = LocalDateTime.parse(s.getDateValidation(), formatter);
        }
        if(e==null){
            de=LocalDateTime.MIN;
        }else{
             de = LocalDateTime.parse(e.getDateValidation(), formatter);
        }
        
        LocalDateTime daty;
        if(ds.isAfter(de)){
            daty=ds;
        }else{
            daty=de;
        }
        StringBuilder stringBuilder = new StringBuilder(date);
        stringBuilder.setCharAt(10,' ');
        date = stringBuilder.toString();
        date=date+":00";
        LocalDateTime datev=LocalDateTime.parse(date, formatter);
        if(datev.isAfter(daty)){
            double stock=Entree.getStockRestant(c,this.getMagasin().getIdMagasin(),this.getArticle().getIdArticle());
            if(stock>=this.getQte()){
                this.decomposer(c);
                this.updateDateValidation(c, date);
            }else{
                throw new Exception("Stock insuffisant");
            }
        }else{
            throw new Exception("Date invalide");
        }   
        c.commit();
    }
    
    public void updateDateValidation(Connection c,String date)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update sortie set datevalidation='"+date+"' where idsortie="+this.getIdSortie());
    }
    
    public void updateMontant(Connection c)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update sortie set montant="+this.getMontant()+" where idsortie="+this.getIdSortie());
    }
    
    public static double[] getSortieByDate(Connection c,int idm,int ida,String date)throws Exception{
        Statement s=c.createStatement();
        double [] rep=new double[2];
        ResultSet res = s.executeQuery("select sum(qte) as qte,sum(montant) as montant from getsortie where datevalidation is not null and idmagasin="+idm+" and idarticle="+ida+" and datevalidation<='"+date+"'");
        while(res.next()){
            rep[0]=res.getDouble("qte");
            rep[1]=res.getDouble("montant");
        }
        return rep;
    }
    
    
    
}
