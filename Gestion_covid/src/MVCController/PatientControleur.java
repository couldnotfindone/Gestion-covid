package MVCController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import MVCView.AjoutPatient;
import MVCView.Test;
import MVCView.Tests;
import MVCView.formListeP;
import ModelDAO.DAOPatient;

import javax.swing.JButton;
import javax.swing.JFrame;

import MVCModel.ListPatients;
import MVCModel.Patient;

public class PatientControleur implements ActionListener{
	public ListPatients ListePa;
	JFrame fr,frs;
	Patient pa;
	DAOPatient ed = new DAOPatient();
	
	public PatientControleur(JFrame f, Patient p)
	{
	fr = f;
	pa = p;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	JButton b = (JButton)arg0.getSource();
	if (b.getText()=="ajouter")
	{
	new AjoutPatient(pa);

	}
	
	if (b.getText()=="Enregistrer")
	{
		((AjoutPatient)fr).getInfoPatient();
		System.out.println (pa);
		ed.insert(pa);
		fr.setVisible(false);
		
       

	}
	if (b.getText()=="Statistiques")
	{
		System.out.println("rrr"+ed.getListes().size());

		new Tests(ed.getListes());

	}
	if (b.getText()=="Tests")
	{
		new Test(ed.getListe());

	}
	if (b.getText()=="Supprimer")
	{
	 int s= ((formListeP)fr).getIdSelectionne();
	pa = (Patient) ed.findByID(s);
	System.out.println (pa);
	pa.addObserver((formListeP)fr);
	ed.delete(pa.getId());
	((formListeP)fr).MettreAjour("Supp",pa);

	}
	}

	}
