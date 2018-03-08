package model;

import java.io.Serializable;
import java.util.List;

import javafx.beans.InvalidationListener;

public class DataResult implements Serializable, Tabellen {

	private static final long serialVersionUID = 1L;

	private final List<MetaData> metaData;
    private final List<List<Object>> data;

	public DataResult(List<MetaData> metaData, List<List<Object>> data) {
        this.metaData = metaData ;
        this.data = data ;
    }

	public List<MetaData> getMetaData() {
		return metaData;
	}

	public List<List<Object>> getData() {
		return data;
	}

	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}

}
