
package intelligenceartificielle;

import java.io.*;
import java.util.ArrayList;



public class Codage 
{
    	
    public static final String EXTENSION_TRADUCTION = ".trd";
    public static final String FILENAME_TRADUCTION = "traduction";
    public static final String REPERTOIRE_TRADUCTION = System.getProperty("user.dir");
    
    protected ArrayList<Traduction> traduction;
        
        public String traduireCode(int pCode)
	{
		int i;
		for(i=0;i<traduction.size();i++)
		{
			if(traduction.get(i).code == pCode)
			{
				return traduction.get(i).mot;
			}
		}
		return "erreur";
	}
        
        
        
        public int traduireMot(String pMot,boolean permission,boolean estVille)
        {
            	int i;
		for(i=0;i<traduction.size();i++)
		{
			if(traduction.get(i).mot.equals(pMot))
			{
				return traduction.get(i).code;
			}
		}
		
                // si la traduction n'est pas trouve
                if (permission == false) // pas de permission d'ecriture
                {
                    return 000000;
                }
                else // permission d'ecriture
                {
                    int newCode;
                    i--; // derniere position
                    Traduction aux = new Traduction();
                    
                    if (estVille == false) 
                    {
                        if ((traduction.get(i).code + 1) % 10 == 0) 
                        {
                            newCode = traduction.get(i).code + 2;
                        } 
                        else 
                        {
                            newCode = traduction.get(i).code + 1;
                        }
                    }
                    
                    else // est une Ville
                    {
                        if ((traduction.get(i).code + 1) % 10 == 0) 
                        {
                            newCode = traduction.get(i).code + 1;
                        } 
                        else 
                        {
                            newCode = traduction.get(i).code + 10 - traduction.get(i).code % 10;
                        } 
                    }
                    

                    aux.code = newCode;
                    aux.mot = pMot;
                    
                    traduction.add(aux);
                    return newCode;
                }
                
        }
        
        public ArrayList<Traduction> getCodage() 
	{
		return traduction;
	}

	public void setCodage(ArrayList<Traduction> traduction) 
	{
		this.traduction = traduction;
	}
        
        
    public void readTraduction() 
    {
        File repertoire = new File(REPERTOIRE_TRADUCTION); // cr�ation du repertoire
        File fichier = new File(repertoire, FILENAME_TRADUCTION + EXTENSION_TRADUCTION); //cr�ation du fichier
        traduction = new ArrayList<Traduction>();
        try {
            FileReader fileReader = new FileReader(fichier);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne = "";
            while ((ligne = bufferedReader.readLine()) != null) //tant que la ligne est pas nulle
            {
                Traduction aux = new Traduction();
                aux.lireTraduction(ligne);
                traduction.add(aux);
                aux = null;
            }
            fileReader.close();
            bufferedReader.close();
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
    
    	public void saveTraduction()
	{
		File repertoire = new File(REPERTOIRE_TRADUCTION); // cr�ation du repertoire
		File fichier = new File(repertoire,FILENAME_TRADUCTION+EXTENSION_TRADUCTION); //cr�ation du fichier
		
		try
		{
			fichier.createNewFile(); // creation du fichier dans le dossier
			FileWriter fileWriter = new FileWriter(fichier, false); // false = souscrire si fichier existant
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for (int i=0;i<traduction.size();i++) //parcourir le Arraylist
			{
				Traduction aux = traduction.get(i);
				printWriter.println(aux.transformeString()); // imprime une ligne dans le fichier
			}
			
			// quando terminar:
			printWriter.flush(); // permet l ecriture dans le fichier
			printWriter.close(); // ferme le fichier
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}


	}
        
        public Codage()
        {
            readTraduction();        
        }

    int size() {
        return traduction.size();
    }

    int getCode(int i) {
        return traduction.get(i).code;
    }
    
    public ArrayList<String> getToutesLesMots() {
         ArrayList<String> s = new  ArrayList<String>();
         s.add(" ");
         for (int i = 0; i < traduction.size(); i++){
             s.add(traduction.get(i).getMot());
         }
         return s;
    }
}
