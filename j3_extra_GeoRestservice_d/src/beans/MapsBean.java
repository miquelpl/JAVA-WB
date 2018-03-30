package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import com.example.Example;
import com.example.Result;
import geoservice.GeoJsonFactory;

@SessionScoped
@ManagedBean
public class MapsBean {
	
	private String adresse;
	private String result = "41.381542, 2.122893";
	private List<String> listResult;
	private List<String> listGeometry;
	private HtmlDataTable dataList;

	public MapsBean() {
	}

	@PostConstruct
    public void init() {
		listResult = new ArrayList<>();
		listGeometry = new ArrayList<>();
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
	public List<String> getListResult() {
		return listResult;
	}
	public void setListResult(List<String> listResult) {
		this.listResult = listResult;
	}
	public List<String> getListGeometry() {
		return listGeometry;
	}
	public void setListGeometry(List<String> listGeometry) {
		this.listGeometry = listGeometry;
	}
	public HtmlDataTable getDataList() {
		return dataList;
	}
	public void setDataList(HtmlDataTable dataList) {
		this.dataList = dataList;
	}

	public void adresseSuchen() {
		Example results = GeoJsonFactory.createAllResults(this.adresse);
		for(Result elem : results.getResults()) {
			listResult.add(elem.getFormattedAddress());	
			listGeometry.add(elem.getGeometry().getLocation().getLat()+", "+elem.getGeometry().getLocation().getLng());	
		}
	}

	public void select() {
		this.result = (String) dataList.getRowData();
	}




}
