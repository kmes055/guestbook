package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import javax.servlet.Servlet;
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
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb", //JDBC URL
					"study",	// DBMS 사용자 아이디
					"study");	// DBMS 사용자 암호
			stmt = conn.createStatement();
			
			String query = "SELECT EMAIL, CONTENT FROM FEED ORDER BY MOD_TIME DESC";
			rs = stmt.executeQuery(query);
			
			response.setContentType("text/html; charset=UTF-8");
			
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		
		// TODO 
		// Set page view by list of feeds
		// or one object for each feed?
		PrintWriter out = response.getWriter();
		
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
		
		out.println("done");
		
	}
}

