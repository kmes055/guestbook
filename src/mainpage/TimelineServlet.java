package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Feed;

import javax.servlet.http.HttpServletRequest;

@WebServlet("/main")
@SuppressWarnings("serial")
public class TimelineServlet extends HttpServlet {
	
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		

		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.createStatement();
			
			String query = "SELECT FNO, EMAIL, CONTENT FROM FEED ORDER BY FNO DESC";
			rs = stmt.executeQuery(query);
			
			response.setContentType("text/html; charset=UTF-8");
			ArrayList<Feed> feeds = new ArrayList<Feed>();
			
			while (rs.next()) {
				String content = rs.getString("CONTENT");
				content = content.replace("\n", "<br>");
				feeds.add(new Feed()
						.setFno(rs.getInt("FNO"))
						.setEmail(rs.getString("EMAIL"))
						.setContent(content));
			}
			
			request.setAttribute("feeds",  feeds);
			
			RequestDispatcher rd = request.getRequestDispatcher("/feed/main.jsp");
			rd.include(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("Something is wrong");
		// TODO 
		// Set page view by list of feeds
		// or one object for each feed?
		
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
	}
}

