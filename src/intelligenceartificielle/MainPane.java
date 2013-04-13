package intelligenceartificielle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

public class MainPane extends JPanel implements Scrollable {
    
    // colocar um panel de botoes na esquerda, com janelas que mudam na direita (tudo isso no CENTER)
    

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    private GroupLayout groupLayoutGauche;
    private JButton boutonMachin;
    private JButton boutonChose;
    private JPanel panelGauche;
    private JPanel panelDroite;
    private JSplitPane splitPane;
    private GroupLayout groupLayoutDroite;
    private Component separator;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolkit.getScreenSize(); //new Dimension(400,500); 
    private Font font;
    private JScrollPane panelHaut;
    private JScrollPane panelBas;
    private GroupLayout groupLayoutHaut;
    private Container layoutHaut;
    private JSeparator separatorGaucheDroite;
    private JButton boutonChargerBDF;
    private JButton boutonCreerBDF;
    private JButton boutonModifierBDF;
    private JButton boutonAfficherBDF;
    private JButton boutonChargerBDR;
    private JButton boutonCreerBDR;
    private JButton boutonAfficherBDR;
    private JButton boutonModifierBDR;
    private JLabel labelBDR;
    private JLabel labelBDF;
    private int maxUnitIncrement = 15;

    public MainPane() {
        super(new GridLayout(1,0));
       
        //cree les boutons
        creeLesBoutons();
        //creeLesLabels()
        creeLesLabels();
        //cree les separators
        creeLesSeparators();
        //cree le panel de la gauche et choisit le GroupLayout
        creeLePanelDeLaGaucheEtChoisitLeGroupLayout();
        //met les boutons dans le panel de la gauche
        metLesBoutonsDansLePanelDeLaGauche();
        
        
        //cree le panel de la droite et choisit le Layout
        creeLePanelDeLaDroiteEtChoisitLeLayout();
        
        //cree le panelHaut
        creeLePanelHaut();
        //met les panel Gauche et Droite dans le panelHaut
        metLesPanelGaucheEtDroiteDansLePanelHaut();
        
        //cree le panelBas
        creeLePanelBas();
        
        //met les panel Haut et Bas dans un JSplitPane vertical
        metLesPanelHautEtBasJSplitPaneVertical();
        
        //Add the split pane to this panel.
        add(splitPane);
        
    }



    private void metLesBoutonsDansLePanelDeLaGauche() {
//          groupLayoutGauche.setHorizontalGroup(
//            groupLayoutGauche.createParallelGroup(Alignment.CENTER)
//            .addComponent(labelBDF)
//            .addComponent(boutonChargerBDF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(boutonAfficherBDF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(boutonCreerBDF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(boutonModifierBDF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                  
//            .addComponent(separator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)    
//
//            .addComponent(labelBDR)
//            .addComponent(boutonChargerBDR, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(boutonAfficherBDR, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(boutonCreerBDR, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(boutonModifierBDR, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)                  
//            
//            .addGroup(groupLayoutGauche.createSequentialGroup()  
//        ));
//          groupLayoutGauche.setVerticalGroup(
//            groupLayoutGauche.createParallelGroup(Alignment.LEADING)
//            .addGroup(groupLayoutGauche.createSequentialGroup()
//                .addComponent (labelBDF)
//                .addComponent(boutonChargerBDF)
//                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(boutonAfficherBDF)
//                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(boutonCreerBDF)
//                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(boutonModifierBDF)
//                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)                  
//                
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                  
//                .addComponent(labelBDR)
//                .addComponent(boutonChargerBDR)
//                .addComponent(boutonAfficherBDR)
//                .addComponent(boutonCreerBDR)
//                .addComponent(boutonModifierBDR)
//
//                .addContainerGap(307, Short.MAX_VALUE)
//        ));        
    }

    private void creeLesBoutons() {
        //boutons Base de Faits
        boutonChargerBDF = new JButton("Charger", new ImageIcon(getClass().getResource("images/chargerBDF22.png")));
        boutonCreerBDF = new JButton("Créer",  new ImageIcon(getClass().getResource("images/creerBDF22.png")));
        boutonModifierBDF = new JButton ("Modifier",  new ImageIcon(getClass().getResource("images/modifierBDF22.png")));
        boutonAfficherBDF = new JButton("Afficher",  new ImageIcon(getClass().getResource("images/afficherBDF22.png")));
        //boutons Base de Regles
        boutonChargerBDR = new JButton("Charger", new ImageIcon(getClass().getResource("images/chargerBDR22.png")));
        boutonCreerBDR = new JButton("Créer",  new ImageIcon(getClass().getResource("images/creerBDR22.png")));
        boutonModifierBDR = new JButton ("Modifier",  new ImageIcon(getClass().getResource("images/modifierBDR22.png")));
        boutonAfficherBDR = new JButton("Afficher",  new ImageIcon(getClass().getResource("images/afficherBDR22.png")));
    }

