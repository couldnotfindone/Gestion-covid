package MVCView;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import MVCModel.Patient;
import MVCModel.Selection;
import MVCModel.Statistics;
import ModelDAO.DAOPatient;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import MVCController.PatientControleur;
public class AjoutPatient extends JFrame implements ActionListener {
	
	 JLabel lnom = new JLabel ("Nom");
	 JTextField tnom = new JTextField();
	 JLabel lprenom = new JLabel ("Prenom");
	 JTextField tprenom = new JTextField();
	 JLabel lage = new JLabel ("Age");
	 JTextField tage = new JTextField();
	 JLabel lgouvernomat= new JLabel ("Gouvernomat");
	 JComboBox tgouvernomat = new JComboBox(Statistics.getGouvernorats());
	 JCheckBox b1=new JCheckBox("Arrivée de l'étranger");
	 JCheckBox b2=new JCheckBox("Contact avec une personne positive");
	 JCheckBox b3=new JCheckBox("Température élevée");
	 JCheckBox b4=new JCheckBox("Toux sèche");
	 JCheckBox b5=new JCheckBox("Difficulté respiratoire");
	 JCheckBox b6=new JCheckBox("Troubles digestifs");
	 JCheckBox b7=new JCheckBox("Mal de gorge");
	 JCheckBox b8=new JCheckBox("Maux de tête");
	 JCheckBox b9=new JCheckBox("Courbatures et douleurs");
	 JCheckBox b10=new JCheckBox("Fatigue générale");
	 JPanel p = new JPanel (new GridLayout (0,2));
	 JLabel text= new JLabel ("Cochez les symptomes et/ou incidents correspondants au patient");
	 JPanel p1 = new JPanel (new GridLayout (0,1));
	 JPanel p2 = new JPanel (new GridLayout (0,2));

	 Container contenue =this.getContentPane();
	 Patient pa;
	 DAOPatient DAO=new DAOPatient();
	 

	 public AjoutPatient(Patient pat)
	 {
		 super ("Ajouter un Patient");
		 pa=pat;
		
	 p.add(lnom); p.add(tnom);
	 p.add(lprenom); p.add(tprenom);
	 p.add(lage); p.add(tage);
	 p.add(lgouvernomat); p.add(tgouvernomat);
	 p1.add(text);
	 p1.add(b1);p1.add(b2);p1.add(b3);p1.add(b4);p1.add(b5);p1.add(b6);p1.add(b7);p1.add(b8);
	 p1.add(b9);p1.add(b10);
	 JPanel pan=new JPanel();
	 ImageIcon icone1 = new ImageIcon("Valid.png");
	 ImageIcon icone2 = new ImageIcon("cancel.png");
	 JButton btnSave = new JButton (icone1);
	 btnSave.setText("Enregistrer");
	 JButton btnVide = new JButton (icone2);
	 btnVide.setText("Vider");
	 btnVide.addActionListener(this);
	 btnSave.setBackground(Color.white);btnVide.setBackground(Color.white);
	 p2.add(btnSave);
	 p2.add(btnVide);
	 
	 this.setSize(new Dimension (400, 500));
	 pan.add(p);
	 pan.add(p1);
	 pan.add(p2);
	 contenue.add(pan);
	 setVisible(true);
     this.setVisible(true);                            

	 btnSave.addActionListener(new PatientControleur(this, pa) );
	 }
	 public void getInfoPatient ()
	 {
	 new Selection();
	 pa.setNom(tnom.getText());
	 pa.setPrénom(tprenom.getText());
	 int age=(int)Integer.valueOf(tage.getText());
	 pa.setÂge(age);
	 pa.setgouvernorat((String)tgouvernomat.getSelectedItem());
	 if(b1.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(0));
		 }
	 if(b2.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(1));
		 }
	 if(b3.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(2));
		 }
	 if(b4.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(3));
		 }
	 if(b5.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(4));
		 }
	 if(b6.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(5));
		 }
	 if(b7.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(6));
		 }
	 if(b8.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(7));
		 }
	 if(b9.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(8));
		 }
	 if(b10.isSelected()==true) {
		 pa.addCritère(Selection.getCritere(9));
		 }
	 
	 }
	 public void actionPerformed(ActionEvent arg0) {
         tnom.setText("");
		 tprenom.setText("");
		 tage.setText("");
		 b1.setSelected(false);b2.setSelected(false);b3.setSelected(false);b4.setSelected(false);b5.setSelected(false);b6.setSelected(false);
		 b7.setSelected(false);b8.setSelected(false);b9.setSelected(false);b10.setSelected(false);
		 tgouvernomat.setSelectedIndex(0);
	 }
}
