package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="session1", urlPatterns="/session1" )
public class Session1Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head></head>");
		out.print("<body>");
		
		String vorname = req.getParameter("vorname");
		String nachname = req.getParameter("nachname");
		
		out.write(vorname+" "+nachname+"<br>");
		out.print("<h1>Hallo "+vorname+" "+nachname+"!!!</h1>");
		out.write("<a href='/ServletTest/session2'>weiter</a>");

		out.print("</body>");
		out.print("</html>");
		
		session.setAttribute("vorname", vorname);
		session.setAttribute("nachname", nachname);
		session.setAttribute("datum", LocalDate.now());
		
		out.close();
	}

}
