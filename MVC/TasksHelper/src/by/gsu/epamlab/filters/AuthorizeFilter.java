package by.gsu.epamlab.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.beans.UserBean;
import by.gsu.epamlab.utils.Constants;

public class AuthorizeFilter implements Filter {

    public AuthorizeFilter() {
    }

	public void destroy() {
	}

	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		  request.setCharacterEncoding("UTF-8");
		  HttpSession session = ((HttpServletRequest) request).getSession();
        	  UserBean user = (UserBean) session.getAttribute(Constants.CURR_USER);
    	      if (user != null) {
    	          filterChain.doFilter(request, response);
    	      } else {
    	    	  session.invalidate();
    	    	  HttpServletResponse httpResponse = (HttpServletResponse) response;
    	    	  httpResponse.sendRedirect(Constants.INDEX_PAGE);
    	      }  
	   }

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
