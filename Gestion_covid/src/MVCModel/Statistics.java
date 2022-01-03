package MVCModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Statistics {
	static String[] gouvernorats=new String[]{"Tozeur","tataouine","Mednine","kebili","Gabes","Gafsa","Sfax","kasserine","Sidi Bouzide","kairouane","Mahdia","Monastir","Sousse","Siliana","Kef","Jendouba","Beje","Ariana","Bizerte","Tunis","Nabeul","Hammamet","Ben arous","Zaghouan"};

	
	static String[] tranchesAges=new String[] {"Nourisson","Enfant","Jeune","Adulte","Vieux"};

	public static Map<String, Integer> statsPositifsGouvernorat(List<Patient> lptest) {
	Patient pat;
	Map<String,Integer> map=new  HashMap<String,Integer>();
	for(int i=0;i<24;i++)
	{
		map.putIfAbsent(getGouvernorats()[i], 0);
	}
	Iterator<Patient> its=lptest.iterator();
	while(its.hasNext()) {
		
		pat=its.next();
		if(pat.isTestCovid()==true) {
		map.replace(pat.getGouvernorat(), map.get(pat.getGouvernorat())+1);
		}
	}
	return map;
	}

	public static Map<String, Integer> statsPositifsAge(List<Patient> lptest) {

	Patient pats;
	Map<String,Integer> maps=new  HashMap<String,Integer>();
	for(int j=0;j<5;j++)
	{
		maps.putIfAbsent(tranchesAges[j], 0);
	}
	Iterator<Patient> its=lptest.iterator();
	while(its.hasNext()) {
		
		pats=its.next();
		if(pats.isTestCovid()==true) {
		maps.replace(pats.categorieAge(), maps.get(pats.categorieAge())+1);
		}
	}
	return maps;
	}
	
	public static Map<String, Integer> statsPotiantGouvernorat(Collection<Patient> Cptest) {
		Patient pat;
		Map<String,Integer> map=new  HashMap<String,Integer>();
		for(int i=0;i<24;i++)
		{
			map.putIfAbsent(getGouvernorats()[i], 0);
		}
		Iterator<Patient> its=Cptest.iterator();
		while(its.hasNext()) {
			
			pat=its.next();
			map.replace(pat.getGouvernorat(), map.get(pat.getGouvernorat())+1);
		
		}
		return map;
	}
	
public static Map<String, Integer> statsPatientsAge(List<Patient> lptest) {

Patient pats;
Map<String,Integer> maps=new  HashMap<String,Integer>();
for(int j=0;j<5;j++)
{
	maps.putIfAbsent(tranchesAges[j], 0);
}
Iterator<Patient> its=lptest.iterator();
while(its.hasNext()) {
	
	pats=its.next();
	maps.replace(pats.categorieAge(), maps.get(pats.categorieAge())+1);
	
}
return maps;
}

public static String[] getGouvernorats() {
	return gouvernorats;
}
public static String[] gettranchesAges() {
	return tranchesAges;
}

}
