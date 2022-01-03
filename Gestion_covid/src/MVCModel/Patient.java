package MVCModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class Patient extends Observable implements Comparable {
private int Id;	
private String nom;
private String pr�nom;
private String gouvernorat;
private int �ge;
private int score;
HashSet<String> crit�res=new HashSet<String>();
private boolean  testCovid=false;
private ArrayList<Observer> lo = new ArrayList<Observer>();
public void addObserver (Observer o)
{
lo.add(o);
}
public Patient() {}
public Patient(String n,String p,String g,int a) {
	nom=n;
	pr�nom=p;
	gouvernorat=g;
	�ge=a;
	
	
}
public boolean isTestCovid() {
	return testCovid;
}

public String getGouvernorat() {
	return gouvernorat;
}
public void addcriteres(HashSet<String> cr) {
	crit�res.addAll(cr);
}
public void addCrit�re(String sy) {
	crit�res.add(sy);
}
public HashSet<String> getCrit�res() {
	return crit�res;
}
public void setCrit�res(HashSet<String> crit�res) {
	this.crit�res = crit�res;
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
	Iterator<String> it=crit�res.iterator();
	int scores=0;
	while(it.hasNext()) {
		String e=it.next();
		if((e=="Arriv�e de l'�tranger")||(e=="Contact avec une personne positive")||(e=="Temp�rature �lev�e")||(e=="Toux s�che") 
||(e=="Difficult� respiratoire")){
			scores=scores+2;
		}
		if((e=="Troubles digestifs")||(e=="Mal de gorge")||(e=="Maux de t�te")||(e=="Courbatures et douleurs")||(e=="Fatigue G�n�rale")){
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
	if(�ge>=0 && �ge<=3) {
		str= "Nourisson";}
	if(�ge>=4 && �ge<=18) {
		str= "Enfant";}
	if(�ge>=19 && �ge<=40) {
		str= "Jeune";}
	if(�ge>=41 && �ge<=65) {
		str=  "Adulte";}
	if(�ge>65) {
		str=  "Vieux";
	}
	return str;
}
public String getNom() {
	return nom;
}

public String getPr�nom() {
	return pr�nom;
}

public int get�ge() {
	return �ge;
}
public boolean gettestCovid() {
	return testCovid;
}
public void setNom(String n) {
	 nom=n;
}

public void setPr�nom(String p) {
	 pr�nom=p;
}

public void set�ge(int ag) {
	 �ge=ag;
}
public void setgouvernorat(String g) {
	gouvernorat=g;
}
public String toString() {
	return this.nom+" "+this.pr�nom+" ("+this.�ge+" ans), Score Covid-19: "+this.score;
	
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
