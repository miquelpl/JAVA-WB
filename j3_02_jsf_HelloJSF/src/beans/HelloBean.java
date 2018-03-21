package beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean  // default-name: helloBean
public class HelloBean {
	
	private String message;
	private List<String> namen = Arrays.asList("Max", "Otto", "Ina");

	public List<String> getNamen() {
		return namen;
	}

	public void setNamen(List<String> namen) {
		this.namen = namen;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
