package cn.swu.edu.javaweb.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CommonFilter
 */

public class CommonFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		response.setCharacterEncoding("utf-8");
		response.setDateHeader("Expires", -1);				//告诉浏览器数据能够缓存多长时间，-1或0表示不缓存
        response.setHeader("Cache_Control", "no-cache");	//支持HTTP 1.1，告诉浏览器要不要缓存数据，如“no-cache”
        response.setHeader("Pragma", "no-cache");			//支持HTTP 1.0。告诉浏览器要不要缓存数据，如“no-cache”
        //response.setContentType("text/html;charset=utf-8");
		//设置响应报头允许当前应用被跨域请求（CROS）
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		HttpSession session = request.getSession();
		
		//获得用户请求的URL
		String url = request.getRequestURI();
		boolean check =false;
		
		// 因为是全局过滤，所以会对所有请求进行过滤，诸如css、js、png等等
        // 所以应该做到只拦截.html和.jsp请求，对请求地址的末尾进行判断
        // 修订 servlet加入拦截过滤范围
        if (url.endsWith(".jsp") || url.endsWith(".html") || url.endsWith("Servlet") || url.endsWith(".action" ) || url.endsWith(".do")) {
            check = true;
        }
        
        if(url.endsWith("/gift/login.action") 
        		|| url.endsWith("/gift/login.jsp") 
        		|| url.endsWith("/gift/validateColorServlet") 
        		|| url.endsWith("/gift/register.jsp")
        		|| url.endsWith("/gift/404.jsp") 
        		|| url.endsWith("/gift/register.action")) {
        	check = false;
        }
        if (check) {
            // 判断session中此值是否存在
            if (session.getAttribute("user") != null) {
                //System.out.println("---->通过】");
                chain.doFilter(request, response); //放行
            } else {
                //System.out.println("---->未通过!】");
                response.sendRedirect("/gift"); //跳转回根目录
            }
        } else {
            // 非html和jsp请求一律不管
            chain.doFilter(request, response);
        }
	}


}