    private void creeLePanelDeLaGaucheEtChoisitLeGroupLayout() {
        panelGauche = new JPanel();
        groupLayoutGauche = new GroupLayout(panelGauche);
        panelGauche.setLayout(groupLayoutGauche);
    }

    private void creeLePanelDeLaDroiteEtChoisitLeLayout() {
        panelDroite = new JPanel();
        //changer si on choisit un autra Layout :
//        groupLayoutDroite = new GroupLayout(panelDroite);
//        panelDroite.setLayout(groupLayoutDroite);
//        groupLayoutDroite.setHorizontalGroup(
//            groupLayoutDroite.createParallelGroup(Alignment.LEADING)
//            .addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
//        );
//        groupLayoutDroite.setVerticalGroup(
//            groupLayoutDroite.createParallelGroup(Alignment.LEADING)
//            .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
//        );
    }


    private void creeLaZoneDeTexte() {
        textArea = new JTextArea("Programme en cours d'execution.",screenSize.height/100, screenSize.width/100);
        font = new Font("Arial", Font.PLAIN, 12);
        textArea.setFont(font);
        textArea.setEditable(false); //l'utilisateur ne peut pas ecrire dans la textArea
        scrollPane = new JScrollPane(textArea);
    }


    private void metLesPanelHautEtBasJSplitPaneVertical() {
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelHaut, panelBas);
        splitPane.setContinuousLayout(true); // le deplacement du DividerLocation est fait de forme continue
        //splitPane.setResizeWeight(0.10); // division en x%
        splitPane.setDividerLocation(320);
        
    }

    private void creeLePanelHaut() {
        panelHaut = new JScrollPane(new JPanel());
        
//        groupLayoutHaut = new GroupLayout(panelHaut);
//        panelHaut.setLayout(groupLayoutHaut);
    }

    private void creeLePanelBas() {
        //cree la zone de texte
        creeLaZoneDeTexte();
        panelBas = scrollPane;
    }

    private void metLesPanelGaucheEtDroiteDansLePanelHaut() {
        
//        groupLayoutHaut.setHorizontalGroup(
//            groupLayoutHaut.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(groupLayoutHaut.createSequentialGroup()
//                .addComponent(panelGauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent (separatorGaucheDroite)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(panelDroite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        groupLayoutHaut.setVerticalGroup(
//            groupLayoutHaut.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(panelGauche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent (separatorGaucheDroite)
//            .addComponent(panelDroite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
    }

    private void creeLesSeparators() {
        separator = new JSeparator();
        separatorGaucheDroite = new JSeparator(JSeparator.VERTICAL);
    }

    private void creeLesLabels() {
        labelBDF = new JLabel("Base de Faits");
        labelBDR = new JLabel("Base de R�gles");
    }

    //methodes publiques 
    
    public void setPane (Container container){
        //panelHaut.removeAll();
        panelHaut.setLayout(new ScrollPaneLayout());
       // panelHaut.setPreferredSize(container.getPreferredSize());
        panelHaut.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelHaut.getViewport().setView( container );
        //panelHaut.add(container);
        panelHaut.validate();// inutile?
        panelHaut.repaint();
    }
    
    public void append (String textArea){
        this.textArea.append(textArea);
    }


    public Dimension getPreferredScrollableViewportSize() {
        return super.getPreferredSize();
    }
 
    public int getScrollableUnitIncrement(Rectangle visibleRect,
                                          int orientation,
                                          int direction) {
        //Get the current position.
        int currentPosition = 0;
        if (orientation == SwingConstants.HORIZONTAL) {
            currentPosition = visibleRect.x;
        } else {
            currentPosition = visibleRect.y;
        }
 
        //Return the number of pixels between currentPosition
        //and the nearest tick mark in the indicated direction.
        if (direction < 0) {
            int newPosition = currentPosition -
                             (currentPosition / maxUnitIncrement)
                              * maxUnitIncrement;
            return (newPosition == 0) ? maxUnitIncrement : newPosition;
        } else {
            return ((currentPosition / maxUnitIncrement) + 1)
                   * maxUnitIncrement
                   - currentPosition;
        }
    }
 
    public int getScrollableBlockIncrement(Rectangle visibleRect,
                                           int orientation,
                                           int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return visibleRect.width - maxUnitIncrement;
        } else {
            return visibleRect.height - maxUnitIncrement;
        }
    }
 
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }
 
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
 
    public void setMaxUnitIncrement(int pixels) {
        maxUnitIncrement = pixels;
    }

    public Container getPane(){
        Container p = (Container)(panelHaut.getViewport());
        return p;
    }
}
