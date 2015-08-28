package by.gsu.epamlab.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import by.gsu.epamlab.utils.Constants;
import by.gsu.epamlab.dao.DaoException;
import by.gsu.epamlab.dao.ITaskDAO;
import by.gsu.epamlab.dao.factory.UserFactory;
@MultipartConfig
public class UploadServlet extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
			final String path = getServletContext().getRealPath("/") + Constants.FILE_PATH;
			final Part filePart = request.getPart(Constants.FILE);
			int idTask = Integer.parseInt(request.getParameter(Constants.TASK_ID));
			final String fileName = getFileName(filePart);
			OutputStream out = null;
			InputStream filecontent = null;
			final PrintWriter writer = response.getWriter();
			try {
				out = new FileOutputStream(new File(path + File.separator + fileName));
				filecontent = filePart.getInputStream();
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				ITaskDAO taskDAO = UserFactory.getTaskClassFromFactory();
				taskDAO.addFile(idTask, fileName);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
				dispatcher.forward(request, response);
			} catch (FileNotFoundException ex) {
				DaoException.jump(request, response, Constants.UPLOAD_ERROR);
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);
			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
				if (writer != null) {
					writer.close();
				}
			}	
	}
	
	private String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

}