
import connexion.Connexion;
import java.sql.Connection;
import java.util.Vector;
import model.*;

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
       
       Note note=new Note();
        note.note(c, 10);
        double d=note.calculNote(c);
        System.out.println(d);
    }
}


