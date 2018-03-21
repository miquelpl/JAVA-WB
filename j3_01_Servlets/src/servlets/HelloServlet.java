package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head></head>");
		out.print("<body>");
		out.print("<h1>Hallo Servlet....</h1>");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date = LocalDate.now().format(formatter);
		System.out.println(date);
		out.print("<h1>"+LocalDate.now().toString()+" !!!</h1>");

		out.print("</body>");
		out.print("</html>");
	}


}
