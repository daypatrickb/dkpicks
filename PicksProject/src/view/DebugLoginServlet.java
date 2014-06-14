package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Constants;

import model.User;
import service.UserService;

public class DebugLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
	
		String tokenData = (String) request.getSession().getAttribute("token");
		if (tokenData != null) 
		{
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print("Current user is already connected.");
			return;
		}
		
		String gPlusId = request.getParameter("user");
		
		if (gPlusId == null || gPlusId.trim().length() == 0)
		{
			// TODO render login box here
		}
		
		User user = UserService.getUserByID(gPlusId);
		
		request.getSession().setAttribute(Constants.SESSION_USER_ID_KEY, user);
		
		response.sendRedirect("/");	
		
	}
	
	
	
	
}
