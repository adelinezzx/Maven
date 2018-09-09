package weibo.com.yc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*action")
public class CharacterEncoding implements Filter{
	private String encoding="utf-8";
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// 1. request中的    parameter
		HttpServletRequest request=(HttpServletRequest) arg0;
		// 2. 通过response.getWriter() ->  PrintWriter, -> 
		HttpServletResponse response=(HttpServletResponse) arg1;
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		//response.setContentType("text/html;charset="+encoding);    response.setContentType("text/plain;charset=utf-8");
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		if(arg0.getInitParameter("encoding")!=null){
			encoding=arg0.getInitParameter("encoding");
		}
		
	}

}
