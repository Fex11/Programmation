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
 * @author MOREL BEN Taboaly
 */
public class RepAdmin {
    int idra;
    int idQ;
    String rep;
    

    public RepAdmin(int idra, int idQ, String rep) {
        this.idra = idra;
        this.idQ = idQ;
        this.rep = rep;
    }

    public int getIdra() {
        return idra;
    }

    public void setIdra(int idra) {
        this.idra = idra;
    }

    public int getIdQ() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ = idQ;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }
    
    public static void insertReponse(Connection c,int idQ,String reponse,boolean typeRep)throws Exception {
        Statement sm=c.createStatement();
        sm.executeUpdate("insert into reponse (idq,reponse,typer) values ("+idQ+",'"+reponse+"',"+typeRep+")");
    }
    
    public static void Reponse(Connection c,int idQ,String reponse,String typeRep)throws Exception{
         if(typeRep.hashCode() == "Vrai".hashCode()){
             RepAdmin.insertReponse(c, idQ, reponse, true);
         }else{
             RepAdmin.insertReponse(c, idQ, reponse, false);
         }
    }
    
    public static Vector<RepAdmin> getAllRepByIdQ(Connection c,int idQ)throws Exception{
        Statement sm=c.createStatement();
        Vector<RepAdmin> v=new Vector<RepAdmin>();
        ResultSet res = sm.executeQuery("select * from reponse where idq="+idQ+"");
        while(res.next()){
            v.add(new RepAdmin(res.getInt("idra"),res.getInt("idQ"),res.getString("reponse")));
        }
        return v;
    }
}
