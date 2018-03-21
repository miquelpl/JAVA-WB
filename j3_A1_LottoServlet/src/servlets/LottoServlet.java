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

import model.Lotto;

@WebServlet(name="lotto", urlPatterns="/lotto" )
public class LottoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head></head>");
		out.print("<body>");

		out.print("<h1>Lotto 1</h1>");
		
		int anzahl = Integer.parseInt(req.getParameter("anzahl"));
		int lottozahlen = Integer.parseInt(req.getParameter("lottozahlen"));

		Integer[] lotto = new Lotto().getLottoZahlen(anzahl, lottozahlen);
		
		if(lotto==null) {
			out.println("<h1>Error in parameter</h1>");
		}
		else {
			int pos = 1;
			out.print("<table border=1px>");
			out.print("<tr><th>Row</th><th>Value</th></tr>");
			for(Integer elem : lotto) {
				out.println("<tr><td>"+(pos++)+"</td><td>"+elem.intValue()+"</td></tr>");
			}
			out.print("</table>");
			
			out.write("<h1><a href='/LottoServlet/lottosort'>Sortieren</a></h1>");
			
			out.print("</body>");
			out.print("</html>");
			
			session.setAttribute("anzahl", anzahl);
			session.setAttribute("lottozahlen", lottozahlen);
			session.setAttribute("lotto", lotto);
			session.setAttribute("datum", LocalDate.now());
		}
		
		out.close();
	}

}
