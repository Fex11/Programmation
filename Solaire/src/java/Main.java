
import connexion.Connexion;
import importer.ImportCSV;
import java.sql.Connection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import model.Journee;
import model.Luminosite;
import model.Source;

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
       /*Luminosite[] luminosites=new Luminosite[10];
       luminosites[0]=new Luminosite(0,"08:00",2);
       luminosites[1]=new Luminosite(0,"09:00",4);
       luminosites[2]=new Luminosite(0,"10:00",4);
       luminosites[3]=new Luminosite(0,"11:00",4);
       luminosites[4]=new Luminosite(0,"12:00",6);
       luminosites[5]=new Luminosite(0,"13:00",4);
       luminosites[6]=new Luminosite(0,"14:00",3);
       luminosites[7]=new Luminosite(0,"15:00",3);
       luminosites[8]=new Luminosite(0,"16:00",2);
       luminosites[9]=new Luminosite(0,"17:00",2);*/
       //Prevision
       /*Journee j=Journee.coupure(c,"2023-11-27");
       for(int i=0;i<j.getSources().length;i++){
           System.out.println(j.getSources()[i].getNomSource()+" ==> coupure : "+j.getSources()[i].getHeureCoupure()+" , nb_eleve_am : "+j.getSources()[i].getNbEtudiantAM()+" , nb_eleve_pm : "+j.getSources()[i].getNbEtudiantPM()+" , conso : "+j.getSources()[i].getConso());
           for(int k=0;k<j.getSources()[i].getDetails().length;k++){
               System.out.println(j.getSources()[i].getDetails()[k].getHeure()+" , batterie : "+j.getSources()[i].getDetails()[k].getCapaciteBatterieRestant()+" , pui : "+j.getSources()[i].getDetails()[k].getPuissancePanneau());
           }
       }*/
       
       //Journee ray
       Journee journee=Journee.getJournee(c,"2023-12-07");
       journee.getConsoAllSourceByJournee(); 
       LocalTime h=journee.getSources()[0].getHeureCoupure(80.45,journee.getLuminosites());
       System.out.println(h);
       
       //ImportCSV.importer();
      
       
       
    
       
    }
}
