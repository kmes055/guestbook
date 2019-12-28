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

import spms.dao.FeedDao;
import spms.vo.Feed;

@WebServlet("/check")
@SuppressWarnings("serial")
public class CheckPasswordServlet extends HttpServlet {
	
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if (request.getParameter("fno") == null) {
			// TODO show Error?
			request.setAttribute("viewUrl", "redirect:main.do");
		}else {
			request.setAttribute("fno", request.getParameter("fno"));
			request.setAttribute("wrongMessage",  "");
			request.setAttribute("viewUrl", "feed/check.jsp");
		}
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("fno", request.getParameter("fno"));
		
		try {
			ServletContext sc = this.getServletContext();
			FeedDao feedDao = (FeedDao)sc.getAttribute("feedDao");
			
			String fno = request.getParameter("fno");
			String passwd = request.getParameter("passwd");
			
			if (feedDao.check(new Feed().setFno(fno).setPwd(passwd))) {
				request.setAttribute("viewUrl", "feed/modify.jsp");
			} else {
				request.setAttribute("wrongMessage", "비밀번호가 틀렸습니다.");
				request.setAttribute("viewUrl", "feed/check.jsp");
			}
		
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
		}
	}
}