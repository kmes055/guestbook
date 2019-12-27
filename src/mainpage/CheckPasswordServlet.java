package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
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
		request.setAttribute("fno", request.getParameter("fno"));

		RequestDispatcher rd = request.getRequestDispatcher("feed/check.jsp");
		rd.include(request, response);
		
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
		// Get password from user
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
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
			
			stmt = conn.prepareStatement("SELECT FNO FROM FEED WHERE FNO=? AND PWD=?;");
			stmt.setLong(1, fno);
			stmt.setString(2, passwd);
			rs = stmt.executeQuery();
			response.setContentType("text/html; charset=UTF-8");
			
			if (rs.next()) {
				response.sendRedirect("modify?fno=" + fno);
			}else {
				out.println("<html><head><title>비밀번호 오류</title></head>");
				out.println("<body><p>비밀번호가 틀렸습니다.</p>");
				out.println("<button type='button' onclick=\"location.href='check?fno=" + fno + "'\">뒤로가기</button>");
				out.println("</body></html>");
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