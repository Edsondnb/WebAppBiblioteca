package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Alquiler;
import connection.DBConnection;

public class AlquilerController implements IAlquilerController{

	@Override
	public String listarAlquileres(String username) {
		
		Gson gson = new Gson();
		DBConnection cn = new DBConnection();
		
		String sql = "SELECT l.id, l.titulo, l.genero, l.novedad"
				+ ", a.fecha FROM libros l INNER JOIN alquiler a ON  "
				+ "l.id = a.id INNER JOIN usuarios u ON "
				+ "a.username = u.username"
				+ "	WHERE a.username =  '" + username + "'";
		
		List<String> alquileres = new ArrayList<String>();
		
		try {
			Statement st = cn.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				boolean novedad = rs.getBoolean("novedad");
				Date fechaAlquiler = rs.getDate("fecha");
				
				Alquiler alquiler = new Alquiler(id, titulo, fechaAlquiler, novedad, genero);
				
				//agrengando el objeto al json
				alquileres.add(gson.toJson(alquiler));
						
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			cn.desconectar();
		}
		
		return gson.toJson(alquileres);
	}

	
	
	
}
