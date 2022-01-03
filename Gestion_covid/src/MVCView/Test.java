package MVCView;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import MVCController.PatientControleur;
import MVCModel.ListPatients;
import MVCModel.Patient;
import ModelDAO.DAOPatient;

public class Test extends JFrame {
	String col[]= {"Nom","prenom","Age","Gouvernomat","Score Covid-19"};
	String cont[][];
	JTable tp;
	public static Patient p1=null;
	public static String action=""; 

	public Test(ListPatients liste) {
		super("Test des Patients");
		cont=Remplir(liste);
		ImageIcon icone = new ImageIcon("virus.png");
	    JLabel Titre=new JLabel(icone);
	    Titre.setText("Test des Patients");
		tp=new JTable(new DefaultTableModel(cont,col));
		tp.setPreferredSize(new Dimension(500,250));
		tp.setMinimumSize(new Dimension(500,200));
		JScrollPane pt=new JScrollPane(tp);
		JPanel pa1=new JPanel();
	    JPanel pa2 = new JPanel ();
	    JPanel pa3 = new JPanel (new GridLayout (0,1));
		ImageIcon icone1 = new ImageIcon("tick.png");
		ImageIcon icone2 = new ImageIcon("printer.png");
		JButton btnEnreg=new JButton(icone1);
		btnEnreg.setText("Enregistrer");
		JButton btnImprimer=new JButton(icone2);
		btnImprimer.setText("Imprimer");
		pa1.add(pt);
		btnEnreg.setBackground(Color.white);btnImprimer.setBackground(Color.white);
		pa2.add(btnEnreg);pa2.add(btnImprimer);
		pa1.setLayout(new BoxLayout(pa1,BoxLayout.LINE_AXIS));
		pa2.setLayout(new BoxLayout(pa2,BoxLayout.LINE_AXIS));
		pa3.setLayout(new BoxLayout(pa3,BoxLayout.PAGE_AXIS));
		pa3.add(Titre);pa3.add(pa1);pa3.add(pa2);
		 Container contenue =this.getContentPane();
		 contenue.add(pa3);
		 this.setSize(new Dimension(700,600));

		 setVisible(true);
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
				Imprimer();
				}
			});
	}
	private String[][] Remplir(ListPatients  l)
	{
		int i=0;
		ListPatients liste=new ListPatients();
		 List<Patient>lptest = l.ToBeTested(2);
		 Iterator<Patient> itPatients = lptest.iterator();
			String [][] t = new String [lptest.size()][5];

		 Patient p;
		while (itPatients.hasNext())
		 {
			
		 p= itPatients.next();
		 System.out.println (p);
				t[i][0]=p.getNom();
				t[i][1]=p.getPrénom();
				t[i][2]=String.valueOf(p.getÂge());
				t[i][3]=p.getGouvernorat();
				t[i][4]=String.valueOf(p.getScore());
				i++;
			}
		System.out.println("aaa"+lptest.size());
	return t;
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
	public void Imprimer()
	{
		try {
	    tp.print();
		}catch(Exception err)
		{
			err.printStackTrace();
		}
	}

}