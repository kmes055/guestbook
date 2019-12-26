package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

@WebServlet("/upload")
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Guest Book</title>");
		out.println("</head><body><h1>방명록</h1>");
		out.println("<form action='upload' method='post'>");
		out.println("Email: <input type='text' name='mail' style='width: 200px;'>");
		out.println(" Password: <input type='password' name='passwd' style='width: 100px;'>");
		out.println("<br><textarea name='content' style='width:1000px;height:400px;'></textarea><br>");
		out.println("<input type='submit' value='확인'>");
		out.println("</form></body></html>");
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
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
			stmt = conn.prepareStatement("INSERT INTO FEED VALUES (NULL, ?, ?, NOW(), NOW(), ?);");
			stmt.setString(1, mail);
			stmt.setString(2, passwd);
			stmt.setString(3, content);

			/*
			 * if (!this.checkEmail(mail)) { // TODO show some error message throw new
			 * Exception("Email format is wrong"); }
			 */
			stmt.executeUpdate();
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
	}

	private Boolean checkEmail(String mail) {
		// TODO 
		// Complete regular expression
		// For now, (alphabet+number)@(alphabet).(2~3 lower case alphabet)
		Pattern p = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z]+.[a-z]{2,3}");
		return p.matcher(mail).matches();
	}
}

