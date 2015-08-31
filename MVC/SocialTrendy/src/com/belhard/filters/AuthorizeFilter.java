package com.belhard.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belhard.beans.SocialBean;
import com.belhard.services.SocialLoginService;
import com.belhard.utils.Constants;

public class AuthorizeFilter implements Filter {

    public AuthorizeFilter() {
    }

	public void destroy() {
	}

	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		  request.setCharacterEncoding(Constants.UTF8);
		  HttpSession session = ((HttpServletRequest) request).getSession();
		  session.setAttribute(Constants.REPORT, Constants.EMPTY_STR);
		  HttpServletResponse httpResponse = (HttpServletResponse) response;
		  HttpServletRequest httpRequest = (HttpServletRequest) request;
		  try {
			     SocialBean user = null;                                            //check user from session
		            try{
		            	user = (SocialBean)session.getAttribute(Constants.USR);
		            }catch (Exception e) {
			        }   
		            if(user != null) {
		            	if(user.isActive()) {
							session.setAttribute(Constants.USR, user);
		            		filterChain.doFilter(request, response);
		            		return;
		            	}else {
		            		httpResponse.sendRedirect(Constants.INACTIVE_PAGE);
		            		return;
		            	}
		            } else {
		            	if (httpRequest.getCookies().length != 0) {                 //check user from cookies
			                Cookie[] dataSaved = httpRequest.getCookies();
			                boolean login = false;
			                boolean	passw = false;
			                String cookiemail = null;
			                String cookiepass = null;
			                for (int i = 0; i < httpRequest.getCookies().length; i++) {
			                    Cookie cookie = dataSaved[i];
			                    if(cookie.getName().equals(Constants.MAIL)){
			                    	login = true;
			                    	cookiemail = cookie.getValue();
			                    	}else {
			                    		if (cookie.getName().equals(Constants.PASS)) {
			                    			passw = true;	
			                    			cookiepass = cookie.getValue();		                    		
			                    	}	
				                  }
			                }            
			                if (login && passw) { 	
			                	user = SocialLoginService.checkLoginUser(cookiemail, cookiepass);
			                	if(user != null) {
			                		session.setAttribute(Constants.USR, user);
			                		if(user.isActive()) {
			                			filterChain.doFilter(request, response);	
			                			return;
			                		}else{
			                			httpResponse.sendRedirect(Constants.INACTIVE_PAGE); 
			                			return;
			                		}
			                	}       			
			                }
			            }
		            }
	        } catch (Exception e) {
	        }
       	    httpResponse.sendRedirect(Constants.LOGIN_PAGE);
	   }

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
