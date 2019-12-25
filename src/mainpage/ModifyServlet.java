package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Guest Book</title></head>");
		out.println("<body><h1>게시물 수정</h1>");
		out.println("<form action='modify' method='post'>");
		out.println("<br><input type='text' name='content' style='width:1000px; height:400px;'><br>");
		out.println("<input type='submit' value=확인");
		out.println("</form></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
		// Get password from user
		
		Connection conn = null;
		Statement stmt = null;
		
		// TODO get feed no. from html? other view?
		int fno = 1;
		String content = request.getParameter("content");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb", //JDBC URL
					"study",	// DBMS 사용자 아이디
					"study");	// DBMS 사용자 암호
			stmt = conn.createStatement();
			
			// TODO make another table contains modify history
			String query = String.format("UPDATE FEED SET CONTENT=%s, MOD_DATE=NOW() WHERE FNO = %d;", content, fno);
			stmt.executeUpdate(query);
			
			response.setContentType("text/html; charset=UTF-8");
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			//try {if (rs != 0) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
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