package spms.servlet;

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
		if (request.getParameter("fno") == null) {
			// TODO show Error
			response.sendRedirect("main");
		}else {
			request.setAttribute("fno", request.getParameter("fno"));
			request.setAttribute("wrongMessage",  "");
			RequestDispatcher rd = request.getRequestDispatcher("feed/check.jsp");
			rd.include(request, response);
		}
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		request.setAttribute("fno", request.getParameter("fno"));
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			stmt = conn.prepareStatement("SELECT FNO FROM FEED WHERE FNO=? AND PWD=?;");
			stmt.setString(1, request.getParameter("fno"));
			stmt.setString(2, request.getParameter("passwd"));
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("feed/modify.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("wrongMessage", "비밀번호가 틀렸습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("feed/check.jsp");
				rd.forward(request, response);
			}
		
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}