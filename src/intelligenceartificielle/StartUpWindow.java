/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenceartificielle;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JWindow;

/**
 *
 * @author Gateway
 */
class StartUpWindow extends JWindow {

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolkit.getScreenSize(); //new Dimension(400,500); 
    
    StartUpWindow() {
        StartUp panel = new StartUp(getClass().getResource("images/startup.png"));
        this.getContentPane().add(panel);
        this.setLocation((int)(screenSize.width/2-panel.getImage().getWidth()/2),(int)(screenSize.height/2-panel.getImage().getHeight()/2));
        this.setSize(new Dimension(panel.getImage().getWidth(), panel.getImage().getHeight()));
        this.setVisible(true);

  
        wait(2000); // do nothing for 2000 miliseconds

        this.setVisible(false);  
    }   
    
    private void wait(int milliseconds){
        for (int j=0; j < milliseconds * 5 * 1E5; j++);
    }
}
