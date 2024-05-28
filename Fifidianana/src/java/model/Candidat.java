/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fex
 */
public class Candidat {
    int numero;
    String nom;
    String antoko;
    int vote;
    double pourc;

    public String getAntoko() {
        return antoko;
    }

    public void setAntoko(String antoko) {
        this.antoko = antoko;
    }
    
    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public double getPourc() {
        return pourc;
    }

    public void setPourc(double pourc) {
        this.pourc = pourc;
    }

    public Candidat(int numero, String nom, String antoko, int vote) {
        this.numero = numero;
        this.nom = nom;
        this.antoko = antoko;
        this.vote = vote;
    }

   

    public static Candidat getCandidat(String s){
        String[] tout=s.split("//");
        if(tout.length==3){
            int numero=Integer.parseInt(tout[0]);
            String nom=tout[1];
            String antoko="";
            int vote=0;
            for(int i=0;i<tout[2].length();i++){
                char a=tout[2].charAt(i);
                if (Character.isDigit(a)) {
                    String nb=""+a;
                    int j=i;
                    while(tout[2].charAt(j+1)!=' '){
                        nb=nb+tout[2].charAt(i+1);
                        j++;
                    }
                    vote=Integer.parseInt(nb);
                    break;
                }else{
                    antoko=antoko+a;
                }
            }
            return new Candidat(numero,nom,antoko,vote);
        }else if(tout[0].hashCode()=="10".hashCode()){
            int numero=Integer.parseInt(tout[0]);
            String nom=tout[1]+tout[2];
            nom=nom.replace('-', ' ');
            String antoko="";
            int vote=0;
            for(int i=0;i<tout[3].length();i++){
                char a=tout[3].charAt(i);
                if (Character.isDigit(a)) {
                    String nb=""+a;
                    int j=i+1;
                    while(tout[3].charAt(j)!=' '){
                        nb=nb+tout[3].charAt(j);
                        j++;
                    }
                    vote=Integer.parseInt(nb);
                    break;
                }else{
                    antoko=antoko+a;
                }
            }
            return new Candidat(numero,nom,antoko,vote);
        }else if(tout[0].hashCode()=="13".hashCode()){
            int numero=Integer.parseInt(tout[0]);
            String nom=tout[1];
            String antokotemp=tout[2]+tout[3];
            String antoko="";
            int vote=0;
            int ind=antokotemp.indexOf("us");
            String nb=""+antokotemp.charAt(ind+2);
            antoko=antokotemp.substring(0, ind+2);
            int j=ind+3;
            while(antokotemp.charAt(j)!=' '){
                nb=nb+tout[3].charAt(j);
                j++;
            }
            vote=Integer.parseInt(nb);
            return new Candidat(numero,nom,antoko,vote);
            
        }
        return new Candidat(0,"","",0);
    }
}
