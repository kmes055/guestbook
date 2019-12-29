package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
	urlPatterns="/*",
	initParams={
		@WebInitParam(name="content_type",value="text/html; charset=UTF-8")
	})
public class CharacterEncodingFilter implements Filter{
	FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType(config.getInitParameter("content_type")); 
		nextFilter.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
