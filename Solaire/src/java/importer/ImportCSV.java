/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importer;
import connexion.Connexion;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ImportCSV {




    public static void importer() throws Exception{
        // Spécifiez le chemin d'accès du fichier CSV
        String cheminFichierCSV = "E:\\Sujet Donneés S5 - Copie.csv";
        try {
            // Créez un objet Scanner pour lire le fichier CSV
            Scanner scanner = new Scanner(new File(cheminFichierCSV));
            Connection c=Connexion.getConnectionCsv();
            // Parcourez les lignes du fichier CSV
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                // Divisez la ligne en colonnes en utilisant la virgule comme délimiteur
                String[] colonnes = ligne.split(";");
                // Faites quelque chose avec les colonnes (par exemple, imprimez-les)
                if(colonnes.length==12){
                    String date=colonnes[0];
                    String[] dates=date.split("/");
                    date=dates[2]+"-"+dates[1]+"-"+dates[0];
                    Double[] doubles=new Double[10];
                    for(int i=1;i<11;i++){
                        colonnes[i]=colonnes[i].replace(',','.');
                        doubles[i-1]=Double.parseDouble(colonnes[i]);
                    }
                    String heure=colonnes[11];
                    String[] heureLum=new String[11];
                    heureLum[0]="";
                    heureLum[1]="";
                    heureLum[2]="08:00";
                    heureLum[3]="09:00";
                    heureLum[4]="10:00";
                    heureLum[5]="11:00";
                    heureLum[6]="14:00";
                    heureLum[7]="15:00";
                    heureLum[8]="16:00";
                    heureLum[9]="17:00";
                    System.out.println("date :"+date+" , am : "+doubles[0]+" , pm : "+doubles[1]+" , heureCoupure : "+heure);
                    insertCoupure(c, date, heure);
                    insertPresence(c, date, doubles[0], doubles[1]);
                    for(int i=2;i<doubles.length;i++){
                        insertLuminosite(c, date,heureLum[i], doubles[i]);
                    }
                    
                }
            }
            c.close();
            // Fermez le scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertCoupure(Connection c,String date,String heure)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into coupure(daty,idSource,heure) values('"+date+"',1,'"+heure+"')");
    }
    
    public static void insertPresence(Connection c,String date,double am,double pm)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into presence(daty,idSalle,nb_eleve_AM,nb_eleve_PM) values('"+date+"',1,"+am+","+pm+")");
    }
    
    public static void insertLuminosite(Connection c,String date,String heure,double lumi)throws Exception{
        Statement s=c.createStatement();
        s.executeUpdate("insert into luminosite (daty,heure,luminosite) values('"+date+"','"+heure+"',"+lumi+")");
    }
        
        
        
    }


    

