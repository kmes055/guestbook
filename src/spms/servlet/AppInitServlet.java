package spms.servlet;

import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.sql.DriverManager;


/* 
 * Not used anymore.
 */

@SuppressWarnings("serial")
public class AppInitServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			sc.setAttribute("conn", conn);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		Connection conn = (Connection)this.getServletContext().getAttribute("conn");
		
		try {
			if (conn != null && !conn.isClosed()) conn.close();
		} catch (Exception e) {}
	}
}
