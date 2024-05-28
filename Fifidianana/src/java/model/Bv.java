/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author fex
 */
public class Bv {
  


    public static String getFichier() throws Exception{
        // Spécifiez le chemin du fichier texte
        String cheminFichier = "E:/donee/310305280101.pdf.txt";

        File fichier = new File(cheminFichier);

        try {
            FileReader lecteurFichier = new FileReader(fichier);
            BufferedReader lecteurBufferise = new BufferedReader(lecteurFichier);
            String rep="";
            String ligne;
            while ((ligne = lecteurBufferise.readLine()) != null) {
                rep=rep+"//"+ligne;
            }
            lecteurBufferise.close();
            return rep;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "tsy mety";
    }
    
    public static String get(String chaine, String debut, String fin) {
        int debutIndex = chaine.indexOf(debut);
        int finIndex = chaine.indexOf(fin);

        if (debutIndex != -1 && finIndex != -1) {
            return chaine.substring(debutIndex+6, finIndex);
        } else {
            return null;
        }
    }
    
    public static Vector<String> getStringCandidat()throws Exception{
        String s=Bv.test();
         Vector<String> v=new Vector<String>();
         for(int i=1;i<13;i++){
              String debut="//"+i+"//";
              int j=i+1;
              String fin="//"+j+"//";
              String res=Bv.get(s,debut,fin);
              res=i+"//"+res;
              v.add(res);
         }
         int ind=s.indexOf("//13//");
         String siteny=s.substring(ind+6);
         siteny=13+"//"+siteny;
         v.add(siteny);
         return v;
    }
    
}
