/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenceartificielle;

import java.awt.Desktop;
import java.io.File;
/**
 *
 * @author Gateway
 */
public class OpenPDF {
 
//Cross platform solution to view a PDF file
 
	public OpenPDF() {
 
	  try {
 
                File repertoire = new File(System.getProperty("user.dir"));
		File pdfFile = new File(repertoire, "Manuel dUtilisation - Aide au Voyageur.pdf");
		if (pdfFile.exists()) {
 
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}
 
		} else {
			System.out.println("Le fichier .pdf n'a pas été trouvé !");
		}
 
		//System.out.println("Done");
 
	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
 
	}
}    

