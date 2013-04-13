/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligenceartificielle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Gateway
 */
public class PaneRegles extends JPanel {
    ArrayList<ArrayList<JComboBox>> listOfRegles = new ArrayList<ArrayList<JComboBox>>();
    ArrayList<JComboBox> listOfComboBoxes = new ArrayList<JComboBox>();
    ArrayList<JLabel> listOfLabels = new ArrayList<JLabel>();
    ArrayList<JCheckBox> checkBox = new ArrayList<JCheckBox>();
    
    public PaneRegles(){
        listOfComboBoxes = null;
        listOfLabels = null;
        checkBox = null;
    }
    
    public PaneRegles(ArrayList<JComboBox> listOfComboBoxes){
        this.listOfComboBoxes = listOfComboBoxes;
        this.checkBox.add(new JCheckBox("Ville ?"));
        creeComposants();
    }

    public PaneRegles(ArrayList<JComboBox> listOfComboBoxes, int nbRegles, int nbConditionsParRegle){
        this.listOfComboBoxes = listOfComboBoxes;
        
        creeComposants(nbRegles, nbConditionsParRegle);
    }    
    
    public PaneRegles(JComboBox listOfComboBoxes){
        this.listOfComboBoxes.add(listOfComboBoxes);
    }    

    private void creeComposants() {
        this.setLayout(new FlowLayout());
        
        listOfLabels.add(new JLabel("Si "));
        this.add(listOfLabels.get(0));
        
        for (int i = 0; i < listOfComboBoxes.size() - 1; i++){
            this.add(listOfComboBoxes.get(i));
            
            listOfLabels.add(new JLabel(" et "));
            if (i != listOfComboBoxes.size()-2) this.add(listOfLabels.get(i+1));
        }
        
        listOfLabels.add(new JLabel(" Alors "));
        this.add(listOfLabels.get(listOfLabels.size()-1));
        
        this.add(listOfComboBoxes.get(listOfComboBoxes.size()-1));
        this.add(checkBox.get(0));
    }

    private void creeComposants(int nbRegles, int nbConditionsParRegle) {
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel[] ligne = new JPanel[nbRegles];
        
        for (int k=0; k< nbRegles; k++){
            this.checkBox.add(new JCheckBox("Ville ?"));
            
            ligne[k] = new JPanel();
            ligne[k].add(new JLabel("Si "));
            
            for (int i = 0; i < nbConditionsParRegle; i++){
                ligne[k].add(listOfComboBoxes.get(i+k*(1+nbConditionsParRegle)));
                
                if (i != nbConditionsParRegle-1) ligne[k].add(new JLabel(" et "));
            }
            
            ligne[k].add(new JLabel(" Alors "));//nbConditionsParRegle*(k+1))
            ligne[k].add(listOfComboBoxes.get(nbConditionsParRegle+k*(1+nbConditionsParRegle)));
            ligne[k].add(checkBox.get(k));          
            listOfRegles.add(listOfComboBoxes);
            this.add(ligne[k]);
        }
        this.add(ligne[nbRegles-1]);
        
    }    
    
    public JComboBox get (int i){
        return listOfComboBoxes.get(i);
    }
    
    public ArrayList<JComboBox> listOfComboBoxes(int i){
        return listOfRegles.get(i);
    }
    
    public ArrayList<JComboBox> listOfComboBoxes(){
        ArrayList<JComboBox> list = new ArrayList<JComboBox>();
//        for (int j = 0; j<listOfRegles.get(j).size(); j++)
//            for (int i = 0; i<listOfRegles.get(i).size()-1; i++){
//                list.add(listOfRegles.get(i).get(j));
//            }
        list = listOfComboBoxes;
        return list;
    }    
    
    public ArrayList<ArrayList<JComboBox>> getRegles (){
        return listOfRegles;
    }
    
    public boolean getCheckBoxBDRState(int i){
        return checkBox.get(i).isSelected();
    }

   public void setComboItem(JComboBox combo){
        for (int i = 0; i< listOfComboBoxes.size(); i++)
            if (listOfComboBoxes.get(i) == combo){
                listOfComboBoxes.get(i).setSelectedIndex(combo.getSelectedIndex());
            }
    }


        
}
