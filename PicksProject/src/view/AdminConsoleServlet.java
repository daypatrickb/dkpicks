package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.UIHelper;

import model.User;
import service.UserService;




public class AdminConsoleServlet extends HttpServlet 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3188161841207827449L;

	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		User user = UserService.getUser(req);

		if (user == null)
		{
			resp.sendRedirect("/login.html");		
        	return;
		}
        

        if (! UserService.isUserAdmin(user))
        {
        	resp.sendRedirect("/");
        }
        
        
        resp.setContentType("text/html");
        
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Admin</title><link rel=stylesheet href=\"static/scoreboard.css\" type=\"text/css\"></head><body>");
        UIHelper.renderHeader(out, user);

        out.println("You can do anything at zombocom");
        

	}

	
}
