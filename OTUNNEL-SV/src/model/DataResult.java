package model;

import java.io.Serializable;
import java.util.List;

import javafx.beans.InvalidationListener;

public class DataResult implements Serializable, Tabellen {

	private static final long serialVersionUID = 1L;

	private final List<String> columnNames ;
    private final List<List<Object>> data ;
    private MetaData rsmd;

    public MetaData getRsmd() {
		return rsmd;
	}

	public void setRsmd(MetaData rsmd) {
		this.rsmd = rsmd;
	}

	public DataResult(List<String> columnNames, List<List<Object>> data, MetaData rsmd) {
        this.columnNames = columnNames ;
        this.data = data ;
        this.rsmd = rsmd;
    }

    public int getNumColumns() {
        return columnNames.size();
    }

    public String getColumnName(int index) {
        return columnNames.get(index);
    }

    public int getNumRows() {
        return data.size();
    }

    public Object getData(int column, int row) {
        return data.get(row).get(column);
    }

    public List<List<Object>> getData() {
        return data ;
    }

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}
}
