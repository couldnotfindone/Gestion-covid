package ModelDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import MVCModel.Patient;
import MVCModel.ListPatients;

public class DAOPatient<T> implements IDAO{
	public Connection cn= null;
	public DAOPatient()
	{
	super();
	//SingleConnexion.s
	}

	@Override
	public ArrayList<Patient> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Patient> Id=null;;
		TreeSet<Patient> listepat=null;

		Patient pat;
		try {
			ResultSet rs=SingleConnexion.OuvrirReq("select *from patient");
			Id=new ArrayList<Patient>();
			listepat=new TreeSet<Patient>();
			while(rs.next()){
				pat=new Patient();
				pat.setId(rs.getInt(1));
				pat.setNom(rs.getString(2));
				pat.setPrénom(rs.getString(3));
				pat.setgouvernorat(rs.getString(4));
				pat.setÂge(rs.getInt(5));
				pat.setScore(rs.getInt(6));
				Id.add(pat);
				listepat.add(pat);
			}
        	}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("zz"+listepat.size());

		return Id;
	}
	ListPatients listes=null;

	public ListPatients getListe() {
		Patient pat;
		try {
			ResultSet rs=SingleConnexion.OuvrirReq("select *from patient");
			listes=new ListPatients();
			while(rs.next()){
				pat=new Patient();
				pat.setId(rs.getInt(1));
				pat.setNom(rs.getString(2));
				pat.setPrénom(rs.getString(3));
				pat.setgouvernorat(rs.getString(4));
				pat.setÂge(rs.getInt(5));
				pat.setScore(rs.getInt(6));
				
				listes.addPatient(pat);
    
				System.out.println(pat.getNom());
			}
        	}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(listes.getNbPatient());

		return listes;
	}
	public ArrayList<Patient> getListes() {
		ArrayList<Patient> Ids=null;;

		Patient pat;
		try {
			ResultSet rs=SingleConnexion.OuvrirReq("select *from patient");
			Ids=new ArrayList<Patient>();

			while(rs.next()){
				pat=new Patient();
				pat.setId(rs.getInt(1));
				pat.setNom(rs.getString(2));
				pat.setPrénom(rs.getString(3));
				pat.setgouvernorat(rs.getString(4));
				pat.setÂge(rs.getInt(5));
				pat.setScore(rs.getInt(6));
				
				Ids.add(pat);
    
				System.out.println(pat.getNom());
			}
        	}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(Ids.size());

		return Ids;
	}
	@Override
	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		boolean resDelete=false;
		try {
			Connection cn=SingleConnexion.getConnexion();
			String deleteSQL="delete from patient where Id='"+o+"'";
			System.out.println(deleteSQL);
			Statement s=cn.createStatement();
			resDelete=s.execute(deleteSQL);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
	}
		return resDelete;
	}


	@Override
	public boolean insert(Object o) {
		// TODO Auto-generated method stub
	boolean resInsert=false;
		try {
			Connection cn=SingleConnexion.getConnexion();
			String insrtSQL="insert into patient (Nom,Prenom,Gouvernorat,Age,ScoreCovid) VALUES"
					+"(?,?,?,?,?)";
			PreparedStatement s=cn.prepareStatement(insrtSQL);
			
			s.setString(1,((Patient) o).getNom());
			s.setString(2, ((Patient) o).getPrénom());
			s.setString(3, ((Patient) o).getGouvernorat());
			s.setInt(4,(int) ((Patient) o).getÂge());
			s.setInt(5, ((Patient) o).calculeScore());
			System.out.println(insrtSQL);
			if(s.executeUpdate()>0)
				resInsert=true;
		    cn.commit();
		    
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return resInsert;
	}


	@Override
	public Object findByID(Object id) {
		// TODO Auto-generated method stub
		Patient pat=null;
		int idPat=(int)id;
		try {
			
			System.out.println(idPat);
			ResultSet rs=SingleConnexion.OuvrirReq("select *from patient where Id='"+idPat+"'");
			
			if(rs!=null){
				rs.next();
				pat=new Patient();
				pat.setId(rs.getInt(1));
				pat.setNom(rs.getString(2));
				pat.setPrénom(rs.getString(3));
				pat.setgouvernorat(rs.getString(4));
				pat.setÂge(rs.getInt(5));
			}
        	}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return pat;
	}

}
