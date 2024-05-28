
import java.util.Vector;
import model.Bv;
import model.Candidat;


public class Main {
     public static void main(String[] args) throws Exception {
        //Vector<String> v=Bv.getStringCandidat();
        Vector<Candidat> c=new Vector<Candidat>();
        for(int i=0;i<v.size();i++){
            c.add(Candidat.getCandidat(v.get(i)));
            //System.out.println(v.get(i));
        }
        for(int i=0;i<c.size();i++){
            System.out.println("num : "+c.get(i).getNumero()+" // nom : "+c.get(i).getNom()+" // antoko : "+c.get(i).getAntoko()+" // vote : "+c.get(i).getVote()+"\n");
        }
        
     }
}
