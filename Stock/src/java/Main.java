
import connexion.Connexion;
import java.sql.Connection;
import java.util.Vector;
import model.Article;
import model.Entree;
import model.Etat;
import model.Magasin;
import model.Sortie;
import model.Unite;

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
       Connection c=Connexion.getConnection();
       //Entree.entree(c,"2023/07/13 21:31:34", 1, 3, 10, 900);
       //Sortie.sortie(c,"2023/07/22 21:31:36", 1, 3, 7);
        c.setAutoCommit(false);
       //Sortie s=Sortie.getById(c, 2);
       //s.valider(c,"2023-02-07 23:06");
       //Sortie[] s=Sortie.getAllSortieNv(c);
       //double[] rep=Entree.getEntreeByDate(c, 1, 1,"2023/02/01 21:31:36");
       //Etat e=Etat.getEtat(c,"2023/02/01 21:31:36","2023/02/03 21:31:36","%","%");
       
       Entree[] ee=Entree.getAllEntreeNv(c);
       Sortie s=Sortie.getById(c, 2);
       s.valider(c,"2024-02-01 21:31");
        //Vector<Entree> reste=Entree.getEntreeRestant(c,1,1);
        c.commit();
        System.out.println(ee.length);
        
    }
}
