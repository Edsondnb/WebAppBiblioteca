package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	static String bd = "libreria_udemy";
	static String port = "3308";
	static String login = "root";
	static String password = "pass";
	static String url = "jdbc:mariadb://localhost:"+ port + "/" + bd;
	
	Connection connection = null;
	
	public DBConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url,login,password);
			
			if(connection == null) {
				System.out.println("La conexion con "+ bd + " a fallado");
			}else {
				System.out.println("Coneccion con " +  bd +" satisfactoria");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void desconectar() {
		connection = null;
	}

}
