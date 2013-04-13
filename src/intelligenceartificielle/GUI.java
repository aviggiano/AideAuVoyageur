/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenceartificielle;

//import intelligenceartificielle.menuDeletePopUp;
//import components.SplitPaneAI;

//import components.SplitPaneAI;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import sun.security.x509.X400Address;
/**
 *
 * @author Gateway
 */
public class GUI extends JFrame implements ActionListener, MouseListener{
    // variables de la GUI
    String[] lookAndFeel = {"Windows", "Nimbus", "Motif", "Ocean"}; // look and feel "Windows". essayez "Nimbus", "Steel", "Ocean", etc.private SplitPaneAI splitPaneAI;
    private JFileChooser fileChooser;
    private FileFilterIA fileFilter; 
    
    private JMenuItem MIAbout;
    private JMenuItem MIManuel;
    private JMenuItem MIDeletePopUp;
    private JMenuItem MIEditPopUp;
    
    private JMenuBar barreDeMenus;
  
    private JMenu menuHelp;
    
    private JPopupMenu popUpMenu;
    private boolean enable = true;
    protected JTextArea textArea;
    protected JScrollPane scrollPane;
    protected Toolkit toolkit = Toolkit.getDefaultToolkit();
    protected Dimension screenSize = toolkit.getScreenSize(); //new Dimension(400,500); 
    protected Dimension frameSize;
    private JToolBar toolBar;
    private boolean ok;
    private MainPane mainPane;
    private JButton boutonChainageAvant;
    private JButton boutonChainageArriere;
    private JRadioButtonMenuItem MIWindowsLAF;
    private JRadioButtonMenuItem MINimbusLAF;
    private JRadioButtonMenuItem MIMetalLAF;
    private JMenu menuLAF;
    private ButtonGroup buttonGroupLAF;
    private JMenuItem MIChargerBDF;
    private JMenuItem MICreerBDF;
    private JMenuItem MIModifierBDF;
    private JMenuItem MIAfficherBDF;
    private JMenuItem MIChargerBDR;
    private JMenuItem MICreerBDR;
    private JMenuItem MIModifierBDR;
    private JMenuItem MIAfficherBDR;
    private JMenu menuBDF;
    private JMenu menuBDR;
    private JButton boutonRechercheAvancee;
    private JButton boutonRechercheRapide;
    private BaseDeRegles bdr;
    private BaseDeFaits bdf;
    private Codage codage;
    
    private boolean chargeeBDF = false;
    private boolean chargeeBDR = false;
    private JComboBox comboBoxAge;
    private JComboBox comboBoxCivilite;
    private JComboBox comboBoxEnfants;
    private JComboBox comboBoxPersonalite;
    private JButton boutonSaveBDF;
    private JButton boutonCancelBDF;
    private ArrayList<JComboBox> comboBoxChoixBDF;
    private ArrayList<JComboBox> comboBoxChoixBDR;
    private JComboBox comboBoxClimat;
    private JComboBox comboBoxBudget;
    private JComboBox comboBoxTouristique;
    private JComboBox comboBoxOptions;
    private PanelAux panelAux = new PanelAux();
    private JPanel panelPlusInfos;
    
    private int creeOuModifieBDF;
    private final int CREE = 0;
    private final int MODIFIE = 1;
    private  ArrayList<JComboBox> listOfComboBoxes = new ArrayList<JComboBox>();
    private boolean ctrl;
    private boolean nouvelleCreation = false;
    private JButton boutonSaveBDR;
    private JButton boutonCancelBDR;
    private PaneRegles paneRegles;
    private int creeOuModifieBDR;
    private int[] answerBDR;
    private JMenuItem MISave;
    private JMenuItem MISaveAs;
    private JMenu menuFile;
    private JMenuItem MIClose;
    private boolean chargeeDunAutreFichier = false;
    private boolean premiereFoisNumParametresSupplementaires = true;
    private int numParametresSupplementaires = 10;

    /*
     * Constructor
     */    
    public GUI (){
        
        try {
 
            //StartUP
            loadingScreen();
            //set title, size and location
            setTitleSizeAndLocation();
            //set look and feel
            setLookAndFeel(lookAndFeel[0]); 
            //prend le container du JFrame
            Container container = getContentPane();

            //on cree les differentes parties du conteneur
                //partie NORTH
            creePartieNORTH();
                //partie CENTER
            creePartieCENTER();
               
            //racemblement des parties
            //met tout ca dans le conteneur avec un BorderLayout
            metToutCaDansLeConteneurAvecUnBorderLayout(container);
            //demarre les BDF et BDR
            demarrer();            
        }
//        catch (UserException ex) {
//                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
        catch (Exception e){}
            //fileChooser = new JFileChooser(baseDeDonnees.getDir());
            fileChooser = new JFileChooser("."); // current dir == "."
            
            fileFilter = new FileFilterIA();
            fileChooser.addChoosableFileFilter(new FileFilterIA( new String[] {".bdf"}, "Base de Faits (*.bdf)"));
            fileChooser.addChoosableFileFilter(new FileFilterIA( new String[] { ".bdr" }, "Base de Règles (*.bdr)"));
            fileChooser.setAcceptAllFileFilterUsed(true);
    }
    
    
    
    
    
    // methodes qui traitent les actions de click de la souris
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Object source = e.getSource();
        
        ok=true;

        if (command.equals("Windows Look & Feel")) {
            setLookAndFeel(lookAndFeel[0]);
            SwingUtilities.updateComponentTreeUI(this); 
        }
        
        if (command.equals("Fermer")){
            codage.saveTraduction();
            System.exit(0);            
        }
        
        if (e.getSource().equals(MISave)){
            saveBaseDeDonnees("base de faits", bdf);
            saveBaseDeDonnees("base de regles", bdr);
        }
        
        if (e.getSource().equals(MISaveAs)){
            saveAsBDF();
            saveAsBDR();
        }        

        if (command.equals("Nimbus Look & Feel")){
            setLookAndFeel(lookAndFeel[1]);
            SwingUtilities.updateComponentTreeUI(this); 
        }

        if (command.equals("Metal Look & Feel")){
            setLookAndFeel("Metal");
            SwingUtilities.updateComponentTreeUI(this);
        }
        
        if (command.equals("About")){
            about();
        }
        if (e.getSource().equals(MIManuel)){
            OpenPDF openPDF = new OpenPDF();
        }
        
