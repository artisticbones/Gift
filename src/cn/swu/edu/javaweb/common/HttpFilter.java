package cn.swu.edu.javaweb.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class HttpFilter
 */
public abstract class HttpFilter implements Filter {


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		
		doFilter(request2, response2, chain);
	}
	
	public abstract void doFilter(HttpServletRequest request ,HttpServletResponse response ,FilterChain chain) throws IOException, ServletException ;

	private FilterConfig filterConfig;
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = fConfig;
		init();
	}
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	protected void init() {
		// TODO Auto-generated method stub
		
	}

}