package spms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import spms.dao.FeedDao;
import spms.vo.Feed;

import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

@WebServlet("/upload")
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		String passwd = request.getParameter("passwd");
		String content = request.getParameter("content").replaceAll(";", "\\;");
		
		try {
			ServletContext sc = this.getServletContext();
			
			if (this.checkEmail(mail)) {
				FeedDao feedDao = (FeedDao)sc.getAttribute("feedDao");
				feedDao.upload(new Feed().setEmail(mail).setPwd(passwd).setContent(content));
			}
			else {
				throw new Exception();
			}
			request.setAttribute("viewUrl", "redirect:main.do");
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
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

