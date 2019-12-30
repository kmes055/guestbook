package spms.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.controls.Controller;
import spms.listeners.ContextLoaderListener;

import org.springframework.context.ApplicationContext;
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
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			Controller pageController = (Controller)ctx.getBean(servletPath);
			
			if (pageController == null) {
				throw new Exception("요청한 서비스를 찾을 수 없습니다");
			}
			
			if (pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			String viewUrl = pageController.execute(model);
			
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
			
		}
	}
	
	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model, 
			DataBinding dataBinding) throws Exception{
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		for(int i = 0; i < dataBinders.length; i += 2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>)dataBinders[i + 1];
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
}