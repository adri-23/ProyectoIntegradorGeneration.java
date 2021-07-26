package generation.mx.controles;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import generation.mx.models.UserModel;
import generation.mx.services.UserService;

@RestController              // va respomder a un metodo http
@RequestMapping("/user")    //ruta la cual nos va responder
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping // va responder la ruta que se definio en el controlador q es (/user)
	public ArrayList<UserModel> getUsers() {
		return userService.getUsers()
;	}
	@PostMapping
	public UserModel saveUser(@RequestBody UserModel user) {
		return userService.saveUser(user);
	}
	/*Retorna la busqueda de usuario por ID por lo que esto solo se manda mediante POSTMAN
	 * como GET: localhost;8080/user/3 */
	@GetMapping(path = "/{id}")
	public Optional<UserModel> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id); //Se cambio el null por el id
	}
	
	//Metodo para la eliminación
	/*Se usa el método HTTP de DELETE mediante el @DeleteMapping
	 * Esta función nos devolverá una cadena que diga si se eliminó o no el usuario*/
	@DeleteMapping(path="/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		boolean ok = userService.deleteUser(id); //Cachamos el valor del booleano
		
		//ok Es equivalente a ok==true/
		if(ok) {
			return "Se eliminó el usuario";
		}else {
			return "No se pudo eliminar el usuario";
		}
	}
	
	@GetMapping(path= "/query")
	public ArrayList<UserModel> getUsersByName(@RequestParam(value="name", defaultValue="") String name){
		return userService.getUsersByName(name);
	}
}
