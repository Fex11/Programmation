
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Liantsoa
 */
public class Sardinas {
    
    public static Vector<String> residuel(Vector<String> v,String mot){
      Vector<String> rep=new Vector<String>();
      for(int i=0;i<v.size();i++){
          if(v.get(i).startsWith(mot)){
              String suffixe="";
              if(v.get(i).length()==mot.length()){
                  suffixe="mot vide";
              }else{
                  suffixe=v.get(i).substring(mot.length());
              }
              rep.add(suffixe);
          }
      }
      return rep;
    } 
    
    public static Vector<String> quotient(Vector<String> numerateur,Vector<String> denominateur){
        Vector<String> rep=new Vector<String>();
        for(int i=0;i<denominateur.size();i++){
            String mot=denominateur.get(i);
            Vector<String> temp=residuel(numerateur,mot);
            for(int j=0;j<temp.size();j++){
                rep.add(temp.get(j));
            }
        }
        return rep;
    }
    
    public static Vector<String> manalaMotVide(Vector<String> v){
        for(int i=0;i<v.size();i++){
          if(v.get(i).hashCode()=="mot vide".hashCode()){
              v.remove(i);
          }
        }
        return v;
    }
    
    public static boolean checkMotVide(Vector<String> v){
        for(int i=0;i<v.size();i++){
          if(v.get(i).hashCode()=="mot vide".hashCode()){
              return true;
          }
        }
        return false;
    }
    
    public static Vector<String> unionVecteurs(Vector<String> vecteur1, Vector<String> vecteur2) {
        // Création d'un ensemble pour stocker les éléments uniques
        Set<String> ensemble = new HashSet<>();

        // Ajout des éléments du premier vecteur à l'ensemble
        ensemble.addAll(vecteur1);

        // Ajout des éléments du deuxième vecteur à l'ensemble
        ensemble.addAll(vecteur2);

        // Création d'un nouveau vecteur contenant les éléments de l'ensemble
        Vector<String> union = new Vector<>(ensemble);

        return union;
    }
    
    public static boolean compareVecteurs(Vector<String> vecteur1, Vector<String> vecteur2) {
        // Conversion des vecteurs en ensembles pour éliminer les doublons
        Set<String> set1 = new HashSet<>(vecteur1);
        Set<String> set2 = new HashSet<>(vecteur2);

        // Comparaison des ensembles
        return set1.equals(set2);
    }
    
    
    public static boolean check(Vector<String> language){
        Vector<Vector<String>> rep=new Vector<Vector<String>>();
        rep.add(language);
        Vector<String> un=quotient(language, language);
        un=manalaMotVide(un);
        rep.add(un);
        boolean code=false;
        int i=1;
        while(code==false){
            Vector<String> gauche=quotient(rep.get(i),language);
            Vector<String> droite=quotient(language,rep.get(i));
            Vector<String> temp=unionVecteurs(gauche, droite);
            boolean test=checkMotVide(temp);
            if(test==true){
                int mitovy=i+1;
                System.out.println("Mot vide tao amin L"+mitovy);
                return false; 
            }
            for(int j=0;j<rep.size();j++){
                code=compareVecteurs(temp,rep.get(j));
                //System.out.println(temp);
                //System.out.println(rep.get(j));
                if(code==true){
                    int mitovy=i+1;
                    System.out.println("Mitovy ny L"+mitovy+" sy L"+j);
                    break;
                }
            }
            rep.add(temp);
            i++;
        }
        return code;
    }
}
