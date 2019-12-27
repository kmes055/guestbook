package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Feed;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet("/upload")
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String mail = request.getParameter("mail");
		String passwd = request.getParameter("passwd");
		String content = request.getParameter("content");
		
		response.setContentType("text/html;charset=UTF-8");
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			
			stmt = conn.prepareStatement("INSERT INTO FEED VALUES (NULL, ?, ?, NOW(), NOW(), ?);");
			stmt.setString(1, mail);
			stmt.setString(2, passwd);
			stmt.setString(3, content.replaceAll(";", "\\;"));

			/*
			if (!this.checkEmail(mail)) { // TODO show some error message 
			throw new Exception("Email format is wrong"); }
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

