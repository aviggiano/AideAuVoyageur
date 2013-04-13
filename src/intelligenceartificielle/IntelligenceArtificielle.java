/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenceartificielle;

import java.util.ArrayList;

/**
 *
 * @author Gateway
 */
public class IntelligenceArtificielle 
{

    /**
     * @param args the command line arguments
     */ 
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        //StartUpWindow startUpWindow = new StartUpWindow();

        // Throw a nice little title page up on the screen first
        //SplashScreen splash = SplashScreen.getInstance("startup.gif", 2000);
        //splash.setVisible(true);

        
        GUI gui = new GUI();
        gui.setVisible(true);
    }
    
    public static void chainageAvant(BaseDeFaits bdf,BaseDeRegles bdr)
    {
    	int i,j;
        boolean conditionVrai = true;
        int numeroChanges = 1;
        ArrayList<Regle> resultat = new ArrayList<Regle>();
        
        while(numeroChanges != 0)
        {
            numeroChanges = 0;
            for (i=0;i<bdr.size();i++)
            {
                if (bdf.chercheFait(bdr.get(i).getAlors()) == -1)
                {
                    conditionVrai = true;
                    for(j=0;j<bdr.get(i).taille();j++)
                    {
                        if(bdf.chercheFait(bdr.get(i).getIndice(j)) == -1)
                        {
                           conditionVrai = false; 
                        }
                    }
                    if(conditionVrai == true)
                    {
                        Regle r = new Regle(bdr.get(i).getAlors(),-1);
                        bdf.addRegle(r);
                        resultat.add(r);
                        numeroChanges++;
                    }
                }
            }
        }
    }
    
    public static boolean chainageArriere(BaseDeFaits bdf,BaseDeRegles bdr,int but)
    {
        boolean conditionVrai = true;
        if(bdf.chercheFait(but) != -1)
        {
            return true;
        }
        
        ArrayList<Integer> indices = bdr.chercheAlors(but);
        for(int i=0;i<indices.size();i++)
        {
            conditionVrai = true;
            for(int j=0;j<bdr.get(indices.get(i)).taille();j++)
            {
                conditionVrai = conditionVrai && chainageArriere(bdf,bdr,bdr.get(indices.get(i)).getIndice(j));
            }
            if(conditionVrai == true)
            {
                return true;
            }
        }
        
        return false;
    }

}
