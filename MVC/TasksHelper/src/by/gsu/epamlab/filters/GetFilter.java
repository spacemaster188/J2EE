package by.gsu.epamlab.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.utils.Constants;

public class GetFilter implements Filter {

    public GetFilter() {
    }

	public void destroy() {
	}

	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		  request.setCharacterEncoding("UTF-8");
		  HttpSession session = ((HttpServletRequest) request).getSession();
		  HttpServletRequest http_request = (HttpServletRequest) request;
		  String http_method = http_request.getMethod();  
		    
          if(http_method.equalsIgnoreCase(Constants.GET)){
          session.setAttribute(Constants.ERROR, Constants.NOT_ALLOWED);
    	  RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ERROR_PAGE);
		  dispatcher.forward(request, response);
          }else{
        	  Map<String, String> headerMap = new HashMap<String, String>();
        	  Map<String, String> parameterMap = new HashMap<String, String>();
        		Enumeration<String> headerNames = http_request.getHeaderNames();
        		while (headerNames.hasMoreElements()) {
        			String key = headerNames.nextElement();
        			String value = http_request.getHeader(key);
        			headerMap.put(key, value);
        		}
        		session.setAttribute(Constants.REQUEST_HEADER_MAP, headerMap);
        		
        		Enumeration<String> parameterNames= http_request.getParameterNames();
        		while (parameterNames.hasMoreElements()) {
        			String key = parameterNames.nextElement();
        			String value = http_request.getParameter(key);
        			parameterMap.put(key, value);
        		}
        		session.setAttribute(Constants.REQUEST_PARAMETERS_MAP, parameterMap);
        		
        	  filterChain.doFilter(request, response);
          }
	   }

	public void init(FilterConfig fConfig) throws ServletException {
	}

}