package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modify")
@SuppressWarnings("serial")
public class ModifyServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("fno", request.getParameter("fno"));
		
		RequestDispatcher rd = request.getRequestDispatcher("feed/modify.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		
		int fno = Integer.parseInt(request.getParameter("fno"));
		String content = request.getParameter("content");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.createStatement();
			
			content = content.replaceAll(";", "\\;");
			String query = String.format("UPDATE FEED SET CONTENT='%s', MOD_DATE=NOW() WHERE FNO=%d;", content, fno);
			stmt.executeUpdate(query);
			
			response.setContentType("text/html; charset=UTF-8");
			response.sendRedirect("main");
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		//////////////////////////////////////////////////////////////////////
		// 							Debugging part							//
		//////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////////////////////////////
		// 							Debugging part							//
		//////////////////////////////////////////////////////////////////////
		
		out.println("done");
	}
}