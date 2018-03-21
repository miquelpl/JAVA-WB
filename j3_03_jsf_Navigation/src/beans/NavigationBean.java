package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NavigationBean {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// Verwendung für Navigation -> action
	public String login() {
		return message;
	}
}
