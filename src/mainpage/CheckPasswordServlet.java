package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
@SuppressWarnings("serial")
public class CheckPasswordServlet extends HttpServlet {
	
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Guest Book</title></head>");
		out.println("<body><h1>비밀번호를 입력해주세요</h1>");
		out.println("<form action='main' method='post'>");
		out.println("Password: <input type='password' name='passwd' style='width: 100px;'>");
		out.println("<input type='submit' value='확인'>");
		out.println("</form></body></html>");
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
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
				response.sendRedirect("/modify");
			}else {
				// TODO show denial message + back btn
				out.println("<html><head><title>비밀번호 오류</title></head>");
				out.println("<body><p>비밀번호가 틀렸습니다.</p>");
				out.println("<form action='back' method='post'>");
				out.println("<a href='/check'>뒤로가기</a>");
				out.println("</form></body></html>");
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