package spms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import spms.controls.Controller;
import spms.controls.FeedListController;
import spms.dao.FeedDao;
import spms.vo.Feed;

import javax.servlet.http.HttpServletRequest;

@WebServlet("*.do")
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
	
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
		
		try {
			ServletContext sc = this.getServletContext();
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("feedDao", sc.getAttribute("feedDao"));
			
			String pageControllerPath = null;
			Controller pageController = null;
			
			if("/main.do".equals(servletPath)) {
				pageController = new FeedListController();
			} else if ("/upload.do".equals(servletPath)) {
				pageControllerPath = "/upload";
			} else if ("/modify.do".equals(servletPath)) {
				pageControllerPath = "/modify";
			} else if ("/check.do".equals(servletPath)) {
				pageControllerPath = "/check";
			}

			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			rd.include(request, response);

			String viewUrl = pageController.execute(model);
			//String viewUrl = (String)request.getAttribute("viewUrl");
			
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			//request.setAttribute("error", e);
			throw new ServletException(e);
			
		} finally {
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}

