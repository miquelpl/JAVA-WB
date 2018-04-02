package server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.EmployeesDAO;

@Path("messages")
public class MessageService {

	@GET				// get aufrufen  // http://localhost:8081/rest/messages
	@Produces(MediaType.TEXT_PLAIN)
	public String message() {
		
		EmployeesDAO dao = new EmployeesDAO();
		return dao.findAll().toString();
	}


}
