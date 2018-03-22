package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import dao.KundeMySQLDAO;
import model.Kunde;

@ManagedBean
public class KundeBean {
	private int status = 2;
	private Kunde kunde;

	public KundeBean() {
	}
	
	@PostConstruct
	public  void init(){
		kunde = new Kunde();
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String choose() {
		return(String.valueOf(status));
	}
	
	public Kunde getKunde() {
		return kunde;
	}
	
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	
	public String getUsername() {
		return this.kunde.getUsername();
	}

	public void setUsername(String username) {
		this.kunde.setUsername(username);
	}

	public String getPasswort() {
		return this.kunde.getPasswort();
	}
	public String login() {
		KundeMySQLDAO kmsd = new KundeMySQLDAO();
		Kunde temporalKunde = kmsd.findKunde(getUsername(), getPasswort());
		if(temporalKunde!=null) {
			this.kunde = temporalKunde;
			return("ShowKunde");
		}
		return("Error");
	}
	public String storeNewKunde(){
		System.out.println(kunde.toString());
		KundeMySQLDAO kmsd = new KundeMySQLDAO();
		if(kmsd.storeNewKunde(kunde)) {
			return("Login");
		}
		return("Error");
	}
	
}
