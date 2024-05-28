
import sun.misc.FpUtils;





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
        String rep=Fonction.convert(133, 16);
        double repp=Fonction.convertToBase10(85,16);
        String reppp=Fonction.base2To16(1011010011);
        System.out.println(reppp);
    }
    
}
