package intelligenceartificielle;
import java.util.ArrayList;




public class BaseDeRegles extends BaseDeDonnees 
{
	
	public BaseDeRegles() 
	{
		super();
		extension = ".bdr";
		// TODO Auto-generated constructor stub
	}
	
	public BaseDeRegles(ArrayList<Regle> pDonnes)
	{
		super(pDonnes);
		extension = ".bdr";
	}

//	public String toString() 
//	{
//
//		String resultat = "";
//		int i;
//		for (i=0;i<donnes.size();i++)
//		{
//			Regle r = donnes.get(i);
//			resultat =  resultat + "\nSi " + traduireCode(r.getIndice(0));
//			int j;
//			for (j=1;j<r.taille();j++)
//			{
//				resultat = resultat + " et " + traduireCode(r.getIndice(j));
//			}
//			
//			resultat = resultat + " alors " + traduireCode(r.getAlors());
//			
//		}
//		return resultat;
//	}
	
	public void addRegle(ArrayList<Integer> pRegle,int alors)
	{
		Regle newRegle = new Regle(pRegle,alors);
		donnes.add(newRegle);
	}
	
         public int chercheRegle(Regle aChercher)
        {
            for(int i=0;i<donnes.size();i++)
            {
                if (donnes.get(i).equals(aChercher))
                {
                    return i;
                }
            }
            return -1;
        }
        
        
        public ArrayList<Integer> chercheAlors(int aChercher)
        {
            ArrayList<Integer> indices = new ArrayList<Integer>();
            for(int i=0;i<donnes.size();i++)
            {
                if (donnes.get(i).getAlors() == aChercher)
                {
                    indices.add(i);
                }
            }
            return indices;
        }


        
    @Override
    public String getExt(){
        return ".bdr";
    }

    @Override
        public String toString (){
            return "Base de Règles";
        }	
}
