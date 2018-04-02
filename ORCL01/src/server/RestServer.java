package server;

import javax.swing.JOptionPane;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class RestServer {

	public static void main(String[] args) {
		
		try {
			//HttpServer server = (HttpServer) new HttpServerFactory.create("http://localhost:8081/rest");
			HttpServer server = HttpServerFactory.create("http://localhost:8081/rest");
			server.start();
			JOptionPane.showMessageDialog(null, "Ende");
			server.stop(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
