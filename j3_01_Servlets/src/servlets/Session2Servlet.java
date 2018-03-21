package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="session2", urlPatterns="/session2" )
public class Session2Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head></head>");
		out.print("<body>");
		
		out.print("<h1>Session 2</h1>");
		
		String vorname = String.valueOf(session.getAttribute("vorname"));
		String nachname = String.valueOf(session.getAttribute("nachname"));
		LocalDate date = (LocalDate) session.getAttribute("datum");
		
		out.write(vorname+" "+nachname+"<br>");
		out.print("<h1>Hallo "+vorname+" "+nachname+"!!!</h1>");
		out.print("<h1>Letztes mal "+date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))+" !!!</h1>");

		out.print("</body>");
		out.print("</html>");
		
		session.setAttribute("vorname", vorname);
		session.setAttribute("nachname", nachname);
		session.setAttribute("datum", LocalDate.now());
		
		out.close();
	}

}
