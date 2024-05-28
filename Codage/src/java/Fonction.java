
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
public class Fonction {
    
    public static String convert(int nb,int base){
        String rep="";
        int reste=1000;
        Vector<Integer> restes=new Vector<Integer>();
        while(nb>base){
            reste=nb%base;
            nb=nb/base;
            restes.add(reste);
        }
        rep=rep+String.valueOf(nb);
        for(int i=restes.size()-1;i>=0;i--){
            rep=rep+restes.get(i).toString();
        }
        return rep;
    }
    
    public static int convertToBase10(int nb,int base){
        String nbs=String.valueOf(nb);
        int rep=0;
        int puissance=nbs.length()-1;
        int reponse=0;
        for(int i=0;i<nbs.length();i++){
            char charr=nbs.charAt(i);
            int chiffre = charr - '0';
            double nombre=Math.pow(base,puissance);
            int nbb=reponse=(int)nombre;
            rep=rep+(chiffre*nbb);
            puissance--;
        }
        return rep;
    }
    
    public static String base2To16(int nb){
        String nbs=String.valueOf(nb);
        int index=nbs.length()-1;
        String concat="";
        Vector<String> rep=new Vector<String>();
        while(index+1>=4){
            int inverse=index-3;
            index=index-4;
            for(int i=0;i<4;i++){
                concat=concat+nbs.charAt(inverse);
                inverse++;
            }
            int binaire=Integer.valueOf(concat);
            concat="";
            int base10=convertToBase10(binaire,2);
            String hexa=toHexa(base10);
            rep.add(hexa);
        }
        if(index>=0){
            int ii=3-index;
            for(int i=0;i<ii;i++){
                concat=concat+"0";
            }
            for(int i=0;i<=index;i++){
                concat=concat+nbs.charAt(i);
            }
            int binaire=Integer.valueOf(concat);
            concat="";
            int base10=convertToBase10(binaire,2);
            String hexa=toHexa(base10);
            rep.add(hexa);
        }
        String repp="";
        for(int i=rep.size()-1;i>=0;i--){
            repp=repp+rep.get(i);
        }
        return repp;
    }
    
    public static String toHexa(double nb){
        if(nb>9){
            double a=nb-10;
            char charr = (char) ('a' + a);
            return String.valueOf(charr);
        }else{
            return String.valueOf(nb);
        }
    }
}
