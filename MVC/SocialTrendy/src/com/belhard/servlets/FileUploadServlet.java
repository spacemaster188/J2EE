package com.belhard.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.services.SocialUploadService;
import com.belhard.utils.Constants;


public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constants.UTF8);
		response.setCharacterEncoding(Constants.UTF8);
		HttpSession session = request.getSession();
		SocialBean user = (SocialBean) session.getAttribute(Constants.USR);
		int idUser = user.getId();

		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if(isMultiPart){
		ServletFileUpload upload = new ServletFileUpload ();
		
		try{
		FileItemIterator itr = upload.getItemIterator(request);
		while(itr.hasNext()){
			FileItemStream item = itr.next();
			if(item.isFormField()){
                String fieldName = item.getFieldName();
				InputStream is = item.openStream();
				byte[] b =  new byte[is.available()];
				is.read(b);
				String value = new String(b);
                response.getWriter().println(fieldName+value);
				
			}else{
				String path = getServletContext().getRealPath(Constants.SLASH);
				String fieldName = item.getFieldName();
				if(SocialUploadService.processFile(path, item, fieldName)){          //file upload success
					 String fileName = item.getName();	 
					 SocialUploadService.doUploadFileProcess(idUser, fileName, fieldName);
				session.setAttribute(Constants.REPORT, Constants.UPLOAD_SUCCESS);
				}else{                                                              //file upload failed
				session.setAttribute(Constants.REPORT, Constants.UPLOAD_FAILED);
				}
			}
		}
		}catch(FileUploadException | DaoException fue){
			DaoException.jump(request, response, Constants.DAO_ERR);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
		dispatcher.forward(request, response);
		}
	}

}
