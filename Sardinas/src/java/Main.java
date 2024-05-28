
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
       Vector<String> v=new Vector<String>();
       v.add("1");
       v.add("00");
       v.add("01");
       v.add("10");
       
      System.out.println(Sardinas.check(v));
       
    }
}
