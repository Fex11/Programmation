
import static com.oracle.jrockit.jfr.ContentType.Bytes;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
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
public class Noeud implements Comparable<Noeud>{
    char s;
    double p;
    Noeud gauche;
    Noeud droite;
    String code="";
 
    public Noeud(char s, double p) {
        this.s = s;
        this.p = p;
    }
    
    public Noeud(char s, String code) {
        this.s = s;
        this.code = code;
    }

    public Noeud(char s, double p, Noeud gauche, Noeud droite) {
        this.s = s;
        this.p = p;
        this.gauche = gauche;
        this.droite = droite;
    }
    
    @Override
    public int compareTo(Noeud noeud) {
        return Double.compare(this.p, noeud.p);
    }
    
    public char getS() {
        return s;
    }

    public void setS(char s) {
        this.s = s;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public Noeud getGauche() {
        return gauche;
    }

    public void setGauche(Noeud gauche) {
        this.gauche = gauche;
    }

    public Noeud getDroite() {
        return droite;
    }

    public void setDroite(Noeud droite) {
        this.droite = droite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    
    
    public static Vector<Noeud> getProba(String texte){
        Vector<Noeud> vv=new Vector<Noeud>();
        double longueur=texte.length();
        for(int i=0;i<texte.length();i++){
            char c=texte.charAt(i);
            double count=1;
            boolean exist=false;
            for(int k=0;k<vv.size();k++){
                if(c==vv.get(k).getS()){
                    exist=true;
                    break;
                }
            }
            if(exist==false){
                for(int j=i+1;j<texte.length();j++){
                    if(c==texte.charAt(j)){
                        count++;
                    }
                }
                double proba=count/longueur;
                Noeud noeud=new Noeud(c,proba,null,null);
                vv.add(noeud);
            }
        }
        /*double somme=0;
        for(int i=0;i<vv.size();i++){
            somme=somme+vv.get(i).getP();
        }        
        if(somme==1){
            System.out.println("poinsa");
        }else{
            System.out.println("tsy poinsa");
        }*/
        return vv;
    }
    
    public static Noeud getRacineArbre(Vector<Noeud> v){
        PriorityQueue<Noeud> queue = new PriorityQueue<>(v);
        while (queue.size() > 1) {
            Noeud droite = queue.poll();
            Noeud gauche = queue.poll();
            Noeud nouveau=new Noeud('a',gauche.getP()+droite.getP(), gauche, droite);
            queue.offer(nouveau);
        }
        Noeud reponse = queue.poll();
        return reponse;
    }
    
    public static void getCode(Noeud noeud,Vector<Noeud> v){
        if(noeud.getGauche()!=null){
            noeud.getGauche().setCode(noeud.getCode()+"1");
            getCode(noeud.getGauche(), v);
        }else{
            v.add(noeud);
            return;
        }
        if(noeud.getDroite()!=null){
            noeud.getDroite().setCode(noeud.getCode()+"0");
            getCode(noeud.getDroite(), v);
        }else{
            v.add(noeud);
        }
        //System.out.println(noeud.getGauche().getCode()+"/"+noeud.getGauche().getP());
        //System.out.println(noeud.getDroite().getCode()+"/"+noeud.getDroite().getP());
    } 
    
    public static String codage(String texte,Vector<Noeud> v){
        String reponse="";
        for(int i=0;i<texte.length();i++){
            char s=texte.charAt(i);
            for(int j=0;j<v.size();j++){
                if(s==v.get(j).getS()){
                    reponse=reponse+v.get(j).getCode();
                    break;
                }
            }
        }
        return reponse;
    } 
    
    public static String decodage(String texte,Vector<Noeud> v){
        String reponse="";
        String code="";
        for(int i=0;i<texte.length();i++){
            code=code+texte.charAt(i);
            for(int j=0;j<v.size();j++){
                if(code.hashCode()==v.get(j).getCode().hashCode()){
                    reponse=reponse+v.get(j).getS();
                    code="";
                    break;
                }
            }
        }
        return reponse;
    }
    
    public static int ecriture(String filePath , String texteCodé){   
        int inc=0;

        // Remplir la chaîne binaire avec des zéros à droite
        while (texteCodé.length() % 8 != 0) {
            texteCodé = texteCodé + "0";
            inc++;
        }

        // Calcul du nombre de bytes nécessaires
        int nb = texteCodé.length() / 8;

        // Création du tableau de bytes
        byte[] bytes = new byte[nb];

        // Conversion de la chaîne binaire en tableau de bytes
        for (int i = 0; i < nb; i++) {
            String oneByte = texteCodé.substring(i * 8, (i + 1) * 8);
            byte byteValue = (byte) Integer.parseInt(oneByte, 2);
            bytes[i] = byteValue;
        }

        // Écriture des bytes dans un fichier
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inc;
    }
    
    public static String lecture(String filePath , int inc){
        String reponse="";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            
            StringBuilder binaryStringBuilder = new StringBuilder();
            int byteValue;
            
            while ((byteValue = fis.read()) != -1) { // Lire chaque byte du fichier
                String binaryString = Integer.toBinaryString(byteValue & 0xFF); // Convertir en binaire
                
                // Assurer une longueur de 8 caractères
                while (binaryString.length() < 8) {
                    binaryString = "0" + binaryString;
                }
                
                binaryStringBuilder.append(binaryString); // Ajouter à la chaîne binaire complète
            }
            reponse=binaryStringBuilder.toString();
         
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reponse.substring(0,reponse.length()-inc);
    }
    
    public static String lire(String filePath){  
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }  
    
    
    public static void compression(String pathFile){
        String compressedFile="D:\\huffman\\compressed.txt";
        String texte=Noeud.lire(pathFile);
        Vector<Noeud> v=Noeud.getProba(texte);
        Noeud racine=Noeud.getRacineArbre(v);
        Vector<Noeud> code=new Vector<Noeud>();
        Noeud.getCode(racine, code);
        String texteCodé=Noeud.codage(texte, code);
        int inc=Noeud.ecriture(compressedFile , texteCodé);
        writeCode(inc, code);
        double entro=entropie(v);
        double lm=longueurMoyenne(code);
        double min=entro/(Math.log(2) / Math.log(2));
        double max=(entro/(Math.log(2) / Math.log(2)))+1;
        
        System.out.println("Min="+min);
        System.out.println("lm="+lm);
        System.out.println("Max="+max);
        if(min<=lm && lm<=max){
            System.out.println("optimale");
        }
    }
    
    public static String decompression(String pathFile){
        int inc = 0;
        Vector<Noeud> code=new Vector<Noeud>();
        readCode(0, code);
        String texteDecodéBinaire=Noeud.lecture(pathFile , inc);
        String texteInitial=Noeud.decodage(texteDecodéBinaire , code);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\huffman\\decompressed.txt"))) {
            writer.write(texteInitial);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
        return texteInitial;
    }
    
    public static void writeCode(int inc,Vector<Noeud> code){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\huffman\\dico.txt"))) {
            writer.write(Integer.toString(inc));
            writer.newLine();
            for (int i=0;i<code.size();i++) {
                String line=code.get(i).getS()+"fex"+code.get(i).getCode();
                writer.write(line);
                writer.newLine(); // Ajoute une nouvelle ligne après chaque chaîne
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
    
    public static void readCode(int inc,Vector<Noeud> code){
        String filename = "D:\\huffman\\dico.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.length()==1){
                    inc = Integer.parseInt(line);
                }
                String[] codes=line.split("fex");
                if(codes.length==2){
                    if(codes[0].length()>0){
                        char c=codes[0].charAt(0);
                        String binaire=codes[1];
                        Noeud noeud=new Noeud(c,binaire);
                        code.add(noeud);
                    }else{
                        char c='\n';
                        String binaire=codes[1];
                        Noeud noeud=new Noeud(c,binaire);
                        code.add(noeud);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
    
    public static double longueurMoyenne(Vector<Noeud> code){
        double rep=0;
        for(int i=0;i<code.size();i++){
            rep=rep+(code.get(i).getP()*code.get(i).getCode().length());
        }
        return rep;
    }
    
    public static double entropie(Vector<Noeud> code){
        double rep=0;
        for(int i=0;i<code.size();i++){
            double temp=(code.get(i).getP())*(Math.log(code.get(i).getP()) / Math.log(2));
            rep=rep+temp;
        }
        return -rep;
    }
}
