/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenceartificielle;

/**
 *
 * @author Gateway
 */

public class UserException extends Exception {
    /**
     *
     */
    public final int ERREUR_INCONNU=0;

    public final int ERREUR_BDF = 1;
    private final int error;
    /**
     *
     * @param i type de l'erreur
     */
    public UserException(int i){
       super();
       error=i;


   }
    /**
     *
     * @return l'erreur
     */
    public int geterror(){
       return error;
   }

   public String toString (){
       if (error==ERREUR_BDF)
           return "Impossible d'afficher la Base de Faits.";
       else return "";
   }

}