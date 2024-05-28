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
public class Reponse {
    int idReponse;
    String Reponse;
    boolean type;

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Reponse(int idReponse, String Reponse, boolean type) {
        this.idReponse = idReponse;
        this.Reponse = Reponse;
        this.type = type;
    }
    
    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

    public Reponse(int idReponse) {
        this.idReponse = idReponse;
    }
    
    
    public static void insertReponse(Connection c,String[] s,int idc)throws Exception{
        String[][] str=new String[s.length][2];
        int[] idq=new int[s.length];
        int[] idr=new int[s.length];
        for(int i=0;i<s.length;i++){
            str[i]=s[i].split("y");
            idq[i]=Integer.parseInt(str[i][0]);
            idr[i]=Integer.parseInt(str[i][1]);
            insertReponse(c, idc, idq[i], idr[i]);
        }
    }
    public static void insertReponse(Connection c,int idc,int idq,int idr)throws Exception{
         Statement s=c.createStatement();
         s.executeUpdate("insert into reponseClient values("+idc+","+idr+","+idq+")");
    }
    
    public static Vector<Reponse> getReponseClientByIdq(Connection c,int idc,int idq)throws Exception{
        Statement s=c.createStatement();
        Vector<Reponse> v=new Vector<Reponse>();
        ResultSet res = s.executeQuery("select*from reponseClient where idc="+idc+" and idq="+idq);
            while(res.next()){
            v.add(new Reponse(res.getInt("idr")));
        }
        return v;
    }
    
    public static int noteReponseClientByIdq(Connection c,int idc,Question q)throws Exception{
        Vector<Reponse> r=getReponseClientByIdq(c, idc, q.getIdQuestion());
        Vector<Integer> idr=new Vector<Integer>(); 
        for(int i=0;i<q.getReponse().size();i++){
            if(q.getReponse().get(i).isType()==true){
                idr.add(q.getReponse().get(i).getIdReponse());
            }
        }
        if(idr.size()!=r.size()){
            return 0;
        }else{
            int nb=0;
            for(int i=0;i<idr.size();i++){
                for(int j=0;j<idr.size();j++){
                    if(idr.get(i)==r.get(i).getIdReponse()){
                        nb++;
                        break;
                    }
                }
            }
            if(nb==idr.size()){
                return 1;
            }
            return 0;
        }
    }
    
    public static void noteClient(Connection c,Cv cv) throws Exception{
        Vector<Question> q=Question.getAllQuestionByAnnonce(c, cv.getAnnonce());
        int note=0;
        for(int i=0;i<q.size();i++){
            q.get(i).getReponseByQuestion(c);
            int n=noteReponseClientByIdq(c, cv.getIdClient(), q.get(i));
            note=note+n;
        }
        Cv.finTest(c, cv.getIdCv(), cv.getAnnonce(), note);
    }
    
}