        // MenuItem BDF
        if (e.getSource().equals(MIChargerBDF)){
            mainPane.append("\nChoisissez votre Base de Faits");
            chargerBDF();
            try {
                
                afficherBDF();
            } catch (UserException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (e.getSource().equals(MIAfficherBDF)){
            try{
                afficherBDF();
            }
            catch (UserException exc) {
                mainPane.append(exc.toString());
            }
        }
        
        if (e.getSource().equals(MICreerBDF)){
            creerBDF();
            nouvelleCreation = true;
        }
        
        if (e.getSource().equals(MIModifierBDF)){
                modifierBDF();
        }

         // MenuItem BDR
        if (e.getSource().equals(MIChargerBDR)){
            mainPane.append("\nChoisissez votre Base de Règles");
            chargerBDR();
        }
        
        if (e.getSource().equals(MIAfficherBDR)){
            afficherBDR();
        }
        
        if (e.getSource().equals(MICreerBDR)){
            creerBDR();
        }
        
        if (e.getSource().equals(MIModifierBDR)){
            modifierBDR();
        }
        
        // Boutons Chainage Avant et Arriere
        if (e.getSource().equals(boutonChainageAvant)){
            chainageAvant();
        }
        
        if (e.getSource().equals(boutonChainageArriere)){
            chainageArriere();
        }        
        
        if (e.getSource().equals(boutonSaveBDF)){
            saveBDF();
            try {
                afficherBDF();
            } catch (UserException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        if (e.getSource().equals(boutonCancelBDF)){
            cancelBDF();
        }        

        if (e.getSource().equals(boutonSaveBDR)){
            saveBDR();
            afficherBDR();
        }

        
        if (e.getSource().equals(boutonCancelBDR)){
            cancelBDR();
        }               
      
        if   (e.getSource().equals(panelAux.jButton1)){
            plusDInfos(panelPlusInfos);
            
                premiereFoisNumParametresSupplementaires = false; // ????????
        }
        
        
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    private void setLookAndFeel(String nomDuLookAndFeel) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (nomDuLookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            mainPane.append(e.toString());
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void creeMenuAvecIcones() {
        //on doit mettre a chaque fois getClass().getRessource(URL) pour construire le JAR
        //si on ne met que ImageIcon(URL) ca ne marche pas
        MISave = new JMenuItem("Enregistrer", new ImageIcon(getClass().getResource("images/save16.gif")));
        MISave.setToolTipText("Enregistre les bases de données");
        MISave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        MISaveAs = new JMenuItem("Enregistrer sous...", new ImageIcon(getClass().getResource("images/SaveAs16.gif")));
        MISaveAs.setToolTipText("Enregistre les bases de données avec un nom quelconque");
//        MIOpen = new JMenuItem("Ouvrir", new ImageIcon(getClass().getResource("images/open16.png")));
//        MIOpen.setToolTipText("Carrega um banco de dados");
//        MIOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
//        MIImport = new JMenuItem("Importer", new ImageIcon(getClass().getResource("images/load16.gif")));
//        MIImport.setToolTipText("Insere uma nova entrada");
//        MIImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
//        MIDelete = new JMenuItem("Effacer", new ImageIcon(getClass().getResource("images/delete16.gif")));
//        MIDelete.setToolTipText("Deleta uma entrada antiga");
//        MIDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
//        MINew = new JMenuItem("Nouveau", new ImageIcon(getClass().getResource("images/new16.png")));
//        MINew.setToolTipText("Cria um novo banco de dados");
//        MINew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
//        MIFind = new JMenuItem("Find", new ImageIcon(getClass().getResource("images/search16.png")));
//        MIFind.setToolTipText("Busca uma entrada");
//        MIFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
        MIAbout = new JMenuItem("About", new ImageIcon(getClass().getResource("images/help16.png")));
        MIManuel = new JMenuItem ("Manuel de l'Utilisateur", new ImageIcon(getClass().getResource("images/pdf16.gif")));
        MIClose = new JMenuItem("Fermer");

        //pour le LookAndFeel
        MIWindowsLAF = new JRadioButtonMenuItem("Windows Look & Feel");
            MIWindowsLAF.setSelected(true); //cette methode peut etre ameilleure (si on initialize le lookAndFeel comme qqch d'autre que le [0], ca va cocher toujours le Windows LAF
        MINimbusLAF = new JRadioButtonMenuItem("Nimbus Look & Feel");
        MIMetalLAF = new JRadioButtonMenuItem("Metal Look & Feel");
            //on les met dans un ButtonGroup (on ne peut choisir qu'un LAF a la fois)
            buttonGroupLAF = new ButtonGroup();
            buttonGroupLAF.add(MIWindowsLAF);
            buttonGroupLAF.add(MINimbusLAF);
            buttonGroupLAF.add(MIMetalLAF);  
            
        //nouvelles directrices
        //menu itens Base de Faits
        MIChargerBDF = new JMenuItem("Charger", new ImageIcon(getClass().getResource("images/chargerBDF22.png")));
        MICreerBDF = new JMenuItem("Créer",  new ImageIcon(getClass().getResource("images/creerBDF22.png")));
        MIModifierBDF = new JMenuItem ("Modifier",  new ImageIcon(getClass().getResource("images/modifierBDF22.png")));
        MIAfficherBDF = new JMenuItem("Afficher",  new ImageIcon(getClass().getResource("images/afficherBDF22.png")));
        //menu itens Base de Regles
        MIChargerBDR = new JMenuItem("Charger", new ImageIcon(getClass().getResource("images/chargerBDR22.png")));
        MICreerBDR = new JMenuItem("Créer",  new ImageIcon(getClass().getResource("images/creerBDR22.png")));
        MIModifierBDR = new JMenuItem ("Modifier",  new ImageIcon(getClass().getResource("images/modifierBDR22.png")));
        MIAfficherBDR = new JMenuItem("Afficher",  new ImageIcon(getClass().getResource("images/afficherBDR22.png")));        
        
            
        //implementer apres
        MIEditPopUp = new JMenuItem("Edit");
        MIDeletePopUp = new JMenuItem("Delet");
        MIDeletePopUp.setActionCommand("Deletem");
        
        creeLesMenus();
    }

    private void creeBoutonsAvecIcones() {
        //boutons Chainage Avant et Arriere
        boutonChainageAvant = new JButton("Chainage Avant", new ImageIcon(getClass().getResource("images/chainageAvant22.png")));
        boutonChainageArriere = new JButton("Chainage Arrière", new ImageIcon(getClass().getResource("images/chainageArriere22.png")));
        
        //boutons Recherche Rapide et Recherche Avancee
        boutonRechercheRapide = new JButton("Recherche Rapide", new ImageIcon(getClass().getResource("images/s2.png")));
        boutonRechercheAvancee = new JButton("Recherche Avancée", new ImageIcon(getClass().getResource("images/s2.png"))); 
    }

    private void metLesMenusDansLeurPlace() {
        //menu File
        menuFile.add(MISave);
        menuFile.add(MISaveAs);
//        menuFile.add(MIOpen);
        menuFile.add(MIClose);
//        //menu Edit
//        menuEdit.add(MIImport);
//        menuEdit.add(MIDelete);
//        menuEdit.add(new JSeparator());
//        menuEdit.add(MIFind);
        //menu Base de Faits
        menuBDF.add(MIChargerBDF);
        menuBDF.add(MIAfficherBDF);
        menuBDF.add(MICreerBDF);
        menuBDF.add(MIModifierBDF);
        //menu Base de Regles
        menuBDR.add(MIChargerBDR);
        menuBDR.add(MIAfficherBDR);
        menuBDR.add(MICreerBDR);
        menuBDR.add(MIModifierBDR);        
        //menu Look And Feel
        menuLAF.add(MIWindowsLAF);
        menuLAF.add(MINimbusLAF);
        menuLAF.add(MIMetalLAF);        
        
        //menu Help
        menuHelp.add(MIAbout);
        menuHelp.add(MIManuel);
        //barre de menus
        barreDeMenus.add(menuFile);
//        barreDeMenus.add(menuEdit);
        barreDeMenus.add(menuBDF);
        barreDeMenus.add(menuBDR);
        barreDeMenus.add(menuLAF);
        barreDeMenus.add(menuHelp);
        setJMenuBar(barreDeMenus);
        //les menus pop up
        popUpMenu = new JPopupMenu("Options Menu");
        popUpMenu.add(MIEditPopUp);
        popUpMenu.add(MIDeletePopUp);
    }

    private void metLesListenersPourLesBoutonsEtLesMenus() {
        // les menu itens
        MISave.addActionListener(this);
        MISaveAs.addActionListener(this);
//        MIOpen.addActionListener(this);
        MIClose.addActionListener(this);
        MIAbout.addActionListener(this);
        MIManuel.addActionListener(this);
//        MIImport.addActionListener(this);
//        MIDelete.addActionListener(this);
//        MINew.addActionListener(this);
//        MIFind.addActionListener(this);
        
        MIChargerBDF.addActionListener(this);
        MICreerBDF.addActionListener(this);
        MIModifierBDF.addActionListener(this);
        MIAfficherBDF.addActionListener(this);

        MIChargerBDR.addActionListener(this);
        MICreerBDR.addActionListener(this);
        MIModifierBDR.addActionListener(this);
        MIAfficherBDR.addActionListener(this);
        
        MIWindowsLAF.addActionListener(this);
        MINimbusLAF.addActionListener(this);
        MIMetalLAF.addActionListener(this);
        // les pop up menu
        MIEditPopUp.addActionListener(this);
        MIDeletePopUp.addActionListener(this);
        // les boutons
        boutonChainageAvant.addActionListener(this);
        boutonChainageArriere.addActionListener(this);
        boutonRechercheRapide.addActionListener(this);
        boutonRechercheAvancee.addActionListener(this);
        // set enabled pour les menus
        MISave.setEnabled(enable);
        MISaveAs.setEnabled(enable);
//        MIImport.setEnabled(enable);
//        MIDelete.setEnabled(enable);
//        MIFind.setEnabled(enable);
        // set enabled pour les boutons
        boutonChainageAvant.setEnabled(enable);
        boutonChainageArriere.setEnabled(enable);
        boutonRechercheRapide.setEnabled(enable);
        boutonRechercheAvancee.setEnabled(enable);
        

//        
//        boutonSaveBDF.addActionListener(this);
//        boutonCancelBDF.addActionListener(this);
    }


    private void setTitleSizeAndLocation() {
        setTitle("Intelligence Artificielle");
        frameSize = new Dimension ((int)(screenSize.width/2), (int)(screenSize.height/1.5));
        
        setSize(frameSize); 
        setLocation((int)(screenSize.width/4), (int)(screenSize.height/6)); // localisation standard
        // exit on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    codage.saveTraduction();
                }
                catch (Exception exc) {}
                System.exit(0);
            }
        });
    }

    private void metUneBarreDOutils() {
        //met un toolbar
        toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        toolBar.setRollover(enable); // le lookAndFeel peut ne pas respecter cette methode
        toolBar.add(boutonChainageAvant);
        toolBar.add(boutonChainageArriere);
        // Les boutons "Recherche Rapide" et "Recherche Avancee" vont etre implementes apres
        //toolBar.add(boutonRechercheRapide);
        //toolBar.add(boutonRechercheAvancee);
        
        toolBar.setFloatable(false); //l'utilisateur ne peut pas deplacer le ToolBar
        toolBar.setBorder(new EtchedBorder());
    }

    private void metToutCaDansLeConteneurAvecUnBorderLayout(Container c) {
            c.add(toolBar, BorderLayout.NORTH);
            c.add(mainPane, BorderLayout.CENTER);
            //fontMetrics = ((JTable)((JViewport)((JScrollPane)tabbedPane.getComponentAt(0)).getComponent(0)).getComponent(0)).getFontMetrics(((JTable)((JViewport)((JScrollPane)tabbedPane.getComponentAt(0)).getComponent(0)).getComponent(0)).getFont());
    }

    private void creeUnSplitPaneOuToutesLesActionsAurontLieu() {
        mainPane = new MainPane();
    }

    private void creePartieNORTH() {
        //cree le menu avec les icones
        creeMenuAvecIcones();
        //cree les boutons avec icones
        creeBoutonsAvecIcones();
        //met les menus dans leur place
        metLesMenusDansLeurPlace();
        //met les listeners pour les boutons et les menus
        metLesListenersPourLesBoutonsEtLesMenus();
        //met une barre d'outils
        metUneBarreDOutils();
    }

    private void creePartieCENTER() {
        //cree un split pane ou toutes les actions auront lieu
        creeUnSplitPaneOuToutesLesActionsAurontLieu();
    }

    private void loadingScreen() {
        // Loading screen 
//        JWindow window = new JWindow();
//        StartUp panel = new StartUp(getClass().getResource("images/startup.png"));
//        window.getContentPane().add(panel);
//        window.setLocation((int)(screenSize.width/2-panel.getImage().getWidth()/2),(int)(screenSize.height/2-panel.getImage().getHeight()/2));
//        window.setSize(new Dimension(panel.getImage().getWidth(), panel.getImage().getHeight()));
//        window.setVisible(true);
//        doNothing(2000); // do nothing for 2000 miliseconds
//        window.setVisible(false);System.out.println("a");
    }

    private void creeLesMenus() {      
        //menus
        barreDeMenus = new JMenuBar();
        menuFile = new JMenu("Fichier");
//        menuEdit = new JMenu("Editar");
        menuBDF = new JMenu ("Base de Faits");
        menuBDR = new JMenu ("Base de Règles");
        menuLAF = new JMenu ("Look & Feel");
        menuHelp = new JMenu("Help");
    }

    private void chargerBDF() {
        int returnValue = 0;
        try{
            fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);
            returnValue = fileChooser.showOpenDialog(this);
            
            if (returnValue == JFileChooser.APPROVE_OPTION) { //JFileChooser.APPROVE_OPTION
                File file = fileChooser.getSelectedFile();
                bdf.readData(file);         
                passeLaBDF();
                chargeeBDF = true;
                mainPane.append("\nBase de Faits chargée avec succès.");
            }
            else {
                mainPane.append("\nChargement annulé."); 
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
    }


    
    private void modifierBDF() {
        
        creeOuModifieBDF = MODIFIE;
        
        if (chargeeBDF == false){
            JPanel paneSave = new JPanel();
            paneSave.add(new JLabel("Voulez devez d'abord creer une Base de Faits pour pouvoir la modifier."), BorderLayout.CENTER);

            fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);   
            int reponse = JOptionPane.showConfirmDialog(this, paneSave , "Attention !", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (reponse == 0) {
                        creerBDF();
                        chargeeBDF = true;
                        //mainPane.append("\nFichier enregistré.");
            }
            else {
                mainPane.append("\nModification annulée."); 
                return;
            }                           

        }
        
        else {
            
//                panelAux = new PanelAux(comboBoxChoixBDF); 
//                panelAux.jButton1.addActionListener(this);
            panelPlusInfos = panelAux;
                
            JPanel paneFinal = new JPanel();

            for (int i = 0; i< comboBoxChoixBDF.size(); i++){
                if (chargeeBDF == false){ 
                    panelAux.setComboItem(comboBoxChoixBDF.get(i));
                }
                else { // si BDF a ete chargee d'un fichier
                    //if (i <= 6) comboBoxChoixBDF.get(i).setSelectedIndex(0);
                }
                if (i > 6) comboBoxChoixBDF.get(i).setEditable(true);
                
                comboBoxChoixBDF.get(i).setSelectedItem(comboBoxChoixBDF.get(i).getSelectedItem());
                
                // on a besoin de ça ??? (je sais pas)
                for (int j=0; j<listOfComboBoxes.size() && i>6; j++) {
                    if (comboBoxChoixBDF.get(i) ==  listOfComboBoxes.get(j)) {
                        comboBoxChoixBDF.get(i).setSelectedItem(listOfComboBoxes.get(j).getSelectedItem());
                    }
                }
            }

            //System.out.println(comboBoxChoixBDF.size());
            for (int i = 0; i<listOfComboBoxes.size();i++){
                System.out.println(listOfComboBoxes.get(i).getSelectedItem());
                if (true) { // au lieu de true : creeOuModifieBDF == MODIFIE && ctrl == true
                    listOfComboBoxes.get(i).setSelectedItem(listOfComboBoxes.get(i).getSelectedItem());
                }
            }
            ctrl=false;            
            
            boutonSaveBDF = new JButton ("Enregistrer");
            boutonCancelBDF = new JButton ("Annuler");
            boutonSaveBDF.addActionListener(this);
            boutonCancelBDF.addActionListener(this);


            JPanel paneBas = new JPanel();
            paneBas.setLayout(new FlowLayout());
            paneBas.add(boutonSaveBDF);
            paneBas.add(boutonCancelBDF);

            paneFinal.setLayout(new BorderLayout());
            paneFinal.add(paneBas, BorderLayout.SOUTH);        
            paneFinal.add(panelAux, BorderLayout.CENTER);                

            //panelPlusInfos = panelAux;
            mainPane.setPane(paneFinal);  
        }
        
    }

    private void testeModifierBDF() {
        ArrayList<String> regles = new ArrayList<String>();
        regles.add("regle 1");
        regles.add("regle 2");
        regles.add("regle 3");
        regles.add("regle avec un beaucoup trop gros nom");
        regles.add("r5");
        regles.add("regle 6");   
        regles.add("regle 7");
        
        ArrayList<String> listePossibilites = new ArrayList<String>();
        listePossibilites.addAll(regles);
        listePossibilites.add("nouvelle regle");
        listePossibilites.add("<effacer cette regle>");
        
        int pixel = 10;
        GridLayout gridLayout = new GridLayout (3, 4, pixel, pixel);
        
        JPanel paneFinal = new JPanel();
        JPanel pane = new JPanel();
        pane.setLayout(gridLayout);
        
        JCheckBox checkBox[] = new JCheckBox[listePossibilites.size()];
      
        for (int i=0; i< listePossibilites.size(); i++){             
            if (i<regles.size()){
                checkBox[i] = new JCheckBox(regles.get(i));      
                checkBox[i].addActionListener(this);                             
                checkBox[i].setSelected(true);
                pane.add(checkBox[i]);                
            }            
            else {
                checkBox[i] = new JCheckBox(listePossibilites.get(i));      
                checkBox[i].addActionListener(this);                             
                pane.add(checkBox[i]);       
            }
        }
        
        
        JButton boutonSave = new JButton ("Enregistrer");
            boutonSave.addActionListener(this);
        JButton boutonCancel = new JButton ("Annuler");
            boutonCancel.addActionListener(this);

        JPanel paneBas = new JPanel();
        paneBas.setLayout(new FlowLayout());
        paneBas.add(boutonSave);
        paneBas.add(boutonCancel);
      
        paneFinal.setLayout(new BorderLayout());
        paneFinal.add(paneBas, BorderLayout.SOUTH);        
        paneFinal.add(pane, BorderLayout.CENTER);
        
        mainPane.setPane(paneFinal);        
        
    }

    private void doNothing(int milliseconds){
        for(int i = 0; i < milliseconds * 5 * 1E5; i++);
    }

    private void demarrer() throws FileNotFoundException {
        /*
         * Probleme ICI
         */
        bdr = new BaseDeRegles();
        bdr.setDir(System.getProperty("user.dir"));
        bdr.setFileName("regles");
        bdr.readData();


        bdf = new BaseDeFaits();
        bdf.setDir(System.getProperty("user.dir"));
        bdf.setFileName("faits");
        bdf.readData();
        
        codage = new Codage();
    }

    private void afficherBDF () throws UserException {
        int pixel = 6;
        GridLayout gridLayout = new GridLayout (0, 1, pixel, pixel);
        
        JPanel pane = new JPanel();
        JLabel titre = new JLabel("Base de Faits");
        
        Font fontTitre = new Font("Arial", Font.BOLD, 14);
        titre.setFont(fontTitre);
        
        pane.add(titre);
        
        afficherLabels(pane, gridLayout, titre, bdf);        
    }

    private void creerBDF() {
        creeOuModifieBDF = CREE;
        
        JPanel paneSave = new JPanel();
        paneSave.add(new JLabel("Voulez-vous enregistrer votre ancienne Base de Faits ?"), BorderLayout.CENTER);
        
        if (chargeeBDF == true){
            try{
                fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);   
                int reponse = JOptionPane.showConfirmDialog(this, paneSave , "Attention !", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reponse == 0) {
                    try{
                        int returnValue = fileChooser.showSaveDialog(this);
                        
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            
                            if (fileChooser.getFileFilter().getDescription().equals(fileChooser.getChoosableFileFilters()[0].getDescription())) {
                                file = new File(file.getAbsolutePath()+bdf.getExt());
                            } 
                            
                            bdf.saveData(file);          
                            chargeeBDF = true;
                            mainPane.append("\nFichier enregistré.");
                        }
                        else {
                            mainPane.append("\nEnregistrement annulé."); 
                        }                            
                    }
                    catch (Exception e){}
                }
            }
            catch(Exception e1){
                ok=false;
                mainPane.append("\nErreur.");        
            }

        }
        
        creeInfosPersonnelles();
        creeVotreDestination();
        creeRecherchePersonalisee();
        creePaneEtMetToutCaDedans();
        
    }

