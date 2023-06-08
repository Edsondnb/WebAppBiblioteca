package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import beans.Libro;
import connection.DBConnection;

public class LibroController implements ILibroController{

	@Override
	public String listar(boolean ordenar, String orden) {
		
		Gson gson = new Gson();
		
		DBConnection cn = new DBConnection();
		String sql = "select * from libros";
		
		if(ordenar == true) {
			sql += " ORDER BY genero " + orden;
		}
		
		List<String> libros = new ArrayList<String>();
		
		try {
			Statement st = cn.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				String autor = rs.getString("autor");
				int copias = rs.getInt("copias");
				boolean novedad = rs.getBoolean("novedad");
				
				Libro libro = new Libro(id, titulo, genero, autor, copias, novedad);
				libros.add(gson.toJson(libro));
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			cn.desconectar();
		}
		
		
		return gson.toJson(libros);
	}

	@Override
	public String alquilar(int id, String username) {
	
		Timestamp fecha = new Timestamp(new Date().getTime());
		
		DBConnection cn = new DBConnection();
		String sql = "Insert into alquiler values ('"+ id + "','" + username +"','"
						+fecha+"')";
		
		try {
			
			Statement st = cn.getConnection().createStatement();
			st.executeQuery(sql);
			
			//guarda verdadero o falso dependiendo de la respuesta del metodo 
			String modificar = modificar(id);
			
			if(modificar == "true") {
				return "true";
			}			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}finally {
			cn.desconectar();
		}
		
		return "false";
	}

	@Override
	public String modificar(int id) {
		
		DBConnection cn = new DBConnection();
		String sql = "UPDATE libros set copias = (copias - 1) where id = " + id;
		
		try {
			
			Statement st = cn.getConnection().createStatement();
			st.executeQuery(sql);
			
			return "true";
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}finally {
			cn.desconectar();
		}
		
		return "false";
	}

	@Override
	public String devolver(int id, String username) {
		
		DBConnection cn = new DBConnection();
		String sql = "delete from alquiler where id=" + id + " and username = '" + username + "' limit 1";
		
		try {
			
			Statement st = cn.getConnection().createStatement();
			st.executeQuery(sql);
			
			this.sumarCantidad(id);
			
			return "true";
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			cn.desconectar();
		}
		
		return "false";
	}
	
	
	/*Sumara las cantidad cuando se accione el boton 
	 * devolver libro y se sumara de nuevo a la tabla libros*/
	@Override
	public String sumarCantidad(int id) {
		
		DBConnection cn = new DBConnection();
		
		String sql = "UPDATE libros set copias = "
				+ "	(Select copias from libros where id = " + id +") + 1 where id = "+ id;		
		
		try {	
			Statement st = cn.getConnection().createStatement();
			st.executeQuery(sql);
			
			return "true";
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			cn.desconectar();
		}
		
		return "false";
	}


}
