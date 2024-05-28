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
import static model.Sortie.getLastSortie;

/**
 *
 * @author fex
 */
public class Entree {
    int idEntree;
    String date;
    Magasin magasin;
    Article article;
    double qte;
    double pu;
    boolean marque;
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
    
    

    public Entree(int idEntree, String date, Magasin magasin, Article article, double qte, double pu, boolean marque,String dv,String unite,double valeur)throws Exception{
        this.idEntree = idEntree;
        this.date=date;
        this.magasin = magasin;
        this.article = article;
        this.setQte(qte);
        this.setPu(pu);
        this.marque = marque;
        this.dateValidation=dv;
        this.setUnite(unite);
        this.setValeur(valeur);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
     
    public int getIdEntree() {
        return idEntree;
    }

    public void setIdEntree(int idEntree) {
        this.idEntree = idEntree;
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

    public double getPu() {
        return pu;
    }

    public void setPu(double pu)throws Exception{
        if(pu<0){
            throw new Exception("Pu negatif");
        }
        this.pu = pu;
    }

    public boolean isMarque() {
        return marque;
    }

    public void setMarque(boolean marque) {
        this.marque = marque;
    }

    public String getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(String dateValidation) {
        this.dateValidation = dateValidation;
    }
    
    
    
    
    public static Entree getById(Connection c,int id) throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from getEntree where identree="+id);
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            return new Entree(res.getInt("identree"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("pu"),res.getBoolean("marque"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur"));
        }
        return null;
    }
    
    public int insert(Connection c)throws Exception{
        Statement s=c.createStatement();
        ResultSet res = s.executeQuery("insert into entree (daty,idmagasin,idarticle,qte,pu,marque,datevalidation,unite,valeur)  values('"+this.date+"',"+this.magasin.getIdMagasin()+","+this.article.getIdArticle()+","+this.qte+","+this.pu+",'true','"+this.date+"','"+this.unite+"',"+this.valeur+") returning identree");
        while(res.next()){
            return res.getInt("identree");
        }
        return 0;
    }
    
    public int insertV(Connection c)throws Exception{
        Statement s=c.createStatement();
        ResultSet res = s.executeQuery("insert into entree (daty,idmagasin,idarticle,qte,pu,marque,unite,valeur)  values('"+this.date+"',"+this.magasin.getIdMagasin()+","+this.article.getIdArticle()+","+this.qte+","+this.pu+",'true','"+this.unite+"',"+valeur+") returning identree");
        while(res.next()){
            return res.getInt("identree");
        }
        return 0;
    }
    
    public void changeMarque(Connection c)throws Exception{
         Statement s=c.createStatement();
         s.executeUpdate("update entree set marque='false' where identree="+this.idEntree);
    }
    
    public static Entree[] getAllEntreeNv(Connection c) throws Exception{
        Statement sm=c.createStatement();
        Vector<Entree> v=new Vector<Entree>();
        ResultSet res = sm.executeQuery("select * from getEntree where datevalidation is null");
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            Entree e=new Entree(res.getInt("identree"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("pu"),res.getBoolean("marque"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur"));
            v.add(e);
        }
        return v.toArray(new Entree[v.size()]);
    }
    
    public static Entree[] getAllEntreeV(Connection c) throws Exception{
        Statement sm=c.createStatement();
        Vector<Entree> v=new Vector<Entree>();
        ResultSet res = sm.executeQuery("select * from getEntree where datevalidation is not null");
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            Entree e=new Entree(res.getInt("identree"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("pu"),res.getBoolean("marque"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur"));
            v.add(e);
        }
        return v.toArray(new Entree[v.size()]);
    }
    
    public static Entree[] getEntreeRestant(Connection c,int idm,int ida)throws Exception{
        Statement s=c.createStatement();
        Article a=Article.getArticleById(c, ida);
        String tri="";
        if(a.getSortie().hashCode()=="fifo".hashCode()){
            tri="asc";
        }else{
            tri="desc";
        }
        Vector<Entree> v= new Vector<Entree>();
        ResultSet res = s.executeQuery("select * from getReste where marque=true and idmagasin="+idm+" and idarticle="+ida+" and datevalidation is not null order by daty "+tri);
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            Entree e=new Entree(res.getInt("identree"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("pu"),res.getBoolean("marque"),res.getString("datevalidation"),"",0);
            v.add(e);
        }
        return v.toArray(new Entree[v.size()]);
    }
    
    public static double getStockRestant(Connection c,int idm,int ida)throws Exception{
        Statement s=c.createStatement();
        ResultSet res = s.executeQuery("select sum(qte) from getReste where marque=true and idmagasin="+idm+" and idarticle="+ida+" and datevalidation is not null");
        while(res.next()){
            return res.getDouble("sum");
        }
        return 0;
    }
    
    public static Entree getLastEntree(Connection c,int idm,int ida)throws Exception{
        Statement s=c.createStatement();
        ResultSet res = s.executeQuery("select * from getentree where datevalidation=(select max(datevalidation) from getentree) and idmagasin="+idm+" and idarticle="+ida);
        while(res.next()){
            Magasin m=new Magasin(res.getInt("idmagasin"),res.getString("magasin"),res.getString("localisation"));
            Article ar=new Article(res.getInt("idarticle"),res.getString("code"),res.getString("article"),res.getString("unite"),res.getString("sortie"));
            return new Entree(res.getInt("identree"),res.getString("daty"),m,ar,res.getDouble("qte"),res.getDouble("pu"),res.getBoolean("marque"),res.getString("datevalidation"),res.getString("unitee"),res.getDouble("valeur"));
        }
        return null;
    }
    
    public static double[] getEntreeByDate(Connection c,int idm,int ida,String date)throws Exception{
        Statement s=c.createStatement();
        double [] rep=new double[2];
        ResultSet res = s.executeQuery("select sum(qte) as qte,sum(qte*pu) as montant from getentree where datevalidation is not null and idmagasin="+idm+" and idarticle="+ida+" and datevalidation<='"+date+"'");
        while(res.next()){
            rep[0]=res.getDouble("qte");
            rep[1]=res.getDouble("montant");
        }
        return rep;
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
            this.updateDateValidation(c, date);
            Sortie.insertMouvement(c,0,this.idEntree,0,0);
        }else{
            throw new Exception("Date invalide");
        }   
        c.commit();
    }
    
    public void updateDateValidation(Connection c,String date)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("update entree set datevalidation='"+date+"' where identree="+this.getIdEntree());
    }
    
}
