package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;
import common.Constants;

public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 437378234576787953L;

	/*
	 * Gson object to serialize JSON responses to requests to this servlet.
	 */
	private static final Gson GSON = new Gson();

	/*
	 * Default HTTP transport to use to make HTTP requests.
	 */
	private static final HttpTransport TRANSPORT = new NetHttpTransport();

	/*
	 * Default JSON factory to use to deserialize JSON.
	 */
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

	/*
	 * This is the Client ID that you generated in the API Console.
	 */
	private static final String CLIENT_ID = "300346544300.apps.googleusercontent.com";

	/*
	 * This is the Client Secret that you generated in the API Console.
	 */
	private static final String CLIENT_SECRET = "XovHdbd6BKiwyFGoez3KkQZS";
	  
	
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		if (session == null)
		{
			response.sendRedirect("/");
			return;
		}
		
		
		String tokenData = (String) session.getAttribute("token");
		
		if (tokenData != null) 
		{
			try 
			{
				// Build credential from stored token data.
				GoogleCredential credential = new GoogleCredential.Builder()
						.setJsonFactory(JSON_FACTORY)
						.setTransport(TRANSPORT)
						.setClientSecrets(CLIENT_ID, CLIENT_SECRET)
						.build()
						.setFromTokenResponse(
								JSON_FACTORY.fromString(tokenData,
										GoogleTokenResponse.class));
				
				
				@SuppressWarnings("unused")
				// Execute HTTP GET request to revoke current token.
				HttpResponse revokeResponse = TRANSPORT
						.createRequestFactory()
						.buildGetRequest(
								new GenericUrl(
										String.format(
												"https://accounts.google.com/o/oauth2/revoke?token=%s",
												credential.getAccessToken())))
						.execute();
				
				
				// Reset the user's session
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(GSON.toJson("Successfully disconnected."));

				

			} catch (IOException e) 
			{
				// For whatever reason, the given token was invalid.
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print(GSON.toJson("Failed to revoke token for given user."));
			}
		}
		
		session.removeAttribute("token");
		session.removeAttribute(Constants.SESSION_USER_ID_KEY);

		session.invalidate();

		response.sendRedirect("/");
	}


}
