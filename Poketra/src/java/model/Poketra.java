/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Poketra {
    int idPoketra;
    String nom;
    int etat; 
    Categorie categorie;
    Look look;
    Mp[] mp;
    TaillePoketra[] taille;
    MetierPoketra[] metier;

    public MetierPoketra[] getMetier() {
        return metier;
    }

    public void setMetier(MetierPoketra[] metier) {
        this.metier = metier;
    }

    
    
    
    public Mp[] getMp() {
        return mp;
    }

    public void setMp(Mp[] mp) {
        this.mp = mp;
    }

    public TaillePoketra[] getTaille() {
        return taille;
    }

    public void setTaille(TaillePoketra[] taille) {
        this.taille = taille;
    }
    
    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Look getLook() {
        return look;
    }

    public void setLook(Look look) {
        this.look = look;
    }

    public Poketra(int idPoketra, String nom, int etat, Categorie categorie, Look look) {
        this.idPoketra = idPoketra;
        this.nom = nom;
        this.etat = etat;
        this.categorie = categorie;
        this.look = look;
    }
    
    
    public static Poketra[] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Poketra> v=new Vector<Poketra>();
        ResultSet res = sm.executeQuery("select*from getPoketra");
        while(res.next()){
            Look look=new Look(res.getInt("idlook"),res.getString("look"));
            Categorie categorie=new Categorie(res.getInt("idcategorie"),res.getString("categorie"));
            v.add(new Poketra(res.getInt("idpoketra"),res.getString("nom"),res.getInt("etat"),categorie,look));
        }
        return v.toArray(new Poketra[v.size()]);
    
    }
    
    public static Poketra getById(Connection c,int id)throws Exception {
        Statement sm=c.createStatement();
        Poketra poketra=null;
        ResultSet res = sm.executeQuery("select*from getPoketra where idPoketra="+id);
        while(res.next()){
            Look look=Look.getInfoLookById(c,res.getInt("idlook"));
            Categorie categorie=new Categorie(res.getInt("idcategorie"),res.getString("categorie"));
            poketra=new Poketra(res.getInt("idpoketra"),res.getString("nom"),res.getInt("etat"),categorie,look);
        }
        return poketra;
    }
    
    public static void insert(Connection c,int idl,int idc,String nom)throws Exception { 
        String query = "INSERT INTO poketra(idCategorie,idLook,nom,etat) VALUES (?,?,?,0)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idc);
            preparedStatement.setInt(2,idl);
            preparedStatement.setString(3,nom);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateEtat(Connection c,int id)throws Exception { 
        String query = "update poketra set etat=1 where idpoketra=?";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertMp(Connection c,int idp,int idmp)throws Exception { 
        String query = "INSERT INTO MpPoketra(idPoketra,idMp) VALUES (?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idp);
             preparedStatement.setInt(2,idmp);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertPrix(Connection c,int idp,int idt,double prix)throws Exception { 
        String query = "INSERT INTO prixDeVente(idPoketra,idtaille,prix) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idp);
             preparedStatement.setInt(2,idt);
             preparedStatement.setDouble(3,prix);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertQte(Connection c,int idp,int idmp,int idtaille,double qte)throws Exception { 
        String query = "INSERT INTO QteMp(idPoketra,idTaille,idMp,qte) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idp);
            preparedStatement.setInt(2,idtaille);
            preparedStatement.setInt(3,idmp);
            preparedStatement.setDouble(4,qte);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Poketra[] recherche(Connection c,String s)throws Exception {
        Statement sm=c.createStatement();
        Vector<Poketra> v=new Vector<Poketra>();
        ResultSet res = sm.executeQuery("select idPoketra from recherche where nom='"+s+"'");
        while(res.next()){
            int id=res.getInt("idPoketra");
            Poketra p=getById(c, id);
            v.add(p);
        }
        return v.toArray(new Poketra[v.size()]);
    
    }
    
    public static Mp[] getMpPoketra(Connection c,int idPoketra)throws Exception {
        Statement sm=c.createStatement();
        Vector<Mp> v=new Vector<Mp>();
        ResultSet res = sm.executeQuery("select * from getMpPoketra where idpoketra="+idPoketra+" order by idmp");
        while(res.next()){
            Mp mp=new Mp(res.getInt("idmp"),res.getString("nom"),res.getDouble("pu"),res.getString("unite"));
            v.add(mp);
        }
        return v.toArray(new Mp[v.size()]);
    }
    
    public static Poketra[] f(Connection c,double p1,double p2)throws Exception{
        Statement sm=c.createStatement();
        Vector<Poketra> v=new Vector<Poketra>();
        ResultSet res = sm.executeQuery("select*from getPrix where prix>="+p1+" and prix<="+p2);
        while(res.next()){
            int id=res.getInt("idPoketra");
            Poketra p=getById(c, id);
            TaillePoketra[] t=new TaillePoketra[1];
            t[0]=new TaillePoketra(res.getDouble("prix"),res.getInt("idtaille"),res.getString("taille"),res.getInt("niveau"));
            p.setTaille(t);
            v.add(p);
        }
        return v.toArray(new Poketra[v.size()]);
    }
    
    public void getTaille(Connection c)throws Exception{
        Statement sm=c.createStatement();
        Vector<TaillePoketra> v=new Vector<TaillePoketra>();
        ResultSet res = sm.executeQuery("select * from getPrix where idpoketra="+this.idPoketra);
        while(res.next()){
            TaillePoketra t=new TaillePoketra(res.getDouble("prix"),res.getInt("idtaille"),res.getString("taille"),res.getInt("niveau"));
            int stock=getStock(c,this.getIdPoketra(),res.getInt("idtaille"));
            t.setStockk(stock);
            v.add(t);
        }
        this.setTaille(v.toArray(new TaillePoketra[v.size()]));
    }
    public TaillePoketra getTailleRay(Connection c,int idtaille)throws Exception{
        Statement sm=c.createStatement();
        TaillePoketra v=null;
        ResultSet res = sm.executeQuery("select * from getPrix where idpoketra="+this.idPoketra+" and idtaille="+idtaille);
        while(res.next()){
            v=new TaillePoketra(res.getDouble("prix"),res.getInt("idtaille"),res.getString("taille"),res.getInt("niveau"));
            
        }
        return v;
    }
    
    public MpPoketra[] getMpTaillePoketra(Connection c,int idtaille)throws Exception{
        Statement sm=c.createStatement();
        Vector<MpPoketra> v=new Vector<MpPoketra>();
        ResultSet res = sm.executeQuery("select * from getqte where idpoketra="+this.idPoketra+" and idtaille="+idtaille);
        while(res.next()){
            MpPoketra mp=new MpPoketra(res.getDouble("qte"),res.getInt("idmp"),res.getString("matiere"),res.getDouble("pu"),"unite");
            v.add(mp);
        }
        return v.toArray(new MpPoketra[v.size()]);
    }
    
    public void getAllMpTaillePoketra(Connection c)throws Exception{
        for(int i=0;i<this.getTaille().length;i++){
            MpPoketra[] mp=this.getMpTaillePoketra(c, this.getTaille()[i].idTaille);
            this.getTaille()[i].setMpPoketra(mp);
        }
    }
    
    public static Poketra getAllInfoById(Connection c,int id)throws Exception{
        Poketra p=Poketra.getById(c, id);
        p.getTaille(c);
        p.getAllMpTaillePoketra(c);
        return p;
    }
    
    public static void fabricationPoketra(Connection c,int idpoketra,int idTaille,int qte,String date)throws Exception{
        Poketra p=Poketra.getById(c, idpoketra);
        TaillePoketra taille=p.getTailleRay(c, idTaille);
        MpPoketra[] mp=p.getMpTaillePoketra(c, idTaille);
        taille.setMpPoketra(mp);
        StockMp[] stock=new StockMp[mp.length];
        for(int i=0;i<mp.length;i++){
            stock[i]=StockMp.getById(c, mp[i].idMp);
        }
        for(int i=0;i<mp.length;i++){
            if(stock[i].getQte()<mp[i].getQte()*qte){
                double nn=mp[i].getQte()*qte-stock[i].getQte();
                throw new Exception("tsy ampy "+nn+" ny "+mp[i].getNom());
            }
        } 
        int id=entre(c, idpoketra,idTaille, date, qte);
        int nvqte=addStockPoketra(c, idpoketra,idTaille, qte);
        Poketra.historique(c,idpoketra, idTaille, date, nvqte);
        for(int i=0;i<mp.length;i++){
            StockMp.sortieMp(c, date,mp[i].getIdMp(),mp[i].getQte()*qte,id);
            double nvqt=StockMp.manalaStock(c, mp[i].getIdMp(),mp[i].getQte()*qte);
            Mp.historique(c,mp[i].getIdMp(), nvqt, date);
        }
    }
    
    public static Poketra hhh(Connection c,int idPoketra)throws Exception{
        Poketra p=Poketra.getAllInfoById(c, idPoketra);
        MetierPoketra[] m=MetierPoketra.getMetierByIdPoketra(c, idPoketra);
        p.setMetier(m);
        for(int i=0;i<p.getTaille().length;i++){
            double prix=TaillePoketra.getPrix(c,p.getIdPoketra(),p.getTaille()[i].getIdTaille());
            p.getTaille()[i].setPrixVente(prix);
        }
        double main=0;
        for(int j=0;j<p.getMetier().length;j++){
            main=main+(p.getMetier()[j].getTh()*p.getMetier()[j].getNb()*p.getMetier()[j].getTemp());
        }
        /*for(int i=0;i<p.getTaille().length;i++){
           if(p.getTaille()[i].getIdTaille()==3){
               p.getTaille()[i].setMainOeuvre(main);
           }else if(p.getTaille()[i].getIdTaille()==2){
               p.getTaille()[i].setMainOeuvre(main*2);
           }else if(p.getTaille()[i].getIdTaille()==1){
               p.getTaille()[i].setMainOeuvre(main*4);
            }
        }*/
        for(int i=0;i<p.getTaille().length;i++){
            double multi=Math.pow(2,p.getTaille()[i].getNiveau()-1);
            p.getTaille()[i].setMainOeuvre(main*multi);
        }
        
    return p;
    }
    
    public static int entre(Connection c,int idp,int idt,String date,int nb)throws Exception{
        String query = "INSERT INTO entree(idPoketra,idTaille,daty,nb) VALUES ("+idp+","+idt+",'"+date+"',"+nb+")returning identree";
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery(query);
        while(res.next()){
            return res.getInt("identree");
        }
        return 0;
    }
    
    public static void entree(Connection c,int idp,int idt,String date,int nb){
        String query = "INSERT INTO entree(idPoketra,idTaille,daty,nb) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idp);
            preparedStatement.setInt(2,idt);
            preparedStatement.setString(3,date);
            preparedStatement.setInt(4,nb);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void checkSortie(Connection c,int idp,int idt,String date,int qte)throws Exception{
        int stock=getStock(c, idp, idt);
        if(stock<qte){
            int a=qte-stock;
            throw new Exception("Tsy ampy "+a+" ny stock");
        }
        sortie(c, idp, idt, date, qte);
        int nvqte=manalaStock(c, idp, idt, qte);
        historique(c, idp, idt, date, nvqte);
    }
    
    public static void sortie(Connection c,int idp,int idt,String date,int nb){
        String query = "INSERT INTO sortie(idPoketra,idTaille,daty,nb) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idp);
            preparedStatement.setInt(2,idt);
            preparedStatement.setString(3,date);
            preparedStatement.setInt(4,nb);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void init(Connection c,int idp,int idt)throws Exception { 
        String query = "INSERT INTO stockPoketra(idpoketra,idtaille,stock) VALUES (?,?,0)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
          preparedStatement.setInt(1,idp);
          preparedStatement.setInt(2,idt);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int getStock(Connection c,int idp,int idt)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from stockPoketra where idpoketra="+idp+" and idtaille="+idt);
        while(res.next()){
           return res.getInt("stock");
        }
        return 0;
    } 
    
    public static int addStockPoketra(Connection c,int idp,int idt,int qte)throws Exception{
        int stock=getStock(c, idp, idt);
        int nvqte=stock+qte;
        String query = "update stockPoketra set stock=? where idpoketra=? and idtaille=?";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setDouble(1,nvqte);
            preparedStatement.setInt(2,idp);
            preparedStatement.setInt(3,idt);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nvqte;
    }
    public static int manalaStock(Connection c,int idp,int idt,int qte)throws Exception{
        int stock=getStock(c, idp, idt);
        int nvqte=stock-qte;
        String query = "update stockPoketra set stock=? where idpoketra=? and idtaille=?";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setDouble(1,nvqte);
            preparedStatement.setInt(2,idp);
            preparedStatement.setInt(3,idt);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nvqte;
    }
    
    public static void historique(Connection c,int idp,int idt,String date,int nvqte)throws Exception{
        String query = "INSERT INTO historique(idpoketra,idtaille,daty,stock) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1,idp);
            preparedStatement.setInt(2,idt);
            preparedStatement.setString(3,date);
            preparedStatement.setInt(4,nvqte);
            ////
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
