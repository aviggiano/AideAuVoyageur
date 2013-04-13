package intelligenceartificielle;
import java.util.ArrayList;
import java.io.*;

public abstract class BaseDeDonnees 
{

	protected ArrayList<Regle> donnes;
	protected String fileName;
	protected String dir;
	protected String extension;

	

	//Constructor
	protected BaseDeDonnees()
	{
		donnes = new ArrayList<Regle>();
	}
	
	protected BaseDeDonnees(ArrayList<Regle> pDonnes)
	{
		donnes = pDonnes;
	}
	
	

	
	public void addRegle(Regle pRegle)
	{
		donnes.add(pRegle);
	}



	public String getDir() 
	{
		return dir;
	}

	public void setDir(String dir) 
	{
		this.dir = dir;
	}
	
	public void saveData() 
	{
		File repertoire = new File(dir); // cr�ation du repertoire
		File fichier = new File(repertoire,fileName+extension); //cr�ation du fichier

                saveData(fichier);
	}
        
	public void saveData(File fichier) 
	{
		try
		{
			fichier.createNewFile(); // creation du fichier dans le dossier
			FileWriter fileWriter = new FileWriter(fichier, false); // false = souscrire si fichier existant
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for (int i=0;i<donnes.size();i++) //parcourir le Arraylist
			{
				Regle aux = donnes.get(i);
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
	        
        
	public void readData() throws FileNotFoundException
	{
		File repertoire = new File(dir); // cr�ation du repertoire
		File f = new File(repertoire,fileName+extension); //cr�ation du fichier
                RandomAccessFile fichier = new java.io.RandomAccessFile(f, "r"); 
		
		try
		{
			
			String ligne = "";
            while ((ligne = fichier.readLine() ) != null) //tant que la ligne est pas nulle
            {
                Regle aux = new Regle();
                aux.lireRegle(ligne);
                donnes.add(aux);
            }
            
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
        
        public void readData(File f)
	{
            donnes = new ArrayList<Regle>();
	try
	{
            RandomAccessFile fichier = new java.io.RandomAccessFile(f, "r");
			
            String ligne = "";
            while ((ligne = fichier.readLine() ) != null) //tant que la ligne est pas nulle
            {
                Regle aux = new Regle();
                aux.lireRegle(ligne);
                donnes.add(aux);
            }
            
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String getFileName() 
	{
		return fileName;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public void deleteRegle(int indice)
	{
		donnes.remove(indice);
	}
	
        public int size()
        {
            return donnes.size();
        }
	
        public Regle get(int indice)
        {
            return donnes.get(indice);
        }
	
        public abstract String getExt();
        
        public ArrayList<Regle> getRegles(){
            return donnes;
        }
        

}
