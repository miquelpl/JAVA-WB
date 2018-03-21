package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Lotto;

@WebServlet(name="lottosort", urlPatterns="/lottosort" )
public class LottoSortServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head></head>");
		out.print("<body>");

		out.print("<h1>Lottosort 2</h1>");

		Integer[] lotto = (Integer[]) session.getAttribute("lotto");
		Arrays.sort(lotto);
		
		int pos = 1;
		out.print("<table border=1px>");
		out.print("<tr><th>Row</th><th>Value</th></tr>");
		for(Integer elem : lotto) {
			out.println("<tr><td>"+(pos++)+"</td><td>"+elem.intValue()+"</td></tr>");
		}
		out.print("</table>");
		
		out.print("</body>");
		out.print("</html>");
		
		out.close();
	}
}





//		String anzahl = String.valueOf(session.getAttribute("anzahl"));
//		String lottozahlen = String.valueOf(session.getAttribute("lottozahlen"));
//		LocalDate date = (LocalDate) session.getAttribute("datum");