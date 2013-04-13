/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenceartificielle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


class StartUp extends JPanel {

    BufferedImage image;
    
   StartUp(String name){
        try{
            image = ImageIO.read(new File(name));
        }catch(Exception a){System.out.println("Impossible de charger l'image.");}
        
    }

    StartUp(URL resource) {
        this(resource.getFile());
    }

    StartUp (ImageIcon imageIcon){
        this(imageIcon.toString());
    }
    StartUp(File f) {
        try{
            image = ImageIO.read(f);
        }catch(Exception a){System.out.println("Impossible de charger l'image.");}
        
    }    
    
    public BufferedImage getImage(){
        return image;
    }

    @Override
    public void paint(Graphics g) {
    g.drawImage(image, 0, 0, null); // 0,0 c'est les coordonnes ou l'image va etre charge
  }


}

