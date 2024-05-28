package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author PC
 */
public class Grade {
    int id;
    int experience;
    int augmentation;
    String designation;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getAugmentation() {
        return augmentation;
    }
    public void setAugmentation(int augmentation) {
        this.augmentation = augmentation;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public Grade(int id, int experience, int augmentation, String designation) {
        this.id = id;
        this.experience = experience;
        this.augmentation = augmentation;
        this.designation = designation;
    }

    public void insert(Connection c)throws Exception { 
        String query = "INSERT INTO grade(experience,augmentation,designation)  VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1, experience);
            preparedStatement.setInt(2, augmentation);
            preparedStatement.setString(3, designation);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Grade [] getAll(Connection c)throws Exception {
        Statement sm=c.createStatement();
        Vector<Grade> v=new Vector<Grade>();
        ResultSet res = sm.executeQuery("select * from Grade order by id");
        while(res.next()){
            int id=res.getInt(1);
            int experience=res.getInt(2);
            int augmentation=res.getInt(3);
            String designation= res.getString(4);
            v.add(new Grade(id,experience,augmentation,designation));
        }
        return v.toArray(new Grade[v.size()]); 
    }
    public static void updateById (Connection c, int id,int augmentation){
        String query = "update grade set augmentation=? where id =?";
        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setInt(1, augmentation);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Grade getLast(Connection c)throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select * from Grade order by experience desc");
        while(res.next()){
            return new Grade(res.getInt("id"),res.getInt("experience"),res.getInt("augmentation"),res.getString("designation"));
        }
        return null; 
    }
    
    public static void inserer(Connection c,String desi,int exp,int aug)throws Exception{
        Grade grade=new Grade(0,exp,aug,desi);
        Grade last=getLast(c);
        if(last==null){
            grade.insert(c);
        }else{
           if(grade.getExperience()>last.getExperience() && grade.getAugmentation()>last.getAugmentation()){
               grade.insert(c);
           }else{
               String erreur="L'augmentation doit etre superieur a "+last.getAugmentation()+" et l'experience a "+last.getExperience();
               throw new Exception(erreur);
           }
        }
    }
}