    private void chainageAvant() {
        IntelligenceArtificielle.chainageAvant(bdf, bdr);
        ArrayList<String> reponse = filtreReponse();
        
        int pixel = 6;
        GridLayout gridLayout = new GridLayout (0, 1, pixel, pixel);
        
        JPanel pane = new JPanel();
        JLabel titre = new JLabel("Votre destination de voyage");
        
        Font fontTitre = new Font("Arial", Font.BOLD, 14);
        titre.setFont(fontTitre);        
        
        pane.add(titre);
                
        afficherLabels(pane, gridLayout, titre, reponse);
    }

    private void chainageArriere() {
        int but;
        JPanel paneReponse = new JPanel();
        JLabel textReponse = new JLabel();
        ImageIcon icon;
        Font font = new Font("Arial", Font.BOLD, 14);
        textReponse.setFont(font);
        
        JPanel paneDestination = new JPanel();
        JLabel labelDestination = new JLabel ("Destination");
        JComboBox comboBoxDestination = new JComboBox(getVilles(bdr).toArray((new String[getVilles(bdr).size()])));
        comboBoxDestination.addActionListener(this);
        
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Vous voulez aller à quelle destination ?"), BorderLayout.WEST);
     
        JPanel p2 = new JPanel();
        
        p2.add(labelDestination);
        p2.add(comboBoxDestination, BorderLayout.EAST);
        p2.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        paneDestination.add(p1);
        paneDestination.add(p2);
        paneDestination.setLayout(new BoxLayout(paneDestination, BoxLayout.Y_AXIS));
        
        boolean peutYAller;
        
        mainPane.append("\nVérification des paramètres...");
        
                int reponse = JOptionPane.showConfirmDialog(this, paneDestination , "Vérification de destination de voyage", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (reponse == 0) {
                    but = codage.traduireMot(comboBoxDestination.getSelectedItem().toString(),false,false);
                    peutYAller = IntelligenceArtificielle.chainageArriere(bdf, bdr, but);
                    
                    if (peutYAller == true) {
                        textReponse.setText("\nVous etes acceptible pour aller à \n" + comboBoxDestination.getSelectedItem().toString());
                        icon = new ImageIcon(getClass().getResource("images/smiley.png"));
                    }                    
                    else {
                        textReponse.setText("\nLa destination de " + comboBoxDestination.getSelectedItem().toString() +"\n ne vous convient pas.");
                        icon = new ImageIcon(getClass().getResource("images/smileySad.png"));
                    }
                    
                    textReponse.setIcon(icon);
                    paneReponse.add(textReponse, BorderLayout.CENTER);
                    paneReponse.setLayout(new BoxLayout(paneReponse, BoxLayout.Y_AXIS));
                    
                    mainPane.append(" Destination vérifiée.");
                    mainPane.setPane(paneReponse);
                }
                else {
                    mainPane.append(" Vérification annulée."); 
                }
                

    }

    private void afficherLabels(JPanel pane, GridLayout layout, JLabel titre, ArrayList<String> aAfficher) {
        pane.setLayout(layout);
       
        JLabel label[] = new JLabel[aAfficher.size()];
        for (int i=0; i < aAfficher.size(); i++){
            label[i] = new JLabel(aAfficher.get(i), JLabel.LEFT);
            pane.add(label[i]);
        }
                
        mainPane.setPane(pane);
    }

    private void afficherLabels(JPanel pane, GridLayout layout, JLabel titre, BaseDeFaits bdf) {
        
        ArrayList<String> string = new ArrayList<String>();
        for (int i = 0; i < bdf.size(); i++){
            string.add(codage.traduireCode(bdf.get(i).getIndice(0)));
        }
        
        afficherLabels(pane, layout, titre, string);
    }
    
    private void afficherLabels(JPanel pane, GridLayout layout, JLabel titre, BaseDeRegles bdr) {
        
        ArrayList<String> string = new ArrayList<String>();
        
        System.out.println(bdr.size());
        for (int i = 0; i < bdr.size(); i++){
            string.add(toStringBDR(bdr.get(i)));
        }
        
        afficherLabels(pane, layout, titre, string);
    }    

    private void creerBDR() {
        
        creeOuModifieBDR = CREE;
        
        JPanel paneSave = new JPanel();
        paneSave.add(new JLabel("Voulez-vous enregistrer votre ancienne Base de Règles ?"), BorderLayout.CENTER);
        
        fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]); 
        if (chargeeBDR == true){
            try{
                int reponse = JOptionPane.showConfirmDialog(this, paneSave , "Attention !", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reponse == 0) {
                    try{
                        int returnValue = fileChooser.showSaveDialog(this);
                        
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            
                            if (fileChooser.getFileFilter().getDescription().equals(fileChooser.getChoosableFileFilters()[1].getDescription())) {
                                file = new File(file.getAbsolutePath()+bdr.getExt());
                            } 
                            
                            bdr.saveData(file);          
                            chargeeBDR = true;
                            mainPane.append("\nFichier enregistré.");
                        }
                        else {
                            mainPane.append("\nChargement annulé."); 
                        }                            
                    }
                    catch (Exception e){}
                }
            }
            catch(Exception e1){
                ok=false;
                mainPane.append("\nErreur.");        
            }
        }
        
        int[] nbRegles = new int[2];
        
        nbRegles = creePopUpPourLesRegles();    
        if (nbRegles != null){
            creeListeDeComboBoxBDR();
            creePaneRegles(nbRegles);            
        }
        else
            mainPane.setPane(new JPanel());

    }

    private void afficherBDR() {
        int pixel = 6;
        GridLayout gridLayout = new GridLayout (0, 1, pixel, pixel);
        
        JPanel pane = new JPanel();
        JLabel titre = new JLabel("Base de Règles");
        
        Font fontTitre = new Font("Arial", Font.BOLD, 14);
        titre.setFont(fontTitre);
        
        pane.add(titre);
        
        afficherLabels(pane, gridLayout, titre, bdr);                
    }

    private void chargerBDR() {
        try{
            fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);   
            int returnValue = fileChooser.showOpenDialog(this);
            
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                bdr.readData(file);         
                passeLaBDR();
                chargeeBDR = true;
            }
            else {
                mainPane.append("\nChargement annulé."); 
            }
            
