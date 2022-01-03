package MVCView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import MVCModel.ListPatients;
import MVCModel.Patient;
import MVCModel.Statistics;

public class Tests extends JFrame {

	 private JTree arbre;   

	  private Component ParGouvernorats(ListPatients listPat1){
		List<Patient>lptest = listPat1.ToBeTested(2);
	    //Création d'une racine
	    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Gouvernorats");
	    for(int i = 0; i < Statistics.getGouvernorats().length; i++){
	      DefaultMutableTreeNode rep = new DefaultMutableTreeNode(Statistics.getGouvernorats()[i]);
	          //Cette fois, on ajoute les Patients
	          for(int k = 0; k < lptest.size(); k++) {
	        	if(((lptest.get(k).getGouvernorat().equals(Statistics.getGouvernorats()[i]))==true)&&(lptest.get(k).isTestCovid()==true)){
	           rep.add(new DefaultMutableTreeNode(lptest.get(k).toString()));
	            System.out.println(lptest.get(0).toString());

	     }
	     
	    }
	      racine.add(rep);
	    }
	    //Nous créons, avec notre hiérarchie, un arbre
	    JTree arbre = new JTree(racine);
	    JScrollPane JP= new JScrollPane(arbre);
	    
	    //tableau
	    String col[]= {"Gouvernorats","Nombres de patients"};
	    String cont[][] = null;
	    JTable tp;
		    cont=Remplir(listPat1);
			ImageIcon icone = new ImageIcon("home.png");
		    JLabel Titre=new JLabel(icone);
		    Titre.setText("Nombres de patients par Gouvernorats");
			ImageIcon icone1 = new ImageIcon("tick.png");
			ImageIcon icone2 = new ImageIcon("printer.png");
			JButton btnEnreg=new JButton(icone1);
			btnEnreg.setText("Enregistrer");
			JButton btnImprimer=new JButton(icone2);
			btnImprimer.setText("Imprimer");
	    	tp=new JTable(new DefaultTableModel(cont,col));
	    	tp.setPreferredSize(new Dimension(700,350));
	    	tp.setMinimumSize(new Dimension(700,300));
	    	JScrollPane pt=new JScrollPane(tp);
	    	JPanel pa1=new JPanel();
	    	pa1.add(pt);
	    	
	    JPanel p = new JPanel (new GridLayout (0,2));
	    JPanel p2 = new JPanel ();
	    JPanel p3 = new JPanel (new GridLayout (0,1));

	    p.add(JP);
	    p.add(pa1);
	    btnEnreg.setBackground(Color.white);btnImprimer.setBackground(Color.white);
	    p2.add(btnEnreg);p2.add(btnImprimer);
		p3.setLayout(new BoxLayout(p3,BoxLayout.PAGE_AXIS));
	    p3.add(Titre);p3.add(p);p3.add(p2);
		btnEnreg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt)
			{
			  // Créer un fichier excel dans le dossier local avec le contenu de la JTable 
				JFileChooser dialogue = new JFileChooser(new File("."));
				PrintWriter sortie;
				File fichier;
				dialogue.setAcceptAllFileFilterUsed(false);
				dialogue.setFileFilter(new FileNameExtensionFilter("Excel files (*.xls)", "xls"));
				if (dialogue.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
				    fichier = dialogue.getSelectedFile();
				    String file=fichier+".xls";
				    System.out.println(file);
				    exporter(tp,new File(file));
				}
			    
			}
		});

		btnImprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt)
			{
			Imprimer(tp);
			}
		});
	    return p3;
	    }
	  private String[][] Remplir(ListPatients listPat)
		{
		  int i=0;
			String [][] t = new String [Statistics.getGouvernorats().length][2];
			 List<Patient>lptest = listPat.ToBeTested(2);
			 Map<String, Integer> hmp1 =Statistics.statsPositifsGouvernorat(lptest);
			 for(Map.Entry<String, Integer> entry : hmp1.entrySet()) {
		     // la clef peut être obtenue par entry.getKey()
			t[i][0]=entry.getKey();
	          // la valeur correspondante par entry.getValue()
			t[i][1]=String.valueOf(entry.getValue());
			i++;
		}
		return t;
}
	  
	  private Component ParAges(ListPatients listPat2){
		    List<Patient>lptest = listPat2.ToBeTested(2);
		    //Création d'une racine
		 
		    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Ages");
		    for(int i = 0; i < Statistics.gettranchesAges().length; i++){
		      DefaultMutableTreeNode rep = new DefaultMutableTreeNode(Statistics.gettranchesAges()[i]);
	          //Cette fois, on ajoute les Patients
	          for(int k = 0; k < lptest.size(); k++) {
	        	if(((lptest.get(k).categorieAge().equals(Statistics.gettranchesAges()[i]))==true)&&(lptest.get(k).isTestCovid()==true)){
	           rep.add(new DefaultMutableTreeNode(lptest.get(k).toString()));
	            System.out.println(lptest.get(k).isTestCovid());
	        	}
	     }
		      racine.add(rep);
		    }
		    //Nous créons, avec notre hiérarchie, un arbre
		    JTree arbre = new JTree(racine);
		    JScrollPane JP= new JScrollPane(arbre);
		    
		    //tableau
		    String col[]= {"Ages","Nombres de patients"};
		    String cont[][] = null;
		    JTable tp;

			    cont=Remplir1(listPat2);
				ImageIcon icone = new ImageIcon("age.png");
			    JLabel Titre=new JLabel(icone);
			    Titre.setText("Nombres de patients par Ages");
			   Titre.setSize(10,10);
			    ImageIcon icone1 = new ImageIcon("tick.png");
				ImageIcon icone2 = new ImageIcon("printer.png");
				JButton btnEnreg=new JButton(icone1);
				btnEnreg.setText("Enregistrer");
				JButton btnImprimer=new JButton(icone2);
				btnImprimer.setText("Imprimer");
		    	tp=new JTable(new DefaultTableModel(cont,col));
		    	tp.setPreferredSize(new Dimension(700,350));
		    	tp.setMinimumSize(new Dimension(700,300));
		    	JScrollPane pt=new JScrollPane(tp);
		    	JPanel pa1=new JPanel();
		    	pa1.add(pt);
		    	
		    JPanel p = new JPanel (new GridLayout (0,2));
		    JPanel p2 = new JPanel ();
		    JPanel p3 = new JPanel (new GridLayout (0,1));
		    p.add(JP);
		    p.add(pa1);
		    btnEnreg.setBackground(Color.white);btnImprimer.setBackground(Color.white);
		    p2.add(btnEnreg);p2.add(btnImprimer);
			p3.setLayout(new BoxLayout(p3,BoxLayout.PAGE_AXIS));
		    p3.add(Titre);p3.add(p);p3.add(p2);
			btnEnreg.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt)
				{
				  // Créer un fichier excel dans le dossier local avec le contenu de la JTable 
					JFileChooser dialogue = new JFileChooser(new File("."));
					PrintWriter sortie;
					File fichier;
					dialogue.setAcceptAllFileFilterUsed(false);
					dialogue.setFileFilter(new FileNameExtensionFilter("Excel files (*.xls)", "xls"));
					if (dialogue.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
					    fichier = dialogue.getSelectedFile();
					    String file=fichier+".xls";
					    System.out.println(file);
					    exporter(tp,new File(file));
					}
				    
				}
			});

			btnImprimer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt)
				{
				Imprimer(tp);
				}
			});
		    return p3;
		    }
		  private String[][] Remplir1(ListPatients listPat)
			{  
			  int i=0;
				String [][] t = new String [Statistics.gettranchesAges().length][2];
				 List<Patient>lptest = listPat.ToBeTested(2);
				 Map<String, Integer> hmp1 =Statistics.statsPositifsAge(lptest);
				 for(Map.Entry<String, Integer> entry : hmp1.entrySet()) {
				     // la clef peut être obtenue par entry.getKey()
					t[i][0]=entry.getKey();
			          // la valeur correspondante par entry.getValue()
					t[i][1]=String.valueOf(entry.getValue());
					i++;
				}
			return t;
	}
		  
	  public Tests(ArrayList<Patient> List) {

		  ListPatients listPat = new ListPatients();
	      Patient p;
			for(int i=0;i<List.size();i++){
					p=List.get(i);
					listPat.addPatient(p);
					
			}
			 for(int k = 0; k < listPat.getNbPatient(); k++) {
				 System.out.println(listPat.getPatient(k).toString());
			 }

   
    this.setLocationRelativeTo(null);
    this.setTitle("Les arbres");
	JTabbedPane jtp = new JTabbedPane();
	JPanel onglet1 = new JPanel();
    onglet1.add(ParGouvernorats(listPat));
	JPanel onglet2 = new JPanel();
	onglet2.add(ParAges(listPat));
    jtp.add("Statestique par Gouvernorat",onglet1);
	jtp.add("Statestique par Tranche d'age", onglet2);
	 Container contenue =this.getContentPane();
	 contenue.add(jtp);
	 this.setSize(new Dimension(1000,600));
	 setVisible(true);
	 
}
		public void exporter(JTable table, File file)
		{
	    	JOptionPane jop = new JOptionPane();

			try
			{
		 
					TableModel model = table.getModel();
					FileWriter out = new FileWriter(file);
					for(int i=0; i < model.getColumnCount(); i++) {
				out.write(model.getColumnName(i) + "\t");
					}
					out.write("\n");
		 
					for(int i=0; i< model.getRowCount(); i++) {
				for(int j=0; j < model.getColumnCount(); j++) {
					out.write(model.getValueAt(i,j).toString()+"\t");
					}
					out.write("\n");
				}
		 
				out.close();
		    	jop.showMessageDialog(null, "Enregistrement términer avec succés", "Enregistrement", JOptionPane.INFORMATION_MESSAGE);

			}	catch(Exception err)
			{
				err.printStackTrace();
			}
		}
		public void Imprimer(JTable table)
		{
			try {
				table.print();
			}catch(Exception err)
			{
				err.printStackTrace();
			}
		}
	  
}
