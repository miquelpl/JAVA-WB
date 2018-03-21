package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="p", urlPatterns="/param" )
public class ParamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head></head>");
		out.print("<body>");
		
		out.write(req.getParameter("vorname")+" "+req.getParameter("nachname")+"<br>");
		out.print("<h1>Hallo "+req.getParameter("vorname")+" "+req.getParameter("nachname")+"!!!</h1>");

		out.print("</body>");
		out.print("</html>");
	}

}
