package MVCModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class Patient extends Observable implements Comparable {
private int Id;	
private String nom;
private String prénom;
private String gouvernorat;
private int âge;
private int score;
HashSet<String> critères=new HashSet<String>();
private boolean  testCovid=false;
private ArrayList<Observer> lo = new ArrayList<Observer>();
public void addObserver (Observer o)
{
lo.add(o);
}
public Patient() {}
public Patient(String n,String p,String g,int a) {
	nom=n;
	prénom=p;
	gouvernorat=g;
	âge=a;
	
	
}
public boolean isTestCovid() {
	return testCovid;
}

public String getGouvernorat() {
	return gouvernorat;
}
public void addcriteres(HashSet<String> cr) {
	critères.addAll(cr);
}
public void addCritère(String sy) {
	critères.add(sy);
}
public HashSet<String> getCritères() {
	return critères;
}
public void setCritères(HashSet<String> critères) {
	this.critères = critères;
}
public boolean isCovid() {
	return testCovid;
}
public boolean equals(Object o) {
	return o.toString()==gouvernorat;
}
public void setResultatTest(boolean Test) {
	testCovid=Test;
}
public int calculeScore() {
	Iterator<String> it=critères.iterator();
	int scores=0;
	while(it.hasNext()) {
		String e=it.next();
		if((e=="Arrivée de l'étranger")||(e=="Contact avec une personne positive")||(e=="Température élevée")||(e=="Toux sèche") 
||(e=="Difficulté respiratoire")){
			scores=scores+2;
		}
		if((e=="Troubles digestifs")||(e=="Mal de gorge")||(e=="Maux de tête")||(e=="Courbatures et douleurs")||(e=="Fatigue Générale")){
							scores=scores+1;
						}
	}
	
	return scores;
	
}
@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	if( score==((Patient) o).getScore())
           return 0;
	else
	{
		if( score>((Patient) o).getScore())
	           return 1;
		else 
			return -1;
	}

}
public String categorieAge() {
	String str="";
	if(âge>=0 && âge<=3) {
		str= "Nourisson";}
	if(âge>=4 && âge<=18) {
		str= "Enfant";}
	if(âge>=19 && âge<=40) {
		str= "Jeune";}
	if(âge>=41 && âge<=65) {
		str=  "Adulte";}
	if(âge>65) {
		str=  "Vieux";
	}
	return str;
}
public String getNom() {
	return nom;
}

public String getPrénom() {
	return prénom;
}

public int getÂge() {
	return âge;
}
public boolean gettestCovid() {
	return testCovid;
}
public void setNom(String n) {
	 nom=n;
}

public void setPrénom(String p) {
	 prénom=p;
}

public void setÂge(int ag) {
	 âge=ag;
}
public void setgouvernorat(String g) {
	gouvernorat=g;
}
public String toString() {
	return this.nom+" "+this.prénom+" ("+this.âge+" ans), Score Covid-19: "+this.score;
	
}
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
}
