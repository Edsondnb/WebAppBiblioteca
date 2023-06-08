package controller;

import java.util.Map;

public interface IUsuarioController {
	
	public String login(String username, String contrasena);
	
	public String register(String username, String contrasena, String nombre, 
			String apellidos, String email, double saldo, boolean premium);

	public String pedir(String username);
	
	public String restarDinero(String username, double nuevoSaldo);
	
	//actualizacion de datos
	public String modificar(String username, String nuevaContrasena, 		
		String nuevoNombre, String nuevoApellido, String nuevoEmail, double nuevoSaldo,
		boolean nuevoPremium);
	
	//eliminado usuarios
	public String verCopias(String username);
	
	public String devolverLibros(String username, Map<Integer, Integer> copias);
	
	public String elimiar(String username);
	
}
