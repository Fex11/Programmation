
import connexion.Connexion;
import java.sql.Connection;
import java.util.Vector;
import model.*;
import static model.Poketra.getStock;

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
         System.out.println(c);
    }
    
}
