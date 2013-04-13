package intelligenceartificielle;
import java.util.ArrayList;


public class BaseDeFaits extends BaseDeDonnees 
{
	private static final int ALORS_VIDE = -1;
	
	
	public BaseDeFaits() 
	{
		super();
		extension = ".bdf";
		// TODO Auto-generated constructor stub
	}
	
	public BaseDeFaits(ArrayList<Regle> pDonnes,ArrayList<Traduction> pTrad)
	{
		super(pDonnes);
		extension = ".bdf";
	}
	
	public void addRegle(ArrayList<Integer> pRegle)
	{
		Regle newRegle = new Regle(pRegle,ALORS_VIDE);
		donnes.add(newRegle);
	}
	
//	public String toString() 
//	{
//
//		String resultat = "";
//		int i;
//		for (i=0;i<donnes.size();i++)
//		{
//			Regle r = donnes.get(i);
//			resultat =  resultat + "\n" + traduireCode(r.getIndice(0));
//			int j;
//			for (j=1;j<r.taille();j++)
//			{
//				resultat = resultat + " et " + traduireCode(r.getIndice(j));
//			}
//			
//		}
//		return resultat;
//	}

        public int chercheFait(int aChercher)
        {
            for(int i=0;i<donnes.size();i++)
            {
                if (donnes.get(i).getIndice(0) == aChercher)
                {
                    return i;
                }
            }
            return -1;
        }

    @Override
    public String getExt(){
        return ".bdf";
    }

    @Override
        public String toString (){
            return "Base de Faits";
        }

}
