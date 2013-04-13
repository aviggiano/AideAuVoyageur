package intelligenceartificielle;
import java.util.ArrayList;
import java.util.Collections;


public class Regle 
{
	private ArrayList<Integer> regle;
	private int alors;
	private static final int ALORS_VIDE = -1;
	
	public Regle()
	{
		regle = new ArrayList<Integer>();
		alors = ALORS_VIDE;
		return;
	}
	
	public Regle(ArrayList<Integer> pRegle,int pAlors)
	{
                regle = pRegle;
		alors = pAlors;
		ordonner();
		return;
	}
        
        	public Regle(int pRegle,int pAlors)
	{
		ArrayList<Integer> aux = new ArrayList<Integer>();
                aux.add(pRegle);
                regle = aux;
		alors = pAlors;
		return;
	}
	
	public void ordonner()
	{
		Collections.sort(regle);
	}

	public ArrayList<Integer> getRegle() 
	{
		return regle;
	}

	public void setRegle(ArrayList<Integer> regle) 
	{
		this.regle = regle;
		ordonner();
	}
	
	public void lireRegle(String texte)
	{
		if(regle.isEmpty())
		{
			String[] chiffres = texte.split(" ");
			alors = Integer.parseInt(chiffres[0]);
			int i;
			for(i=1;i<chiffres.length;i++)
			{
				regle.add(Integer.parseInt(chiffres[i]));
			}
			ordonner(); // c'est trop nul de l'ordoner a chaque fois !!!
			return;
		}
		System.out.println("Erreur! Regle deja existente: impossible de lire regle");
	}

	@Override
	public String toString() 
	{
		return "regle=" + regle;
	}
	
	public String transformeString()
	{
		String texte = "";
		if(!regle.isEmpty())
		{
			texte = Integer.toString(alors);
			int i;
			for(i=0;i<regle.size();i++)
			{
				texte = texte + " " + Integer.toString(regle.get(i));
			}
			
		}
		
		return texte;
	}
	
	public int getIndice(int pIndice)
	{
		return regle.get(pIndice);
	}
	
	public int taille()
	{
		return regle.size();
	}

	public int getAlors() 
	{
		return alors;
	}

	public void setAlors(int alors) 
	{
		this.alors = alors;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regle == null) ? 0 : regle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regle other = (Regle) obj;
		if (regle == null) {
			if (other.regle != null)
				return false;
		} else if (!regle.equals(other.regle))
			return false;
		return true;
	}
	
	
	
	
	
}
