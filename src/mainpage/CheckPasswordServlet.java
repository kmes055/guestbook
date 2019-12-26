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
		int fno = Integer.parseInt(request.getParameter("fno"));

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Guest Book</title></head>");
		out.println("<body><h1>비밀번호를 입력해주세요</h1>");
		out.println("<form action='check' method='post'>");
		out.println("<input type='hidden' name='fno' value='" + fno + "' />");
		out.println("Password: <input type='password' name='passwd' style='width: 100px;'/>");
		out.println("<input type='submit' value='확인'/>");
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
		int fno = Integer.parseInt(request.getParameter("fno"));
		String passwd = request.getParameter("passwd");
		
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
			
			String query = "SELECT PWD FROM FEED WHERE FNO=" + fno;
			
			rs = stmt.executeQuery(query);
			response.setContentType("text/html; charset=UTF-8");
			if (rs.next()) {
				if (passwd.equals(rs.getString("PWD"))) {
					// TODO go to modify page

					response.sendRedirect("modify?fno=" + fno);
				}else {
					// TODO show denial message + back btn
					out.println("<html><head><title>비밀번호 오류</title></head>");
					out.println("<body><p>비밀번호가 틀렸습니다.</p>");
					out.println("<form action='back'>");
					out.println("<button type='button' onclick=\"location.href='check?fno=1'\">뒤로가기</button>");
					out.println("</form></body></html>");
				}
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
	}
		
		
}