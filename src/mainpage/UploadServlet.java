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

import java.util.regex.Pattern;

@WebServlet("/upload")
@SuppressWarnings("serial")
public class UploadServlet extends GenericServlet {
	
	@Override
	public void service(
			ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String mail = request.getParameter("mail");
		// TODO
		// Search *** input tag in HTML standard
		// make content compatible with multiple lines
		String passwd = request.getParameter("passwd");
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

			if (!this.checkEmail(mail)) {
				// TODO show some error message
				throw new Exception("Email format is wrong");
			}
			String query = String.format("INSERT INTO MEMBERS VALUES ('%s', '%s', '%s', NOW());", mail, passwd, content);
			
			rs = stmt.executeQuery(query);
			response.setContentType("text/html; charset=UTF-8");
			
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

	private Boolean checkEmail(String mail) {
		// TODO 
		// Complete regular expression
		// For now, (alphabet+number)@(alphabet).(2~3 lower case alphabet)
		// (optional) Test cases
		// https://blogs.msdn.microsoft.com/testing123/2009/02/06/email-address-test-cases/
		Pattern p = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z]+.[a-z]{2,3}");
		return p.matcher(mail).matches();
	}
}

