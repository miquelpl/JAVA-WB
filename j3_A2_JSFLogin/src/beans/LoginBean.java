package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import model.User;
import model.UserService;

//@SessionScoped
@ManagedBean
public class LoginBean {

	//private List<String> listNamen;
	
	private String username;
	private String passwort;
	private String message;
	
	private UserService userService;
	private List<User> listUser = new ArrayList<>();
	

	public LoginBean() {
	}

	@PostConstruct
	public void init() {
		userService = new UserService();
		listUser = userService.findAll();
	}

	public String getUsername() {
		return username;
	}
	public String getPasswort() {
		return passwort;
	}
	public String getMessage() {
		return message;
	}
	public List<User> getListUser() {
		return listUser;
	}
	public List<String> getListNamen() {
		List<String> listNamen = new ArrayList<>();
		for(User user : listUser) {
			if(!user.getUsername().equals(this.username))
				listNamen.add(user.getUsername());
		}
		return(listNamen);
	}

	// Verwendung für Navigation -> action
	public String login() {
		this.message = "false";
		List<User> users = listUser.stream()
				.filter(u->u.getUsername().equals(username))
				.collect(Collectors.toList());
		if(users.size()>0) {
			User user = users.get(0);
			this.message = user.getPasswort().equals(this.passwort) ? "true" : "false";
		}
		return(this.message);
	}
	
//	public String login() {
//		this.message = "false";
//		User user = null;
//		for (int i = 0; i < listUsers.size(); i++) {
//			if(listUsers.get(i).getUsername().equals(this.username)) {
//				user = listUsers.get(i);
//				break;
//			}
//		}
//		this.message = user.getPasswort().equals(this.passwort) ? "true" : "false";
//		return(this.message);
//	}
	
	public void setMessage(String message) {
		this.message = message;
	}
//	public void setListNamen(List<String> listNamen) {
//		this.listNamen = listNamen;
//	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
}
