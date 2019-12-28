package spms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import spms.dao.FeedDao;
import spms.vo.Feed;

import javax.servlet.http.HttpServletRequest;

@WebServlet("/main")
@SuppressWarnings("serial")
public class TimelineServlet extends HttpServlet {
	
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			ServletContext sc = this.getServletContext();
			
			FeedDao feedDao = (FeedDao)sc.getAttribute("feedDao");

			request.setAttribute("feeds",  feedDao.selectNextFeed());
			request.setAttribute("viewUrl", "feed/main.jsp");
			
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}

