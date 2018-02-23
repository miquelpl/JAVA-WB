package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Hyperlink;

public class ServiceLinks extends Service<List<Hyperlink>>{

	private static final String REGEX_HYPERLINK ="\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	private String html;

	public void setHTML(String html) {
		this.html = html;
	}

	@Override protected Task<List<Hyperlink>> createTask() {
		Task<List<Hyperlink>> task = new Task<List<Hyperlink>>() {
			@Override protected List<Hyperlink> call() throws Exception, IOException {

				List<Hyperlink> result = new ArrayList<>();
				Pattern pattern = Pattern.compile(REGEX_HYPERLINK, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(html);
				while (matcher.find()) {
					Hyperlink temporalLink = new Hyperlink(matcher.group());
					result.add(temporalLink);
					updateValue(result);

				//System.out.println(matcher.group() );
				}
				//System.out.println(result);
				return(result);
			}
		};
		return task;
	}
}


