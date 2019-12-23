package mainpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/main")
@SuppressWarnings("serial")
public class TimelineServlet extends GenericServlet {
	
	@Override
	public void service(
			ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		
		// TODO
		// Search *** input tag in HTML standard
		
		String passwd = request.getParameter("passwd");
		String content = request.getParameter("content");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//////////////////////////////////////////////////////////////////////
		// 							Debugging part							//
		//////////////////////////////////////////////////////////////////////
		out.println(mail + " " + passwd + " " + content + "<br>You finished echo.<br>");
		
		//////////////////////////////////////////////////////////////////////
		// 							Debugging part							//
		//////////////////////////////////////////////////////////////////////
		
		// TODO
		// 1. Get current time
		// 2. Call module including JDBC to save data into DB table.
		// 3. (Optional) render page
		
		out.println("done");
	}
		
		
}

