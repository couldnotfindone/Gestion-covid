package MVCView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import MVCController.PatientControleur;
import MVCModel.Patient;
import MVCModel.Selection;
import MVCModel.ListPatients;
import ModelDAO.DAOPatient;

public class formListeP extends JFrame implements Observer {
	ImageIcon icone1 = new ImageIcon("study.png");
	ImageIcon icone2 = new ImageIcon("delete.png");
	ImageIcon icone3 = new ImageIcon("exam.png");
	ImageIcon icone4 = new ImageIcon("graph.png");
	ImageIcon icone5 = new ImageIcon("tick.png");
	ImageIcon icone6 = new ImageIcon("printer.png");
	ImageIcon icone7 = new ImageIcon("person.png");
	ImageIcon icone8 = new ImageIcon("refresh.png");

public ListPatients l = new ListPatients();

String col[]= {"Id","Nom","prenom","Age","Gouvernomat","Score Covid-19"};
String cont[][];
JTable tp;
static DefaultTableModel model;
public static Patient p1=null;
public static String action=""; 
Patient Pat = new Patient();
public formListeP(ArrayList liste) {
	super("Liste des Patients");
	 Pat.addObserver(this);
	cont=Remplir(liste);
    JLabel Titre=new JLabel(icone7);
    Titre.setText("Liste des Patients");
    model=new DefaultTableModel(cont,col);
	tp=new JTable(model);
	tp.setPreferredSize(new Dimension(500,250));
	tp.setMinimumSize(new Dimension(500,200));
	tp.getColumnModel().getColumn(0).setMinWidth(0);
	tp.getColumnModel().getColumn(0).setMaxWidth(0);
	JScrollPane pt=new JScrollPane(tp);
	
	JPanel pa1=new JPanel();
	pa1.setLayout(new BoxLayout(pa1,BoxLayout.LINE_AXIS));
	pa1.add(pt);
	JPanel pa2=new JPanel();
	JPanel pa3=new JPanel();
	pa2.setLayout(new BoxLayout(pa2,BoxLayout.LINE_AXIS));
	JButton btnAdd=new JButton(icone1);
	btnAdd.setText("ajouter");
	JButton btnRemove=new JButton(icone2);
	btnRemove.setText("Supprimer");
	JButton btnTester=new JButton(icone3);
	btnTester.setText("Tests");
	JButton btnStatistiques=new JButton(icone4);
	btnStatistiques.setText("Statistiques");
	JButton btnEnreg=new JButton(icone5);
	btnEnreg.setText("Enregistrer");
	JButton btnImprimer=new JButton(icone6);
	btnImprimer.setText("Imprimer");
	JButton btnRefresh=new JButton(icone8);
	btnRefresh.setText("Refresh");
     btnAdd.setBackground(Color.white);btnRefresh.setBackground(Color.white);btnRemove.setBackground(Color.white);btnTester.setBackground(Color.white);btnStatistiques.setBackground(Color.white);btnEnreg.setBackground(Color.white);btnImprimer.setBackground(Color.white);
	pa2.add(btnAdd);pa2.add(btnRefresh);pa2.add(btnRemove);pa2.add(btnTester);pa2.add(btnStatistiques);pa3.add(btnEnreg);pa3.add(btnImprimer);
	JPanel p=new JPanel();
	p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
	p.add(Titre);p.add(pa1);p.add(pa2);p.add(pa3);
	this.setSize(new Dimension(700,500));

	//TableColumn  col = tp.getColumnModel().getColumn(1);
    //définir le Renderer
    //col.setCellRenderer(new MyRenderer(Color.lightGray, Color.blue));
	
	this.setVisible(true);
	getContentPane().add(p,BorderLayout.CENTER);

	btnAdd.addActionListener(new PatientControleur(this,Pat));
	btnStatistiques.addActionListener(new PatientControleur(this,Pat));
	btnTester.addActionListener(new PatientControleur(this,Pat));
	btnRemove.addActionListener(new PatientControleur(this,Pat));
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

btnRefresh.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent evt)
	{
		model.setRowCount(0);
		
		DAOPatient l = new DAOPatient();
		ArrayList<Patient> p=l.findAll();
		String[]t=new String[6];
		for(int i=0;i<p.size();i++) {
		    t[0]=String.valueOf(p.get(i).getId());
			t[1]=p.get(i).getNom();
			t[2]=p.get(i).getPrénom();
			t[3]=String.valueOf(p.get(i).getÂge());
			t[4]=p.get(i).getGouvernorat();
			t[5]=String.valueOf(p.get(i).getScore());
			((DefaultTableModel)tp.getModel()).addRow(t);
		}
	}
});
}
public void MettreAjour(String mode,Patient p) {

	
	if(mode.contentEquals("Supp")) {
		System.out.println("Suppression effectuée");
		((DefaultTableModel)tp.getModel()).removeRow(tp.getSelectedRow());
	}

}
	private String[][] Remplir( ArrayList<Patient> p)
	{

		String [][] t = new String [p.size()][6];

		for(int i=0;i<p.size();i++) {
			    t[i][0]=String.valueOf(p.get(i).getId());
				t[i][1]=p.get(i).getNom();
				t[i][2]=p.get(i).getPrénom();
				t[i][3]=String.valueOf(p.get(i).getÂge());
				t[i][4]=p.get(i).getGouvernorat();
				t[i][5]=String.valueOf(p.get(i).getScore());
				
			}

	return t;
}
	public void exporter(JTable table, File file)
	{
    	JOptionPane jop = new JOptionPane();

		try
		{
	 
				TableModel model = table.getModel();
				FileWriter out = new FileWriter(file);
				for(int i=1; i < model.getColumnCount(); i++) {
			out.write(model.getColumnName(i) + "\t");
				}
				out.write("\n");
	 
				for(int i=0; i< model.getRowCount(); i++) {
			for(int j=1; j < model.getColumnCount(); j++) {
				out.write(model.getValueAt(i,j).toString()+"\t");
				}
				out.write("\n");
			}
	 
			out.close();
	    	jop.showMessageDialog(null, "Enregistrement  términer avec succés", "Enregistrement", JOptionPane.INFORMATION_MESSAGE);

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

	public static void main (String []args)
	{    
	

		DAOPatient l = new DAOPatient();
		new formListeP(l.findAll());
       
		}
		public int getIdSelectionne()
		{

		 int s = (int)Integer.valueOf((String) tp.getValueAt(tp.getSelectedRow(), 0));

		return s;
		}
	
		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			//model.setRowCount(0);
			//DAOPatient l = new DAOPatient();
			//formListeP p=new formListeP(l.findAll());	
			//cont=Remplir(liste);
			// model.fireTableDataChanged();
		}
		

}
