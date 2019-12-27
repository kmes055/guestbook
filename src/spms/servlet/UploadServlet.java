package spms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
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
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String mail = request.getParameter("mail");
		String passwd = request.getParameter("passwd");
		String content = request.getParameter("content");
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			stmt = conn.prepareStatement("INSERT INTO FEED VALUES (NULL, ?, ?, NOW(), NOW(), ?);");
			stmt.setString(1, mail);
			stmt.setString(2, passwd);
			stmt.setString(3, content.replaceAll(";", "\\;"));

			/*
			if (!this.checkEmail(mail)) { 
			// TODO show some error message 
			throw new Exception("Email format is wrong"); 
			}
			*/
			
			stmt.executeUpdate();			
			response.sendRedirect("main");
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}

	private Boolean checkEmail(String mail) {
		// TODO 
		// Complete regular expression
		// For now, (alphabet+number)@(alphabet).(2~4 lower case alphabet)
		Pattern p = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z]+.[a-z]{2,4}");
		return p.matcher(mail).matches();
	}
}

