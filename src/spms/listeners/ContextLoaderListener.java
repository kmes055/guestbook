package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.session.SqlSessionFactory;

import spms.dao.FeedDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			
			FeedDao feedDao = new FeedDao();
			feedDao.setConnection(conn);
			
			sc.setAttribute("mySqlFeedDao", feedDao);
		}  catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//try { conn.close(); } catch (Exception e) {}
	}
}
