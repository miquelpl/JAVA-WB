package model;

import java.util.Arrays;
import java.util.List;

public class UserService {
	
	public List<User> listUser = Arrays.asList(
							new User("Max", "max"), 
							new User("Otto", "otto"), 
							new User("Günter", "günter"), 
							new User("Hans", "hans"), 
							new User("Scott", "tiger"), 
							new User("Maria", "maria"), 
							new User("Eva", "eva"), 
							new User("Sonia", "sonia"), 
							new User("Sarah", "sarah"), 
							new User("Ina", "ina"));

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}
	
	public List<User> findAll() {
		return listUser;
	}

}
