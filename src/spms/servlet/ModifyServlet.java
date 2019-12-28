package spms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.FeedDao;
import spms.vo.Feed;

@WebServlet("/modify")
@SuppressWarnings("serial")
public class ModifyServlet extends HttpServlet {
//	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		if (request.getParameter("fno") == null) {
//			// TODO show Error
//			request.setAttribute("viewUrl", "redirect:main.do");
//		}else {
//			request.setAttribute("fno", request.getParameter("fno"));
//			request.setAttribute("viewUrl", "feed/modify.jsp");
//		}
//	}
//	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fno = request.getParameter("fno");
		String content = request.getParameter("content");
		
		try {
			ServletContext sc = this.getServletContext();
			FeedDao feedDao = (FeedDao)sc.getAttribute("feedDao");
			feedDao.modify(new Feed().setFno(fno).setContent(content));
			
			request.setAttribute("viewUrl", "redirect:main.do");
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
		}
	}
}