//        } catch (FileNotFoundException fileNotFoundException){
//            ok=false;
//            mainPane.append("\nLe fichier '"+fileNotFoundException.getMessage()+"' n'existe pas.");
//        } catch (UserException ex) {
//            ok=false;
//            mainPane.append("\nErreur dans la lecture du fichier !\n"+ex);
//        } catch (IOException ex2) {
//            ok=false;
//            mainPane.append("\nErreur inconnu dans la lecture du fichier !\n"+ex2);
        } catch(Exception e1) {
            ok=false;
            mainPane.append("\nChargement annulé.");
        }
        if (ok) {
            mainPane.append("\nBase de Faits chargé avec succès.");
        }                
    }

    private void modifierBDR() {
        
        creeOuModifieBDR = MODIFIE;
        
        if (chargeeBDR == false){
            JPanel paneSave = new JPanel();
            paneSave.add(new JLabel("Voulez devez d'abord creer une Base de Règles pour pouvoir la modifier."), BorderLayout.CENTER);

            fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);   
            int reponse = JOptionPane.showConfirmDialog(this, paneSave , "Attention !", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (reponse == 0) {
                        creerBDR();
                        //mainPane.append("\nFichier enregistré.");
            }
            else {
                mainPane.append("\nModification annulée."); 
                return;
            }                           

        }
        
        else {
            
                
            JPanel paneFinal = new JPanel();

            try {
                for (int i = 0; i< paneRegles.listOfComboBoxes().size(); i++){
                    paneRegles.setComboItem(paneRegles.listOfComboBoxes().get(i));
                    paneRegles.listOfComboBoxes().get(i).setEditable(true);
                }

                for (int i = 0; i<paneRegles.listOfComboBoxes().size(); i++){
                    if (creeOuModifieBDR == MODIFIE) {
                        paneRegles.listOfComboBoxes().get(i).setSelectedItem(paneRegles.listOfComboBoxes().get(i).getSelectedItem());
                    }
                }         

                boutonSaveBDR = new JButton ("Enregistrer");
                boutonCancelBDR = new JButton ("Annuler");
                boutonSaveBDR.addActionListener(this);
                boutonCancelBDR.addActionListener(this);


                JPanel paneBas = new JPanel();
                paneBas.setLayout(new FlowLayout());
                paneBas.add(boutonSaveBDR);
                paneBas.add(boutonCancelBDR);

                paneFinal.setLayout(new BorderLayout());
                paneFinal.add(paneBas, BorderLayout.SOUTH);        
                paneFinal.add(paneRegles, BorderLayout.CENTER);                

                //panelPlusInfos = panelAux;
                mainPane.setPane(paneFinal);  
            }
            catch (NullPointerException exc) {
                // ça devrait traiter le cas de passerLaBDR
                afficherBDR();
            }
        }
    }
    
    ArrayList <String> filtreReponse() 
    {
        ArrayList<String> reponse = new ArrayList<String>();
        for (int i=0;i<bdf.size();i++)
        {
            if(bdf.get(i).getIndice(0) % 10 == 0)
            {
                reponse.add(codage.traduireCode(bdf.get(i).getIndice(0)));
            }
        }
        return reponse;
    }

        public ArrayList<String> getVilles(BaseDeRegles bdr){
            ArrayList<String> villes = new ArrayList<String>();
            int codeDeLaVille;
            
            for (int i = 0; i<bdr.size(); i++){
                codeDeLaVille = (bdr.get(i).getAlors());
                if ((bdr.get(i).getAlors() % 10 == 0) && !villes.contains(codage.traduireCode(codeDeLaVille))){
                    villes.add(codage.traduireCode(codeDeLaVille));
                }
                    
            }
            return villes;
        }

    private void creeInfosPersonnelles() {   
        int[] age = {1, 2, 3, 4};
        int[] civilite = {5, 6, 7, 8};
        int[] enfants = {9, 11};
        int[] personalite = {22, 23};
        
        comboBoxAge = new JComboBox(getRegleSpecifique(codage, age).toArray((new String[getRegleSpecifique(codage, age).size()])));
        comboBoxCivilite = new JComboBox(getRegleSpecifique(codage, civilite).toArray((new String[getRegleSpecifique(codage, civilite).size()])));
        comboBoxEnfants = new JComboBox(getRegleSpecifique(codage, enfants).toArray((new String[getRegleSpecifique(codage, enfants).size()])));
        comboBoxPersonalite = new JComboBox(getRegleSpecifique(codage, personalite).toArray((new String[getRegleSpecifique(codage, personalite).size()])));
        
        comboBoxAge.addActionListener(this);
        comboBoxCivilite.addActionListener(this);
        comboBoxEnfants.addActionListener(this);
        comboBoxPersonalite.addActionListener(this);
        
        comboBoxChoixBDF = new ArrayList<JComboBox>();
        
        comboBoxChoixBDF.add(comboBoxAge);
        comboBoxChoixBDF.add(comboBoxCivilite);
        comboBoxChoixBDF.add(comboBoxEnfants);
        comboBoxChoixBDF.add(comboBoxPersonalite);
        
        

    }

        public ArrayList<String> getRegleSpecifique (Codage codage, int[] codeSpecifique){
            ArrayList<String> specifique = new ArrayList<String>();
            int code;
            
            specifique.add(" ");
            for (int i = 0; i<codage.size(); i++){
                for (int j = 0; j<codeSpecifique.length; j++){
                    code = (codage.getCode(i));
                    if ((code == codeSpecifique[j])){
                        specifique.add(codage.traduireCode(code));
                    }
                }
            }
            return specifique;
        }
        
        
    private void creeVotreDestination() {
        int[] climat = {29, 31, 32};
        int[] budget = {47, 48, 49};
        int[] touristique = {52, 53, 54};
        
        comboBoxClimat = new JComboBox(getRegleSpecifique(codage, climat).toArray((new String[getRegleSpecifique(codage, climat).size()])));
        comboBoxBudget = new JComboBox(getRegleSpecifique(codage, budget).toArray((new String[getRegleSpecifique(codage, budget).size()])));
        comboBoxTouristique = new JComboBox(getRegleSpecifique(codage, touristique).toArray((new String[getRegleSpecifique(codage, touristique).size()])));
     
        
        comboBoxClimat.addActionListener(this);
        comboBoxBudget.addActionListener(this);
        comboBoxTouristique.addActionListener(this);
        
        
        comboBoxChoixBDF.add(comboBoxClimat);
        comboBoxChoixBDF.add(comboBoxBudget);
        comboBoxChoixBDF.add(comboBoxTouristique);
        
    }

    private void creeRecherchePersonalisee() {

        int[] options = new int[100];
        for (int i = 1; i < 100; i++) options[i] = i;
        
        for (int i = 0; i < 10; i++){
            comboBoxOptions = new JComboBox(getRegleSpecifique(codage, options).toArray((new String[getRegleSpecifique(codage, options).size()])));
            comboBoxOptions.setEditable(true);
            comboBoxOptions.addActionListener(this);
            comboBoxChoixBDF.add(comboBoxOptions); 
        }
//        comboBoxOptions = new JComboBox(getRegleSpecifique(codage, options).toArray((new String[getRegleSpecifique(codage, options).size()])));
//
//        comboBoxOptions.addActionListener(this);
//        
//        comboBoxChoixBDF.add(comboBoxOptions);       
    }

    private JComboBox creeComboBoxOptions(){
        int[] options = new int[100];
        for (int i = 1; i < 100; i++) options[i] = i;
        
        return new JComboBox(getRegleSpecifique(codage, options).toArray((new String[getRegleSpecifique(codage, options).size()])));
            
    }
    private void creePaneEtMetToutCaDedans() {
        panelAux = new PanelAux(comboBoxChoixBDF); 
        //PanelAux panelAux2 = new PanelAux(comboBoxChoixBDF);
            panelAux.jButton1.addActionListener(this);
            panelPlusInfos = panelAux;
            
        JPanel paneFinal = new JPanel();

        
        boutonSaveBDF = new JButton ("Enregistrer");
        boutonCancelBDF = new JButton ("Annuler");
        boutonSaveBDF.addActionListener(this);
        boutonCancelBDF.addActionListener(this);

        
            
        JPanel paneBas = new JPanel();
        paneBas.setLayout(new FlowLayout());
        paneBas.add(boutonSaveBDF);
        paneBas.add(boutonCancelBDF);
      
        paneFinal.setLayout(new BorderLayout());
        paneFinal.add(paneBas, BorderLayout.SOUTH);        
        paneFinal.add(panelAux, BorderLayout.CENTER);
        
        mainPane.setPane(paneFinal);  
    }

    private void saveBDF() {
        bdf = new BaseDeFaits();
        String traduit = "";
        
        for (int i=0; i<comboBoxChoixBDF.size(); i++){
            traduit = comboBoxChoixBDF.get(i).getSelectedItem().toString();
            
            if (!traduit.equals(" ")){
                  Regle regles = new Regle(codage.traduireMot(traduit, true, false),-1);
                  bdf.addRegle(regles);              
            }

        }
        
        chargeeBDF = true;
        ctrl = true;
    }

    public String toStringBDR(Regle regle){
            String resultat = "";
                    Regle r = regle;
                    
                    if (r.taille() == 0) return resultat + "\nToujours vrai : " + codage.traduireCode(r.getAlors());
                            
                    resultat =  resultat + "\nSi " + codage.traduireCode(r.getIndice(0));
                    int j;
                    for (j=1;j<r.taille();j++)
                    {
                            resultat = resultat + " et " + codage.traduireCode(r.getIndice(j));
                    }

                    resultat = resultat + " alors " + codage.traduireCode(r.getAlors());

            return resultat;
	}

    private void about() {
        JPanel pane = new JPanel();
        JLabel presentation = new JLabel ("Programme d'aide au voyageur. Version 1.0");
        JLabel blank = new JLabel(" ");
        JLabel auteur1 = new JLabel("Antonio Guilherme FERREIRA VIGGIANO");
        JLabel auteur2 = new JLabel("Bruno BRIZIDA DREUX");
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        
        pane.add(presentation);
        pane.add(blank);
        pane.add(auteur1);
        pane.add(auteur2);
        
        JOptionPane.showConfirmDialog(this, pane , "About", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    private void cancelBDF() {
        JPanel pane = new JPanel();
        mainPane.setPane(pane);
    }

    private void creePaneRegles(int[] nbRegles) {
        ArrayList<JComboBox> list = new ArrayList<JComboBox>();
        JComboBox cb = new JComboBox();
        JComboBox toutesLesMots = new JComboBox();
        
        for (int i = 0; i< nbRegles[0] ; i++){
            for (int j = 0; j< nbRegles[1]; j++){
                cb = creeComboBoxOptions();
                cb.setEditable(true);
                list.add(cb);
            }   
            toutesLesMots = new JComboBox((codage.getToutesLesMots()).toArray());
            toutesLesMots.setEditable(true);
            list.add(toutesLesMots);            
        } 
//
//        toutesLesMots = new JComboBox((codage.getToutesLesMots()).toArray());
//        toutesLesMots.setEditable(true);
//        list.add(toutesLesMots);
        
        paneRegles = new PaneRegles(list, nbRegles[0], nbRegles[1]);
        
        JPanel paneFinal = new JPanel();

        
        boutonSaveBDR = new JButton ("Enregistrer");
        boutonCancelBDR = new JButton ("Annuler");
        boutonSaveBDR.addActionListener(this);
        boutonCancelBDR.addActionListener(this);

        
            
        JPanel paneBas = new JPanel();
        paneBas.setLayout(new FlowLayout());
        paneBas.add(boutonSaveBDR);
        paneBas.add(boutonCancelBDR);
      
        paneFinal.setLayout(new BorderLayout());
        paneFinal.add(paneBas, BorderLayout.SOUTH);        
        paneFinal.add(paneRegles, BorderLayout.CENTER);
        
        mainPane.setPane(paneFinal);  
    }

    private void plusDInfos(JPanel panelPlusInfos) {
                JPanel paneCreation = new JPanel();
                JTextField textFieldNumParam = new JTextField (5);
                JLabel labelNumParam = new JLabel ("Nombre de paramètres supplémentaires");

                JPanel paneTop = new JPanel(new FlowLayout(FlowLayout.LEADING));

                paneTop.add(labelNumParam);
                paneTop.add(textFieldNumParam);

                paneCreation.setLayout(new BoxLayout(paneCreation, BoxLayout.Y_AXIS));
                paneCreation.add(paneTop);  
        
            JPanel pane = new JPanel();

            int pixel = 6;
            GridLayout gridLayout = new GridLayout (0, 1, pixel, pixel);

            pane.setLayout(gridLayout);

            JComboBox cbOptions = new JComboBox();
        
        
        
        if (premiereFoisNumParametresSupplementaires == true){
            int reponseOptionPane = JOptionPane.showConfirmDialog(this, paneCreation , "Recherche personalisée", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (reponseOptionPane == JOptionPane.OK_OPTION) {
                        numParametresSupplementaires = Integer.parseInt(textFieldNumParam.getText());
                    }            
            
        }

        
        for (int i = 0; i < numParametresSupplementaires && premiereFoisNumParametresSupplementaires; i++){
            cbOptions = creeComboBoxOptions() ;
            cbOptions.setEditable(true);
            cbOptions.addActionListener(this);
            
            
//            if (premiereFoisNumParametresSupplementaires) {
                listOfComboBoxes.add(cbOptions);
//            }
//            else listOfComboBoxes.set(i, cbOptions);
            
            comboBoxChoixBDF.add(listOfComboBoxes.get(i));
            if (nouvelleCreation == true) {listOfComboBoxes.get(i).setSelectedItem(" ");}
            
        }
        
        for (int i = 0; i < numParametresSupplementaires; i++){
            pane.add(listOfComboBoxes.get(i)); 
        }
        
        

        JPanel paneFinal = new JPanel();
        JPanel paneBas = new JPanel();
        paneBas.setLayout(new FlowLayout());
        paneBas.add(boutonSaveBDF);
        paneBas.add(boutonCancelBDF);

        paneFinal.setLayout(new BorderLayout());
     
        paneFinal.add(panelPlusInfos, BorderLayout.WEST);
        paneFinal.add(pane, BorderLayout.EAST);
        
        this.panelPlusInfos = paneFinal;
        
        paneFinal.add(paneBas, BorderLayout.SOUTH);  
        mainPane.setPane(paneFinal);  
        
    }

    private void passeLaBDF() {
        JComboBox cb;
        creeInfosPersonnelles();
        creeVotreDestination();
        creeRecherchePersonalisee();
        creePaneEtMetToutCaDedans();

        panelAux = new PanelAux(comboBoxChoixBDF); 
        panelAux.jButton1.addActionListener(this);
        panelPlusInfos = panelAux;
            
        for (int i = 0; i < bdf.size(); i++){
//            cb = creeComboBoxOptions();
//            cb.setSelectedItem(codage.traduireCode(bdf.get(i).getIndice(0))); 
//            cb.setEditable(true);
//            listOfComboBoxes.add(cb);
//            comboBoxChoixBDF.add(cb);
            comboBoxChoixBDF.get(i).setSelectedItem(codage.traduireCode(bdf.get(i).getIndice(0)));
        }
        
        chargeeBDF = true;
        
        mainPane.setPane(new JPanel());
    }

    private void cancelBDR() {
        JPanel pane = new JPanel();
        mainPane.setPane(pane);
    }

    private void saveBDR() {
        bdr = new BaseDeRegles();
        ArrayList<Integer> conditions = new ArrayList<Integer>();
        String traduit = "";
        int nbRegles = answerBDR[0];
        int nbConditionsParRegle = answerBDR[1];
        
        for (int k=0; k< nbRegles; k++){
            for (int i=0; i<nbConditionsParRegle; i++){

                traduit = paneRegles.listOfComboBoxes().get(i+k*(1+nbConditionsParRegle)).getSelectedItem().toString();
                if (!traduit.equals(" ")) conditions.add(codage.traduireMot(traduit, true, false));
            }
            
            String alors = paneRegles.listOfComboBoxes().get(nbConditionsParRegle+k*(1+nbConditionsParRegle)).getSelectedItem().toString();
            bdr.addRegle(conditions, codage.traduireMot(alors, true, paneRegles.getCheckBoxBDRState(k)));
            
            conditions = new ArrayList<Integer>();
        }
        
        

        
        chargeeBDR = true;
    }

    private void creeListeDeComboBoxBDR() {
//        comboBoxChoixBDF = new ArrayList<JComboBox>();
//        for (int i = 0; i < bdr.size(); i++){
//            comboBoxChoixBDR.add(paneRegles.get(i));
//        }
    }

    private int[] creePopUpPourLesRegles() {
        JPanel paneCreation = new JPanel();
        JTextField textFieldNbRegles = new JTextField (5);
        JTextField textFieldNbConditionsParRegle = new JTextField(5);
        JLabel labelNbRegles = new JLabel ("Nombre de règles");
        JLabel labelNbConditionsParRegle = new JLabel("Nombre de conditions par règle");
        
        JPanel paneTop = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel paneBot = new JPanel(new FlowLayout(FlowLayout.LEADING));
 
        paneTop.add(labelNbRegles);
        paneTop.add(textFieldNbRegles);
        paneBot.add(labelNbConditionsParRegle);
        paneBot.add(textFieldNbConditionsParRegle);
        
        paneCreation.setLayout(new BoxLayout(paneCreation, BoxLayout.Y_AXIS));
        paneCreation.add(paneTop);
        paneCreation.add(paneBot);

        
        answerBDR = new int[2];
        
        int reponse = JOptionPane.showConfirmDialog(this, paneCreation , "Création d'une Base de Règles", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (reponse == 0) {
                    answerBDR[0] = Integer.parseInt(textFieldNbRegles.getText());
                    answerBDR[1] = Integer.parseInt(textFieldNbConditionsParRegle.getText());
                }
                else answerBDR = null;
        return answerBDR;
    }
    
    public void saveAsBDF() {
        fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);   
            try{
                int returnValue = fileChooser.showSaveDialog(this);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    if (fileChooser.getFileFilter().getDescription().equals(fileChooser.getChoosableFileFilters()[0].getDescription())) {
                        file = new File(file.getAbsolutePath()+bdf.getExt());
                    } 

                    bdf.saveData(file);          
                    chargeeBDF = true;
                    mainPane.append("\nFichier enregistré.");
                }
                else {
                    mainPane.append("\nEnregistrement annulé."); 
                }                            
            }
            catch (Exception e){}
    }
    
    public void saveBaseDeDonnees (String nomDuFichier, BaseDeDonnees baseDeDonnees) {
        File file = fileChooser.getSelectedFile();   
        file = new File(nomDuFichier+baseDeDonnees.getExt());
        baseDeDonnees.saveData(file);
        mainPane.append("\n" + baseDeDonnees.toString() + " enregistrée avec succès dans le fichier ''" + nomDuFichier+baseDeDonnees.getExt() + "''.");
    }        
    
    public void saveAsBDR() {
        fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);   
            try{
                int returnValue = fileChooser.showSaveDialog(this);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    if (fileChooser.getFileFilter().getDescription().equals(fileChooser.getChoosableFileFilters()[1].getDescription())) {
                        file = new File(file.getAbsolutePath()+bdr.getExt());
                    } 

                    bdf.saveData(file);          
                    chargeeBDF = true;
                    mainPane.append("\nFichier enregistré.");
                }
                else {
                    mainPane.append("\nEnregistrement annulé."); 
                }                            
            }
            catch (Exception e){}
    }

    private void passeLaBDR() {
//            
//            for (int i = 0; i< bdr.size(); i++){
//                for (int j = 0; j < )
//                paneRegles.listOfComboBoxes().add(bdr.get(i));
//            }
        
        // PRECISA ARRUMAR ESSA PORCARIA
        
    }
    
}