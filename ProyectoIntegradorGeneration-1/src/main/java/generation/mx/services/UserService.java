package generation.mx.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import generation.mx.models.UserModel;
import generation.mx.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public ArrayList<UserModel> getUsers() {
		return (ArrayList<UserModel>) userRepository.findAll();
	}
	//Podemos hacer que nos de información de lo que no se está enviando bien al Backend
	public UserModel saveUser(UserModel user) {
		//Variables para validación de datos/
		String name = user.getName(); //Obtiene el nombre del usuario.
		String surname = user.getSurname();
		String email = user.getEmail();
		
		//Si vienen todos los datos entonces si guardamos el usuario :D
		if(name != null && surname != null && email != null) {
			return userRepository.save(user);
		}
		
		/*SI no se cumplió que todos los parametros tienen un valor entonces devolvemos solo el usuario
		 * Notaremos que si hacemos las consultas y el se omite el id o el apellido notaremos que el 
		 * id= 0 entonces significa que no se está guardando
		 * surname = null significa que no tiene un valor.
		 * Todo esto nos permite manejarlo desde el JS para indicar que no ingresó algo y permite que la API funcion
		 * correctamente sin importar la circunstancia en la que no le lleguen valores que se requieren y poder
		 * tomar cartas en el asunto*/
		/*Todo lo que sea formulario necesita una validación: En HTML, en JS y en Backend para asegurar que los
		 * datos nunca fallen*/
		return user;
	}
	
	public Optional<UserModel> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	/* **** Método para BORRAR ********* */
	/*
	 * Permite saber si un usuario fue eliminado o no, se auxilia de userRepository
	 * Se usa el método que permite borrar elementos, en este caso el que elimina por Id.
	 * 
	 * @param id Hace referencia al usuario que quiere ser eliminado
	 * @return Nos indica si el usuario fue eliminado o no.
	 * */
	public boolean deleteUser(Long id) {
		//Todo lo pondremos dentro de un Try y un Catch para evitar que existan errores en nuestra Base de datos.
		
		try {
			userRepository.deleteById(id); //Si el usuario se elimina retornaremos un true
			return true;
		}catch(Exception error) { //Si el usuario no se elimina retornamos un false
			return false;
		}
	}
	public ArrayList<UserModel> getUsersByName(String name){
		return userRepository.findByName(name);
	}


}
