package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/main")
@SuppressWarnings("serial")
public class TimelineServlet extends HttpServlet {
	
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Guest Book</title>");
		out.println("</head><body><h1>게시글 목록</h1>");
		out.println("<form action='upload' method='get'>");
		out.println("<input type='submit' value='글쓰기'></form>");
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.createStatement();
			
			String query = "SELECT FNO, EMAIL, CONTENT FROM FEED ORDER BY FNO DESC";
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int fno = rs.getInt("FNO");
				String content = rs.getString("CONTENT");
				content = content.replace("\n", "<br>");
				
				out.println("<br><div style='width:1000px;'>");
				out.println("No. " + rs.getInt("FNO"));
				out.println("<br>Email: " + rs.getString("EMAIL"));
				out.println("<br>");
				out.println(content);
				out.println("<br><br><form action='check' method='get'>");
				out.println("<input type='hidden' name='fno' value=" + fno + " />");
				out.println("<input type='submit' value='수정'>");
				out.println("</form></div>");
			}
			
			out.println("</body></html>");
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("Something is wrong");
		// TODO 
		// Set page view by list of feeds
		// or one object for each feed?
		
		//////////////////////////////////////////////////////////////////////
		// 							Debugging part							//
		//////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////////////
		// 							Debugging part							//
		//////////////////////////////////////////////////////////////////////
		// TODO
		// 1. Get current time
		// 2. Call module including JDBC to save data into DB table.
		// 3. (Optional) render page
	}
}

