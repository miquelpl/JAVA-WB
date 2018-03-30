package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.datatable.DataTable;

import com.example.Example;
import com.example.Result;
import geoservice.GeoJsonFactory;
import model.Adresse;

@SessionScoped
@ManagedBean
public class MapsBean {
	
	private String adresse = "Leopoldstr 30, Berlin";
	private String result = "41.381542, 2.122893";
	private DataTable dataTable;
	private List<Adresse> listAdresse;
	private Adresse selectedAdresse;

	public MapsBean() {
	}

	@PostConstruct
    public void init() {
		listAdresse = new ArrayList<>();
    }
	 
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<Adresse> getListAdresse() {
		return listAdresse;
	}
	public void setListAdresse(List<Adresse> listAdresse) {
		this.listAdresse = listAdresse;
	}
	public DataTable getDataTable() {
		return dataTable;
	}
	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}
	public Adresse getSelectedAdresse() {
		return selectedAdresse;
	}
	public void setSelectedAdresse(Adresse selectedAdresse) {
		this.selectedAdresse = selectedAdresse;
	}

	public void adresseSuchen() {
		Example results = GeoJsonFactory.createAllResults(this.adresse);
		for(Result elem : results.getResults()) {
			listAdresse.add(new Adresse(elem.getFormattedAddress(), elem.getGeometry().getLocation().getLat()+", "+elem.getGeometry().getLocation().getLng()));
		}
		System.out.println(listAdresse);
	}

	public String select() {
		this.result = (String) dataTable.getValue();
		System.out.println(this.result);
		return "";
	}




}
