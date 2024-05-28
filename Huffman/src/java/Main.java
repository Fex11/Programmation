
import java.util.Vector;







/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fex
 */
public class Main {
    public static void main(String[] args) throws Exception {
        /*Vector<Noeud> v=new Vector<Noeud>();
        v.add(new Noeud('1',0.3,null,null));
        v.add(new Noeud('2',0.2,null,null));
        v.add(new Noeud('3',0.15,null,null));
        v.add(new Noeud('4',0.15,null,null));
        v.add(new Noeud('5',0.1,null,null));
        v.add(new Noeud('6',0.1,null,null));*/
        /*String pathFile="D:\\texte.txt";
        String compressedFile="D:\\output.txt";
        String texte=Noeud.lire(pathFile);
        Vector<Noeud> v=Noeud.getProba(texte);
        Noeud racine=Noeud.getRacineArbre(v);
        Vector<Noeud> code=new Vector<Noeud>();
        Noeud.getCode(racine, code);
        String texteCodé=Noeud.codage(texte, code);
        int inc=Noeud.ecriture(compressedFile , texteCodé);
        String texteDecodéBinaire=Noeud.lecture(compressedFile , inc);
        String texteInitial=Noeud.decodage(texteDecodéBinaire , code);
        System.out.println(texteInitial);
        for(int i=0;i<code.size();i++){
            System.out.println("s="+code.get(i).getS());
            System.out.println("p="+code.get(i).getP());
            System.out.println("code="+code.get(i).getCode());
            System.out.println("-----------------");
        }*/
        Noeud.compression("D:\\texte.txt");
        String texte = Noeud.decompression("D:\\huffman\\compressed.txt");
        System.out.println(texte);
        
        
    }
}
