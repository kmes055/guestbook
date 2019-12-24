package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/check")
@SuppressWarnings("serial")
public class CheckPasswordServlet extends GenericServlet {
	
	@Override
	public void service(
			ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO
		// Get password from user
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// TODO
		// get feed no. from html
		int fno = 1;
		
		String passwd = request.getParameter("passwd");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb", //JDBC URL
					"study",	// DBMS 사용자 아이디
					"study");	// DBMS 사용자 암호
			stmt = conn.createStatement();
			
			String query = "SELECT PWD" + 
							" FROM FEED" +
							" WHERE FNO == " + fno;
			
			rs = stmt.executeQuery(query);
			response.setContentType("text/html; charset=UTF-8");
			
			if (passwd.equals(rs.getString("PWD"))) {
				// TODO go to modify page
			}else {
				// TODO show denial message + back btn
			}
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
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