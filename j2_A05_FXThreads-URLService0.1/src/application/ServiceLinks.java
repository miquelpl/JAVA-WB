package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceLinks extends Service<List<String>>{

	private static final String REGEX_HYPERLINK ="\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	private String html;

	public void setHTML(String html) {
		this.html = html;
	}

	@Override protected Task<List<String>> createTask() {
		Task<List<String>> task = new Task<List<String>>() {
			@Override protected List<String> call() throws Exception, IOException {

				ObservableList<String> names = FXCollections.observableArrayList();
//				List<String> result = new ArrayList<>();
				Pattern pattern = Pattern.compile(REGEX_HYPERLINK, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(html);
				while (matcher.find()) {
					names.add(matcher.group() );
					updateValue(names);

				//System.out.println(matcher.group() );
				}
				System.out.println(names);
				return(names);
			}
		};
		return task;
	}
}


