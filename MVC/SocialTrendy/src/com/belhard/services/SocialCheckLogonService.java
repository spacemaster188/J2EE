package com.belhard.services;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.belhard.beans.SocialBean;
import com.belhard.utils.Constants;

public class SocialCheckLogonService {
	private static final Logger logger = Logger.getLogger("SocialCheckLogonService.class");
	
	public static int getLogonId(HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding(Constants.UTF8);
		HttpSession session = request.getSession();
		try {
			if (request.getCookies().length != 0) {                              //get user from cookies
				Cookie[] dataSaved = request.getCookies();
				boolean login = false;
				boolean passw = false;
				String cookiemail = null;
				String cookiepass = null;
				for (int i = 0; i < request.getCookies().length; i++) {
					Cookie cookie = dataSaved[i];
					if (cookie.getName().equals(Constants.MAIL)) {
						login = true;
						cookiemail = cookie.getValue();
					} else {
						if (cookie.getName().equals(Constants.PASS)) {
							passw = true;
							cookiepass = cookie.getValue();
						}
					}
				}
				if (login && passw) {
                   SocialBean user = SocialLoginService.checkLoginUser(cookiemail, cookiepass);
					if (user != null) {
						if (user.isActive()) {
							return user.getId();
						} else {
							return Constants.INACTIVE_USER_INT;
							}
					}
				} else {                                                      // if have no cookies
					int out = getUserFromSession(session);
					if(out >= 0) {
						return out;
					}			
				}
			} else {                                                          // if have no cookies
				int out = getUserFromSession(session);
				if(out >= 0) {
					return out;
				}
			}
		} catch (Exception e) {
			logger.error(Constants.USER_INIT_FAIL);
		}
		
		return Constants.LOGIN_REDIRECT_INT;
	}

	private static int getUserFromSession(HttpSession session){
		SocialBean user = null;
		try {
			user = (SocialBean) session.getAttribute(Constants.USR);
		} catch (Exception e) {
			//do nthg
		}
		if (user != null) {
			if (user.isActive()) {
				return user.getId();
			} else {
				return Constants.INACTIVE_USER_INT;
			}
		}
		return Constants.LOGIN_REDIRECT_INT;
	}

}
