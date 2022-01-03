package MVCModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Selection {
	static HashMap<String, Integer> criteresSelection=new HashMap<String, Integer>();
	public static HashMap<String, Integer> getCriteresSelection() {
		return criteresSelection;
	}
	public Selection() {
		criteresSelection.put("Arrivée de l'étranger",2);
		criteresSelection.put("Contact avec une personne positive",2);
		criteresSelection.put("Température élevée",2);
		criteresSelection.put("Toux sèche",2);
		criteresSelection.put("Difficulté respiratoire",2);
		criteresSelection.put("Troubles digestifs",1);
		criteresSelection.put("Mal de gorge",1);
		criteresSelection.put("Maux de tête",1);
		criteresSelection.put("Courbatures et douleurs",1);
		criteresSelection.put("Fatigue générale",1);
		
	}
	public Selection(HashMap<String, Integer> critere) {
		criteresSelection=critere;
		
	}
	public boolean verifCritere(String cr) {
	return criteresSelection.containsKey(cr);
	}
	public Set<String> verifCritere() {
		TreeSet<String> critere=new TreeSet<String>();
		Set<Entry<String, Integer>> set=criteresSelection.entrySet();
		Iterator<Entry<String, Integer>> its=set.iterator();
		while(its.hasNext()) {
			Entry<String, Integer> ent=its.next();
			critere.add(ent.getKey());
		}
		return critere;
	}
	public void setCritere(HashSet HashMap) {
		((Set<Entry<String, Integer>>) criteresSelection).add((Entry<String, Integer>) HashMap);
	}	
	
	public static String  getCritere(int pos) {
		
	String critere="";
	int x=0;
	Iterator<String> it=criteresSelection.keySet().iterator();
	while(it.hasNext() && x<=pos) {
		critere=it.next();
		x++;
		}
		return critere;
	}
	
	public int  getScoreCritere(int pos) {
		int x=0;
		int Score = 0 ;
		Set<Entry<String, Integer>> set=criteresSelection.entrySet();
		Iterator<Entry<String, Integer>> it=set.iterator();
		while(it.hasNext() && x<=pos) {
			Entry<String, Integer> pat=it.next();
			Score=pat.getValue();
			x++;
			}
		return Score;
		}
	
}
