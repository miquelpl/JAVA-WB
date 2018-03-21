package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;

import model.User;
import model.UserService;

@ManagedBean
public class LoginBean {

	private List<String> listNamen;
	
	private String username;
	private String passwort;
	private String message;

	public LoginBean() {
		this.listNamen = getListNamen();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	// Verwendung für Navigation -> action
	public String login() {
		this.message = "false";
		UserService us = new UserService();
		List<User> users = us.findAll().stream()
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
//		UserService us = new UserService();
//		User user = null;
//		List<User> listUsers = us.findAll();
//		for (int i = 0; i < listUsers.size(); i++) {
//			if(listUsers.get(i).getUsername().equals(this.username)) {
//				user = listUsers.get(i);
//				break;
//			}
//		}
//		this.message = user.getPasswort().equals(this.passwort) ? "true" : "false";
//		return(this.message);
//	}
	
	public List<String> getListNamen() {
		List<String> listNamen = new ArrayList<>();
		UserService us = new UserService();
		for(User user : us.findAll()) {
			listNamen.add(user.getUsername());
		}
		return(listNamen);
	}

}
