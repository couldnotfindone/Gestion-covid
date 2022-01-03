package ModelDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import java.sql.SQLException;
public class SingleConnexion {
		private static Connection connexion=null;
		static{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetJAva?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
       public static ResultSet OuvrirReq(String req) {
			ResultSet res=null;
		
			try {
				if(connexion!=null) {
					Statement state=connexion.createStatement();
					res=state.executeQuery(req);
				}
				else
					System.out.println("Connexion non intialisée");
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
				return res;
			
		}
		public static Connection getConnexion() {
			return connexion;
		}
}