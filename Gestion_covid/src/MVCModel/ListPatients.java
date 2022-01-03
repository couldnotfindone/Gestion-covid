package MVCModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListPatients {
	TreeSet<Patient> listepat=new TreeSet<Patient>();
	public ListPatients() {}
	public void addPatient(Patient p) {
		listepat.add(p);
	}
	public void removePatient(Patient p) {
		listepat.remove(p);
	}
	public int getNbPatient() {
		return listepat.size();
	}
	public Patient getPatient(int pos) {
		int x=0;
		Patient patient = null;
		Iterator<Patient> it=listepat.iterator();
		if(pos==0) {
			patient=it.next();
		}
		while(it.hasNext() || x<pos) {
			Patient pat=it.next();
			patient=pat;
			x++;
			}
		return patient;
		}
	public int getnbPatient(Patient p) {
		int x=0;
		Patient patient = null;
		Iterator<Patient> it=listepat.iterator();
		while(it.hasNext() && p==it.next()) {
			x++;
			}
		return x;
		}
	public Set<Patient> getPatients() {
		
		return listepat;
          
}
	public List<Patient> ToBeTested(int nb){
		int x=0;
		ArrayList<Patient> liste=new ArrayList<Patient>();
		Iterator<Patient> it=listepat.iterator();
		while(it.hasNext()) {
			Patient pat=it.next();
			x++;
			if(x>listepat.size()-nb) {
				liste.add(pat);
			
			}
			}
	
		int  max=liste.get(0).getScore();
		for(int i =1; i<liste.size(); i++) { // this is to find all numbers in array1 that is below user's number, and add it to the ArrayList
	        if (liste.get(i).getScore()>max) {
	        	max=liste.get(i).getScore();
	         }
		}
		Iterator<Patient> its=liste.iterator();
		while(its.hasNext()) {
			Patient pats=its.next();
			if(pats.getScore()==max) {
				pats.setResultatTest(true);
			}
			}
		return liste;
		
	}
	public ArrayList<Patient> PatientsParGouv(String g) {
		Iterator<Patient> it=listepat.iterator();
		ArrayList<Patient> listeParGouv=new ArrayList<Patient>();
		while(it.hasNext()) {
			Patient pat=it.next();
			if(pat.getGouvernorat()==g) {
				listeParGouv.add(pat);
			}
		}
		return listeParGouv;
	}
	public ArrayList<Patient> PatientsParAge(String g) {
		Iterator<Patient> its=listepat.iterator();
		ArrayList<Patient> listeParAge=new ArrayList<Patient>();
		while(its.hasNext()) {
			Patient pat=its.next();
			if(pat.categorieAge()==g) {
				listeParAge.add(pat);
			}
		}
		return listeParAge;
	}
	
	public static void EnregistrerResultat(List<Patient> p) {
		Iterator<Patient> its=p.iterator();
		while(its.hasNext()) {
			Patient pat=its.next();
			System.out.print(pat.getNom()+" , Resultat Positif?(O/N) ");
			if(pat.isCovid()==true) 
				System.out.println("O");
			else
				System.out.println("N");

			

	}
	}
}
